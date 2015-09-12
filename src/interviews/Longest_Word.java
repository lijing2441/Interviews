package interviews;

import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;

public class Longest_Word {
	/**
	 * Given { "dog", "google", "facebook", "internationalization", "blabla" } 
	 * 
	 * the longest words are(is) ["internationalization"].
	 * 
	 * Given { "like", "love", "hate", "yes" } 
	 * 
	 * the longest words are ["like", "love", "hate"].
	 * 
	 * @param dictionary
	 * @return
	 */
	ArrayList<String> longestWords(String[] dictionary) {
		// write your code here
		if (dictionary.length == 0)
			return new ArrayList<String>();
		Map<Integer, ArrayList<String>> map = new HashMap<Integer, ArrayList<String>>();
		int max = 0;
		for (String str : dictionary) {
			int len = str.length();
			ArrayList<String> list = null;
			if (map.containsKey(len)) {
				list = map.get(len);
			} else {
				list = new ArrayList<String>();
			}
			list.add(str);
			map.put(len, list);
			if (len > max)
				max = len;
		}
		return map.get(max);
	}
}
