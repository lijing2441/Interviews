package interviews;

import java.util.Stack;

public class Invert_Binary_Tree {
	/**
	 *   1         1
 		/ \       / \
	   2   3  => 3   2
   	      /       \
  		 4         4
	 */
	// recursive
	/**
     * @param root: a TreeNode, the root of the binary tree
     * @return: nothing
     */
    public void invertBinaryTree(TreeNode root) {
        // write your code here
        if(root == null || (root.left == null && root.right == null)) return;
        invertBinaryTree(root.right);
        invertBinaryTree(root.left);
        TreeNode tmpLeft = root.left;
        root.left = root.right;
        root.right = tmpLeft;
    }
    // iterative
    public void invertBinaryTreeIte(TreeNode root) {
        // write your code here
        if(root == null) return;
        Stack<TreeNode> stack = new Stack<TreeNode>();
        stack.push(root);
        while(!stack.isEmpty()) {
            TreeNode node = stack.pop();
            if(node.left == null && node.right == null) continue;
            TreeNode tmp = node.left;
            node.left = node.right;
            node.right = tmp;
            if(node.left != null) stack.push(node.left);
            if(node.right != null) stack.push(node.right);
        }
    }
}
