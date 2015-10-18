package interviews;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.TreeMap;

public class Four_Sum2 {
	// O(n^2 logn)
	// TreeMap guaranteed log(n) time cost for the containsKey, get, put and
	// remove operations
	// 这道题如果用hashmap的话，需要在后面一部找pair的时候轮询n^2内再轮询n^2
	public static List<List<Integer>> fourSum(int[] num, int target) {
		List<List<Integer>> res = new ArrayList<>();
		if (num.length < 4) {
			return res;
		}
		Arrays.sort(num);
		TreeMap<Integer, List<Pair>> map = new TreeMap<>();
		// insert all possible pairs
		for (int i = 0; i < num.length; i++) {
			for (int j = i + 1; j < num.length; j++) {
				Pair pair = new Pair(num[i], i, num[j], j);
				int sum = num[i] + num[j];
				List<Pair> list;
				if (map.containsKey(sum)) {
					list = map.get(sum);
				} else {
					list = new ArrayList<>();
					map.put(sum, list);
				}
				list.add(pair);
			}
		}
		Integer first = map.firstKey();
		Integer last = map.lastKey();
		while (first != null && last != null && first <= last) {
			if (first + last > target) {
				last = map.lowerKey(last);
			} else if (first + last < target) {
				first = map.higherKey(first);
			} else if (first == last) { // 只需要轮询一个bucket
				List<Pair> list = map.get(first);
				for (int i = 0; i < list.size(); i++) {
					Pair a = list.get(i);
					if (a.a == a.b) { // limit the case to 0 0 1 1, but eliminate the 0 1 0 1 case
						// 1, 5, 3, 3 case should be resolved before by 1, 3, 3, 5
						for (int j = i + 1; j < list.size(); j++) {
							Pair b = list.get(j);
							if (b.a == b.b) {
								if (a.bi < b.ai) {
									res.add(Arrays.asList(new Integer[] { a.a, a.b, b.a, b.b }));
									break;
								}
							} else {
								break;
							}
						}
						break;
					}
				}
				last = map.lowerKey(last);
				first = map.higherKey(first);
			} else {  // 需轮询两个bucket
				Pair lastA = null;
				for (Pair a : map.get(first)) {
					if (lastA != null && a.same(lastA)) {
						continue;
					}
					lastA = a;
					Pair lastB = null;
					for (Pair b : map.get(last)) {
						if (a.bi < b.ai) { // make sure we do not have duplicate elements
							if (lastB != null && b.same(lastB)) {
								continue;
							}
							lastB = b;
							res.add(Arrays.asList(new Integer[] { a.a, a.b, b.a, b.b }));
						}
					}
				}
				last = map.lowerKey(last);
				first = map.higherKey(first);
			}
		}
		return res;
	}

	public static void main(String[] args) {
		// ArrayList<String> strings = new ArrayList<String>();
		int[] numbers = { 4, 2, 6, 1, 5, 3 };
		// List<Integer> list = Arrays.asList(numbers);
		System.out.println(fourSum(numbers, 16));
		int[] numbers2 = { 1, 0, -1, -1, -1, -1, 0, 1, 1, 1, 2 };
		// List<Integer> list2 = Arrays.asList(numbers2);
		System.out.println(fourSum(numbers2, 2));
	}

}

class Pair {
	int a;
	int ai;
	int b;
	int bi;

	public Pair(int a, int ai, int b, int bi) {
		this.a = a;
		this.ai = ai;
		this.b = b;
		this.bi = bi;
	}

	boolean same(Pair p) {
		return p != null && p.a == a && p.b == b;
	}
}