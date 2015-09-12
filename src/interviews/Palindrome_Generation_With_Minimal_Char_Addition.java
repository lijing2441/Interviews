package interviews;

public class Palindrome_Generation_With_Minimal_Char_Addition {
	/**
	 * Given a string, find the minimum number of characters to be inserted to
	 * convert it to palindrome.
	 * 
	 * @logic The minimum number of insertions in the string str[l…..h]  = 
	 * 		  (1) minInsertions(str[l+1…..h-1]) 										if str[l] is equal to str[h]
	 * 		  (2) min(minInsertions(str[l…..h-1]), minInsertions(str[l+1…..h])) + 1     otherwise
	 * 
	 * Method 1: Naive recursion using the above recurrence
	 * Method 2: DP using a matrix to note down the overlapping subproblems, so that 
	 * 			they can be used directly if same subproblem is encountered again.
	 */
	
	// Recursion method, too many overlapping subproblem, O(2^n)
	public int findMinInsertions(String s){
		if(s == null || s.length() < 2) return 0;
		return helper(s, 0, s.length() - 1);
	}
	public int helper(String s, int l, int r){
		// base cases
		if(l > r) return Integer.MAX_VALUE;
		else if(l == r) return 0;
		else if(l == r - 1) return s.charAt(l) == s.charAt(r)? 0:1;
		else if(s.charAt(l) == s.charAt(r)){
			return helper(s, l + 1, r - 1);
		}else{
			return Math.min(helper(s, l+1, r), helper(s, l, r-1)) + 1;
		}
	}
	// DP method, similar idea based
	// the map should be filled in a diagonal fashion, since the length should be increasing
	// dp[i][j] -> cost that needs to make the substring l->h palindrome
	// O(n^2) and O(n^2)
	public int findMinInsertionsDP(String s){
		if(s == null || s.length() < 2) return 0;
		int n = s.length();
		int[][] map = new int[n][n];
		for(int len = 1; len < n; len++){
			for(int l = 0; l + len < n; l++){
				int r = l + len;
				if(s.charAt(l) == s.charAt(r)){
					//match
					map[l][r] = map[l + 1][r - 1]; 
				}else{
					// we didn't match, so a cost at left or at right
					map[l][r] = Math.min(map[l+1][r], map[l][r-1]) + 1;
				}
			}
		}
		return map[0][n-1];
	}
	/** 
	 *  Method 3: use the logic of Longest Common Subsequence (LCS) Problem. 
	 * 			If we find out LCS of string and its reverse, we know how many maximum 
	 * 			characters can form a palindrome. We need insert remaining characters.
	 * 		1) Find the length of LCS of input string and its reverse. Let the length be ‘l’.
	 * 		2) The minimum number insertions needed is length of input string minus ‘l’.
	 */
	
}
