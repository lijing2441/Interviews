package interviews;

import java.util.Map;
import java.util.HashMap;

public class Palantir_Transform_Map {
	/**
	 * Transform a Map<A, Map<B,C>> to a Map<B, Map<C, Set<A>> 
	 * 
	 * 做一个tuple<A,B,C> array, 然后搞搞就出来了
	 * 
	 * 如果value B相同，然后C不同怎么合并？
	 */
	public static Map<Integer, Map<Integer, Integer>> transformMap(Map<Integer, Map<Integer, Integer>> map) {
		//List<int[]> tuples = new ArrayList<int[]>();
		Map<Integer, Map<Integer, Integer>> res = new HashMap<Integer, Map<Integer, Integer>>();
		for (Map.Entry<Integer, Map<Integer, Integer>> entry: map.entrySet()) {
			int A = entry.getKey();
			for (Map.Entry<Integer, Integer> innerEntry: entry.getValue().entrySet()) {
				int B = innerEntry.getKey();
				int C = innerEntry.getValue();
				Map<Integer, Integer> cur = null;
				if (!res.containsKey(B)) {
					cur = new HashMap<Integer, Integer>();
				} else {
					cur = res.get(B);
				}
				cur.put(A, C);
				res.put(B, cur);
			}
		}
		return res;
	}
	public static void main(String[] args) {
		Map<Integer, Map<Integer, Integer>> map = new HashMap<Integer, Map<Integer, Integer>>();
		Map<Integer, Integer> m1 = new HashMap<Integer, Integer>();
		m1.put(2, 3);
		m1.put(3, 4);
		map.put(1, m1);
		Map<Integer, Integer> m2 = new HashMap<Integer, Integer>();
		m2.put(4, 5);
		m2.put(5, 6);
		map.put(3, m2);
		Map<Integer, Map<Integer, Integer>> res = transformMap(map);
		for (Map.Entry<Integer, Map<Integer, Integer>> e : res.entrySet()) {
			System.out.println(e.getKey());
			System.out.println(e.getValue().size());
			System.out.println();
		}
	}
}
