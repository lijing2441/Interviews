package interviews;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Binary_Tree_Inorder_Traversal {
	/**
	 * Given a binary tree, return the inorder traversal of its nodes' values.
	 * For example: Given binary tree {1,#,2,3},
   	 *	1
     *	 \
     *	  2
     *	 /
   	 *	3
	 *	return [1,3,2].
	 * 
	 */
	// recursive, O(n), O(n)
	public List<Integer> inorderTraversal(TreeNode root) {
		List<Integer> list = new ArrayList<Integer>();
		if (root == null)
			return list;
		return inorderTraversal(root, list);
	}

	public List<Integer> inorderTraversal(TreeNode root, List<Integer> list) {
		if (root != null) {
			inorderTraversal(root.left, list);
			list.add(root.val);
			inorderTraversal(root.right, list);
		}
		return list;
	}

	// iterative way, go down to the last left, then root, then jump to the right
	public List<Integer> inorderTraversalIte(TreeNode root) {
		List<Integer> list = new ArrayList<Integer>();
		if (root == null)
			return list;

		Stack<TreeNode> stack = new Stack<TreeNode>();
		TreeNode cur = root;
		while (!stack.isEmpty() || cur != null) {
			if (cur != null) {
				stack.push(cur);
				cur = cur.left;
			} else {
				cur = stack.pop();
				list.add(cur.val);
				cur = cur.right;
			}
		}
		return list;
	}

}