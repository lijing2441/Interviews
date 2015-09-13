package interviews;                                                                                                                                                                                                                                                                                                                                                    

public class Search_target_In_Sorted_Matrix {
	/**
	 * Write an efficient algorithm that searches for a value in an m x n matrix. 
	 * This matrix has the following properties:
	 * 			- Integers in each row are sorted from left to right.
	 * 			- The first integer of each row is greater than the last integer of the previous row.
	 * For example, Consider the following matrix:
	 * [
	 *  [1,   3,  5,  7],
	 *  [10, 11, 16, 20],
	 *  [23, 30, 34, 50]
	 * ]
	 * Given target = 3, return true.
	 */
	public boolean searchMatrix(int[][] matrix, int target) {
		int m = matrix.length;
		int n = matrix[0].length;
		if (matrix == null || m == 0 || n == 0)
			return false;
		if (target < matrix[0][0] || target > matrix[m - 1][n - 1])
			return false;

		for (int i = 0; i < m; i++) {
			if (matrix[i][0] <= target && matrix[i][n - 1] >= target) {
				for (int j = 0; j < n; j++) {
					if (matrix[i][j] == target)
						return true;
				}
			}
		}
		return false;
	}
	
	// O(m+n) time and O(1) space
	public int searchMatrixOptimized(int[][] matrix, int target) {
        // write your code here
        int res = 0;
        int m = matrix.length;
        if (m == 0) return res;
        int n = matrix[0].length;
        if (n == 0) return res;
        if (target < matrix[0][0] || target > matrix[m - 1][n - 1]) return 0;
        // search from top right corner 
        int row = 0, col = n - 1;
        while (row < m && col >= 0) {
            if (target == matrix[row][col]) {
                res++;
                row++;
                col--;
            } else if (target < matrix[row][col]) {
                col--;
            } else {
                row++;
            }
        }
        return res;
    }
}
