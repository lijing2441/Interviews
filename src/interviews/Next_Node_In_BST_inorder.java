package interviews;

import java.util.Stack;

public class Next_Node_In_BST_inorder {
	/**
	 * Assume you have access to the parent node, find the next node of a given node
	 * 
	 * @pseudo-code: 
	 * 		TreeNode inorderSuccessor(TreeNode n){
	 * 			if(n has a right child)
	 * 				return leftmost child of the right subtree
	 * 			else
	 * 				while(n is a right child of n.parent)
	 * 					n = n.parent
	 * 				return n.parent   //parent has not been fully traversed
	 */
	public TreeNodeWithP inorderSucc(TreeNodeWithP node){
		if(node == null) return null;
		if(node.right != null){
			node = node.right;
			while(node.left != null) node = node.left;
			return node;	
		}else{
			TreeNodeWithP cur = node;
			TreeNodeWithP p = cur.parent;
			while(p != null && p.left != cur){
				cur = p;
				p = p.parent;
			}
			return p;
		}
	}
	/**
	 * Modified version: 
	 * Implement an iterator over a binary search tree (BST). 
	 * Your iterator will be initialized with the root node of a BST.
	 * 
	 * Calling next() will return the next smallest number in the BST.
	 * 
	 * Note: next() and hasNext() should run in average O(1) time and 
	 * uses O(h) memory, where h is the height of the tree.
	 * 
	 * @analysis My first thought was to use inorder traversal to put every node into an array, 
	 * and then make an index pointer for the next() and hasNext(). That meets the O(1) run time 
	 * but not the O(h) memory. This means I cannot use a lot of memory, which suggests that I 
	 * need to make use of the tree structure itself. 
	 * 
	 * And also, one thing to notice is the "average O(1) run time". It's weird to say average O(1), 
	 * because there's nothing below O(1) in run time, which suggests in most cases, I solve it in 
	 * O(1), while in some cases, I need to solve it in O(n) or O(h). These two limitations are big hints.
	 * 
	 * The problem is how to do back trace. Since the TreeNode doesn't have father pointer, we cannot 
	 * get a TreeNode's father node in O(1) without store it beforehand. Back to the first step, when 
	 * we are traversal to the left most TreeNode, we store each TreeNode we met (They are all father 
	 * nodes for back trace).
	 * 
	 * When the current TreeNode has a right branch (It cannot have left branch, remember we traversal 
	 * to the left most), we need to jump to its right child first and then traversal to its right 
	 * child's left most TreeNode. When the current TreeNode doesn't have a right branch, it means there 
	 * cannot be a node with value smaller than itself father node, point the pointer at its father node.
	 */
	private Stack<TreeNode> stack;
    private TreeNode cur;
    
    //constructor, remove the void!!
    public void BSTIterator(TreeNode root) {
        cur = root;
        stack = new Stack<TreeNode>();
    }

    /** @return whether we have a next smallest number */
    public boolean hasNext() {
        return !stack.isEmpty() || cur != null;
    }

    /** @return the next smallest number */
    public int next() {
        TreeNode node = null;
        while(!stack.isEmpty() || cur != null){
            if(cur != null){
                stack.push(cur);
                cur = cur.left;
            }else{
                node = stack.pop();
                cur = node.right;
                break;
            }
        }
        return node.val;
    }
	
	
	/**
	 * For the run time of "next()":
	 * If the returned TreeNode doesn't have a right branch, it is O(1). If it has a right branch, 
	 * it will traversal until its right child's left-most TreeNode. Now, considering the code under 
	 * "// traversal right branch". After I use next() to go through the entire tree once, this part 
	 * of code will traversal each node with a right child once. The total run time of this part is 
	 * O(n). And since I go through the entire tree, the average next() run time is O(n) / n = O(1)
	 */
}

