package interviews;

public class Dungeon_Game {
	/**
	 * The demons had captured the princess (P) and imprisoned her in the
	 * bottom-right corner of a dungeon. The dungeon consists of M x N rooms
	 * laid out in a 2D grid. Our valiant knight (K) was initially positioned in
	 * the top-left room and must fight his way through the dungeon to rescue
	 * the princess.
	 * 
	 * The knight has an initial health point represented by a positive integer.
	 * If at any point his health point drops to 0 or below, he dies
	 * immediately.
	 * 
	 * Some of the rooms are guarded by demons, so the knight loses health
	 * (negative integers) upon entering these rooms; other rooms are either
	 * empty (0's) or contain magic orbs that increase the knight's health
	 * (positive integers).
	 * 
	 * In order to reach the princess as quickly as possible, the knight decides
	 * to move only rightward or downward in each step.
	 * 
	 * @analysis Since the requirement is that we need to have at least 1 blood at the end,
	 * 			 that is, the dungeon[m-1][n-1] grid. We can just track back from the end.
	 * 
	 * blood[i][j] indicates the minimum health that guarantees the survival of the knight 
	 * for the rest of his quest BEFORE entering room(i, j). 
	 * 
	 * Thus, we have: blood[i][j] = Math.max(blood from last step - current consumption, 1)
	 * (1) if the current grid is demon, the blood we need is: blood from last step + abs(demon needs)
	 * (2) if the current grid is 0 or orbs, we just need to retain 1 there, no matter how much we have.
	 * For the edges, we have only one choice from the last step. 
	 * For the inner grids, we have two choice, we choose the smaller one from them.
	 * At last, we just need to return 
	 */
	public int calculateMinimumHP(int[][] dungeon) {
        int m = dungeon.length;
        int n = dungeon[0].length;
        
        if(m == 0 || n == 0) return 0;
        // the life that the knight left until grid(m, n)
        int[][] blood = new int[m][n];
        
        // base case to start, track back since we need to have >= 1 at the end
        blood[m-1][n-1] = Math.max(1 - dungeon[m-1][n-1], 1);
        
        // the first row and column 
        // if the current grid is negative, we need to get here some more blood
        // if not, we can just assign it 1
        for(int i = m-2; i >= 0; i--){
            blood[i][n-1] = Math.max(blood[i+1][n-1] - dungeon[i][n-1], 1);
        }
        for(int i = n-2; i >= 0; i--){
            blood[m-1][i] = Math.max(blood[m-1][i+1] - dungeon[m-1][i], 1);
        }
        // for the normal cases
        for(int i = m-2; i >= 0; i--){
            for(int j = n-2; j >= 0; j--){
                int rightwards = Math.max(blood[i][j+1] - dungeon[i][j], 1);
                int downwards = Math.max(blood[i+1][j] - dungeon[i][j], 1);
                blood[i][j] = Math.min(rightwards, downwards);
            }
        }
        return blood[0][0];
    }
}
