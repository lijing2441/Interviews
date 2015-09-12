package interviews;

import java.util.Stack;

public class Maximal_Depth_Of_Binary_Tree {
	// recursive
	public int maxDepth(TreeNode root) {
		return maxDepth(root, 0);
	}

	public int maxDepth(TreeNode n, int depth) {
		if (n == null)
			return depth;
		else
			return Math.max(maxDepth(n.left, depth + 1), maxDepth(n.right, depth + 1));
	}

	// iterative, use another stack to store the depth of relative node
	// O(n), O(n)
	public int maxDepthI(TreeNode root) {
		if (root == null)
			return 0;
		Stack<TreeNode> stack = new Stack<TreeNode>();
		Stack<Integer> depthStack = new Stack<Integer>();
		stack.push(root);
		depthStack.push(1);
		int max = 0;
		while (!stack.isEmpty()) {
			TreeNode node = stack.pop();
			Integer d = depthStack.pop();
			max = Math.max(d, max);
			if (node.left != null) {
				stack.push(node.left);
				depthStack.push(1 + d);
			}
			if (node.right != null) {
				stack.push(node.right);
				depthStack.push(1 + d);
			}
		}
		return max;
	}

}
