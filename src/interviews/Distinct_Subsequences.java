package interviews;

public class Distinct_Subsequences {
	/**
	 * Given a string S and a string T, count the number of distinct
	 * subsequences of T in S.
	 * 
	 * A subsequence of a string is a new string which is formed from the
	 * original string by deleting some (can be none) of the characters without
	 * disturbing the relative positions of the remaining characters. (ie, "ACE"
	 * is a subsequence of "ABCDE" while "AEC" is not).
	 * 
	 * Here is an example: S = "rabbbit", T = "rabbit"
	 * 
	 * Return 3.
	 * 
	 * @logic: 设母串的长度为i，子串的长度为j
	 * 		   table[i][j] = the number of distinct subsequences of T[0,j] in S[0,i]
	 * 
	 * (1) 若母串的最后一个字符与子串的最后一个字符不同，则长度为 j 的子串在长度为 i 的母串中出现的次数就是
	 * 	   母串的前 i - 1 个字符中子串出现的次数，即 t[i][j] = t[i - 1][j]，
	 * (2) 若母串的最后一个字符与子串的最后一个字符相同，那么除了母串前 i - 1 个字符出现子串的次数外，
	 *     还要加上子串的前 j - 1 个字符在母串的前 i - 1 个字符中出现的次数，
	 *     即 t[i][j] = t[i - 1][j] + t[i - 1][j - 1]。
	 */
	
	public int numDistinct2DP(String S, String T) {
		int si = S.length(), ti = T.length();
		if (si <= 0 || ti <= 0 || si < ti)
			return 0;

		int[][] dptable = new int[si][ti];
		// base cases
		if (S.charAt(0) == T.charAt(0))
			dptable[0][0] = 1;
		else
			dptable[0][0] = 0;
		
		for (int j = 0; j < ti; ++j) {
			for (int i = 1; i < si; ++i) {
				dptable[i][j] = dptable[i - 1][j];
				if (S.charAt(i) == T.charAt(j)) {
					if (j == 0)
						dptable[i][j] += 1;
					else
						dptable[i][j] += dptable[i - 1][j - 1];
				}
			}
		}
		return dptable[si - 1][ti - 1];
	}
 
	/**
	 * Since we just use the value of last loop, 1-D array is enough, but in order to use the value
	 * from last loop, we need to run the inner loop from right to left, to not cover the value.
	 * 
	 * In i-th iteration,
	 * at the beginning, recurs[j] = the number of distinct subsequences of T[0,j] in S[0,i-1];
	 * after being updated, recurs[i] = the number of distinct subsequences of T[0,j] in S[0,i].
	 * 
	 * We have to run from T.length down to 0 since we don't want to overwrite recurs[j] 
	 * which would be used for recurs[j+1] later.
	 */
	public int numDistinct(String S, String T) {
		int si = S.length(), ti = T.length();
		if (si <= 0 || ti <= 0 || si < ti)
			return 0;

		int[] recurs = new int[ti];
		for (int i = 0; i < si; i++) {
			//j应该从尾到头，因为每次要使用上一次loop的值。如果从头往尾扫的话，重复计算了, 会overwrite。
			for (int j = Math.min(i, ti - 1); j >= 0; j--) {
				// if they are matched
				if (S.charAt(i) == T.charAt(j)) {
					// the first character
					if(j == 0){
						recurs[j] += 1;
					}else{
						recurs[j] += recurs[j - 1];
					}
				}
			}
		}
		return recurs[ti - 1];
	}
}
