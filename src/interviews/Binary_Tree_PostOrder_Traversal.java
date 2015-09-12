package interviews;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Stack;

public class Binary_Tree_PostOrder_Traversal {
	// recursive
	public List<Integer> postorderTraversal(TreeNode root) {
		List<Integer> res = new ArrayList<Integer>();
		if (root == null)
			return res;
		return postorderTraversal(root, res);
	}

	public List<Integer> postorderTraversal(TreeNode root, List<Integer> res) {
		if (root != null) {
			if (root.left != null)
				postorderTraversal(root.left, res);
			if (root.right != null)
				postorderTraversal(root.right, res);
			res.add(root.val);
		}
		return res;
	}

	// iterative, using a hashset to store the result
	public List<Integer> postorderTraversalIte(TreeNode root) {
		List<Integer> res = new ArrayList<Integer>();
		if (root != null) {
			HashSet<TreeNode> visited = new HashSet<TreeNode>();
			Stack<TreeNode> stack = new Stack<TreeNode>();
			stack.push(root);
			while (!stack.isEmpty()) {
				TreeNode node = stack.peek();
				if ((node.left == null && node.right == null) || visited.contains(node.right) || visited.contains(root.left)) {
					stack.pop();
					res.add(node.val);
					visited.add(node);
				} else {
					if (node.right != null)
						stack.push(node.right);
					if (node.left != null)
						stack.push(node.left);
				}
			}
		}
		return res;
	}
}
