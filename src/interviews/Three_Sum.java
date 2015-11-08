package interviews;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class Three_Sum {
	// brute-force method, O(n^3) time, constant space
	public int threeSum(int[] a, int t) {
		int n = a.length;
		int count = 0;
		for (int i = 0; i < n; i++) {
			for (int j = i + 1; j < n; j++) {
				for (int k = j + 1; k < n; k++) {
					if (a[i] + a[j] + a[k] == t) {
						count++;
					}
				}
			}
		}
		return count;
	}

	// HashMap method, O(n^2)
	public List<List<Integer>> threeSumHash(int[] num) {
		List<List<Integer>> res = new ArrayList<List<Integer>>();
		Arrays.sort(num);
		HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
		for (int i = 0; i < num.length; i++) {
			map.put(num[i], i);
		}
		for (int i = 0; i < num.length; i++) {
			if (i > 0 && num[i] == num[i - 1])
				continue;
			for (int j = i + 1; j < num.length; j++) {
				if (j > i + 1 && num[j] == num[j - 1])
					continue;
				if (map.containsKey(0 - num[i] - num[j])) {
					if (map.get(0 - num[i] - num[j]) > j) {
						List<Integer> list = new ArrayList<Integer>();
						list.add(num[i]);
						list.add(num[j]);
						list.add(0 - num[i] - num[j]);
						res.add(list);
					}
				}
			}
		}
		return res;
	}

	// traditional ways
	public List<List<Integer>> threeSum(int[] num) {
		List<List<Integer>> res = new ArrayList<List<Integer>>();
		int n = num.length;
		Arrays.sort(num);

		for (int i = 0; i < n - 2; i++) {
			if (i > 0 && num[i] == num[i - 1])
				continue;
			int j = i + 1;
			int k = n - 1;
			while (j < k) {
				if (j > i + 1 && num[j] == num[j - 1]) {
					j++;
					continue;
				}
				if (k < n - 1 && num[k] == num[k + 1]) {
					k--;
					continue;
				}
				if (num[i] + num[j] + num[k] == 0) {
					List<Integer> l = new ArrayList<Integer>();
					l.add(num[i]);
					l.add(num[j]);
					l.add(num[k]);
					res.add(l);
					j++;
					k--;
				} else if (num[i] + num[j] + num[k] > 0) {
					k--;
				} else {
					j++;
				}
			}
		}
		return res;
	}

	// backtracking : combination sum III
	public List<List<Integer>> threeSumBacktracking(int[] num) {
		List<List<Integer>> res = new ArrayList<List<Integer>>();
		int n = num.length;
		Arrays.sort(num);
		//helper(num, 3, 0, )
		
		return res;
	}
	/**
	 * Three sum in a BST, sum to zero.
	 * 
	 * @logic A Better Solution is to create an auxiliary array and store Inorder traversal of BST in the array. 
	 * 		  This solution works in O(n^2) time, but requires O(n) auxiliary space.
	 * 
	 *        Another idea: O(n^2) time and uses O(Logn) extra space: 
	 *        1) Convert given BST to Doubly Linked List (DLL) 
	 *        2) Now iterate through every node of DLL and if the key of node is negative, 
	 *        then find a pair in DLL with sum equal to key of current node multiplied by -1. 
	 *        To find the pair, we can use the approach used in hasArrayTwoCandidates().
	 */
	public boolean isTripletPresent(TreeNode root){
		if(null == root) return false;
		// Convert given BST to doubly linked list.  head and tail store the
	    // pointers to first and last nodes in DLL
		TreeNode head = null;
		TreeNode tail = null;
		
		convertBSTtoDLL(root, head, tail);
		// head should be the smallest since it's the leftmost, and should be negative to have a zero sum
		// iterate through every node after and find if there is a pair with sum equal to -1 * head.val
		while(head.right != tail && head.val < 0){
			// If there is a pair with sum equal to  -1*head->key, then return true 
			// otherwise move forward
			if(isPresentSum(head.right, tail, -1*head.val)){
				return true;
			}else{
				head = head.right;
			}
		}
		return false;
	}
	public boolean isPresentSum(TreeNode head, TreeNode tail, int sum){
		while(head != tail){
			if(head.val + tail.val == sum) return true;
			else if(head.val + tail.val < sum) head = head.right;
			else tail = tail.left;
		}
		return false;
	}
	public void convertBSTtoDLL(TreeNode root, TreeNode head, TreeNode tail){
		if(null == root) return;
		//First convert the left subtree
		if(root.left != null){
			convertBSTtoDLL(root.left, head, tail);
		}
		// Then change left of current root as last node of left subtree
		root.left = tail;
		// If tail is not NULL, then set right of tail as root, else current
	    // node is head
		if(tail != null){
			tail.right = root;
		}else{
			head = root;
		}
		//update tail
		tail = root;
		
		//update the right subtree
		if(root.right != null){
			convertBSTtoDLL(root.right, head, tail);
		}
	}
}
