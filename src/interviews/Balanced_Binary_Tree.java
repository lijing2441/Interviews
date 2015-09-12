package interviews;

public class Balanced_Binary_Tree {
	/**
	 * Given a binary tree, determine if it is height-balanced.
	 * 
	 * For this problem, a height-balanced binary tree is defined as a binary
	 * tree in which the depth of the two subtrees of every node never differ by
	 * more than 1.
	 */
	public boolean isBalanced(TreeNode root) {
		if (root == null)
			return true;

		int heightDiff = Math.abs(height(root.left) - height(root.right));
		return (heightDiff <= 1 && isBalanced(root.left) && isBalanced(root.right));
	}

	public int height(TreeNode root) {
		if (root == null)
			return 0;
		else {
			return (1 + Math.max(height(root.left), height(root.right)));
		}
	}
	// optimized Method
	// can just check height, if diff > 1, return -1;
	public boolean isBalanced2(TreeNode root) {
		if (checkHeight(root) == -1)
			return false;
		else
			return true;
	}

	public int checkHeight(TreeNode root) {
		if (root == null)
			return 0;
		int leftHeight = checkHeight(root.left);
		if (leftHeight == -1)
			return -1;
		int rightHeight = checkHeight(root.right);
		if (rightHeight == -1)
			return -1;

		if (Math.abs(leftHeight - rightHeight) > 1)
			return -1;
		else
			return (1 + Math.max(leftHeight, rightHeight));

	}
}
