package interviews;

public class Recover_Binary_Search_Tree {
	/**
	 * Two elements of a binary search tree (BST) are swapped by mistake.
	 * 
	 * Recover the tree without changing its structure.
	 * 
	 * Note: A solution using O(n) space is pretty straight forward. Could you
	 * devise a constant space solution?
	 * 
	 * left must be smaller than root, right must be greater or equal to the root
	 */
	// O(n) method, use in-order to get the two nodes 
	public TreeNode first = null;
    public TreeNode second = null;
    public TreeNode prev = new TreeNode(Integer.MIN_VALUE);
    
    public void recoverTree(TreeNode root) {
        if(root == null || (root.left == null && root.right == null)) return;
        inorder(root);
        int tmp = first.val;
        first.val = second.val;
        second.val = tmp;
    }
    public void inorder(TreeNode root){
        if(root == null) return;
        inorder(root.left);
        if(first == null && prev.val >= root.val) first = prev;
        if(first != null && prev.val >= root.val) second = root;
        prev = root;
        inorder(root.right);
    }
    
    //O(1) space method: Morris Traversal
    /**
	 * 1. Initialize current as root 
	 * 2. While current is not NULL 
	 * 		-> If current does not have left child 
	 * 			a) Print current’s data
	 * 			b) Go to the right, i.e., current = current->right 
	 * 		-> Else 
	 * 			a) Make current as right child of the rightmost node in current's left subtree 
	 * 			b) Go to this left child, i.e., current = current->left
	 * 
	 */
    public void recoverTreeMorris(TreeNode root) {
        if(root == null ||(root.left == null && root.right == null)) return;
        TreeNode first = null, second = null, node1 = null, node2 = null, prev = null;
        TreeNode cur = root;
        while(cur != null){
            if(cur.left == null){
                if(node1 == null) node1 = cur;
                else if(node2 == null) node2 = cur;
                else{
                    node1 = node2;
                    node2 = cur;
                }
                cur = cur.right;
            } else {
                prev = cur.left;
                while(prev.right != null && prev.right != cur) prev = prev.right;
                if(prev.right == null){
                    prev.right = cur; // change the tree structure
                    cur = cur.left;
                    continue;
                }else{
                    prev.right = null;  // change the structure back
                    if(node2 == null) node2 = cur;
                    else{
                        node1 = node2;
                        node2 = cur;
                    }
                    cur = cur.right;
                }
            }
            if(node1 != null && node2 != null && node1.val > node2.val){
                if(first == null) first = node1;
                second = node2;
            }
        }
        int tmp = first.val;
        first.val = second.val;
        second.val = tmp;
    }
}
