package interviews;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Legal_Telephone_Number {
	/**
	 * Give the length of the telephone number and a list of banned number,
	 * which the telephone cannot include, output a list of numbers that
	 * represents legal telephone number.
	 * 
	 * Additional requirements: 1. 相邻数字不要重复; 2. 第一个数字如果为4，后面不能再有4
	 */
	public static List<List<Integer>> generateLegelNumber(int length, List<Integer> bannedNum) {
		List<List<Integer>> res = new ArrayList<List<Integer>>();
		Set<Integer> candidates = new HashSet<Integer>();
		for (int i = 0; i <= 9; i++) {
			if (!bannedNum.contains(i)) {
				candidates.add(i);
			}
		}
		helper(res, new ArrayList<Integer>(), candidates, length);
		return res;
	}
	public static void helper(List<List<Integer>> res, List<Integer> cur, Set<Integer> candidates, int len) {
		if (cur.size() == len) {
			// 一定要重新开一个list，不然会清零
			res.add(new ArrayList<Integer>(cur));
			return;
		}
		for (int i : candidates) {
			if (cur.size() > 0) {
				if (cur.get(cur.size() - 1) == i) continue;
				if (i == 4 && cur.get(0) == 4) continue;
			}
			cur.add(i);
			helper(res, cur, candidates, len);
			cur.remove(cur.size() - 1);
		}
	}
	
	public static void main(String[] args) {
		int len = 3;
		List<Integer> banned = new ArrayList<Integer>();
		banned.add(2);
		banned.add(5);
		
		List<List<Integer>> res = generateLegelNumber(len, banned);
		//System.out.print(res.size());
		for (List<Integer> list : res) {
			for (int i : list) {
				System.out.print(i);
			}
			System.out.println();
		}
	}
}
