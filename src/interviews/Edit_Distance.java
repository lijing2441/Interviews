package interviews;

public class Edit_Distance {
	/**
	 * Given two words word1 and word2, find the minimum number of steps required 
	 * to convert word1 to word2. (each operation is counted as 1 step.)
	 * 
	 * You have the following 3 operations permitted on a word:
	 * 
	 * a) Insert a character
	 * b) Delete a character
	 * c) Replace a character
	 * 
	 * @analysis DP used here. dp[i][j] means the minimum number of steps to convert 
	 * 						   word1[0...i-1] to word2[0...j-1]
	 * 						   (1) word1[0...i-1] is matched with word2[0...j], a gap exists (1)
	 * 						   (2) word2[0...j-1] is matched with word1[0...i], a gap exists (1)
	 * 						   (3) word1[0...i-1] is matched with the current word2[0...j-1], a match 
	 * 							   (match = 0 / 1, depending on whether it's matched or not) 
	 * 			 Recurrence: dp[i][j] = Math.min(Math.min(dp[i-1][j], dp[i][j-1]) + 1, dp[i-1][j-1] + cost)
	 * 						 if word1[i-1] = word2[j-1] cost = 0
	 * 						 otherwise cost = 1
	 * 			 At last, return the dp[m][n]
	 * 			 basic cases: dp[i][0] = i (all gaps)
	 * 						  dp[0][j] = j (all gaps)
	 */
	public int minDistance(String word1, String word2) {
        int m = word1.length();
        int n = word2.length();
        if(m == 0 || n == 0) return m == 0? n : m;
        int[][] dp = new int[m + 1][n + 1];
        for(int i = 0; i <= m; i++) dp[i][0] = i;
        for(int i = 0; i <= n; i++) dp[0][i] = i;
        for(int i = 1; i <= m; i++){
            for(int j = 1; j <= n; j++){
                int cost = 0;
                if(word1.charAt(i - 1) != word2.charAt(j - 1)) cost = 1;
                dp[i][j] = Math.min(Math.min(dp[i - 1][j], dp[i][j - 1]) + 1, dp[i - 1][j - 1] + cost);
            }
        }
        return dp[m][n];
    }
	
	/**
	 * Problem II: One Edit Distancce Given two strings S and T, determine if
	 * they are both one edit distance apart. 
	 * 
	 * Hint: 
	 * 1. If | n – m | is greater than 1, we know immediately both are not one-edit distance apart. 
	 * 2. It might help if you consider these cases separately, m == n and m ≠ n. 
	 * 3. Assume that m is always ≤ n, which greatly simplifies the conditional statements. 
	 * 	  If m > n, we could just simply swap S and T. 
	 * 4. If m == n, it becomes finding if there is exactly one modified operation. 
	 * 	  If m ≠ n, you do not have to consider the delete operation. Just consider the insert operation in T.
	 * 
	 * 这个题目只要O(1)的空间，O(n)的时间复杂度。假定有一下几种情况 
	 * 1）修改一个字符（假定两个String等长）
	 * 2）插入一个字符（中间或者结尾）
	 * 
	 * [注意事项] 1）shift变量的作用
	 */
	public boolean isOneEditDistance(String s, String t) {
		// we always consider the situation s.length < t.length
		// Thus if there is one length difference, only T has the one more character.
		int m = s.length();
		int n = t.length();
		if (m > n)	return isOneEditDistance(t, s);
		if (n - m > 1)	return false;
		
		int i = 0, shift = n - m;
		// proceed until the mismatch
		while (i < m && s.charAt(i) == t.charAt(i)){
			i++;
		}
		if (i == m)
			// if two string are the same (shift == 0), return false
			return shift > 0; 
		// if the two strings is not same, shift == 
		if (shift == 0)
			// if n == m skip current char in s (modify operation in s)
			// the one edit distance is used here
			i++;
		// since if shift == 1, t has one more character
		while (i < m && s.charAt(i) == t.charAt(i + shift))
			// use shift to skip one char in t
			i++; 
		return i == m;
	}
}
