package interviews;

import java.util.ArrayList;

public class Binary_Tree_Level_Order_Bottom_Up {
	/**
	 * Problem: 
	 * Given a binary tree, return the bottom-up level order traversal of its nodes' values. 
	 * (ie, from left to right, level by level from leaf to root).
			
			For example:
			Given binary tree {3,9,20,#,#,15,7},
			    3
			   / \
			  9  20
			    /  \
			   15   7
			return its bottom-up level order traversal as:
			[
			  [15,7],
			  [9,20],
			  [3]
			]
	 */
	public ArrayList<ArrayList<Integer>> levelOrderBottom(TreeNode root) {
        ArrayList<ArrayList<Integer>> res = new ArrayList<ArrayList<Integer>>();
        if(null == root) return res;
        ArrayList<TreeNode> cur = new ArrayList<TreeNode>();
        cur.add(root);
        while(!cur.isEmpty()){
            ArrayList<TreeNode> next = new ArrayList<TreeNode>();
            ArrayList<Integer> list = new ArrayList<Integer>();
            for(TreeNode node: cur){
                list.add(node.val);
                if(node.left != null) next.add(node.left);
                if(node.right != null) next.add(node.right);
            }
            res.add(0, list);
            cur = next;
        }
        return res;
    }
}
