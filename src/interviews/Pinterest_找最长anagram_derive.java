package interviews;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.TreeMap;

public class Pinterest_找最长anagram_derive {
	/**
	 * Given a list of words output the largest anagram derivative contained in
	 * that set. The definition of an anagram derivative is: Consider the word
	 * 'cat' as a basis, then the word 'tack' is said to an anagram derivative
	 * of 'cat' since it can be re-arranged and appended with an alphabet to
	 * form the word 'tack'. This process can be performed repeatedly, so that
	 * the word 'tacky' is an anagram derivative of 'tack'. Now given a list of
	 * words output the largest anagram derivative in that list.
	 * 
	 * [change each word to a string of sorted letters; build a graph of them,
	 * "act"-->"ackt", find the longest string with in-coming link]
	 */
	public static String getLongestAnagramDeriv(List<String> words) {
		// 存sorted和对应原词
		Map<String, List<String>> sortedMap = new HashMap<String, List<String>>();
		// 存shorter word -> longer word 的graph
		TreeMap<String, List<String>> graph = new TreeMap<String, List<String>>(new Comparator<String>() {
			public int compare(String s1, String s2) {
				return s1.length() - s2.length();
			}
		});
		// 必须先塞长度小的string，这样后面才能塞大的进入相应的list
		Collections.sort(words, new Comparator<String>() {
			public int compare(String s1, String s2) {
				return s1.length() - s2.length();
			}
		});
		// 建图
		for (String s : words) {
			char[] arr = s.toCharArray();
			Arrays.sort(arr);
			String sorted = new String(arr);
			if (!sortedMap.containsKey(sorted)) {
				sortedMap.put(sorted, new ArrayList<String>());
			}
			sortedMap.get(sorted).add(s);
			// insert it into the graph first
			if (graph.containsKey(sorted)) continue; // previously inserted
			else {
				graph.put(sorted, new ArrayList<String>());
				for (int i = 0; i < sorted.length(); i++) {
					// remove each letter to see whether there is a shorter word
					String previous = removeChar(sorted, i);
					if (graph.containsKey(previous)) {
						graph.get(previous).add(sorted);
					}
				}
			}
		}
		String res = "";
		String lastKey = graph.lastKey();
		while (graph.containsKey(lastKey) && graph.get(lastKey).size() == 0) {
			lastKey = graph.lowerKey(lastKey);
		}
		if (graph.containsKey(lastKey)) {
			String sortedString = graph.get(lastKey).get(0);
			res = sortedMap.get(sortedString).get(0);
		}
		return res;
	}
	
	public static String removeChar(String s, int index) {
		StringBuilder sb = new StringBuilder(s);
		sb.deleteCharAt(index);
		return sb.toString();
	}
	public static void main(String[] args) {
		String[] words = {"cat", "ct", "tack", "tacky"};
		String res = getLongestAnagramDeriv(Arrays.asList(words));
		System.out.println(res);
	}
}
