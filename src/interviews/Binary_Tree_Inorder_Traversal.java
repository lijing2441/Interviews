package interviews;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Binary_Tree_Inorder_Traversal {
	/**
	 * Given a binary tree, return the inorder traversal of its nodes' values.
	 * For example: Given binary tree {1,#,2,3},
   	 *	1
     *	 \
     *	  2
     *	 /
   	 *	3
	 *	return [1,3,2].
	 * 
	 */
	// recursive, O(n), O(n)
	public List<Integer> inorderTraversal(TreeNode root) {
		List<Integer> list = new ArrayList<Integer>();
		if (root == null)
			return list;
		return inorderTraversal(root, list);
	}

	public List<Integer> inorderTraversal(TreeNode root, List<Integer> list) {
		if (root != null) {
			inorderTraversal(root.left, list);
			list.add(root.val);
			inorderTraversal(root.right, list);
		}
		return list;
	}

	// iterative way, go down to the last left, then root, then jump to the right
	public List<Integer> inorderTraversalIte(TreeNode root) {
		List<Integer> list = new ArrayList<Integer>();
		if (root == null)
			return list;

		Stack<TreeNode> stack = new Stack<TreeNode>();
		TreeNode cur = root;
		while (!stack.isEmpty() || cur != null) {
			if (cur != null) {
				stack.push(cur);
				cur = cur.left;
			} else {
				cur = stack.pop();
				list.add(cur.val);
				cur = cur.right;
			}
		}
		return list;
	}
}

/**
 * Implement an iterator over a binary search tree (BST). Your iterator will be
 * initialized with the root node of a BST.
 * 
 * Calling next() will return the next smallest number in the BST.
 * 
 * Note: next() and hasNext() should run in average O(1) time and uses O(h)
 * memory, where h is the height of the tree.
 * 
 * @idea My first thought was to use inorder traversal to put every node into an
 *       array, and then make an index pointer for the next() and hasNext().
 *       That meets the O(1) run time but not the O(h) memory. O(h) is really
 *       much more less than O(n) when the tree is huge.
 * 
 *       This means I cannot use a lot of memory, which suggests that I need to
 *       make use of the tree structure itself. And also, one thing to notice is
 *       the "average O(1) run time". It's weird to say average O(1), because
 *       there's nothing below O(1) in run time, which suggests in most cases, I
 *       solve it in O(1), while in some cases, I need to solve it in O(n) or
 *       O(h). These two limitations are big hints.
 * 
 *       Before I come up with this solution, I really draw a lot binary trees
 *       and try inorder traversal on them. We all know that, once you get to a
 *       TreeNode, in order to get the smallest, you need to go all the way down
 *       its left branch. So our first step is to point to pointer to the left
 *       most TreeNode. The problem is how to do back trace. Since the TreeNode
 *       doesn't have father pointer, we cannot get a TreeNode's father node in
 *       O(1) without store it beforehand. Back to the first step, when we are
 *       traversal to the left most TreeNode, we store each TreeNode we met (
 *       They are all father nodes for back trace).
 * 
 *       After that, I try an example, for next(), I directly return where the
 *       pointer pointing at, which should be the left most TreeNode I
 *       previously found. What to do next? After returning the smallest
 *       TreeNode, I need to point the pointer to the next smallest TreeNode.
 *       When the current TreeNode has a right branch (It cannot have left
 *       branch, remember we traversal to the left most), we need to jump to its
 *       right child first and then traversal to its right child's left most
 *       TreeNode. When the current TreeNode doesn't have a right branch, it
 *       means there cannot be a node with value smaller than itself father
 *       node, point the pointer at its father node.
 */
class BSTIterator {
    private Stack<TreeNode> stack;
    private TreeNode cur;
    
    public BSTIterator(TreeNode root) {
        cur = root;
        stack = new Stack<TreeNode>();
    }

    /** @return whether we have a next smallest number */
    public boolean hasNext() {
        return !stack.isEmpty() || cur != null;
    }
    
    /**
     * If node has been put in the stack and current node cur == null, it is O(1). 
     * If not, it will traverse until the current node's left-most TreeNode. 
     * Now, considering the code under "// traversal right branch". After I use next()
     * to go through the entire tree once, this part of code will traversal each node once. 
     * The total run time of this part is O(n). 
     * And since I go through the entire tree, the average next() run time is O(n) / n = O(1)
     */
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
}