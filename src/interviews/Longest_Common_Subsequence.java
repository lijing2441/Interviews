package interviews;

public class Longest_Common_Subsequence {
	/**
	 * Given two strings, find the longest common subsequence (LCS).
	 * 
	 * Your code should return the length of LCS.
	 * 
	 * For "ABCD" and "EDCA", the LCS is "A" (or "D", "C"), return 1.
	 * 
	 * For "ABCD" and "EACB", the LCS is "AC", return 2.
	 */
	public int longestCommonSubsequence(String A, String B) {
		if (A == null || B == null || A.length() == 0 || B.length() == 0)
			return 0;
		int lenA = A.length(), lenB = B.length();
		int[][] dp = new int[lenA + 1][lenB + 1];
		for (int i = 1; i <= lenA; i++) {
			for (int j = 1; j <= lenB; j++) {
				dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
				if (A.charAt(i - 1) == B.charAt(j - 1)) {
					if (i == 1 && j == 1) {
						dp[i][j] = 1;
					} else {
						dp[i][j] = Math.max(dp[i - 1][j - 1] + 1, dp[i][j]);
					}
				}
			}
		}
		return dp[lenA][lenB];
	}
}
