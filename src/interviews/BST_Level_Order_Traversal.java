package interviews;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

public class BST_Level_Order_Traversal {
	// BFS, O(n) time, O(n) space
	public ArrayList<ArrayList<Integer>> levelOrder(TreeNode root) {
		ArrayList<ArrayList<Integer>> res = new ArrayList<ArrayList<Integer>>();
		//corner case
		if (null == root) return res;
		ArrayList<TreeNode> cur = new ArrayList<TreeNode>();
		cur.add(root);
		while (!cur.isEmpty() && cur.get(0) != null) {
			ArrayList<Integer> list = new ArrayList<Integer>();
			ArrayList<TreeNode> next = new ArrayList<TreeNode>();
			// get the next level nodes
			for (TreeNode n : cur) {
				list.add(n.val);
				if (n.left != null)
					next.add(n.left);
				if (n.right != null)
					next.add(n.right);
			}
			res.add(list); // if reversely, res.add(0, list), or before return, add Collections.reverse(res)
			// replace the cur node list with next level node list
			cur = next;
		}
		return res;
	}
	// use only one queue to do BFS,每层记下queue初始size即可
	public ArrayList<ArrayList<Integer>> levelOrderOneQueue(TreeNode root) {
        ArrayList<ArrayList<Integer>> res = new ArrayList<ArrayList<Integer>>();
        if (root == null) return res;
        Queue<TreeNode> q = new LinkedList<TreeNode>();
        q.offer(root);
        while (!q.isEmpty()) {
            ArrayList<Integer> list = new ArrayList<Integer>();
            int size = q.size();
            for (int i = 0; i < size; i++) {
                TreeNode node = q.poll();
                list.add(node.val);
                if (node.left != null) {
                    q.offer(node.left);
                }
                if (node.right != null) {
                    q.offer(node.right);
                }
            }
            res.add(list);
        }
        return res;
    }
	
	/**
	 * Print it out
	 */
	public void printByLevelOrder(TreeNode root) {
		if (null == root)
			return;
		ArrayList<TreeNode> cur = new ArrayList<TreeNode>();
		cur.add(root);
		while (!cur.isEmpty() && cur.get(0) != null) {
			ArrayList<TreeNode> next = new ArrayList<TreeNode>();
			for (TreeNode n : cur) {
				System.out.print(n.val + " ");
				if (n.left != null)
					next.add(n.left);
				if (n.right != null)
					next.add(n.right);
			}
			System.out.println();
			cur = next;
		}
	}
	// without using extra spaces except stack
	public void PrintLevel(TreeNode root, int level) {
		helper(root,level,0);
	}
	public void helper(TreeNode root,int level,int currentLevel){
		if(root == null){
			return;
		}else{
			if(currentLevel == level){
				System.out.print(root.val + " ");
			}else if(currentLevel < level){
				helper(root.left,level,currentLevel + 1);
				helper(root.right,level,currentLevel + 1);
			}else{
				return;
			}
			return;
		}
	}

	// DFS, using HashMap
	public ArrayList<ArrayList<Integer>> levelOrderDFS(TreeNode root) {
		ArrayList<ArrayList<Integer>> res = new ArrayList<ArrayList<Integer>>();
		HashMap<Integer, ArrayList<Integer>> map = new HashMap<Integer, ArrayList<Integer>>(); // key == levelOrder
		levelOrder(root, 1, map);

		for (int i = 0; i < map.size(); i++) {
			ArrayList<Integer> l = map.get(i + 1);
			res.add(l);
		}
		return res;
	}

	public void levelOrder(TreeNode node, int levelNum, HashMap<Integer, ArrayList<Integer>> map) {
		if (null == node)
			return;
		ArrayList<Integer> curList = null;
		if (map.containsKey(levelNum)) {
			curList = map.get(levelNum);
		} else {
			curList = new ArrayList<Integer>();
		}
		curList.add(node.val);
		map.put(levelNum, curList);
		levelOrder(node.left, levelNum + 1, map);
		levelOrder(node.right, levelNum + 1, map);
	}
}
