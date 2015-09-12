package interviews;

import java.util.Stack;

public class Kth_Smallest_Element_In_BST {
	/**
	 * Given a binary search tree, write a function kthSmallest to find the kth smallest element in it.
	 */
	// first Binary Search
	public int kthSmallest(TreeNode root, int k) {
        int count = countNodes(root.left);
        if(k <= count) return kthSmallest(root.left, k);
        else if(k > count + 1) return kthSmallest(root.right, k - count - 1);
        else return root.val;
    }
    public int countNodes(TreeNode node) {
        if(node == null) return 0;
        else return 1 + countNodes(node.left) + countNodes(node.right);
    }
    
    // recursion
    public static int count = 0;
    public static int res = 0;
    
    public int kthSmallestRecur(TreeNode root, int k) {
        count = k;
        helper(root);
        return res;
    }
    public void helper(TreeNode root) {
        if(root.left != null) helper(root.left);
        count--;
        if(count == 0) {
            res = root.val;
            return;
        }
        if(root.right != null) helper(root.right);
    }
    
    // Iterative
    public int kthSmallestIte(TreeNode root, int k) {
        Stack<TreeNode> stack = new Stack<TreeNode>();
        while(root != null) {
            stack.push(root);
            root = root.left;
        }
        while(k > 0) {
            TreeNode cur = stack.pop();
            k--;
            if(k == 0) return cur.val;
            TreeNode next = cur.right;
            while(next != null) {
                stack.push(next);
                next = next.left;
            }
        }
        return -1;
    }
}
