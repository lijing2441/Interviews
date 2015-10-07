package interviews;

public class Airbnb_走格子求最大分数 {
	/**
	 * 4 * 4 matrix, 每个格子里面可能正可能负可能0，求从(0, 0)到任意一个点，找最大的分数
	 * 
	 * O(3^16) running time
	 */
	static int[] x = {1, 0, -1, 0};
	static int[] y = {0 ,1, 0, -1};
	static int max = Integer.MIN_VALUE; 
	public static int findMaxScore(int[][] matrix) {
		max = matrix[0][0];
		int curSum = matrix[0][0];

		boolean[][] used = new boolean[4][4];
		used[0][0] = true;
		findMaxHelper(matrix, used, 0, 0, curSum);
		return max;
	}
	public static void findMaxHelper(int[][] matrix, boolean[][] used, int row, int col, int curSum) {
		if (max < curSum) max = curSum;
		for (int i = 0; i < 4; i++) {
			int newRow = row + y[i];
			int newCol = col + x[i];
			if (newRow >= 0 && newRow < 4 && newCol >= 0 && newCol < 4 && !used[newRow][newCol]) {
				used[newRow][newCol] = true;
				findMaxHelper(matrix, used, newRow, newCol, curSum + matrix[newRow][newCol]);
				used[newRow][newCol] = false;
			}
		}
	}
	
	public static void main(String[] args) {
		int[][] matrix = {
				{10, -1, -10, 40},
				{-2, 15, -8, -7},
				{20, 1, 7, -5},
				{0, 7, 3, -2}
		};
		System.out.println(findMaxScore(matrix) + ", which should be 90");
	}
}
