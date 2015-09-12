package interviews;

import java.util.*;

public class Subset_Sum {
	/**
	 * Given a set of non-negative integers, and a value sum, determine if there
	 * is a subset of the given set with sum equal to given sum.
	 * 
	 * @logic Let isSubSetSum(int set[], int sum) be the function to find whether there is a subset of set[] 
	 * 		  with sum equal to sum. n is the number of elements in set[].
	 * 
	 *        The isSubsetSum problem can be divided into two subproblems 
	 *        a) Include the last element, recur for n = n-1, sum = sum – set[n-1]
	 *        b) Exclude the last element, recur for n = n-1. 
	 *        If any of the above the above subproblems return true, then return true.
	 * 		
	 * 		  isSubsetSum(set, n, sum) = isSubsetSum(set, n-1, sum) || isSubsetSum(arr, n-1, sum-set[n-1])
	 * 		  Base Cases: isSubsetSum(set, n, sum) = false, if sum > 0 and n == 0
	 * 					  isSubsetSum(set, n, sum) = true,  if sum == 0        
	 */
	// recursion, O(2^n)
	// This problem is actually NP-complete and no known polynomial time solution for it so far.
	public boolean isSubsetSum(int[] set, int target){
		if(target == 0) return true;
		if(set.length == 0) return false;
		int n = set.length;
		return subsetSumHelper(set, n, target);
	}
	public boolean subsetSumHelper(int[] set, int len, int target){
		if(target == 0) return true;
		if(set[len - 1] > target) return subsetSumHelper(set, len - 1, target);
		// we use the current element or not
		return subsetSumHelper(set, len - 1, target - set[len - 1]) || subsetSumHelper(set, len-1, target); 
	}
	
	/**
	 * Follow-up: if we need to output the result
	 */
	public ArrayList<ArrayList<Integer>> subsetSum(int[] num, int target){
		ArrayList<ArrayList<Integer>> res = new ArrayList<ArrayList<Integer>>();
		if(num.length == 0 && target == 0) return res;
		int n = num.length;
		helper(num, n-1, target, res, new ArrayList<Integer>());
		return res;
	}
	public void helper(int[] num, int index, int target, ArrayList<ArrayList<Integer>> res, ArrayList<Integer> cur){
		if(target == 0){
			res.add(new ArrayList<Integer>(cur));
			return;
		}
		if(index < 0){
			return;
		}
		// the current element cannot be included in the subset
		if(num[index] > target){
			helper(num, index - 1, target, res, cur);
		}else{
			cur.add(num[index]);
			helper(num, index - 1, target - num[index], res, cur);
			cur.remove(cur.size() - 1);
			helper(num, index - 1, target, res, cur);
		}
	}
	/**
	 * We can try to solve the problem in Pseudo-polynomial time using Dynamic programming. 
	 * We create a boolean 2D table subset[][] and fill it in bottom up manner. 
	 * 
	 * subset[i][j] = true   if there is a subset of set[0..j-1] with sum equal to i 
	 * subset[i][j] = false  otherwise
	 * 
	 * Finally, we return subset[sum][n].
	 * 
	 * @complexity O(target * n), but O(target * n) space
 	 */
	public boolean isSubsetSumDP(int[] set, int target){
		int n = set.length;
		boolean[][] map = new boolean[target + 1][n + 1];
		// If sum is 0, then answer is true, 0 element can do
		for(int i = 0; i <= n; i++){
			map[0][i] = true;
		}
		//If the sum is not zero, but the set is empty, then answer is false
		for(int i = 0; i <= target; i++){
			map[i][0] = false;
		}
		// Fill the subset table in bottom up manner
		for(int i = 1; i <= target; i++){
			for(int j = 1; j <= n; j++){
				map[i][j] = map[i][j-1];
				if(set[j-1] <= i){
					map[i][j] |= map[i - set[j-1]][j-1];
				}
			}
		}
		return map[target][n];
		/*
		 * if we need to output one of the solution, we can backtrack from here
		 * if(map[target][n]){
		 *     //The subset S contains numbers[j] only if table[ i-numbers[j] ][j-1]
		 *     while(target > 0){
		 *         -> If so, recursively compute whether numbers[n-2] is in the subset summing to t-numbers[n-1],
				   -> else recursively compute whether numbers[n-2], is in the subset summing to t
		 *     }
		 * }
		 */
	}
	
	/**
	 * 
	 * Another version of the subset sum:
	 * Given an arraylist of N integers, 
	 * (1) find a non-empty subset whose sum is a multiple of N. 
	 * (2) find a non-empty subset whose sum is a multiple of 2N. 
	 * Compare the solutions of the two questions. 
	 * ->The solution of the second should be 2 solutions of the first (if applicable).
	 * 
	 * @logic O(n^2) solution. 
	 * Basically for each number x from 0 to N-1, we want to keep track of which subsets 
	 * of the input will add up to x, after modulo N. 
	 * So that's what my code does below with the HashMap, and the parameter M can actually be N or 2N.
	 * 
	 * The inner loop doesn't iterate over all the subsets. It iterates over each distinct subset sum mod M, 
	 * which means the inner loop executes O(MN) times. If M = N or M = 2N, the inner loop executes O(N^2) times. 
	 * 
	 * Now, it does appear at first that maybe the solution is actually O(N^3), 
	 * because the line LinkedList<Integer> list = (LinkedList<Integer>)subsets.get(i).clone(); 
	 * actually takes O(N) time to execute. However, that line is only hit if the current subset 
	 * sum mod M is distinct from any other subset sum mod M seen so far. 
	 * 
	 * Since there can only be M different subset sums mod M, the line can only be executed 
	 * M times, for a total of O(MN) time spent on that line.
	 */
	public ArrayList<Integer> findSubsets(int[] number, int M){
		//we use a map to record the (mod - list) pair
		//when the mod == 0, that is, the numbers in the list compose a multiplication of M
		Map<Integer, ArrayList<Integer>> subsets = (Map<Integer, ArrayList<Integer>>) new HashMap();
		subsets.put(0, new ArrayList<Integer>());
		//we loop from left to right and do not go back to avoid duplication
		for(int num : number){
			// we need to create a new set in order to keep the mod fixed for current num
			// and not to enter infinite loop
			HashSet<Integer> keys = new HashSet<Integer>(subsets.keySet());
			for(int key: keys){
				// check if we add the current num to the already existing list
				// whether we can get a multiplication
				int curSum = (num + key) % M;
				// check whether the current list can be a fit
				if(curSum == 0){
					subsets.get(key).add(num);
					return subsets.get(key);
				}
				// check whether the map has already have the mod, if not, add to it.
				// the same mod does not need to be added again, since we will add the same num 
				// later to the same mod, which is useless, since we do not need to return
				// all the solutions, but only one.
				if(subsets.containsKey(curSum)){
					continue;
				}
				ArrayList<Integer> list = new ArrayList<Integer>(subsets.get(key));
				list.add(num);
				subsets.put(curSum, list);
			}
		}
		//we did not get solution, return null
		return null;
	}
}
