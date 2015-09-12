package interviews;

public class Longest_Common_Prefix {
	/**
	 * 
	 * @logic 1) "Horizontal matching (over strings)". Pick up the first string
	 *        and compare it with the rest. Return the minimum prefix found
	 *        among all comparisons.
	 * 
	 *        2) "Vertical matching (over characters)". Compare the characters
	 *        between all strings from left to right. Stop whenever a mismatch
	 *        is found.
	 * @complexity
	 * 		O(m), m is the number of strings
	 */
	public String longestCommonPrefix(String[] strs) {
		if (strs == null)
			return null;
		if (strs.length == 0)
			return "";
		if (strs.length == 1)
			return strs[0];
		String str = strs[0];
		int prefixLength = str.length();
		for (int i = 1; i < strs.length; i++) {
			String cur = strs[i];
			prefixLength = Math.min(prefixLength, cur.length());
			for (int j = 0; j < prefixLength; j++) {
				if (str.charAt(j) != cur.charAt(j)) {
					prefixLength = j;
					break;
				}
			}
		}
		return str.substring(0, prefixLength);
	}

	/**
	 * optimal one, compare the strings vertically
	 */
	public String longestCommonPrefixBetter(String[] strs) {
		if (strs == null || strs.length == 0)
			return "";

		for (int i = 0; i < strs[0].length(); i++) {
			char c = strs[0].charAt(i);
			// go through the other strings
			for (int j = 1; j < strs.length; j++) {
				// reach the end of a string or find a mismatch
				if (i == strs[j].length() || strs[j].charAt(i) != c)
					return strs[0].substring(0, i);
			}
		}
		// if all matched, return the whole string
		return strs[0];
	}
}
