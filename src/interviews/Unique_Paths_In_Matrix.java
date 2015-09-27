package interviews;

public class Unique_Paths_In_Matrix {
	/**
	 * A robot is located at the top-left corner of a m x n grid (marked 'Start'
	 * in the diagram below).
	 * 
	 * The robot can only move either down or right at any point in time. The
	 * robot is trying to reach the bottom-right corner of the grid (marked
	 * 'Finish' in the diagram below).
	 * 
	 * How many possible unique paths are there?
	 */
	public int uniquePaths(int m, int n) {
		if (m == 0 || n == 0)
			return 0;
		if (m == 1 || n == 1)
			return 1;
		int[][] map = new int[m][n];
		for (int i = 0; i < m; i++) {
			map[i][0] = 1;
		}
		for (int i = 0; i < n; i++) {
			map[0][i] = 1;
		}
		for (int i = 1; i < m; i++) {
			for (int j = 1; j < n; j++) {
				map[i][j] = map[i - 1][j] + map[i][j - 1];
			}
		}
		return map[m - 1][n - 1];
	}
	
	// 一维空间
	public int uniquePathsO1Space(int m, int n) {
        if (m * n == 0) return 0;
        int[] dp = new int[n];
        for (int i = 0; i < n; i++) {
            dp[i] = 1;
        }
        ///int index = 1;
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                dp[j] += dp[j - 1];
            }
        }
        return dp[n - 1];
    }
	/**
	 * Follow up for "Unique Paths": 
	 * Now consider if some obstacles are added to the grids. How many unique paths would there be?
	 * An obstacle and empty space is marked as 1 and 0 respectively in the grid.
	 * For example,
	 * There is one obstacle in the middle of a 3x3 grid as illustrated below.
	 * [
	 *   [0,0,0],
	 *   [0,1,0],
	 *   [0,0,0]
	 * ]
	 * 
	 * The total number of unique paths is 2.
	 * Note: m and n will be at most 100.
	 * 
	 * @logic we can just use the given grid to mark down the possible cases, but not create a new one.
	 * @complexity O(mn)
	 */
	public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        
		if(obstacleGrid.length == 0) return 0;
        if(obstacleGrid[0].length == 0) return 0;
        
        int m = obstacleGrid.length;
        int n = obstacleGrid[0].length;
        
        for(int i = 0; i < m; i++){
            for(int j = 0; j < n; j++){
                if(obstacleGrid[i][j] == 1){ 
                	//no possible, maintain 0;
                	obstacleGrid[i][j] = 0; 
                }else if(i == 0 && j == 0){
                	//start point without obstacle
                    obstacleGrid[i][j] = 1;
                }else if(i == 0){
                	//depend on whether the previous grid has obstacle
                    obstacleGrid[i][j] = obstacleGrid[i][j - 1];
                }else if(j == 0){
                    obstacleGrid[i][j] = obstacleGrid[i - 1][j];
                }else{
                    obstacleGrid[i][j] = obstacleGrid[i - 1][j] + obstacleGrid[i][j - 1];
                }
            }
        }
        return obstacleGrid[m - 1][n - 1];
    }
}
