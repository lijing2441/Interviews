package interviews;

import java.util.ArrayList;
import java.util.HashMap;

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
	
	/**
	 * Problem II: 
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
