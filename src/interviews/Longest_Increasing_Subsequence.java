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
	
	
	// O(nlogn) method, binary search to get the right position
	public int lengthOfLIS(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        int n = nums.length;
        int[] res = new int[n];
        res[0] = nums[0];
        int maxLen = 1;
        for (int i = 1; i < n; i++) {
            if (nums[i] > res[maxLen - 1]) {
                res[maxLen] = nums[i];
                maxLen++;
            } else if (nums[i] < res[0]) {
                res[0] = nums[i];
            } else {
                int index = getIndex(res, 0, maxLen - 1, nums[i]);
                res[index] = nums[i];
            }
        }
        return maxLen;
    }
    public int getIndex(int[] nums, int start, int end, int target) {
        while (start <= end) {
            int mid = (start + end) / 2;
            if (nums[mid] == target) return mid;
            else if (nums[mid] < target) {
                start = mid + 1;
            } else {
                end = mid - 1;
            }
        }
        return start;
    }
}
