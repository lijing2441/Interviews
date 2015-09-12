package interviews;

public class Count_Complete_Tree_Nodes {
	/**
	 * Given a complete binary tree, count the number of nodes.
	 */
	public int countNodes(TreeNode root) {
        if(root == null) return 0;
        return countNodes(root, 0);
    }
    
    public int countNodes(TreeNode root, int curSum) {
        int leftHeight = getLeftHeight(root);
        int rightHeight = getRightHeight(root);
        if(leftHeight == rightHeight) {
            return curSum + (1 << leftHeight) - 1;
        }
        if(getRightHeight(root.left) + 1 == leftHeight) {
            return countNodes(root.right, curSum + (1 << (leftHeight - 1)));
        } else {
            return countNodes(root.left, curSum + (1 << (rightHeight - 1)));
        }
    }
    public int getLeftHeight(TreeNode node) {
        int res = 0;
        for(TreeNode n = node; n != null; n = n.left) {
            res++;
        }
        return res;
    }
    public int getRightHeight(TreeNode node) {
        int res = 0;
        for(TreeNode n = node; n != null; n = n.right) {
            res++;
        }
        return res;
    }
}	
