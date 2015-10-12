package interviews;

import java.util.ArrayList;

public class K_Sum {
	// 只需返回个数，用三维dp
	// 对于一个number，用或者不用
	public int kSum(int A[], int k, int target) {
        int n = A.length;
        int[][][] dp = new int[n+1][k+1][target+1];
        for (int i = 0; i <= n; i++) {
            dp[i][0][0] = 1;
        }
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= k; j++) {
                for (int t = 1; t <= target; t++) {
                    dp[i][j][t] = dp[i - 1][j][t];
                    if (A[i - 1] <= t) {
                        dp[i][j][t] += dp[i - 1][j - 1][t - A[i - 1]];
                    }
                }
            }
        }
        return dp[n][k][target];
    }
	
	// k-sum II, 用combination做法，找出来有可能的所有k大小
	public ArrayList<ArrayList<Integer>> kSumII(int[] A, int k, int target) {
        ArrayList<ArrayList<Integer>> res = new ArrayList<ArrayList<Integer>>();
        if (A == null || A.length == 0 || k == 0) return res;
        kSumHelper(A, k, target, 0, 0, res, new ArrayList<Integer>());
        return res;
    }
    public void kSumHelper(int[] nums, int k, int target, int curIndex, int curSum, ArrayList<ArrayList<Integer>> res, ArrayList<Integer> curList) {
        //if (curSum > target) return;
        if (k == 0) {
            if (curSum == target) res.add(curList);
            return;
        }
        //if (curIndex >= nums.length) return;
        for (int i = curIndex; i < nums.length; i++) {
            if (nums[i] + curSum > target) continue;
            else {
                ArrayList<Integer> next = new ArrayList<Integer>(curList);
                next.add(nums[i]);
                kSumHelper(nums, k - 1, target, i + 1, curSum + nums[i], res, next);
            }
        }
    }
}
