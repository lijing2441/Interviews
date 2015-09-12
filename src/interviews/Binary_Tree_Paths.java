package interviews;

import java.util.ArrayList;
import java.util.List;

public class Binary_Tree_Paths {
	/**
	 * Given a binary tree, return all root-to-leaf paths. For example, given
	 * the following binary tree:
	 * 
	 *   1 
	 *  / \ 
	 * 2   3 
	 *  \ 
	 * 	 5
	 * 
	 * All root-to-leaf paths are:
	 * 
	 * ["1->2->5", "1->3"]
	 */
	public List<String> binaryTreePaths(TreeNode root) {
        List<String> res = new ArrayList<String>();
        if(root == null) return res;
        helper(res, root, "");
        return res;
    }
    public void helper(List<String> res, TreeNode root, String cur) {
        String next = "";
        if(cur == "") next = Integer.toString(root.val);
        else next = cur + "->" + Integer.toString(root.val);
        if(root.left == null && root.right == null) {
            res.add(new String(next));
            return;
        } else {
            if(root.left != null) helper(res, root.left, next);
            if(root.right != null) helper(res, root.right, next);
        }
    }
}
