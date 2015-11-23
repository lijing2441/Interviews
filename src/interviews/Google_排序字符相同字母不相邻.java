package interviews;

import java.util.Comparator;
import java.util.Map;
import java.util.HashMap;
import java.util.PriorityQueue;

public class Google_排序字符相同字母不相邻 {
	// 题目大致是BACCBBAAA -> ABABACABC，就是输出相邻字母不能相同的string，这题要用到heap
	public static String getOutput(String input) {
		Map<Character, Integer> map = new HashMap<>();
		char[] arr = input.toCharArray();
		for (char c : arr) {
			int num = 1;
			if (map.containsKey(c)) num += map.get(c);
			map.put(c, num);
		}
		PriorityQueue<Character> heap = new PriorityQueue<Character>(new Comparator<Character>() {
			public int compare(Character o1, Character o2) {
				return map.get(o2) - map.get(o1);
			}
		});
		for (Map.Entry<Character, Integer> entry: map.entrySet()) {
			heap.offer(entry.getKey());
		}
		StringBuilder sb = new StringBuilder();
		while (!heap.isEmpty()) {
			char c = heap.poll();
			if (heap.isEmpty()) {
				sb.append(c);
				break;
			}
			char d = heap.poll();
			sb.append(c);
			sb.append(d);
			map.put(c, map.get(c) - 1);
			map.put(d, map.get(d) - 1);
			if (map.get(c) > 0) heap.offer(c);
			if (map.get(d) > 0) heap.offer(d);
		}
		return sb.toString();
	}
	
	public static void main(String[] args) {
		String input = "BACCBBAAA";
		System.out.println(getOutput(input));
	}
}
