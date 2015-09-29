package interviews;

public class Longest_Increasing_Subsequence {
	/**
	 * Given a sequence of integers, find the longest increasing subsequence
	 * (LIS).
	 * 
	 * You code should return the length of the LIS.
	 * 
	 * Example For [5, 4, 1, 2, 3], the LIS is [1, 2, 3], return 3
	 * 
	 * For [4, 2, 4, 5, 3, 7], the LIS is [4, 4, 5, 7], return 4
	 */
	public int longestIncreasingSubsequence(int[] nums) {
        if (nums.length == 0) return 0;
        int len = nums.length;
        int[] dp = new int[nums.length];
        int max = 1;
        for (int i = 0; i < len; i++) {
            dp[i] = 1;
            for (int j = 0; j < i; j++) {
                if (nums[j] <= nums[i]) {
                    dp[i] = (dp[i] > dp[j] + 1 ? dp[i] : dp[j] + 1);
                }
            }
            if (dp[i] > max) {
                max = dp[i];
            }
        }
        return max;
    }
}
