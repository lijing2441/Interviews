package interviews;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.PriorityQueue;

public class Uber_出现次数Median的字符 {
	/**
	 * 给一个String list，例如 ['a','b','b','c','c','e','e','e']
	 * 返回出现次数是中位数的字符，例子应返回[b, c]
	 */
	public static List<String> getMedianTimesStrings(List<String> list) {
		List<String> res = new ArrayList<String>();
		if (list == null || list.size() == 0) return res;
		Map<String, Integer> map = new HashMap<String, Integer>();
		for (String s: list) {
			int count = 1;
			if (map.containsKey(s)) {
				count += map.get(s);
			}
			map.put(s, count);
		}
		PriorityQueue<Map.Entry<String, Integer>> pq = new PriorityQueue<Map.Entry<String, Integer>>(new Comparator<Map.Entry<String, Integer>>() {
			public int compare(Map.Entry<String, Integer> entry1, Map.Entry<String, Integer> entry2) {
				return entry1.getValue() - entry2.getValue();
			}
		});
		for (Map.Entry<String, Integer> entry: map.entrySet()) {
			pq.offer(entry);
		}
		List<String> seq = new ArrayList<String>();
		while (!pq.isEmpty()) {
			seq.add(pq.poll().getKey());
		}
		int len = seq.size();
		if (seq.size() % 2 == 0) {
			int pos = (len - 1) / 2;
			res.add(seq.get(pos));
			res.add(seq.get(pos + 1));
		} else {
			res.add(seq.get(len / 2));
		}
		return res;
	}
	
	public static void main(String[] args) {
		String[] arr = {"a","b","b","c","c","e","e","e"};
		List<String> list = Arrays.asList(arr);
		List<String> res = getMedianTimesStrings(list);
		for (String s : res) {
			System.out.println(s);
		}
	}
}
