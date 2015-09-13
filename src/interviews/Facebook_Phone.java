package interviews;

import java.util.Map;
import java.util.HashMap;

public class Facebook_Phone {
	/**
	 * Given a k value and a list of tasks as string, k is the cooling time for each task.
	 * 
	 * Find the minimal time the task can be finished.
	 * 
	 * Example: ABCDAE, k = 12, then the minimal time will be 15.
	 * 
	 * follow up: if k is small, how to improve space usability
	 * 			=> remove the map value after k
	 */
	public static void main(String[] args) {
		String tasks = "ABCDAE";
		int k = 12;
		int k2 = 4;
		int res = minimalTime(tasks, k);
		int res2 = minimalTime(tasks, k2);
		System.out.println(res);
		System.out.println(res2);
	}
	
	public static int minimalTime(String tasks, int k) {
		if (tasks == null) return 0; 
		if (tasks.length() == 0 || k == 0) return tasks.length();
		int count = 0;
		int len = tasks.length();
		Map<Character, Integer> map = new HashMap<Character, Integer>();
		for (int i = 0; i < len; i++) {
			char cur = tasks.charAt(i);
			if (map.containsKey(cur) && i - map.get(cur) - 1 < k) {
				count += (k - (i - map.get(cur) - 1));
			}
			map.put(cur, i);
			count++;
			if (i >= k) {
				if (map.get(tasks.charAt(i - k)) <= (i - k)) {
					map.remove(tasks.charAt(i - k));
				}
			}
		}
		return count;
	}
}
