package interviews;

public class strStr {
	/**
	 * Implement strStr().
	 * 
	 * Returns the index of the first occurrence of needle in haystack, or -1 if
	 * needle is not part of haystack.
	 * 
	 * @complexity worst case O(m(n-m+1)) 
	 * 			   1) When all characters of the text and pattern are same.
	 * 					txt = "AAAAAAAAAAAAAAAAAA"
	 * 					pat = "AAAAA".
	 *   		   2) Worst case also occurs when only the last character is different.
	 *			        txt = "AAAAAAAAAAAAAAAAAB"
	 *			        pat = "AAAAB"
	 *
	 * 			   best case O(n)
	 * 			   The best case occurs when the first character of the pattern is not present in text at all.
	 * 					txt  = "AABCCAADDEE"
	 * 					pat = "FAA"
	 */
	public String Solution(String haystack, String needle) {
		if (null == haystack || null == needle)
			return null;

		int m = haystack.length();
		int n = needle.length();

		for (int i = 0; i <= m - n; i++) { // need smaller or equal!
			int match = 0;
			while (match < n && haystack.charAt(i + match) == needle.charAt(match)) {
				match++;
			}
			if (match == n) {
				return haystack.substring(i);
			}
		}
		return null;
	}

	/** 
	 * KMP algorithm: O(n) in worst case
	 * 
	 * The KMP matching algorithm uses degenerating property of the pattern
	 * (pattern having same sub-patterns appearing more than once in the pattern).
	 * 
	 * The basic idea behind KMP’s algorithm is: whenever we detect a mismatch (after some matches), 
	 * we already know some of the characters in the text (since they matched the pattern characters 
	 * prior to the mismatch). We take advantage of this information to avoid matching the characters 
	 * that we know will anyway match.
	 * 
	 * KMP algorithm does some pre-processing over the pattern pat[], constructing an auxiliary array 
	 * lps[] of size m (same as size of pattern). Here lps means longest prefix which is also suffix. 
	 * 
	 * For each sub-pattern pat[0…i] where i = 0 to m-1, lps[i] stores length of the maximum matching 
	 * proper prefix which is also a suffix of the sub-pattern pat[0..i].
	 * 
	 * Examples:
	 * For the pattern “AABAACAABAA”, lps[] is [0, 1, 0, 1, 2, 0, 1, 2, 3, 4, 5]
	 * For the pattern “ABCDE”, lps[] is [0, 0, 0, 0, 0]
	 * For the pattern “AAAAA”, lps[] is [0, 1, 2, 3, 4]
	 * For the pattern “AAABAAA”, lps[] is [0, 1, 2, 0, 1, 2, 3]
	 * For the pattern “AAACAAAAAC”, lps[] is [0, 1, 2, 0, 1, 2, 3, 3, 3, 4]
	 * 
	 */
	public String strStrKMP(String haystack, String needle) {
		if (needle.length() == 0) return haystack;

		int[] overLay = getOverLay(needle);
		
		int big = 0, small = 0;
		while (big < haystack.length() && small < needle.length()) {
			if (haystack.charAt(big) == needle.charAt(small)) {
				big++;
				small++;
			} else if (small == 0)
				big++;
			else
				small = overLay[small - 1] + 1;
		}
		if (small == needle.length())
			return haystack.substring(big - small);
		else
			return null;
	}
	//pre-processing
	private int[] getOverLay(String needle) {
		char[] pat = needle.toCharArray();
		int[] overLay = new int[pat.length];
		overLay[0] = -1;
		int index = -1;
		for (int i = 1; i < pat.length; i++) {
			index = overLay[i - 1];
			while (index >= 0 && pat[index + 1] != pat[i])
				index = overLay[index];
			if (pat[i] == pat[index + 1])
				overLay[i] = index + 1;
			else
				overLay[i] = -1;
		}
		return overLay;
	}
}
