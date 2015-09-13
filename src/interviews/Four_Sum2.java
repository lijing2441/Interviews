package interviews;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.Set;

public class Four_Sum2 {

	public static List<List<Integer>> sum4(List<Integer> list, int target) {
		Collections.sort(list);
		List<List<Integer>> ans = new ArrayList<List<Integer>>();

		// Map <2sum, list of pairs>.1point3acres缃�
		Map<Integer, List<List<Integer>>> map = new HashMap<Integer, List<List<Integer>>>();

		for (int i = 0; i < list.size(); i++) {
			for (int j = i + 1; j < list.size(); j++) {
				int sum2 = list.get(i) + list.get(j);
				List<Integer> pair = new ArrayList<Integer>();
				pair.add(i);
				pair.add(j);
				if (map.containsKey(sum2)) {
					map.get(sum2).add(pair);
				} else {
					List<List<Integer>> listofPair = new ArrayList<List<Integer>>();
					listofPair.add(pair);
					map.put(sum2, listofPair);
				}
			}
		}

		Set<Pair> set = new HashSet<Pair>();
		for (int key : map.keySet()) {
			int anotherkey = target - key;
			if (anotherkey < key)
				continue;
			if (map.containsKey(anotherkey)) {
				for (List<Integer> firstValues : map.get(key)) {
					int a = firstValues.get(0);
					int b = firstValues.get(1);
					for (List<Integer> secondValues : map.get(anotherkey)) {
						int c = secondValues.get(0);
						int d = secondValues.get(1);
						if (Pair.valid(a, b, c, d)) {
							Pair pair = new Pair(a, b, c, d);
							set.add(pair);

						}

					}
				}
			}
		}

		for (Pair p : set) {
			List<Integer> path = new ArrayList<Integer>();
			for (Integer ele : p.eles) {
				path.add(list.get(ele));
			}
			ans.add(path);
		}
		return ans;
	}

	public static void main(String[] args) {
		//ArrayList<String> strings = new ArrayList<String>();
		Integer[] numbers = new Integer[] { 4, 2, 6, 1, 5, 3 };
		List<Integer> list = Arrays.asList(numbers);
		System.out.println(sum4(list, 16));
	}

}

class Pair {
	Integer[] eles;

	public Pair(int a, int b, int c, int d) {
		eles = new Integer[] { a, b, c, d };
		Arrays.sort(eles);
	}

	static boolean valid(int a, int b, int c, int d) {
		if (a == c || b == d || b == c)
			return false;
		return true;
	}

	public int hashCode() {
		Integer ans = 2;
		for (int i : eles) {
			ans += ans * i;
		}
		return ans;
	}

	public boolean equals(Object that) {
		if (!(that instanceof Pair))
			return false;
		if (that == this)
			return true;
		for (int i = 0; i < 4; i++) {
			if (this.eles[i] != ((Pair) that).eles[i])
				return false;
		}

		return true;
	}

}