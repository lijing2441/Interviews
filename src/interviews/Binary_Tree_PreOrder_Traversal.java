package interviews;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Binary_Tree_PreOrder_Traversal {
	/**
	 * Given a binary tree, return the preorder traversal of its nodes' values.
		
		For example:
		Given binary tree {1,#,2,3},
		   1
		    \
		     2
		    /
		   3
		return [1,2,3].
	 */
	// recursive
	public List<Integer> preorderTraversal(TreeNode root) {
		List<Integer> list = new ArrayList<Integer>();
		if (root == null)
			return list;
		return preorderTraversal(root, list);
	}

	public List<Integer> preorderTraversal(TreeNode root, List<Integer> list) {
		if (root != null) {
			list.add(root.val);
			preorderTraversal(root.left, list);
			preorderTraversal(root.right, list);
		}
		return list;
	}

	// iterative
	public List<Integer> preorderTraversalIte(TreeNode root) {
		List<Integer> list = new ArrayList<Integer>();
		if (root == null)
			return list;

		Stack<TreeNode> stack = new Stack<TreeNode>();
		stack.push(root);
		while (!stack.isEmpty()) {
			TreeNode node = stack.pop();
			list.add(node.val);
			if (node.right != null)
				stack.push(node.right);
			if (node.left != null)
				stack.push(node.left);
		}
		return list;
	}
}
