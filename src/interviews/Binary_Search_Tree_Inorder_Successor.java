package interviews;

import java.util.ArrayList;
import java.util.List;
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
	public TreeNode solution2(TreeNode root, TreeNode p) {
		List<TreeNode> stack = new ArrayList<>();
	    TreeNode node = root;
	    while (true) {
	    	// 先找到那个node
	        if (p.val < node.val) {
	            stack.add(node);
	            node = node.left;
	        } else if (p.val > node.val) {
	            // stack.add(node);
	            node = node.right;
	        } else {
	        	// 找到node后，开始找successor
	        	// 如果node右子节点为null，他的succssor为他的parent
	            if (node.right == null) {
	                // Your case one and case two can combined to this.
	                if (stack.isEmpty()) {
	                   return null;
	                } else {
	                    return stack.remove(stack.size() - 1);
	                }
	            } else {
	                // Right Sub Tree Case
	            	// 右子树不为空，找右子树的最左节点
	                node = node.right;
	                while (node.left != null) {
	                    node = node.left;
	                }
	                return node;
	            }
	        }
	    }
	}
}
