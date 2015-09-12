package interviews;

import java.util.ArrayList;
import java.util.List;

public class Unique_Binary_Search_Tree {
	/**
	 * Problem: Given n, how many structurally unique BST's that store values 1...n?
	 * 
	 * @logic
	 * DP: 对于一个数字i，左边为0...j nodes，右边 i - j - 1 nodes, j in range [0, i - 1]
	 * Recurrence: dp[i] = dp[j] + dp[i - j - 1] => until i nodes, number of BST 
	 * Base case: dp[0] = 0, dp[1] = 1;
	 * Complexity: O(n^2) and O(n) space
	 */
	public int numTrees(int n){
		if(n <= 2) return n;
		//1..n corresponds the 1...n nodes
		int[] dp = new int[n + 1]; 
		for(int i = 0; i <= n; i++){
			if(i < 2){
				dp[i] = i;
				continue;
			}
			for(int j = 0; j < i; j++){
				int left = dp[j];
				int right = dp[i - j - 1];
				// if left or right == 0, means the left or right subtree is empty
				// so set them to 1, but not 0;
				dp[i] += (left == 0? 1: left) + (right == 0? 1: right);
			}
		}
		return dp[n];
	}
	public int numTrees2(int n) {
        if(n <= 2) return n;
        int[] dp = new int[n + 1];
        dp[0] = 1;
        for(int i = 1; i <= n; i++) {
            for(int j = 1; j <= i; j++) {
                dp[i] += dp[j - 1] * dp[i - j];
            }
        }
        return dp[n];
    }
	/**
	 * Problem II:
	 * 
	 * Return all the trees: two methods 1) recursively add left and right: O(Catalan(n)), 
	 * 										since we used nested recursion
	 * 									 2) DP, similar idea as above
	 */
	public ArrayList<TreeNode> generateTrees(int n) {
        return treeHelper(1, n);
    }
    public ArrayList<TreeNode> treeHelper(int left, int right){
        ArrayList<TreeNode> res = new ArrayList<TreeNode>();
        if(left > right){
            res.add(null);
        }else if(left == right){
            res.add(new TreeNode(left));
        }else{
            for(int i = left; i <= right; i++){
                ArrayList<TreeNode> lefts = treeHelper(left, i - 1);
                ArrayList<TreeNode> rights = treeHelper(i + 1, right);
                for(TreeNode l: lefts){
                    for(TreeNode r: rights){
                        TreeNode node = new TreeNode(i);
                        node.left = l;
                        node.right = r;
                        res.add(node);
                    }
                }
            }
        }
        return res;
    }
    
    
    //DP method
    @SuppressWarnings("unchecked")
	public List<TreeNode> generateTreesDP(int n) {
        if(n <= 0){
            List<TreeNode> res =new ArrayList<TreeNode>();
            res.add(null);
            return res;
        }

        List<TreeNode>[] dp = new ArrayList[n+1];
        for(int i = 0; i < n+1; ++i){
            dp[i] =  new ArrayList<TreeNode>();
        }

        dp[0].add(null);

        for(int i = 1; i <= n; ++i){
            for(int j = 0; j < i; ++j){
                for(int k = 0; k < dp[j].size(); ++k){
                    for(int l = 0; l < dp[i-1-j].size(); ++l){
                        TreeNode tmp = new TreeNode(1);
                        tmp.left = deepCopy(dp[j].get(k));
                        tmp.right = deepCopy(dp[i-1-j].get(l));
                        dp[i].add(tmp);
                    }
                }
            }
        }

        for(int i = 0; i < dp[n].size(); ++i){
            cur = 1;
            setValue(dp[n].get(i));
        }
        return dp[n];
    }
    TreeNode deepCopy(TreeNode root){
        if(root == null) return null;
        TreeNode tmp = new TreeNode(1);
        tmp.left = deepCopy(root.left);
        tmp.right = deepCopy(root.right);
        return tmp;
    }
    int cur = 0;
    void setValue(TreeNode root){
        if(root.left != null){
            setValue(root.left);
        }
        root.val = cur++;
        if(root.right != null){
            setValue(root.right);
        }

    }
}
