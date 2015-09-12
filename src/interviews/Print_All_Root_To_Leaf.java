package interviews;

public class Print_All_Root_To_Leaf {
	/* Print all root-to-leaf paths of the input tree */
	public static void printPaths(TreeNode root) {
		printPaths(root, new int[1000], 0);
	}

	public static void printPaths(TreeNode root, int[] res, int index) {
		if (root == null)
			return;
		res[index] = root.val;
		if (root.left == null && root.right == null) {
			printArray(res, index);
			return;
		} else {
			printPaths(root.left, res, index + 1);
			printPaths(root.right, res, index + 1);
		}
	}

	public static void printArray(int[] arr, int index) {
		for (int i = 0; i <= index; i++) {
			System.out.print(arr[i] + " ");
		}
		System.out.println();
	}

	public static void main(String[] args) {
		TreeNode root = new TreeNode(1);
		root.left = new TreeNode(2);
		root.right = new TreeNode(3);
		root.left.left = new TreeNode(4);
		root.left.right = new TreeNode(5);

		/* Print all root-to-leaf paths of the input tree */
		printPaths(root);
	}
}
