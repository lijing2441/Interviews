package interviews;

public class Binary_Tree_Maximal_Path_Sum {
	/**
	 * max path considering the value stored in the node
	 * 
	 * localMax 记录左枝，或右枝，或root自己，而globalMax为真正的from any node to any node
	 * 
	 * O(n) time
	 */
	public static int maxPathSum(TreeNode root) {
		int[] max = new int[1];
		max[0] = Integer.MIN_VALUE;
		maxPathSum(root, max);
		return max[0];
	}

	public static int maxPathSum(TreeNode root, int[] max) {
		if (null == root) return 0;

		int leftSum = maxPathSum(root.left, max);
		int rightSum = maxPathSum(root.right, max);
		int localMax = Math.max(root.val, Math.max(root.val + leftSum, root.val + rightSum));
		
		if (localMax > max[0]) max[0] = localMax;
		
		if (root.val + leftSum + rightSum > max[0])
			max[0] = root.val + leftSum + rightSum;
		
		return localMax;
	}
	
	// driver function
		public static void main(String[] args) {
			/*
			 * Constructed binary tree: 
			 * 							  1 
			 * 							 / \ 
			 * 							2   3 
			 *                         / \ 
			 *                        4   5
			 */
			TreeNode root = new TreeNode(1);
			root.left = new TreeNode(2);
			root.right = new TreeNode(3);
			root.left.left = new TreeNode(4);
			root.left.right = new TreeNode(5);

			System.out.println("The max path value of the given BT is " + maxPathSum(root));
		}
}
