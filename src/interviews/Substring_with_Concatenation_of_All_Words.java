package interviews;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * You are given a string, S, and a list of words, L, that are all of the same
 * length. Find all starting indices of substring(s) in S that is a
 * concatenation of each word in L exactly once and without any intervening
 * characters.
 * 
 * For example, given: S: "barfoothefoobarman" L: ["foo", "bar"]
 * 
 * You should return the indices: [0,9]. (order does not matter).
 * 
 * @analysis use two hashmaps to count the strings, one for the list and one for each index of S
 * 			 Then compare them each time to check whether we get a match.
 * 			 O(S长度* L长度) running time, O(L长度 * each string in L长度) space
 */

public class Substring_with_Concatenation_of_All_Words {
	// HashMap to keep track of the counts
	// O(sLen * |L|)
	public ArrayList<Integer> findSubstring(String S, String[] L) {
		ArrayList<Integer> res = new ArrayList<Integer>();
		if (L.length == 0 || L[0].length() == 0) return res;
		
		int len = L.length, n = L[0].length(), sLen = S.length();
		
		if (sLen < n * len) return res;
		
		HashMap<String, Integer> map = new HashMap<String, Integer>();
		//put all strings in L into the original map
		for (String s : L) {
			int num = 1;
			if (map.containsKey(s)) {
				num += map.get(s);
			}
			map.put(s, num);
		}
		//create a map for the current string concatenation
		for (int i = 0; i <= sLen - n * len; i++) {
			HashMap<String, Integer> curMap = new HashMap<String, Integer>();
			int j = 0;
			for (j = 0; j < len; j++) {
				int start = i + j * n;
				int end = start + n;
				String cur = S.substring(start, end);
				// as long as one string is not matched, break
				if (!map.containsKey(cur))
					break;
				// if matched, put it into the current map
				int num = 1;
				if (curMap.containsKey(cur)) {
					num += curMap.get(cur);
				}
				// if the current number has exceeded the maximum, break
				if (num > map.get(cur))
					break;
				curMap.put(cur, num);
			}
			// if we arrive the end of this substring, add the start to res
			if (j == len)
				res.add(i);
		}
		return res;
	}

	/**
	 * Slide windows: avoid several repeating cases
	 * 
	 * bar|foo|the|foo|bar|man ba|rfo|oth|efo|oba|rma|n b|arf|oot|hef|oob|arm|an
	 * 
	 */
	public ArrayList<Integer> findSubstring2(String S, String[] L) {
		ArrayList<Integer> res = new ArrayList<Integer>();
		if (L.length == 0 || L[0].length() == 0)
			return res;
		int len = L.length, wordLen = L[0].length(), sLen = S.length();
		if (sLen < wordLen * len)
			return res;

		HashMap<String, Integer> map = new HashMap<String, Integer>();
		for (String s : L) {
			int num = 1;
			if (map.containsKey(s)) {
				num += map.get(s);
			}
			map.put(s, num);
		}

		for (int i = 0; i < wordLen; i++) {
			HashMap<String, Integer> curMap = new HashMap<String, Integer>();
			int count = 0;
			int left = i;
			for (int j = left; j <= sLen - wordLen; j += wordLen) { 
				// remember here!! the final position is no longer 
				// the beginning of the whole substring, but the last word
				String str = S.substring(j, j + wordLen);
				if (map.containsKey(str)) {
					int num = 1;
					if (curMap.containsKey(str)) {
						num += curMap.get(str);
					}
					curMap.put(str, num);

					if (num <= map.get(str)) {
						count++;
					} else {
						while (curMap.get(str) > map.get(str)) {
							str = S.substring(left, left + wordLen);
							if (curMap.containsKey(str)) {
								curMap.put(str, curMap.get(str) - 1);
								if (curMap.get(str) < map.get(str))
									count--;
							}
							left += wordLen;
						}
					}
					// find one match
					if (count == len) {
						res.add(left);
						String tmp = S.substring(left, left + wordLen);
						if (curMap.containsKey(tmp)) {
							curMap.put(tmp, curMap.get(tmp) - 1);
						}
						count--;
						left += wordLen;
					}
				} else {
					curMap.clear();
					count = 0;
					left = j + wordLen;
				}
			}
		}
		return res;
	}
}
