package interviews;

public class Yahoo_Deepest_Node_In_Tree {
	/**
	 * The deepest left leaf node is the node with value 9.

       1
     /   \
    2     3
  /      /  \  
 4      5    6
        \     \
         7     8
        /       \
       9         10
	 */
	static TreeNode result;
	public static TreeNode deepestLeftLeaf(TreeNode root) {
		int[] maxLevel = new int[1];
		result = null;
		deepestLeftHelper(root, 0, maxLevel, false);
		return result;
	}
	public static void deepestLeftHelper(TreeNode root, int level, int[] maxLevel, boolean isLeft) {
		if (null == root) return;
		if (isLeft && root.left == null && root.right == null && level > maxLevel[0]) {
			result = root;
			maxLevel[0] = level;
			return;
		}
		deepestLeftHelper(root.left, level + 1, maxLevel, true);
		deepestLeftHelper(root.right, level + 1, maxLevel, false);
	}
	public static void main(String[] args) {
		TreeNode root = new TreeNode(1);
	    root.left = new TreeNode(2);
	    root.right = new TreeNode(3);
	    root.left.left = new TreeNode(4);
	    root.right.left = new TreeNode(5);
	    root.right.right = new TreeNode(6);
	    root.right.left.right = new TreeNode(7);
	    root.right.right.right = new TreeNode(8);
	    root.right.left.right.left = new TreeNode(9);
	    root.right.right.right.right = new TreeNode(10);
	    TreeNode result = deepestLeftLeaf(root);
	    System.out.println(result.val);
	}
}
