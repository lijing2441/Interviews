package interviews;

public class Binary_Search_Tree_Insert_Delete_Node {
	/**
	 * Given binary search tree as follow, after Insert node 6, the tree should be:

  2             2
 / \           / \
1   4   -->   1   4
   /             / \ 
  3             3   6
	 */
	/**
     * @param root: The root of the binary search tree.
     * @param node: insert this node into the binary search tree
     * @return: The root of the new binary search tree.
     */
    public TreeNode insertNode(TreeNode root, TreeNode node) {
        // write your code here
        if (root == null) return node;
        if (node == null) return root;
        if (node.val < root.val) {
            root.left = insertNode(root.left, node);
            return root;
        } else {
            root.right = insertNode(root.right, node);
            return root;
        }
    }
    // iterative
    public TreeNode insertNodeIte(TreeNode root, TreeNode node) {
        // write your code here
        if (root == null) return node;
        if (node == null) return root;
        TreeNode curRoot = root;
        boolean inserted = false;
        while (!inserted) {
            if (node.val < curRoot.val) {
                if(curRoot.left != null) {
                    curRoot = curRoot.left;
                } else {
                    curRoot.left = node;
                    inserted = true;
                }
            } else {
                if (curRoot.right != null) {
                    curRoot = curRoot.right;
                } else {
                    curRoot.right = node;
                    inserted = true;
                }
            }
        }
        return root;
    }
}
