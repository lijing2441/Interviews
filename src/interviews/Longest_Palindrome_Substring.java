package interviews;
/**
 * Given a string S, find the longest palindromic substring in S. 
 * You may assume that the maximum length of S is 1000, and there 
 * exists one unique longest palindromic substring.
 */
public class Longest_Palindrome_Substring {
	/**
	 * dp method O(n^2)
	 * Recurrence: dp[i][j] = dp[i + 1][j - 1] if s[i] = s[j] , 
	 * 			   "dp[i][j] == true" indicates s[i->j] is a palindrome
	 */
	public String longestPalindromeDP(String s) {
        if(s == null || s.length() < 2) return s;
        int n = s.length();
        boolean[][] dp = new boolean[n][n];
        int start = 0;
        int end = 0;
        char[] ch = s.toCharArray();
        
        for(int i = 0; i < n; i++){
            dp[i][i] = true;
        }
        for(int i = 0; i < n - 1; i++){
            if(ch[i] == ch[i + 1]){
                dp[i][i + 1] = true;
                start = i; end = i + 2;
            }
        }
        //The update order should be from small length to longer length, diagonally
        for(int len = 2; len < n; len++){
            for(int i = 0; i + len < n; i++){
                int j = i + len;
                if(ch[i] == ch[j] && dp[i + 1][j - 1]){
                    dp[i][j] = true;
                    start = i;
                    end = j + 1;
                }
            }
        }
        return s.substring(start, end);
	}
	/**
	 * Method 2: greedy method, O(n^2)
	 * shorted the length one by one, if find one, it must be the longest
	*/ 
	public String longestPalindrome(String s) {
        if(s == null || s.length() < 2) return s;
        int len = s.length();
        char[] ch = s.toCharArray();
        while(len > 0){
            for(int i = 0; i + len - 1 < ch.length; i++){
                int left = i;
                int right = i + len - 1;
                boolean isPan = true;
                while(left < right){
                    if(ch[left] != ch[right]){
                        isPan = false;
                        break;
                    }else{
                        left++;
                        right--;
                    }
                }
                if(isPan) return s.substring(i, i + len);
            }
            len--;
        }
        return "";
    }
}
