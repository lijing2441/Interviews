package interviews;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class Two_Sum {
	// if not ask for the indices, first sort, then use two pointers
	// O(nlon + n)
	public int[] twoSum(int[] numbers, int target) {
		Arrays.sort(numbers);
		int left = 0;
		int right = numbers.length - 1;
		while (left <= right) {
			if (numbers[left] + numbers[right] == target) {
				int[] res = new int[2];
				res[0] = left + 1;
				res[1] = right + 1;
				return res;
			} else if (numbers[left] + numbers[right] < target) {
				left++;
			} else {
				right--;
			}
		}
		return null;
	}

	// ask for indices, using HashMap to record the result
	// O(n) assume HashMap find only use O(1) time
	public int[] twoSumHash(int[] numbers, int target) {
		int[] res = new int[2];
		HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
		for (int i = 0; i < numbers.length; i++) {
			if (map.containsKey(target - numbers[i])) {
				if (map.get(target - numbers[i]) != i) { // make sure not the same number
					res[1] = i + 1;
					res[0] = map.get(target - numbers[i]) + 1;
				}
			}
			map.put(numbers[i], i);
		}
		return res;
	}
	
	/**
	 * Two sum in a BST: Given a BST and a number x, check whether exists two nodes in the 
	 * BST whose sum equals to x. You cannot use extra array to serialize the BST and do a
	 * 2Sum on it.
	 * 
	 * @logic First brute force is to compare each pair => O(n^2)
	 * 		  Second idea is to create an auxiliary array of inorder traversal of the tree
	 * 		  Third idea: first in place convert BST to a Doubly linked list and find pair in O(n). 
	 * 					  But this modifies the BST, space O(logn)
	 * 		  
	 * Final: O(n) time, O(logn) space without modifying the BST
	 * 		Same as finding the pair in sorted array. We traverse BST in Normal Inorder and Reverse Inorder simultaneously. 
	 * 		In reverse inorder, we start from the rightmost node which is the maximum value node. 
	 *      In normal inorder, we start from the left most node which is minimum value node. We add 
	 * sum of current nodes in both traversals and compare this sum with given target. 
	 * 
	 * (1) If the sum is same as target sum, we return true. 
	 * (2) If the sum is more than target sum, we move to next node in reverse inorder traversal, 
	 * (3) otherwise we move to next node in normal inorder traversal. 
	 * 
	 * If any of the traversals is finished without finding a pair, we return false.
	 */
	public boolean twoSumBST(TreeNode root, int target){
		if(root == null) return false;
		// Create two stacks. s1 is used for normal inorder traversal
	    // and s2 is used for reverse inorder traversal
		Stack<TreeNode> s1 = new Stack<TreeNode>();
		Stack<TreeNode> s2 = new Stack<TreeNode>();
		
		// done1, val1 and curr1 are used for normal inorder traversal using s1
	    // done2, val2 and curr2 are used for reverse inorder traversal using s2
	    boolean done1 = false, done2 = false;
	    int val1 = 0, val2 = 0;
		TreeNode cur1 = root, cur2 = root;
		
		// The loop will break when we either find a pair or one of the two
	    // traversals is complete
		while(true){
			//find the next node in the normal inorder traversal
			while(done1 == false){
				if(cur1 != null){
					s1.push(cur1);
					cur1 = cur1.left;
				}else{
					if(s1.isEmpty()){
						done1 = true;
					}else{
						cur1 = s1.pop();
						val1 = cur1.val;
						cur1 = cur1.right;
						done1 = true;
					}
				}
			}
			//find the next node in the reverse inorder traversal
			while(done2 == false){
				if(cur2 != null){
					s2.push(cur2);
					cur2 = cur2.right;
				}else{
					if(s2.isEmpty()){
						done2 = true;
					}else{
						cur2 = s2.pop();
						val2 = cur2.val;
						cur2 = cur2.left;
						done2 = true;
					}
				}
			}
			if(val1 != val2 && val1 + val2 == target){
				return true;
			}else if(val1 + val2 > target){
				done2 = false; // get the smaller value in the reverse traversal 
			}else if(val1 + val2 < target){
				done1 = false; // get the larger value in the normal traversal
			}
			
			// If any of the inorder traversals is over, then there is no pair
	        // so return false
			if(val1 >= val2) return false;
		}
	}
	
	/**
	 * Design and implement a TwoSum class. It should support the following operations:add and find.
	 * 
	 * add - Add the number to an internal data structure.
	 * find - Find if there exists any pair of numbers which sum is equal to the value.
	 * 
	 * For example,
	 * add(1); add(3); add(5);
	 * find(4) -> true
	 * find(7) -> false
	 * 
	 * [分析] HASH table O(N)存, O(1) 取 
	 */
	private HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();

    public void add(int number) {
    	if(map.containsKey(number)){
    		map.put(number, map.get(number) + 1);
    	}else{
    		map.put(number, 1);
    	}
    }

    public boolean find(int value) {
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            int i = entry.getKey();
            int j = value - i;
            if ((i == j && entry.getValue() > 1) || (i != j && map.containsKey(j))) {
                return true;
            }
        }
        return false;
    }
	
}
