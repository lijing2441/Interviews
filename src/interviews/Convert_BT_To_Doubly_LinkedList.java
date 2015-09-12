package interviews;

public class Convert_BT_To_Doubly_LinkedList {

	// in-order traversal: fix the prev pointer first recursively,
	// then use the prev pointer to get the next pointer and return root;

	public TreeNode BT2DLL(TreeNode root) {
		fixPrevPtr(root);
		return fixNextPtr(root);
	}

	// Changes left pointers to work as previous pointers in converted DLL
	// The function simply does inorder traversal of Binary Tree and updates
	// left pointer using previously visited node

	public void fixPrevPtr(TreeNode root) {
		TreeNode prev = null;
		if (root != null) {
			fixPrevPtr(root.left);
			root.left = prev;
			prev = root;
			fixPrevPtr(root.right);
		}
	}

	// at this time, the left pointers have been fixed to the previous node in
	// in-order traversal
	// Changes right pointers to work as next pointers in converted DLL
	public TreeNode fixNextPtr(TreeNode root) {
		TreeNode prev = null;

		// go to the right most node in BT or last node in DLL
		while (root != null && root.right != null) {
			root = root.right;
		}

		// Start from the rightmost node, traverse back using left pointers.
		// While traversing, change right pointer of nodes.
		while (root != null && root.left != null) {
			prev = root;
			root = root.left;
			root.right = prev;
		}
		// The leftmost node is head of linked list, return it
		return root;
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
