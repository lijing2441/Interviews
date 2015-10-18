package interviews;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

public class BST_Level_Order_Traversal {
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
	public static void PrintLevel(TreeNode root) {
		helper(root, 0, 0);
	}

	public static void helper(TreeNode root, int level, int currentLevel) {
		if (root == null) {
			return;
		} else {
			if (currentLevel == level) {
				System.out.println(root.val + " in level: " + level + "");
//			} else {
				helper(root.left, level + 1, currentLevel + 1);
				helper(root.right, level + 1, currentLevel + 1);
			}
		}
	}

	public static void main(String[] args) {
		TreeNode root = new TreeNode(1);
		root.left = new TreeNode(2);
		root.right = new TreeNode(3);
		root.left.left = new TreeNode(4);
		root.left.right = new TreeNode(5);
		root.right.left = new TreeNode(6);
		root.right.right = new TreeNode(7);
		PrintLevel(root);
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
