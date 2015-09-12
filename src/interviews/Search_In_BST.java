package interviews;

public class Search_In_BST {
	public TreeNode find(TreeNode root, int key) {
		if (null == root)
			return null;
		if (root.val == key)
			return root;
		else if (root.val > key)
			return find(root.left, key);
		else
			return find(root.right, key);
	}

	// iterative way
	public TreeNode findIte(TreeNode root, int key) {
		TreeNode node = root;
		while (node != null) {
			if (node.val == key)
				return node;
			else if (node.val > key)
				node = node.left;
			else
				node = node.right;
		}
		return node;
	}
	/**
	 * find closest node on a BST given a value
	 */
	public TreeNode findClosest(TreeNode root, int target){
		if(null == root) return null;
		int minDist = Integer.MAX_VALUE;
		TreeNode minNode = root;
		TreeNode node = root;
		while(node != null){
			if(node.val == target){
				return node;
			}else if(node.val > target){
				if(node.val - target < minDist){
					minDist = node.val - target;
					minNode = node;
				}
				node = node.left;
			}else{
				if(target - node.val < minDist){
					minDist = target - node.val;
					minNode = node;
				}
				node = node.right;
			}
		}
		return minNode;
	}
}
