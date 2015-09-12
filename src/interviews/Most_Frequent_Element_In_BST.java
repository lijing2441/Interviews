package interviews;

import java.util.ArrayList;
import java.util.Stack;

public class Most_Frequent_Element_In_BST {
	/**
	 * Problem: find the most frequent element in a BST
	 * 
	 * @logic This problem is equivalent to finding the most frequent element in a sorted array:
	 * 
	 * -> Start the counter at zero
	 * -> iterate the tree in-order promises you it is in increasing manner
	 * -> Increment the counter while the current element is equal to the prior one
	 * -> When you find a different element, compare the counter to the current best run; replace if necessary
	 * -> Continue to the next element
	 * 
	 * * you can also check every node's left children with same value stored, since left <= cur < right
	 * 
	 * O(logn) - O(n) depending on whether the BST is balanced or not
	 */
	// this return the maximum repeated element
	// it can also return the times it's repeated in the tree
	public int findMostFrequent(TreeNode root){
		if(null == root) return -1;
		ArrayList<Integer> list = new ArrayList<Integer>();
		inorder(root, list);
		int maxLen = 1;
		int maxNode = list.get(0);
		int curLen = 1;
		int curNode = list.get(0);
		for(int i = 1; i < list.size(); i++){
			if(list.get(i) == list.get(i - 1)){
				curLen++;
			}else{
				curLen = 1;
				curNode = list.get(i);
			}
			if(curLen > maxLen && curNode != maxNode){
				maxLen = curLen;
				maxNode = curNode;
			}
		}
		return maxNode;
	}
	public void inorder(TreeNode root, ArrayList<Integer> list){
		if(root == null) return;
		Stack<TreeNode> stack = new Stack<TreeNode>();
		TreeNode cur = root;
		while(!stack.isEmpty() || cur != null){
			if(cur != null){
				stack.push(cur);
				cur = cur.left;
			}else{
				cur = stack.pop();
				list.add(cur.val);
				cur = cur.right;
			}
		}
	}
}
