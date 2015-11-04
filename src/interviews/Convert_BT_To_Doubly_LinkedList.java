package interviews;

import java.util.Stack;

public class Convert_BT_To_Doubly_LinkedList {
	// fix the left subtree first, go directly right until null
	// connect left subtree with root, and then do the right subtree
	public static TreeNode BT2DLL(TreeNode root) {
		if (root == null) return root;
		root = BT2DLLHelper(root);
		while (root.left != null) {
			root = root.left;
		}
		return root;
	}
	
	public static TreeNode BT2DLLHelper(TreeNode root) {
		if (root == null) {
			return null;
		}
		if (root.left != null) {
			TreeNode left = BT2DLLHelper(root.left);
			while (left.right != null) {
				left = left.right;
			}
			left.right = root;
			root.left = left;
		}
		if (root.right != null) {
			TreeNode right = BT2DLLHelper(root.right);
			while (right.left != null) {
				right = right.left;
			}
			right.left = root;
			root.right = right;
		}
		return root;
	}
	// iterative way, do an inorder traversal
	public static TreeNode BT2DLLIte(TreeNode root) {
		Stack<TreeNode> stack = new Stack<TreeNode>();
		TreeNode res = null;
		TreeNode cur = root;
		while (!stack.isEmpty() || cur != null) {
			if (cur != null) {
				stack.push(cur);
				cur = cur.left;
			} else {
				cur = stack.pop();
				if (res == null) {
					res = cur;
					res.left = null;
				} else {
					res.right = cur;
					cur.left = res;
					res = res.right;
				}
				cur = cur.right;
			}
		}
		res.right = null; // clear up
		// go back to the head of the list
		while (res.left != null) res = res.left;
		return res;
	}
	
	// driver
	public static void main(String[] args) {
		TreeNode root = new TreeNode(1);
		root.left = new TreeNode(2);
		root.right = new TreeNode(3);
		root.left.left = new TreeNode(4);
		root.left.right = new TreeNode(5);
		root.right.left = new TreeNode(6);
		root.right.right = new TreeNode(7);
		TreeNode res = BT2DLL(root);
		while (res.right != null) {
			System.out.println(res.val);
			res = res.right;
		}
		System.out.println(res.val);
		System.out.println();
		while (res.left != null) {
			System.out.println(res.val);
			res = res.left;
		}
		System.out.println(res.val);
	}
	
	
	
	
	
	
	
	
	
	
	
	/**
	 * Method 2: give the DLL a head and a tail
	 */
	public void convertBTtoDLL(TreeNode root, TreeNode head, TreeNode tail){
		if(null == root) return;
		//First convert the left subtree
		if(root.left != null){
			convertBTtoDLL(root.left, head, tail);
		}
		// Then change left of current root as last node of left subtree
		root.left = tail;
		// If tail is not NULL, then set right of tail as root, else current
	    // node is head
		if(tail != null){
			tail.right = root;
		}else{
			head = root;
		}
		//update tail
		tail = root;
		
		//update the right subtree
		if(root.right != null){
			convertBTtoDLL(root.right, head, tail);
		}
	}
}
