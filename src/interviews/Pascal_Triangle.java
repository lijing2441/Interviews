package interviews;

import java.util.ArrayList;

public class Pascal_Triangle {
  /**
   * Given numRows, generate the first numRows of Pascal's triangle. For example, given numRows = 5,
   * Return 
   * [ 
   * 	 [1], 
   *    [1,1], 
   *   [1,2,1], 
   *  [1,3,3,1], 
   * [1,4,6,4,1]
   * ]
   * 
   * @logic add one by one line, complexity: O(n^2); space: O(n^2)
   **/

	public ArrayList<ArrayList<Integer>> generate(int numRows) {
		ArrayList<ArrayList<Integer>> res = new ArrayList<ArrayList<Integer>>();
		if (numRows == 0) return res;

		for (int i = 1; i <= numRows; i++) {
			ArrayList<Integer> current = new ArrayList<Integer>();
			if (i == 1) {
				current.add(1);
				res.add(current);
				continue;
			} else {
				ArrayList<Integer> last = res.get(res.size() - 1);
				for (int k = 0; k < i; k++) {
					if (k == 0 || k == (i - 1)) {
						current.add(1);
					} else {
						current.add(last.get(k - 1) + last.get(k));
					}
				}
				res.add(current);
			}
		}
		return res;
	}

	/** 
	 * Problem II:
	 * 
	 * Given an index k, return the kth row of the Pascal's triangle.
	 * For example, given k = 3,
	 * Return [1,3,3,1].
	 * 
	 * Complexity: O(n^2) and space O(n), since we can reuse the last loop value
     **/	
	public ArrayList<Integer> getRow(int rowIndex) {
		ArrayList<Integer> res = new ArrayList<Integer>();
		res.add(1);
		/*
		 * the first scan: update the previous line, set up the value of indices from the end, except the last index
		 * Reason backward: we need to reuse the last line value;
		 */
		for (int i = 1; i <= rowIndex; i++) {  
			for (int j = i - 1; j >= 1; j--) { 
				res.set(j, res.get(j) + res.get(j - 1));
			}
			res.add(1);
		}
		return res;
	}
}
