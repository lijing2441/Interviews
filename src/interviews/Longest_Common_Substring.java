package interviews;

public class Longest_Common_Substring {
	/**
	 * A simple solution is to one by one consider all substrings of first 
	 * string and for every substring check if it is a substring in second string. 
	 * 
	 * Keep track of the maximum length substring. There will be O(m^2) substrings 
	 * and we can find whether a string is substring on another string in O(n) time by KMP. <strStr>
	 * 
	 * So overall time complexity of this method would be O(n * m^2)
	 * 
	 * Using Naive strStr: O(mn) -> O(n * m^3)
	 */
	/**
	 * DP: The idea is to find length of the longest common suffix for all substrings 
	 * of both strings and store these lengths in a table.
	 * 
	 * @Recurrence
	 * The longest common suffix has following optimal substructure property
	 * 			LCSuffix(X, Y, m, n) = LCSuffix(X, Y, m-1, n-1) + 1     if X[m-1] = Y[n-1]
     *                  			   0                                if X[m-1] != Y[n-1]
	 *
	 *LCSuff[i][j] contains length of longest common suffix of str1[0..i-1] and str2[0..j-1].
	 *
	 *The maximum length Longest Common Suffix is the longest common substring.
	 *		 	LCSubStr(X, Y, m, n)  = Max(LCSuffix(X, Y, i, j)) where 1 <= i <= m and 1 <= j <= n
	 * 
	 * @complexity O(mn) and O(mn)
	 */
	/**
	 * Suffix tree can be used here. But unless multiple requests, it's a waste to build a suffix tree.
	 */
	// Only return the length of the longest common substring
	public static int LCSubstring(String str1, String str2){
		if(str1 == null || str2 == null || str1.length() == 0 || str2.length() == 0) return 0;
		int len1 = str1.length();
		int len2 = str2.length();
		// Create a table to store lengths of longest common suffixes of substrings. 
		// map[i][j] contains length of longest common suffix of str1[0..i-1] and str2[0..j-1].
		// not necessary the longest common substring
		
		// The first row and column have no logical meaning, they are used only for simplicity.
		int[][] map = new int[len1 + 1][len2 + 1];
		int maxLen = 0;
		// the first row and column should be just 0, since no match exists
		for(int i = 1; i <= len1; i++){
			for(int j = 1; j <= len2; j++){
				if(str1.charAt(i - 1) == str2.charAt(j - 1)){
					if(i == 1 && j == 1){
						map[i][j] = 1;
					}else{
						map[i][j] = map[i - 1][j - 1] + 1;
					}
					if(maxLen < map[i][j]) maxLen = map[i][j];
				}
			}
		}
		return maxLen;
	}
	
	// return the longest common substring
	public static String LCSubstring2(String str1, String str2){
		if(str1 == null || str2 == null || str1.length() == 0 || str2.length() == 0) return "";
		int len1 = str1.length();
		int len2 = str2.length();
		// Create a table to store lengths of longest common suffixes of substrings. 
		// LCSuff[i][j] contains length of longest common suffix of str1[0..i-1] and str2[0..j-1]. 
		// The first row and column have no logical meaning, they are used only for simplicity.
		int[][] map = new int[len1 + 1][len2 + 1];
		int maxLen = 0;
		String res = "";
		//int lastStart = 0;
		//StringBuilder sb = new StringBuilder();
		// the first row and column should be just 0, since no match exists
		for(int i = 1; i <= len1; i++){
			for(int j = 1; j <= len2; j++){
				if(str1.charAt(i - 1) == str2.charAt(j - 1)){
					if(i == 1 && j == 1){
						map[i][j] = 1;
					}else{
						map[i][j] = map[i - 1][j - 1] + 1;
					}
					if(maxLen < map[i][j]){
						maxLen = map[i][j];
						int thisStart = i - map[i][j];
						res = str1.substring(thisStart, i);
						/*
						if(lastStart == thisStart){
							//if the current LCS is the same as the last time we find a lcs
							//that is, we just match one more character
							sb.append(str1.charAt(i));
						}else{
							//this block resets the string builder if a different LCS is found
							lastStart = thisStart;
							sb = new StringBuilder();
							sb.append(str1.substring(thisStart, i));
						}
						*/
					}
				}
				
			}
		}
		//String res = sb.toString();
		//System.out.println(res);
		return res;
	}
	public static void main(String[] args){
		String s1 = "OldSite:GeeksforGeeks.org";
		String s2 = "NewSite:GeeksQuiz.com";
		String s = LCSubstring2(s1, s2);
		int len = LCSubstring(s1, s2);
		System.out.print(s + " " + len);
	}
}
