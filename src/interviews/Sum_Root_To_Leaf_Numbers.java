package interviews;

import java.util.*;

//The root-to-leaf path 1->2 represents the number 12.
//The root-to-leaf path 1->3 represents the number 13.
//Return the sum = 12 + 13 = 25.

public class Sum_Root_To_Leaf_Numbers {
	public int sumNumbers(TreeNode root) {
		if (root == null)
			return 0;
		int total = 0;
		Queue<TreeNode> q = new LinkedList<TreeNode>();
		Queue<Integer> sumQ = new LinkedList<Integer>();

		q.add(root);
		sumQ.add(root.val);
		while (!q.isEmpty()) {
			TreeNode node = q.poll();
			int nodeVal = sumQ.poll();
			//find a leaf, add it to the total
			if (node.left == null && node.right == null)
				total += nodeVal;
			// proceed if not leaf
			if (node.left != null) {
				q.add(node.left);
				sumQ.add(10 * nodeVal + node.left.val);
			}
			if (node.right != null) {
				q.add(node.right);
				sumQ.add(10 * nodeVal + node.right.val);
			}
		}
		return total;
	}
}
