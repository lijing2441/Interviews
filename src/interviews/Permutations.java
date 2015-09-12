package interviews;

import java.util.ArrayList;
import java.util.HashSet;
/**
 *  Given a collection of numbers, return all possible permutations.
 *
 *	For example,
 *  [1,2,3] have the following permutations:
 *  [1,2,3], [1,3,2], [2,1,3], [2,3,1], [3,1,2], and [3,2,1].
 */
public class Permutations {
	// time = n!/1! + n!/2! + n!/3! + ... + n!/n!
	// T(n) = n(T(n - 1) + (n-1)!) -> O(n! * n * n)
	public static void permutations(String s) {
		permutation("", s);
	}

	public static void permutation(String prefix, String s) {
		int n = s.length();
		if (n == 0)
			System.out.println(prefix);
		else {
			for (int i = 0; i < n; i++) {
				permutation(prefix + s.charAt(i), s.substring(0, i) + s.substring(i + 1));
			}
		}
	}

	// time O(n!), since it has to do n! things
	// permutate an integer array
	public ArrayList<ArrayList<Integer>> permute(int[] num) {
		ArrayList<ArrayList<Integer>> res = new ArrayList<ArrayList<Integer>>();
		res.add(new ArrayList<Integer>());

		if (num == null || num.length == 0)
			return res;

		for (int i = 0; i < num.length; i++) {
			ArrayList<ArrayList<Integer>> tmp = new ArrayList<ArrayList<Integer>>();
			for (ArrayList<Integer> l : res) {
				for (int j = 0; j <= l.size(); j++) {
					ArrayList<Integer> cur = new ArrayList<Integer>(l); 
					cur.add(j, num[i]);
					tmp.add(cur);
				}
			}
			res = tmp;
		}
		return res;
	}

	// recursive method
	public ArrayList<ArrayList<Integer>> permuteRecur(int[] num) {
		ArrayList<ArrayList<Integer>> res = new ArrayList<ArrayList<Integer>>();
		if (num == null || num.length == 0)
			return res;
		helper(num, 0, res, new ArrayList<Integer>());
		return res;
	}

	public void helper(int[] num, int index, ArrayList<ArrayList<Integer>> res, ArrayList<Integer> cur) {
		if (index == num.length) {
			res.add(cur);
			return;
		}
		for (int i = 0; i <= cur.size(); i++) {
			ArrayList<Integer> tmp = new ArrayList<Integer>(cur);
			tmp.add(i, num[index]);
			helper(num, index + 1, res, tmp);
		}
	}

	public static void main(String[] args) {
		String a = "abc";
		permutations(a);
	}

	/**
	 * Permutation II: duplicate elements might exist. Result list cannot
	 * contain duplicates
	 * 
	 * Using HasSet to store the temp lists
	 */
	public ArrayList<ArrayList<Integer>> permuteUnique(int[] num) {
		ArrayList<ArrayList<Integer>> res = new ArrayList<ArrayList<Integer>>();
		res.add(new ArrayList<Integer>());
		HashSet<ArrayList<Integer>> set = new HashSet<ArrayList<Integer>>();
		for (int i = 0; i < num.length; i++) {
			ArrayList<ArrayList<Integer>> tmp = new ArrayList<ArrayList<Integer>>();
			for (ArrayList<Integer> l : res) {
				for (int j = 0; j <= l.size(); j++) {
					ArrayList<Integer> cur = new ArrayList<Integer>(l);
					cur.add(j, num[i]);
					if (!set.contains(cur)) {
						set.add(cur);
						tmp.add(cur);
					}
				}
			}
			res = tmp;
		}
		return res;
	}
}
