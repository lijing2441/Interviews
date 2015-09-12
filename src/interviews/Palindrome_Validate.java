package interviews;

public class Palindrome_Validate {
	/**
	 * For example, "A man, a plan, a canal: Panama" is a palindrome.
	 * "race a car" is not a palindrome.
	 */
	// O(n), O(1)
	public boolean isPalindrome(String s) {
		String _s = s.replaceAll("[^A-Za-z0-9]", "").toLowerCase();
		
		if (s.length() < 2)
			return true;
		char[] ch = _s.toCharArray();
		int left = 0;
		int right = ch.length - 1;
		while (left <= right) {
			if (ch[left] != ch[right])
				return false;
			left++;
			right--;
		}
		return true;
	}
}
