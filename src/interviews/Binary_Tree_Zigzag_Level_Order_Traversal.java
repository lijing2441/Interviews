package interviews;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Binary_Tree_Zigzag_Level_Order_Traversal {
	/**
	 * Modified Version: Zigzag traversal
	 * @analysis, if even, add it to the tail; if odd, add the level to the head
	 */
	public ArrayList<ArrayList<Integer>> zigzagLevelOrder(TreeNode root) {
		ArrayList<ArrayList<Integer>> res = new ArrayList<ArrayList<Integer>>();
		zigLevelOrder(root, res, 0);
		return res;
	}

	public void zigLevelOrder(TreeNode root, ArrayList<ArrayList<Integer>> res, int index) {
		if (null == root)
			return;

		ArrayList<Integer> cur = null;
		if (res.size() > index) {
			cur = res.get(index);
		} else {
			cur = new ArrayList<Integer>();
			res.add(cur);
		}
		
		// this is the difference to make this zigzag
		if (index % 2 == 0) 
			cur.add(root.val);
		else
			cur.add(0, root.val);

		zigLevelOrder(root.left, res, index + 1);
		zigLevelOrder(root.right, res, index + 1);
	}
	// Iterative way
	public List<List<Integer>> zigzagLevelOrderIte(TreeNode root) {
        List<List<Integer>> res = new ArrayList<List<Integer>>();
        if(root == null) return res;
        Queue<TreeNode> queue = new LinkedList<TreeNode>();
        queue.offer(root);
        int depth = 0;
        while(!queue.isEmpty()) {
            List<Integer> cur = new ArrayList<Integer>();
            int size = queue.size();
            for(int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                if(depth % 2 == 0) {
                    cur.add(node.val);
                } else {
                    cur.add(0, node.val);
                }
                if(node.left != null) queue.offer(node.left);
                if(node.right != null) queue.offer(node.right);
            }
            res.add(cur);
            depth++;
        }
        return res;
    }
}
