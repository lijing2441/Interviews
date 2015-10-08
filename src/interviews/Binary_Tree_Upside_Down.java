package interviews;

public class Binary_Tree_Upside_Down {
	/**
	 * Given a binary tree where all the right nodes are either leaf nodes with a sibling 
	 * (a left node that shares the same parent node) or empty, flip it upside down and turn 
	 * it into a tree where the original right nodes turned into left leaf nodes. 
	 * Return the new root.
	 * 
	 * For example:
	 * Given a binary tree {1,2,3,4,5},
	 * 		1
	 * 	   / \
	 * 	  2   3
	 *   / \
	 *  4   5
	 *  
	 * return the root of the binary tree [4,5,2,#,#,3,1].
	 *      4
	 *     / \
	 *    5   2
	 *       / \
	 *      3   1
	 *      
	 * [分析]
	 * 起始对于每一个节点，相应的操作为：
	 * p.left = parent.right;
	 * p.right = parent;
	 * 
	 * 第一句话“Given a binary tree where all the right nodes are either leaf nodes with a sibling 
	 * (a left node that shares the same parent node) or empty”
	 * 表明这个树是有着很强的限制条件的。也正是基于这样一个基础，一个简单的while循环就可以搞定这道题。
	 * 
	 * [注意事项] 两个copy：parent node 和 parent’s right child
	 * 
	 * ** 1. 对于一个parent来说，若有right node，必须得有left node。而有left node，right node可以为空。
	 * 	  而right node必须为叶子节点。所以该树每层至多有2个节点，并且2节点有共同的parent。
	 * 
	 * 2. 所以对于最底层来说，必有一个left node，而这个left node则为整个新树的根 —— 例子中的4为最底层的左节点，最后成为新树的root。
	 * 3. 原树的根节点，变为了新树的最右节点。
	 * 3. 对于子树1 2 3来说，需要在以2为根的子树2 4 5建立成新树4 5 2后，插入到新树的最右节点2下面。原树的根节点root为right child，
	 *    原树root->right为新树的left node
	 *    
	 * 其实就是每个子树clockwise旋转一下，因为只有左子树，右边最多一个leaf，只用用一个parentRight node表示即可
	 */
	public TreeNode UpsideDownBinaryTree(TreeNode root) {
        TreeNode p = root;
        TreeNode parent = null;
        TreeNode parentRight = null;
        // as long as we still have the left subtree
        while (p != null) {
            TreeNode left = p.left;
            p.left = parentRight;
            parentRight = p.right;
            p.right = parent;
            parent = p;
            p = left;
        }
        return parent;
    }
	/**
	 * Bottom up approach:
	 * Although the code for the top-down approach seems concise, it is actually subtle and 
	 * there are a lot of hidden traps if you are not careful. The other approach is thinking 
	 * recursively in a bottom-up fashion. If we reassign the bottom-level nodes before the 
	 * upper ones, we won’t have to make copies and worry about overwriting something. 
	 * 
	 * We know the new root will be the left-most leaf node, so we begin the reassignment here.
	 * 
	 * 必须画图，然后可以发现，作为任何的node，
	 * all left children nodes are roots. n = n.left, then, n.left=parent.right; n.right=parent;
	 */
	public TreeNode UpsideDownBinaryTreeRecur(TreeNode root) {
		return dfsBottomUp(root, null);
	}

	private TreeNode dfsBottomUp(TreeNode p, TreeNode parent) {
		if (p == null)
			return parent;
		TreeNode root = dfsBottomUp(p.left, p);
		p.left = (parent == null) ? parent : parent.right;
		p.right = parent;
		return root;
	}
}