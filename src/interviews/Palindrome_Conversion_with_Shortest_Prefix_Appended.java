package interviews;

public class Palindrome_Conversion_with_Shortest_Prefix_Appended {
	/**
	 * Given a string S, you are allowed to convert it to a palindrome by adding
	 * characters in front of it. Find and return the shortest palindrome you
	 * can find by performing this transformation.
	 * 
	 * For example:
	 * 
	 * Given "aacecaaa", return "aaacecaaa".
	 * Given "abcd", return "dcbabcd".
	 * 
	 * @logic 
	 * Only need to search in the first half, since the longest palindrome 
	 * cannot be longer than twice of the current word.
	 * 		1. Find the same letters in the middle first
	 * 		2. Get the symmetrical part beginning from the different part
	 * 		3. Add the reverse part of the remaining part in the end 
	 */
	public String shortestPalindrome(String s) {
        if(s == null || s.length() < 2) return s;
        int len = s.length();
        char[] arr = s.toCharArray();
        int maxLen = 1, start = 0, end = 0;
        for(int i = 0; i <= len / 2;) {
            start = end = i;
            while(end < len - 1 && arr[end + 1] == arr[end]) {
                end++;
            }
            // optimize for the case with multiple same letters
            i = end + 1;
            while(end < len - 1 && start > 0 && arr[start - 1] == arr[end + 1]) {
                start--;
                end++;
            }
            if(start == 0 && maxLen < end - start + 1) {
                maxLen = end - start + 1;
            }
        }
        String remaining = s.substring(maxLen, len);
        StringBuilder sb = (new StringBuilder(remaining)).reverse();
        String prefix = sb.toString();
        return prefix + s;
    }
}
