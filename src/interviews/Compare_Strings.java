package interviews;

public class Compare_Strings {
	/**
	 * Compare two strings A and B, determine whether A contains all of the
	 * characters in B.
	 * 
	 * The characters in string A and B are all Upper Case letters.
	 * 
	 * Example For A = "ABCD", B = "ACD", return true.
	 * 
	 * For A = "ABCD", B = "AABC", return false.
	 */
	public boolean compareStrings(String A, String B) {
		// write your code here
		if (A.length() < B.length())
			return false;
		int[] count = new int[26];
		for (int i = 0; i < A.length(); i++) {
			int cur = (int) (A.charAt(i) - 'A');
			count[cur]++;
		}
		for (int i = 0; i < B.length(); i++) {
			int cur = (int) (B.charAt(i) - 'A');
			count[cur]--;
		}
		for (int i = 0; i < 26; i++) {
			if (count[i] < 0)
				return false;
		}
		return true;
	}
}
