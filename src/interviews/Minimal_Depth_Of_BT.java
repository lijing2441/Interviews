package interviews;

import java.util.LinkedList;
import java.util.Queue;

public class Minimal_Depth_Of_BT {
	// recursive method
	public int minDepth(TreeNode root) {
		if (root == null)
			return 0;
		if (root.left == null && root.right == null)
			return 1; // base case of the leaf node

		int left = root.left != null ? minDepth(root.left) : Integer.MAX_VALUE;
		int right = root.right != null ? minDepth(root.right) : Integer.MAX_VALUE;

		return Math.min(left, right) + 1; // remember to plus one here!!!!
	}

	// iterative method
	public int minDepthI(TreeNode root) {
		if (null == root)
			return 0;
		Queue<TreeNode> nodeQ = new LinkedList<TreeNode>();
		Queue<Integer> depthQ = new LinkedList<Integer>();
		nodeQ.add(root);
		depthQ.add(1);
		while (!nodeQ.isEmpty()) {
			TreeNode node = nodeQ.poll();
			Integer depth = depthQ.poll();
			if (node.left == null && node.right == null)
				return depth;
			if (node.left != null) {
				nodeQ.add(node.left);
				depthQ.add(depth + 1);
			}
			if (node.right != null) {
				nodeQ.add(node.right);
				depthQ.add(depth + 1);
			}
		}
		return -1;
	}
}
