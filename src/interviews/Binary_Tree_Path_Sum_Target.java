package interviews;

import java.util.ArrayList;
import java.util.List;

public class Binary_Tree_Path_Sum_Target {
	public List<Integer> findSumPath(TreeNode root, int target) {
		List<Integer> treeSums = new ArrayList<Integer>();
		// 加本节点的value
		treeSums.add(root.val);
		// 加左子树节点的value
		List<Integer> leftSums = null;
		if (root.left != null) {
			leftSums = findSumPath(root.left, target);
			for (int l : leftSums) {
				treeSums.add(root.val + l);
			}
		}
		// 加右子树节点的value
		List<Integer> rightSums = null;
		if (root.right != null) {
			rightSums = findSumPath(root.right, target);
			for (int r : rightSums) {
				treeSums.add(root.val + r);
			}
		}
		if (treeSums.contains(target)) {
			System.out.println("Target exists in the tree");
		}
		// check if we can find a path going through this node and both children
		if (root.left != null && root.right != null) {
			for (int l : leftSums) {
				if (rightSums.contains(target - l - root.val)) {
					System.out.println("Target exists in the tree");
				}
			}
		}
		return treeSums;
	}
}
