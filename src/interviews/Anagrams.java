package interviews;

import java.util.ArrayList;
import java.util.HashMap;

public class Anagrams {
	// check if a string is the anagram of the other
	// using sort, O(nlogn)

	// Count map O(n), O(n), remove chars that are not letters
	@SuppressWarnings("unused")
	private boolean areAnagramsBit(String s1, String s2) {
		char[] ch1 = s1.replaceAll("[^A-Za-z]", "").toLowerCase().toCharArray();
		char[] ch2 = s2.replaceAll("[^A-Za-z]", "").toLowerCase().toCharArray();
		if (ch1.length != ch2.length) return false;
		int[] map = new int[26]; // if only alphabet

		for (char c : ch1)	map[c - 'a']++;
		for (char c : ch2)  map[c - 'a']--;
		for (int i : map) {
			if (i != 0) return false;
		}
		return true;
	}
	/*
	 * 1) Create count arrays of size 256 for both strings if ascii.
	 * 	  
	 * 2) Iterate through every character of both strings and increment the count of character 
	 * 	  in the corresponding count arrays. 
	 * 3) Compare count arrays. If both count arrays are same, then return true.
	 */
	public boolean areAnagrams2(String x, String y) {
		int num_s = x.length();
		int num_t = y.length();
		if (num_s != num_t)
			return false;

		int[] c1 = new int[256];
		int[] c2 = new int[256];
		
		for (int i = 0; i < num_s; i++) {
			c1[x.charAt(i)]++;
			c2[y.charAt(i)]++;
		}
		for (int i = 0; i < 256; i++) {
			if (c1[i] != c2[i])
				return false;
		}
		return true;
	}

	// bucket sort
	// O(n*mlogm), m -> word length, O(mn)
	public ArrayList<String> anagrams(String[] strs) {
		ArrayList<String> list = new ArrayList<String>();
		HashMap<String, ArrayList<String>> map = new HashMap<String, ArrayList<String>>();
		for (String s : strs) {
			char[] ch = s.toCharArray();
			java.util.Arrays.sort(ch);
			String sorted = new String(ch);
			ArrayList<String> l = null;
			if (map.containsKey(sorted)) {
				l = map.get(sorted);
			} else {
				l = new ArrayList<String>();
			}
			l.add(s);
			map.put(sorted, l);
		}
		for (ArrayList<String> res : map.values()) {
			if (res.size() > 1) {
				list.addAll(res);
			}
		}
		return list;
	}
}
