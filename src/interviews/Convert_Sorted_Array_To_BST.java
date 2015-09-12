package interviews;

public class Convert_Sorted_Array_To_BST {
	// O(n), O(n)
	// find the midpoint and recursively construct the left and right subtree
	public TreeNode sortedArrayToBST(int[] num) {
        if(num.length == 0) return null;
        return helper(num, 0, num.length - 1);
    }
    public TreeNode helper(int[] num, int l, int r){
        if(l > r) return null;
        if(l == r) return new TreeNode(num[l]);
        if(l < r){
            int mid = (l + r) / 2;
            TreeNode cur = new TreeNode(num[mid]);
            cur.left = helper(num, l, mid - 1);
            cur.right = helper(num, mid + 1, r);
            return cur;
        }
        return null;
    }
}
