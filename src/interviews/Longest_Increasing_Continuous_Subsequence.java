package interviews;

public class Longest_Increasing_Continuous_Subsequence {
	/**
	 * Give you an integer array (index from 0 to n-1, where n is the size of
	 * this array)，find the longest increasing continuous subsequence in this
	 * array. (The definition of the longest increasing continuous subsequence
	 * here can be from right to left or from left to right)
	 * 
	 * Example For [5, 4, 2, 1, 3], the LICS is [5, 4, 2, 1], return 4.
	 * 
	 * For [5, 1, 2, 3, 4], the LICS is [1, 2, 3, 4], return 4.
	 * 
	 * Note O(n) time and O(1) extra space.
	 */
	public int longestIncreasingContinuousSubsequence(int[] A) {
        if (A == null) return 0;
        int len = A.length;
        if (len <= 2) return len;
        boolean increasing = (A[0] < A[1]);
        int maxSoFar = 2, maxEndHere = 2;
        for (int i = 2; i < len; i++) {
            int cur = A[i];
            if (increasing) {
                if(cur >= A[i - 1]) maxEndHere++;
                else {
                    maxEndHere = 2;
                    increasing = false;
                }
            } else {
                if (cur <= A[i - 1]) maxEndHere++;
                else {
                    maxEndHere = 2;
                    increasing = true;
                }
            }
            if(maxEndHere > maxSoFar) maxSoFar = maxEndHere;
        }
        return maxSoFar;
    }
	
	/**
	 * Problem II: Give you an integer matrix (with row size n, column size m)，
	 * find the longest increasing continuous subsequence in this matrix. 
	 * (The definition of the longest increasing continuous subsequence here 
	 * can start at any row or column and go up/down/right/left any direction).
	 * 
	 * 就是滑雪问题。每个地方由于我们只搜没有进行traverse过的：O(mn) => 时间和空间
	 * 
	 * Snapchat + Google
	 */
	public int[][] dp;
    public int[][] data;
    public int[] x = {-1, 0, 1, 0};
    public int[] y = {0, 1, 0, -1};
    public int longestIncreasingContinuousSubsequenceII(int[][] A) {
        if (A == null || A.length == 0 || A[0].length == 0) return 0;
        int maxLen = 1;
        int m = A.length, n = A[0].length;
        
        data = A;
        dp = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (dp[i][j] == 0) {
                    traverse(i, j);
                }
                if (dp[i][j] > maxLen) {
                    maxLen = dp[i][j];
                }
            }
        }
        return maxLen;
    }
    public int traverse(int row, int col) {
        int max = -1;
        for (int i = 0; i < 4; i++) {
            int newRow = row + y[i];
            int newCol = col + x[i];
            if (newRow < data.length && newRow >= 0 && newCol < data[0].length && newCol >= 0 && data[newRow][newCol] > data[row][col]) {
                if (dp[newRow][newCol] != 0) {
                    // has been searched before
                    max = Math.max(max, dp[newRow][newCol]);
                } else {
                    max = Math.max(max, traverse(newRow, newCol));
                }
            }
        }
        if (max == -1) {
            dp[row][col] = 1;
        } else {
            dp[row][col] = 1 + max;
        }
        return dp[row][col];
    }
}
