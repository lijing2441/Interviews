package interviews;

import java.util.Stack;

public class Symmetry_Tree {
	// recursive way
	public boolean isSymmetric(TreeNode root) {
		if (root == null)
			return true;
		return helper(root.left, root.right);
	}

	public boolean helper(TreeNode left, TreeNode right) {
		if (left == null && right == null)
			return true;
		else if (left == null || right == null)
			return false;
		else if (left.val != right.val)
			return false;
		else
			return (helper(left.left, right.right) && helper(left.right, right.left));
	}

	// iterative way
	public boolean isSymmetric_Ite(TreeNode root) {
		if (root == null)
			return true;
		Stack<TreeNode> leftStack = new Stack<TreeNode>();
		Stack<TreeNode> rightStack = new Stack<TreeNode>();

		leftStack.push(root.left);
		rightStack.push(root.right);
		while (!leftStack.isEmpty() && !rightStack.isEmpty()) {
			TreeNode nLeft = leftStack.pop();
			TreeNode nRight = rightStack.pop();
			// find a leaf
			if (nLeft == null && nRight == null)
				continue;
			// one of them is leaf, return false
			if (nLeft == null || nRight == null)
				return false;
			
			if (nLeft.val != nRight.val)
				return false;
			// continue to their children
			leftStack.push(nLeft.left);
			rightStack.push(nRight.right);
			leftStack.push(nLeft.right);
			rightStack.push(nRight.left);
		}
		return true;
	}

	// same tree?
	public boolean isSameTree(TreeNode p, TreeNode q) {
		if (p == null && q == null)
			return true;
		else if (p == null || q == null)
			return false;

		boolean isLeftSame = isSameTree(p.left, q.left);
		boolean isRightSame = isSameTree(p.right, q.right);

		return ((p.val == q.val) && isLeftSame && isRightSame);
	}
}
