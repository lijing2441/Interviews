package interviews;

import java.util.Stack;

public class Validate_BST {
	/**
	 * Given a binary tree, determine if it is a valid binary search tree (BST).
	 * 
	 * Assume a BST is defined as follows:
	 * 
	 * The left subtree of a node contains only nodes with keys less than the
	 * node's key. The right subtree of a node contains only nodes with keys
	 * greater than the node's key. Both the left and right subtrees must also
	 * be binary search trees.
	 */
	// iterative, using stack, in-order, O(n)
	public boolean isValidBST2(TreeNode root) {
		long prev = Long.MIN_VALUE; // to prevent overflow
		TreeNode cur = root;
		Stack<TreeNode> stack = new Stack<TreeNode>();
		while (!stack.isEmpty() || cur != null) {
			if (cur != null) {
				stack.push(cur);
				cur = cur.left;
			} else {
				cur = stack.pop();
				if (prev >= cur.val)
					return false;
				else
					prev = cur.val;
				cur = cur.right;
			}
		}
		return true;
	}

	// recursive, pre-order
	public boolean isValidBST(TreeNode root) {
		if (root == null)
			return true;
		TreeNode pre = null;
		TreeNode post = null;

		if (root.left != null) {
			pre = root.left;
			while (pre.right != null) {
				pre = pre.right;
			}
			if (pre.val >= root.val)
				return false;
		}
		if (root.right != null) {
			post = root.right;
			while (post.left != null) {
				post = post.left;
			}
			if (post.val <= root.val)
				return false;
		}
		return isValidBST(root.left) && isValidBST(root.right);
	}

	// Another recursion method
	public boolean isValidBST3(TreeNode root) {
		if (root == null)
			return true;
		if (root.left == null && root.right == null)
			return true;
		return helper(root, Integer.MAX_VALUE, Integer.MIN_VALUE);
	}

	public boolean helper(TreeNode root, int max, int min) {
		if (root == null)
			return true;
		if (root.val > max || root.val < min)
			return false;
		if (root.val == Integer.MAX_VALUE && root.right != null)
			return false;
		if (root.val == Integer.MIN_VALUE && root.left != null)
			return false;
		return helper(root.left, root.val - 1, min)
				&& helper(root.right, max, root.val + 1);
	}

}
