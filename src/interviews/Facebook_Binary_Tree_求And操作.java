package interviews;

public class Facebook_Binary_Tree_求And操作 {
	/**
	 *     
	    *                   *                    *
      /   \               /   \                /   \
     1     *     and     *     0     =        *     0
          /  \          / \                 /  \
         0    1        1   0               1    0
     Follow up: deepCopy(tree)
     Follow up2: 如何合并结果中出现的情况？
          * 
        /   \    =>      0
       0    0 
	 *
	 *这道题的意思是，每个树包含中间node和leaf node，其中leaf node可以是0或者1, internal node没有具体值，只是用于构成tree的结构。
	 *当两个tree求AND关系的时候，从root开始，如果都是internal node，那么结果的数也是internal node；如果其中一个是leaf node并且值为1，
	 *那么结果为另外一棵树的相同位置的subtree；如果其中一个节点是leaf并且值为0，那么结果对应的位置也为leaf并且值也为0.
	 */
	public TreeNode getANDBetweenBT(TreeNode n1, TreeNode n2) {
		if (n1 == null || n2 == null) return null;
		if (isLeaf(n1)) {
			if (n1.val == 0) return n1;
			else if (n1.val == 1) return n2;
		} else if (isLeaf(n2)) {
			if (n2.val == 0) return n2;
			else if (n2.val == 1) return n1;
		} else {
			// both are not leaf
			TreeNode root = n1; // n1 and n2 are both "*", it doesn't matter which one to use
			root.left = getANDBetweenBT(n1.left, n2.left);
			root.right = getANDBetweenBT(n1.right, n2.right);
			return root;
		}
		return null;
	}
	public boolean isLeaf(TreeNode node) {
		if (node == null) return false;
		return node.left == null && node.right == null;
	}
}
