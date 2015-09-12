package interviews;

public class Maximal_Path_Sum_In_Tree {
	/**
	 * Given a binary tree, find the maximum path sum.
	 * 
	 * The path may start and end at any node in the tree.
	 */
	// method 1: O(n^2) time, O(1) space
	public static int maxPath(TreeNode root) {
		/* base case where tree is empty */
		if (root == null)
			return 0;

		/* get the height of left and right sub-trees */
		int lheight = height(root.left);
		int rheight = height(root.right);

		/* get the diameter of left and right sub-trees */
		int ldiameter = maxPath(root.left);
		int rdiameter = maxPath(root.right);

		/*
		 * Return max of following three 1) Diameter of left subtree 2) Diameter
		 * of right subtree 3) Height of left subtree + height of right subtree
		 * + 1
		 */
		return Math.max(lheight + rheight + 1, Math.max(ldiameter, rdiameter));
	}

	public static int height(TreeNode node) {
		if (node == null) return 0;
		return 1 + Math.max(height(node.left), height(node.right));
	}

	/**
	 * Method II: O(n) time with extra space requirement, for each node, we do a constant amount of work. 
	 * It calculates height and diameter at the same time.
	 * 
	 * @return Since Java does not have pass-by-reference, an int[] is used to
	 *         return the result.
	 */

	public static int maxPathOptimized(TreeNode root) {
		return diameter(root)[0];
	}

	public static int[] diameter(TreeNode root) {
		int[] res = new int[] {0, 0}; // res[0] -> diameter; res[1] -> height
		if (null == root) return res;

		int[] leftDiameter = diameter(root.left);
		int[] rightDiameter = diameter(root.right);
		
		int height = Math.max(leftDiameter[1], rightDiameter[1]) + 1;
		//find the maximum among the three cases
		res[0] = Math.max(1 + leftDiameter[1] + rightDiameter[1], Math.max(leftDiameter[0], rightDiameter[0]));
		res[1] = height;
		
		return res;
	}

	// driver function
	public static void main(String[] args) {
		/*
		 * Constructed binary tree: 
		 * 							  1 
		 * 							 / \ 
		 * 							2   3 
		 *                         /     \ 
		 *                        4       5
		 */
		TreeNode root = new TreeNode(1);
		root.left = new TreeNode(2);
		root.right = new TreeNode(3);
		root.left.left = new TreeNode(4);
		root.left.right = new TreeNode(5);

		System.out.println("Diameter of the given binary tree is " + maxPathOptimized(root));
		System.out.println("The max path value of the given BT is " + maxPathSum(root));
	}

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
		/* base case where tree is empty */
		if (null == root) {
			return 0;
		}

		int leftSum = maxPathSum(root.left, max);
		int rightSum = maxPathSum(root.right, max);
		int localMax = Math.max(root.val, Math.max(root.val + leftSum, root.val + rightSum));
		
		if (localMax > max[0])
			max[0] = localMax;
		
		if (root.val + leftSum + rightSum > max[0])
			max[0] = root.val + leftSum + rightSum;
		
		return localMax;
	}
}
