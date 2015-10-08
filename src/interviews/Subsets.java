package interviews;

import java.util.*;

public class Subsets {
	/**
	 * recursive way
	 */
	public static ArrayList<ArrayList<Integer>> subsetsR(int[] S) {
		Arrays.sort(S);
		ArrayList<ArrayList<Integer>> res = new ArrayList<ArrayList<Integer>>();
		ArrayList<Integer> list = new ArrayList<Integer>();
		subsetsHelper(S, 0, res, list);
		return res;
	}

	public static void subsetsHelper(int[] S, int cur, 
			ArrayList<ArrayList<Integer>> res, ArrayList<Integer> list) {
		if (cur == S.length) {
			res.add(list);
			return;
		}
		list.add(S[cur]);
		subsetsHelper(S, cur + 1, res, list);
		// remove the last one that just was added
		list.remove(list.size() - 1);
		subsetsHelper(S, cur + 1, res, list);
	}

	/**
	 * iterative way
	 */
	public ArrayList<ArrayList<Integer>> subsets(int[] S) {
		if (S == null)
			return null;
		Arrays.sort(S);
		ArrayList<ArrayList<Integer>> result = new ArrayList<ArrayList<Integer>>();
		for (int i = 0; i < S.length; i++) {
			// for each element, create a new set
			ArrayList<ArrayList<Integer>> temp = new ArrayList<ArrayList<Integer>>();
			for (ArrayList<Integer> a : result) {
				temp.add(new ArrayList<Integer>(a));
			}

			// add S[i] to existing sets
			for (ArrayList<Integer> a : temp) {
				a.add(S[i]);
			}
			// add S[i] only as a set
			ArrayList<Integer> single = new ArrayList<Integer>();
			single.add(S[i]);
			temp.add(single);
			// add the ones with addition of S[i]
			// the original ones are the ones that didn't add S[i]
			result.addAll(temp);
		}
		// finally add empty set
		result.add(new ArrayList<Integer>());
		return result;
	}

	/**
	 * bitwise, O(2^n)
	 */
	public static ArrayList<ArrayList<Integer>> subsetsBit(int[] S) {
		int len = S.length;
		ArrayList<ArrayList<Integer>> res = new ArrayList<ArrayList<Integer>>();
		// since we need the final result sorted
		Arrays.sort(S);
		// since for each element, we can set it to 0 (not in), or 1 (in)
		for (int i = 0; i < Math.pow(2, len); i++) {
			ArrayList<Integer> list = new ArrayList<Integer>();
			int tmp = i;

			for (int j = 0; j < len; j++) {
				// check the current element
				int bit = tmp & 1;
				// next element
				tmp = tmp >> 1;
				if (bit == 1) {
					list.add(S[j]);
				}
			}
			res.add(list);
		}
		return res;
	}

	/**
	 * Given a collection of integers that might contain duplicates, nums,
	 * return all possible subsets.
	 * 
	 * Note: 
	 * - Elements in a subset must be in non-descending order. 
	 * - The solution set must not contain duplicate subsets.
	 */
	public ArrayList<ArrayList<Integer>> subsetsWithDup(int[] num) {
        Arrays.sort(num);
        ArrayList<ArrayList<Integer>> res = new ArrayList<ArrayList<Integer>>();
        ArrayList<Integer> cur = new ArrayList<Integer>();
        HashSet<ArrayList<Integer>> used = new HashSet<ArrayList<Integer>>();
        helper(num, 0, cur, used, res);
        return res;
    }
    public void helper(int[] num, int index, ArrayList<Integer> cur, HashSet<ArrayList<Integer>> used, ArrayList<ArrayList<Integer>> res){
        if(index == num.length){
            res.add(new ArrayList<Integer>(cur));
            return;
        }
        cur.add(num[index]);
        if(!used.contains(cur)){
            used.add(new ArrayList<Integer>(cur));
            helper(num, index + 1, cur, used, res);
        }
        cur.remove(cur.size() - 1);
        helper(num, index + 1, cur, used, res);
    }
    
    // Bitwise
    public ArrayList<ArrayList<Integer>> subsetsWithDupBits(int[] num) {
        Arrays.sort(num);
        ArrayList<ArrayList<Integer>> res = new ArrayList<ArrayList<Integer>>();

        for(int i = 0; i < Math.pow(2, num.length); i++){
            ArrayList<Integer> list = new ArrayList<Integer>();
            for(int j = 0; j < num.length; j++){
                if(((i >> j) & 1) > 0){
                    list.add(num[j]);
                }
            }
            if(!res.contains(list)){
                res.add(list);
            }
        }
        return res;
    }
}
