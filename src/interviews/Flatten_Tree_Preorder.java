package interviews;

import java.util.Stack;

public class Flatten_Tree_Preorder {
	public void flatten(TreeNode root) {
		// O(n), O(n)
		// singly LL, left to null, while right to the pre-order ancestor
		if (root == null) return;
		
		TreeNode dummy = null;
		Stack<TreeNode> stack = new Stack<TreeNode>();
		stack.push(root);
		while (!stack.isEmpty()) {
			TreeNode node = stack.pop();

			if (node.right != null)
				stack.push(node.right);
			if (node.left != null)
				stack.push(node.left);

			if (dummy != null) {
				dummy.right = node;
				dummy.left = null;
			}
			dummy = node;
		}
	}
	
	/**
	 * recursion
	 */
	public void flatten1(TreeNode root) {
        if(root == null || (root.left == null && root.right == null)) return;
        flatten1(root.left);
        flatten1(root.right);
        
        TreeNode right = root.right;
        root.right = root.left;
        root.left = null;
        while(root.right != null) {
            root = root.right;
        }
        root.right = right;
    }
}
