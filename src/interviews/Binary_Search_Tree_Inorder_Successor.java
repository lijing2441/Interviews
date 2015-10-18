package interviews;

import java.util.Stack;

public class Binary_Search_Tree_Inorder_Successor {
	/**
	 * Given a binary search tree and a node in it, find the in-order successor
	 * of that node in the BST.
	 * 
	 * Note: If the given node has no in-order successor in the tree, return null.
	 */
	public TreeNode inorderSuccessor(TreeNode root, TreeNode p) {
        if (root == null) return null;
        Stack<TreeNode> stack = new Stack<TreeNode>();
        TreeNode cur = root;
        boolean lastP = false;
        while (cur != null || !stack.isEmpty()) {
            if (cur != null) {
                stack.push(cur);
                cur = cur.left;
            } else {
                cur = stack.pop();
                if (lastP) return cur;
                if (cur == p) {
                    lastP = true;
                }
                cur = cur.right;
            }
        }
        return null;
    }
	
	// 利用bst的特性, if balanced => can be logn, not => still O(n)
	public TreeNode inorderSuccessorIte(TreeNode root, TreeNode p) {
        if (root == null) return null;
        Stack<TreeNode> stack = new Stack<TreeNode>();
	    TreeNode node = root;
	    while (!stack.isEmpty() || node != null) {
	        if (p.val < node.val) {
	            stack.push(node);
	            node = node.left;
	        } else if (p.val > node.val) {
	            node = node.right;
	        } else {
	            if (node.right == null) {
	                if (stack.isEmpty()) {
	                   return null;
	                } else {
	                    return stack.pop();
	                }
	            } else {
	                node = node.right;
	                while (node.left != null) {
	                    node = node.left;
	                }
	                return node;
	            }
	        }
	    }
	    return null;
    }
}
