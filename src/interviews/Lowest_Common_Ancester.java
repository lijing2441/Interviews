package interviews;

public class Lowest_Common_Ancester {
	// given the root
	public TreeNode LCA(TreeNode root, TreeNode p, TreeNode q) {
		if (null == root)
			return null;
		if (root == p || root == q)
			return root;
		TreeNode left = LCA(root.left, p, q);
		TreeNode right = LCA(root.right, p, q);
		// if from both side we can find the LCA, the only common node is root
		if (left != null && right != null) {
			return root;
		} else {
			//only one side we can find LCA
			return left == null ? right : left;
		}
	}

	// given the parent node of each node, O(logn), O(1)
	public TreeNodeWithP LCA2(TreeNodeWithP p, TreeNodeWithP q) {
		int d_p = depth(p);
		int d_q = depth(q);
		if (d_p > d_q) {
			for (int i = 0; i < d_p - d_q; i++) {
				p = p.parent;
			}
		} else {
			for (int i = 0; i < d_q - d_p; i++) {
				q = q.parent;
			}
		}
		while (p != q) {
			p = p.parent;
			q = q.parent;
		}
		return p;
	}

	public int depth(TreeNodeWithP node) {
		int height = 0;
		while (node != null) {
			node = node.parent;
			height++;
		}
		return height;
	}
}
