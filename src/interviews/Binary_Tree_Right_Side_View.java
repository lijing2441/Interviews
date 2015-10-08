package interviews;

import java.util.ArrayList;
import java.util.List;

public class Binary_Tree_Right_Side_View {
	/**
	 * Given a binary tree, imagine yourself standing on the right side of it, return the values of the nodes you can see ordered from top to bottom.

			For example:
			Given the following binary tree,
			   1            <---
			 /   \
			2     3         <---
			 \     \
			  5     4       <---
			You should return [1, 3, 4].
	 *
	 *
	 * @logic 利用size，只有size对，才加入result list。recursion先加右边（这样在右children存在的情况下，不会加左边）；
	 * 		  如果右边没有，会加上最右的node
	 */
	public List<Integer> res = new ArrayList<Integer>();
    public List<Integer> rightSideView(TreeNode root) {
        if(root == null) return res;
        helper(root, 0);
        return res;
    }
    public void helper(TreeNode root, int depth) {
        if(depth == res.size()) {
            res.add(root.val);
        }
        if(root.right != null) {
            helper(root.right, depth + 1);
        }
        if(root.left != null) {
            helper(root.left, depth + 1);
        }
    }
}
