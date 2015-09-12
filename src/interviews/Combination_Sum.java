package interviews;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Stack;

/**
 * Given a set of candidate numbers (C) and a target number (T), find all unique
 * combinations in C where the candidate numbers sums to T.
 * 
 * The same repeated number may be chosen from C unlimited number of times.
 * 
 * Note: 
 * All numbers (including target) will be positive integers. 
 * Elements in a combination (a1, a2, … , ak) must be in non-descending order. (ie, a1 ≤ a2 ≤ … ≤ ak). 
 * The solution set must not contain duplicate combinations. 
 * 
 * For example, given candidate set 2,3,6,7 and target 7, 
 * 
 * A solution set is: [ [7], [2, 2, 3] ]
 *
 * 思路：backtracking, 对一个数字只要加上之后，sum小于给定sum就叠加，直至不能再加；然后pop，下一个数
 * 
 * NP problem, exponential
 */
public class Combination_Sum {
	public ArrayList<ArrayList<Integer>> combinationSum(int[] candidates, int target) {
        ArrayList<ArrayList<Integer>> res = new ArrayList<ArrayList<Integer>>();
        if(candidates.length == 0) return res;
        Arrays.sort(candidates);
        helper(candidates, target, 0, res, new ArrayList<Integer>(), 0);
        return res;
	}
	public void helper(int[] num, int total, int index, ArrayList<ArrayList<Integer>> res, ArrayList<Integer> cur, int sum){
		if(sum > total) return;
		if(sum == total){
			res.add(cur);
			return;
		}
		for(int i = index; i < num.length; i++){
			ArrayList<Integer> list = new ArrayList<Integer>(cur);
			list.add(num[i]);
			int curSum = sum + num[i];
			helper(num, total, i, res, list, curSum);
		}
	}
	/**
	 * Iterative way: use two stacks
	 */
	public ArrayList<ArrayList<Integer>> combinationSumIte(int[] candidates, int target) {
        ArrayList<ArrayList<Integer>> res = new ArrayList<ArrayList<Integer>>();
        if(candidates.length == 0) return res;
        Arrays.sort(candidates);
        Stack<Integer> stack = new Stack<Integer>();
        Stack<Integer> indexStack = new Stack<Integer>();
        int sum = 0;
        int index = 0;
        int n = candidates.length;
        while(index < n){
            if(sum + candidates[index] < target){
                stack.push(candidates[index]);
                indexStack.push(index);
                sum += candidates[index];
            }else{
                if(sum + candidates[index] == target){
                    stack.push(candidates[index]);
                    res.add(new ArrayList<Integer>(stack));
                    stack.pop();
                }
                if(!indexStack.isEmpty()){
                    index = indexStack.pop();
                    int cur = stack.pop();
                    sum -= cur;
                    while(index == n - 1 && !indexStack.isEmpty()){
                        index = indexStack.pop();
                        cur = stack.pop();
                        sum -= cur;
                    }
                }
                index++;
            }
        }
        return res;
    }
	
	/**
	 * Combination Sum II: numbers cannot be used more than once
	 */
	public class Solution {
	    public ArrayList<ArrayList<Integer>> combinationSum2(int[] num, int target) {
	        ArrayList<ArrayList<Integer>> res = new ArrayList<ArrayList<Integer>>();
	        if(num.length == 0 || target == 0) return res;
	        Arrays.sort(num);
	        helper(num, 0, 0, target, new ArrayList<Integer>(), res, new HashSet<ArrayList<Integer>>());
	        return res;
	    }
	    public void helper(int[] num, int index, int curSum, int target, ArrayList<Integer> cur, ArrayList<ArrayList<Integer>> res, HashSet<ArrayList<Integer>> used){
	        if(index == num.length || curSum >= target){
	            if(target == curSum && !used.contains(cur)){
	                ArrayList<Integer> list = new ArrayList<Integer>(cur);
	                res.add(list);
	                used.add(list);
	            }
	            return;
	        }
	        cur.add(num[index]);
	        helper(num, index + 1, curSum + num[index], target, cur, res, used);
	        cur.remove(cur.size() - 1);
	        helper(num, index + 1, curSum, target, cur, res, used);
	    }
	}
	/**
	 * Combination Sum III: Find all possible combinations of k numbers that add
	 * up to a number n, given that only numbers from 1 to 9 can be used and
	 * each combination should be a unique set of numbers.
	 * 
	 * Ensure that numbers within the set are sorted in ascending order.
	 * 
	 * Example 1:
	 * 
	 * Input: k = 3, n = 7
	 * Output: [[1,2,4]]
	 * 
	 * Example 2:
	 * 
	 * Input: k = 3, n = 9 
	 * Output: [[1,2,6], [1,3,5], [2,3,4]]
	 * 
	 * 一定要记得新建ArrayList，不然会清零。
	 */
	public List<List<Integer>> combinationSum3(int k, int n) {
        List<List<Integer>> res = new ArrayList<List<Integer>>();
	    helper(1, k, n, new ArrayList<Integer>(), res);
	    return res;
    }
    public void helper(int numSoFar, int targetCount, int targetSum, List<Integer> curList, List<List<Integer>> res) {
        if(0 == targetSum && 0 == targetCount) {
            res.add(curList);
            return;
        }
        if(targetSum <= 0 || targetCount == 0 || numSoFar > 9) return;
        for(int i = numSoFar; i <= 9; i++) {
            curList.add(i);
            helper(i + 1, targetCount - 1, targetSum - i, new ArrayList<Integer>(curList), res);
            curList.remove(curList.size() - 1);
        }
    }
}
