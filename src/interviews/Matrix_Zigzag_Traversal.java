package interviews;

public class Matrix_Zigzag_Traversal {
	/**
	 * Given a matrix:
	 * 
	 * [ [1, 2, 3, 4], 
	 *   [5, 6, 7, 8], 
	 *   [9,10, 11, 12] ] 
	 *   
	 * return [1, 2, 5, 9, 6, 3, 4, 7, 10, 11, 8, 12]
	 */
	public int[] printZMatrix(int[][] matrix) {
        int m = matrix.length;
        if (m == 0) return new int[0];
        int n = matrix[0].length;
        if (n == 0) return new int[0];
        //int i = 0, j = 0, index = 1;
        //int start = 0, end = m * n - 1;
        int[] res = new int[m * n];
        int index = 0;
        for (int i = 0; i < m + n - 1; i++) { // i -> (row + col) sum
            if (i % 2 == 1) {
                for (int col = Math.min(i, n - 1); col >= Math.max(0, i - m + 1); col--) {
                    res[index++] = matrix[i - col][col];
                }
            } else {
                for (int row = Math.min(i, m - 1); row >= Math.max(0, i - n + 1); row--) {
                    res[index++] = matrix[row][i - row];
                }
            }
        }
        return res;
    }
}
