package interviews;

public class Interleaving_String {
	/**
	 * Problem: Given s1, s2, s3, find whether s3 is formed by the interleaving of s1 and s2.
	 * For example,
	 * Given: 
	 * s1 = "aabcc",
	 * s2 = "dbbca",
	 * 
	 * When s3 = "aadbbcbcac", return true.
	 * When s3 = "aadbbbaccc", return false.
	 * 
	 * @logic
	 * dp[i][j] = true indicates we can use first i characters from s1 and first j
	 * characters from s2 to generate the s3 with length i+j.
	 * 
	 * case 1: dp[i][j] =  dp[i-1][j] if s1.charAt(i - 1) == s3.charAt(i + j - 1)
	 * case 2: dp[i][j] |= dp[i][j-1] if s2.charAt(j - 1) == s3.charAt(i + j - 1)
	 * 
	 * dp[0][0] = true and the answer is dp[n][m].
	 * 
	 * O(mn) time and O(mn) space
	 */
	public boolean isInterleave(String s1, String s2, String s3) {
		int n1 = s1.length();
		int n2 = s2.length();
		int n3 = s3.length();
		if (n1 + n2 != n3) return false;
		// to remember the n1 * n2 combination
		boolean[][] dp = new boolean[n1 + 1][n2 + 1]; 
		// "" is matched with ""
		dp[0][0] = true;
		for (int i = 0; i <= n1; i++) {
			for (int j = 0; j <= n2; j++) {
				if (i > 0) {
					dp[i][j] = dp[i - 1][j] && s1.charAt(i - 1) == s3.charAt(i + j - 1);
				}
				if (j > 0) {
					// is or!! remember to say it!
					dp[i][j] |= dp[i][j - 1] && s2.charAt(j - 1) == s3.charAt(i + j - 1); 
				}
			}
		}
		return dp[n1][n2];
	}
	//O(mn) time and O(n) space
	// since we only use the current row and last row, we can reduce the space usage to 1-D
	public boolean isInterleave2(String s1, String s2, String s3) {
        int n1 = s1.length();
        int n2 = s2.length();
        int n3 = s3.length();
        if(n1 + n2 != n3) return false;
        boolean[] dp = new boolean[n2 + 1];
        dp[0] = true;
        for(int i = 0; i <= n1; i++){
            for(int j = 0; j <= n2; j++){
                if(i > 0){
                    dp[j] = dp[j] && s1.charAt(i - 1) == s3.charAt(i + j - 1);
                }
                if(j > 0){
                    dp[j] |= dp[j - 1] && s2.charAt(j - 1) == s3.charAt(i + j - 1);
                }
            }
        }
        return dp[n2];
    }
}
