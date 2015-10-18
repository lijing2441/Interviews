package interviews;

public class LinkedIn_Tournament_Tree {
	/**
	 * Tournament tree 找secMin; Tournament tree 的定义是parent 是孩子node的最小值， 如下例 return 5
                2
              /   \
             2     7
           /  \   / \ 
          5    2 8   7
     *（应该可以用递归，检查左右子树的值，找到最小的那个然后返回，如果左右子树最小的值与根节点相同，
     * 就继续向下遍历，如果已经是叶子节点，并且依然与根节点相同，那就返回MAX_VALUE，然后最终返回两个值中的
     * 最小值应该就是secMin了吧？）
	 * 
	 */
	public static int secMin(TreeNode root) {
		if (root == null || (root.left == null && root.right == null)) {
			return -1;
		}
		int left = -1, right = -1;
		if (root.left.val == root.val) {
			left = secMin(root.left);
		} else {
			left = root.left.val;
		}
		if (root.right.val == root.val) {
			right = secMin(root.right);
		} else {
			right = root.right.val;
		}
		if (left == -1 || right == -1) {
			return left == -1 ? right : left;
		}
		return Math.min(left, right);
	}

	public static void main(String[] args) {
		TreeNode root = new TreeNode(2);
		root.left = new TreeNode(2);
		root.right = new TreeNode(7);
		root.left.left = new TreeNode(5);
		root.left.right = new TreeNode(2);
		root.right.left = new TreeNode(8);
		root.right.right = new TreeNode(7);
		System.out.println(secMin(root));
	}
}
