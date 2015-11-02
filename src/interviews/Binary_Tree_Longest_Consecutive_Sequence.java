package interviews;

public class Binary_Tree_Longest_Consecutive_Sequence {
	public int longestConsecutive(TreeNode root) {
        int[] res = new int[1];
        helper(null, root, 0, res);
        return res[0];
    }
    public void helper(TreeNode parent, TreeNode cur, int curLen, int[] res) {
        if (cur == null) return;
        if (parent == null || cur.val != parent.val + 1) curLen = 1;
        else curLen += 1;
        if (res[0] < curLen) res[0] = curLen;
        helper(cur, cur.left, curLen, res);
        helper(cur, cur.right, curLen, res);
    }
}
