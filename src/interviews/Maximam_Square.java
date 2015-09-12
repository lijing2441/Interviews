package interviews;

public class Maximam_Square {
	/**
	 * Given a 2D binary matrix filled with 0's and 1's, find the largest square 
	 * containing all 1's and return its area.
	 * For example, given the following matrix:
			1 0 1 0 0
			1 0 1 1 1
			1 1 1 1 1
			1 0 0 1 0
	 * Return 4.
	 */
	public int maximalSquare(char[][] matrix) {
        if(matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return 0;
        } 
        int m = matrix.length, n = matrix[0].length;
        
        int[][] dp = new int[m][n];
        for(int i = 0; i < n; i++) {
            dp[0][i] = (matrix[0][i] == '1' ? 1 : 0);
        }
        for(int i = 1; i < m; i++) {
            dp[i][0] = (matrix[i][0] == '1' ? 1 : 0);
        }
        int max = 0;
        for(int i = 0; i < m; i++) {
            for(int j = 1; j < n; j++) {
                if(matrix[i][j] == '0') {
                    dp[i][j] = 0;
                } else {
                    if(i > 0) {
                        dp[i][j] = Math.min(Math.min(dp[i - 1][j], dp[i][j - 1]), dp[i - 1][j - 1]) + 1;
                    }
                }
            }
            for(int j = 0; j < n && max <= i; j++) {
                if(max < dp[i][j]) max = dp[i][j];
            }
        }
        return max * max; 
    }
}
