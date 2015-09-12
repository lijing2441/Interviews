package interviews;

public class isCousin_In_Tree {
	/**
	 * Given the binary Tree and the two nodes say ‘a’ and ‘b’, determine
	 * whether the two nodes are cousins of each other or not.
	 * 
	 * Two nodes are cousins of each other if they are at same level and have
	 * different parents.
	 * 
	 * @analysis check the depth of the two nodes first, if at the same level,
	 * 			 check whether they are sibling or not. If not sibling and at the same level, true.
	 * Time Complexity is O(n) as it does at most three traversals of binary tree.
	 */
	public static boolean isCousin(TreeNode root, TreeNode node1, TreeNode node2){
		if(node1 == null || node2 == null) return false;
		// find the level of the two nodes
		int level1 = findLevel(root, node1, 0);
		int level2 = findLevel(root, node2, 0);
		// if they are not at the same level, false
		if(level1 != level2) return false;
		// as long as they are not sibling, they are cousin
		if(!isSibling(root, node1, node2)) return true;
		return false;
	}
	//O(logn)
	public static int findLevel(TreeNode root, TreeNode node, int level){
		if(root == null || node == null) return 0;
		if(root == node) return level;
		int l = findLevel(root.left, node, level + 1);
		if(l != 0) return l;
		return findLevel(root.right, node, level + 1);
	}
	
	public static boolean isSibling(TreeNode root, TreeNode n1, TreeNode n2){
		if(root == null || n1 == null || n2 == null) return false;
		if((root.left == n1 && root.right == n2) || (root.left == n2 && root.right == n1)) return true;
		return isSibling(root.left, n1, n2) || isSibling(root.right, n1, n2);
	}
	
	//Driver
	public static void main(String[] args){
		TreeNode root = new TreeNode(1);
		root.left = new TreeNode(2);
		root.right = new TreeNode(3);
		root.left.left = new TreeNode(4);
		root.left.right = new TreeNode(5);
		root.right.left = new TreeNode(6);
		root.right.right = new TreeNode(7);
		root.left.right.right = new TreeNode(8);
		root.right.left.right = new TreeNode(15);
		
		TreeNode n1 = root.left.left;
		TreeNode n2 = root.right.right;
		if(isCousin(root, n1, n2)){
			System.out.println("yes");
		}
	}
}
