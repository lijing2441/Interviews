package interviews;

import java.util.ArrayList;
import java.util.List;

public class Factor_Combination {
	
	/**
	 * Examples: 
	 * input: 1
	 * output: []
	 * 
	 * input: 37
	 * output: []
	 * 
	 * input: 12
	 * output:
	 * [
	 *   [2, 6],
	 *   [2, 2, 3],
	 *   [3, 4]
	 * ]
	 * 
	 * input: 32
	 * output:
	 * [
	 *   [2, 16],
	 *   [2, 2, 8],
	 *   [2, 2, 2, 4],
	 *   [2, 2, 2, 2, 2],
	 *   [2, 4, 4],
	 *   [4, 8]
	 * ]
	 */
	public List<List<Integer>> getFactors(int n) {
        List<List<Integer>> res = new ArrayList<List<Integer>>();
        if (n <= 3) return res;
        helper(res, n, new ArrayList<Integer>(), 2);
        return res;
    }
    public void helper(List<List<Integer>> res, int sum, List<Integer> cur, int start) {
        if (sum <= 1) {
            if (cur.size() > 1) res.add(new ArrayList<Integer>(cur));
            return;
        }
        for (int i = start; i <= sum; i++) {
            if (sum % i == 0) {
                cur.add(i);
                helper(res, sum / i, cur, i);
                cur.remove(cur.size() - 1);
            }
        }
    }
}
