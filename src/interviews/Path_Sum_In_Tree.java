package interviews;

import java.util.ArrayList;

public class Path_Sum_In_Tree {
	/**
	 * Given a binary tree and a sum, determine if the tree has a root-to-leaf
	 * path such that adding up all the values along the path equals the given sum.
	 * 
	 * @complexity O(n), O(1)
	 **/
	public boolean hasPathSum(TreeNode root, int sum) {
		if (root == null)
			return false;
		int cur = 0;
		return hasPathSum(root, sum, cur);
	}

	public boolean hasPathSum(TreeNode node, int sum, int cur) {
		if (node.left == null && node.right == null) {
			return sum == (cur + node.val);
		} else {
			if (node.left != null) {
				boolean leftRes = hasPathSum(node.left, sum, (cur + node.val));
				if (leftRes)
					return leftRes;
			}
			if (node.right != null) {
				boolean rightRes = hasPathSum(node.right, sum, (cur + node.val));
				if (rightRes)
					return rightRes;
			}
			return false;
		}
	}

	/**
	 * Problem II: if the paths need to be returned
	 * 
	 * @complexity
	 * (1) visit each node constant times
	 * (2) the amortized time of add/remove an item on an ArrayList is O(1)
	 * (3) making a copy for each result costs at most O(logn).
	 * 
	 * So, the worst-case running time and space usage could be O(nlogn), 
	 * where we have a full binary tree and each root-to-leaf path is eligible to be a valid result.
	 */
	public ArrayList<ArrayList<Integer>> pathSum(TreeNode root, int sum) {
		ArrayList<ArrayList<Integer>> res = new ArrayList<ArrayList<Integer>>();
		ArrayList<Integer> path = new ArrayList<Integer>();
		if (root != null) {
			addToRes(root, sum, 0, res, path);
		}
		return res;
	}

	public void addToRes(TreeNode root, int sum, int val, ArrayList<ArrayList<Integer>> res, ArrayList<Integer> path) {

		int curSum = val + root.val;
		// if we have arrived in the final goal and the current node is leaf node
		if (sum == curSum && root.left == null && root.right == null) {
			path.add(root.val);
			res.add(path);
		}
		//left is not leaf
		if (root.left != null) {
			ArrayList<Integer> leftPath = new ArrayList<Integer>();
			leftPath.addAll(path);
			leftPath.add(root.val);
			addToRes(root.left, sum, curSum, res, leftPath);
		}
		//right is not leaf
		if (root.right != null) {
			ArrayList<Integer> rightPath = new ArrayList<Integer>();
			rightPath.addAll(path);
			rightPath.add(root.val);
			addToRes(root.right, sum, curSum, res, rightPath);
		}
	}
	
	/**
	 * Follow-up: the path does not need to start or end in a root or a leaf, 
	 * 			  but must go a straight line down
	 * 
	 * @logic on each node, we recursively look up to see if we've found the sum until the current node.
	 */
	public ArrayList<ArrayList<Integer>> findSum(TreeNode head, int target){
		int depth = depth(head); //logn
		int[] path = new int[depth];
		ArrayList<ArrayList<Integer>> res = new ArrayList<ArrayList<Integer>>();
 		findSum(head, target, path, 0, res);
		return res;
	}
	
	public void findSum(TreeNode node, int target, int[] path, int level, ArrayList<ArrayList<Integer>> res){
		if(node == null) return;
		
		//insert current node into path
		path[level] = node.val;
		
		//look for the path with the target sum ends at the current node
		int cur = 0;
		for(int i = level; i >= 0; i--){
			cur += path[i];
			if(cur == target){
				ArrayList<Integer> curRes = getPath(path, i, level);
				res.add(curRes);
			}
		}
		//search below
		findSum(node.left, target, path, level + 1, res);
		findSum(node.right, target, path, level + 1, res);
	}
	
	public ArrayList<Integer> getPath(int[] path, int start, int end){
		ArrayList<Integer> list = new ArrayList<Integer>();
		for(int i = start; i <= end; i++){
			list.add(path[i]);
		}
		return list;
	}
	
	public int depth(TreeNode node){
		if(node == null) return 0;
		else return 1 + Math.max(depth(node.left), depth(node.right));
	}
}
