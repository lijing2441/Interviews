package interviews;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Facebook_Combination_Product {
	/**
	 * 给一个质数数组，输出所有可能的product，输出顺序无所谓
	 * 
	 * 比如[2, 3, 5] => [2, 3, 5, 6, 10, 15, 30]
	 * 
	 * follow up: 有重复？[2, 2, 2] => [2, 4, 8]
	 */
	// 有些像subsets
	public static List<Integer> getAllProducts(int[] arr) {
		List<Integer> res = new ArrayList<Integer>();
		if (arr == null || arr.length == 0) return res;
		int len = arr.length;
		Set<Integer> set = new HashSet<Integer>();
		for (int i = 0; i < Math.pow(2, len); i++) {
			int tmp = i;
			int cur = 1;
			for (int j = 0; j < len; j++) {
				int bit = tmp & 1;
				tmp >>= 1;
				if (bit > 0) {
					cur *= arr[j];
				}
			}
			if (cur > 1) set.add(cur);
		}
		res.addAll(set);
		return res;
	}
	public static void main(String[] args) {
		int[] arr = {2, 2, 2};
		for (int i : getAllProducts(arr)) {
			System.out.println(i);
		}
	}
	
}
