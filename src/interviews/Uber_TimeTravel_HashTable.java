package interviews;

import java.util.Map;
import java.util.HashMap;
import java.util.TreeMap;

public class Uber_TimeTravel_HashTable {
	/**
	 * Implement TimeTravelingHashTable的get和insert方法
	 * 
	 * TimeTravelingHashTable
	 * 
	 * insert(key, value, timestamp).
	 * 
	 * get(key, timestamp)
	 * 
	 * get(key) // returns value associated with key at latest time
	 * 
	 * @logic 用一个哈希表，key就是key，value是一个treemap,
	 *         treemap节点是time和value，这样按照查找time就是o(logn)吧，哈希表需要遍历。
	 *
	 * TreeMap 用红黑树实现
	 * 
	 * 问面试官：如果在插入时候有相同时间和key，但是value不同，是否覆盖？
	 */
	public class TimeTravelingHashTable {
		Map<Integer, TreeMap<Integer, Integer>> map;
		public TimeTravelingHashTable() {
			map = new HashMap<>();
		}
		
		public void insert(int key, int val, int timestamp) {
			if (map.containsKey(key)) {
				TreeMap<Integer, Integer> curMap = map.get(key);
				curMap.put(timestamp, val); // 没有插入，有就覆盖
			} else {
				TreeMap<Integer, Integer> curMap = new TreeMap<Integer, Integer>();
				curMap.put(timestamp, val);
				map.put(key, curMap);
			}
		}
		public int get(int key, int timestamp) {
			if (map.containsKey(key) && map.get(key).containsKey(timestamp)) {
				return map.get(key).get(timestamp);
			}
			return -1;
		}
		public int get(int key) {
			if (map.containsKey(key)) {
				// latest time to be defined to be the largest timestamp
				TreeMap<Integer, Integer> curMap = map.get(key);
				int latestTime = curMap.lastKey();
				return curMap.get(latestTime);
			}
			return -1;
		}
	}
}