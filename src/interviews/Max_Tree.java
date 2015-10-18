package interviews;

import java.util.Stack;

public class Max_Tree {
	/**
	 * Given an integer array with no duplicates. A max tree building on this array is defined as follow:
	 * 
	 * The root is the maximum number in the array
	 * The left subtree and right subtree are the max trees of the subarray divided by the root number.
	 * Construct the max tree by the given array.
	 * 
	 * Given [2, 5, 6, 0, 3, 1], the max tree constructed by this array is:

    	6
   	   / \
  	  5   3
 	 /   / \
	2   0   1
	 */
	public TreeNode maxTree(int[] A) {
        if (A == null || A.length == 0) return null;
        int len = A.length;
        if (len == 1) return new TreeNode(A[0]);
        Stack<TreeNode> stack = new Stack<TreeNode>(); // 维持一个单调递减的stack
        for (int i = 0; i <= len; i++) {
        	// 设置一个最右最大节点做dummy
            TreeNode right = i < len? new TreeNode(A[i]) : new TreeNode(Integer.MAX_VALUE);
            // 如果stack内的元素比当前元素小，需要pop使之成为当前node的左节点
            while (!stack.isEmpty() && right.val > stack.peek().val) {
                TreeNode node = stack.pop();
                if (stack.isEmpty()) {
                    right.left = node;
                } else {
                	// 还要进一步检查，如果后面的node还小于right，使node成为当前node的右节点，下一步还需要pop
                	// 如果比当前节点大，就可以把node成为当前node的左节点，然后当前元素压栈
                    TreeNode left = stack.peek();
                    if (left.val > right.val) {
                        right.left = node;
                    } else {
                        left.right = node;
                    }
                }
            }
            stack.push(right);
        }
        // 最后一个node (人工设置的infinite) 的左节点即为root
        return stack.peek().left;
    }
	
}
