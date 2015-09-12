package interviews;

import java.util.ArrayList;

public class Combinations {
	/**
	 * Given two integers n and k, return all possible combinations of k numbers out of 1 ... n.
	 * For example, if n = 4 and k = 2, a solution is:
	 * [
	 *   [2,4],
	 *   [3,4],
	 *   [2,3],
	 *   [1,2],
	 *   [1,3],
	 *   [1,4],
	 * ]
	 * @analysis backtracking, NP problem
	 */

	// O(C(n, k) * k), C(n, k) = n!/(k!(n-k)!)
	public ArrayList<ArrayList<Integer>> combine(int n, int k) {
		ArrayList<ArrayList<Integer>> res = new ArrayList<ArrayList<Integer>>();
		if (n * k == 0 || n < k) return res;
		
		//outer loop: add the k elements
		for (int i = 0; i < k; i++) {
			if (res.size() == 0) {
				// add the first element, have n-k+1 choices
				for (int j = 1; j <= (n - k + 1); j++) {
					ArrayList<Integer> l = new ArrayList<Integer>();
					l.add(j);
					res.add(l);
				}
			} else {
				// add the following element
				ArrayList<ArrayList<Integer>> tmp = new ArrayList<ArrayList<Integer>>();
				for (int m = 0; m < res.size(); m++) {
					ArrayList<Integer> last = res.get(m);
					int start = last.get(last.size() - 1);

					for (int l = start + 1; l <= n; l++) {
						// add one more element based on the last element added
						ArrayList<Integer> cur = new ArrayList<Integer>(last);
						cur.add(l);
						tmp.add(cur);
					}
				}
				res = tmp;
			}
		}
		return res;
	}
	
	/**
	 * recursive method
	 * 
	 * 思路：对于一个元素，加或者不加；只要得到大小对的list就返回
	 */
	public ArrayList<ArrayList<Integer>> combineRecur(int n, int k) {
		ArrayList<ArrayList<Integer>> res = new ArrayList<ArrayList<Integer>>();
		if (n * k == 0 || n < k) return res;
		helper(n, k, 1, res, new ArrayList<Integer>());
		return res;
	}
	public void helper(int n, int k, int start, ArrayList<ArrayList<Integer>> res, ArrayList<Integer> cur){
		if(k == 0){
			// we have added k elements to the current list
			res.add(cur);
			return;
		}
		for(int i = start; i <= n; i++){
			ArrayList<Integer> list = new ArrayList<Integer>(cur);
			list.add(i);
			helper(n, k - 1, i + 1, res, list);
		}
	}
}
