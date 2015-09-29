package interviews;

import java.util.ArrayList;

public class Facebook_Vertical_Level_order_BT {
	/**
	 * Print arbitrary binary tree by vertical levels / columns from left to right.
	 * example tree

      a
     / \
    b   c
   / \   \
  d   g   z
   \     / 
    e   i
       /
      q
     /
    x
   /
  x1
/
x2


sample output

x2
d x1
b e x
a g q
c i
	 */
	        
	public static void Print(TreeNode root) {
		ArrayList<ArrayList<Integer>> right = new ArrayList<ArrayList<Integer>>();
		ArrayList<ArrayList<Integer>> left = new ArrayList<ArrayList<Integer>>();
		printHelper(0, root, left, right); 
		// Print result
		for (int i = left.size() - 1; i > 0; i--) {
			System.out.println(left.get(i));
		}
		for (int i = 0; i < right.size(); i++) {
			System.out.println(right.get(i));
		}
	}

	private static void printHelper(int index, TreeNode root,
			ArrayList<ArrayList<Integer>> left,
			ArrayList<ArrayList<Integer>> right) {
		// Base case
		if (root == null)
			return;
		// Normal case
		if (index >= 0) {
			while (right.size() <= index) {
				right.add(new ArrayList<Integer>());
			}
			right.get(index).add(root.val);
		} else {
			while (left.size() <= -index) {
				left.add(new ArrayList<Integer>());
			}
			left.get(-index).add(root.val);
		}
		// Recurse
		printHelper(index - 1, root.left, left, right);
		printHelper(index + 1, root.right, left, right);
	}
	
	public static void main(String[] args) {
		TreeNode root = new TreeNode(1);
		root.left = new TreeNode(2);
		root.right = new TreeNode(3);

		root.left.left = new TreeNode(4);
		root.left.right = new TreeNode(7);

		root.right.right = new TreeNode(26);
		root.right.right.left = new TreeNode('i' - 'a' + 1);
		root.right.right.left.left = new TreeNode('q' - 'a' + 1);
		root.right.right.left.left.left = new TreeNode(100);
		root.right.right.left.left.left.left = new TreeNode(101);
		root.right.right.left.left.left.left.left = new TreeNode(102);

		root.left.left.right = new TreeNode(5);

		Print(root);
	}

}
