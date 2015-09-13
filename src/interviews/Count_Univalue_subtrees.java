package interviews;

public class Count_Univalue_subtrees {
	/**
	 * Given a binary tree, count the number of uni-value subtrees.

A Uni-value subtree means all nodes of the subtree have the same value.

For example:
Given binary tree,
              5
             / \
            1   5
           / \   \
          5   5   5
return 4.
	 */
	public int res = 0;
    public int countUnivalSubtrees(TreeNode root) {
        //if(root == null) return 0;
        helper(root);
        return res;
    }
    public boolean helper(TreeNode root) {
        if(root == null) return false;
        if(root.left == null && root.right == null) {
            res++;
            return true;
        }
        if(root.left == null) {
            boolean rightRes = helper(root.right);
            if(rightRes && root.val == root.right.val) {
                res++;
                return true;
            } else return false;
        }
        if(root.right == null) {
            boolean leftRes = helper(root.left);
            if(leftRes && root.val == root.left.val) {
                res++;
                return true;
            } else return false;
        }
        
        boolean leftRes = helper(root.left);
        boolean rightRes = helper(root.right);
        if(leftRes && rightRes && root.val == root.left.val && root.val == root.right.val) {
            res++;
            return true;
        }
        return false;
    }
	
}
