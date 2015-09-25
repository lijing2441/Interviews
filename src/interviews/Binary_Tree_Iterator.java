package interviews;

import java.util.Stack;

public class Binary_Tree_Iterator {
	/**
	 * Implement an iterator over a binary search tree (BST). Your iterator will
	 * be initialized with the root node of a BST.
	 * 
	 * Calling next() will return the next smallest number in the BST.
	 * 
	 * Note: next() and hasNext() should run in average O(1) time and uses O(h)
	 * memory, where h is the height of the tree.
	 * 
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
		 * If node has been put in the stack and current node cur == null, it is
		 * O(1). If not, it will traverse until the current node's left-most
		 * TreeNode. Now, considering the code under
		 * "// traversal right branch". After I use next() to go through the
		 * entire tree once, this part of code will traversal each node once.
		 * The total run time of this part is O(n). And since I go through the
		 * entire tree, the average next() run time is O(n) / n = O(1)
		 */
		/** @return the next smallest number */
		public int next() {
			TreeNode node = null;
			while (!stack.isEmpty() || cur != null) {
				if (cur != null) {
					stack.push(cur);
					cur = cur.left;
				} else {
					node = stack.pop();
					cur = node.right;
					break;
				}
			}
			return node.val;
		}
	}
}
