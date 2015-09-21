package interviews;

public class BST_Insert_Delete_Node {
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
    /**
     * iterative way to solve the problem
     * 
     * 找空节点，用一个inserted的boolean变量来指示是否iteration可以结束，左边为<, 右边为>=
     */
    public TreeNode insertNodeIte(TreeNode root, TreeNode node) {
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
    
    /**
     * Delete a node from BST
     */
    public TreeNode removeNode(TreeNode root, int value) {
        // make a dummy node here to make root same with others.
        TreeNode dummy = new TreeNode(0);
        dummy.left = root;
        
        TreeNode parent = findParentNode(dummy, root, value);
        TreeNode nodeToRemove = null;
        if (parent.left != null && parent.left.val == value) {
            nodeToRemove = parent.left;
        } else if (parent.right != null && parent.right.val == value) {
            nodeToRemove = parent.right;
        } else {
            return dummy.left;   // we do not have such a node in tree
        }
        
        removeNode(parent, nodeToRemove);
        return dummy.left;
    }
    // find the parent node of the targeting node
    public TreeNode findParentNode(TreeNode parent, TreeNode node, int value) {
        if (node == null) return parent; // in case root == null
        if (node.val == value) {
            return parent;
        }
        if (node.val < value) {
            return findParentNode(node, node.right, value);
        } else {
            return findParentNode(node, node.left, value);
        }
    }
    // move the leftmost child of node.right to node place
    public void removeNode(TreeNode parent, TreeNode node) {
        // if the node.right == null, easy case
        // just connect parent with the node.left child
        if (node.right == null) {
            if (parent.left == node) {
                parent.left = node.left;
            } else {
                parent.right = node.left;
            }
        } else {
            // get the leftmost child of node.right
            TreeNode tmpParent = node;
            TreeNode tmp = node.right;
            while (tmp.left != null) {
                tmpParent = tmp;
                tmp = tmp.left;
            }
            // cut the tmp off from its original location
            if (tmpParent.left == tmp) {
                tmpParent.left = tmp.right;
            } else {
                tmpParent.right = tmp.right;
            }
            // connect the tmp to parent child place
            if (parent.left == node) {
                parent.left = tmp;
            } else {
                parent.right = tmp;
            }
            
            // connect the children of node to tmp
            tmp.left = node.left;
            tmp.right = node.right;
        }
    }
}
