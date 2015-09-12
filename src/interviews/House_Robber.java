package interviews;

public class House_Robber {
	/**
	 * You are a professional robber planning to rob houses along a street. Each
	 * house has a certain amount of money stashed, the only constraint stopping
	 * you from robbing each of them is that adjacent houses have security
	 * system connected and it will automatically contact the police if two
	 * adjacent houses were broken into on the same night.
	 * 
	 * Given a list of non-negative integers representing the amount of money of
	 * each house, determine the maximum amount of money you can rob tonight
	 * without alerting the police.
	 */
	public int robSimple(int[] nums) {
        int n1 = 0, n2 = 0, res = 0;
        for(int i = 0; i < nums.length; i++) {
            res = Math.max(n2 + nums[i], n1);
            n2 = n1;
            n1 = res;
        }
        return res;
    }
	// with O(n) space
	public int rob(int[] nums) {
		if (nums.length == 0)
			return 0;
		if (nums.length == 1)
			return nums[0];
		if (nums.length == 2)
			return Math.max(nums[0], nums[1]);
		
		int[] dp = new int[nums.length];
		dp[0] = nums[0];
		dp[1] = Math.max(nums[0], nums[1]);
		for (int i = 2; i < nums.length; i++) {
			dp[i] = Math.max(nums[i] + dp[i - 2], dp[i - 1]);
		}
		return dp[nums.length - 1];
	}

	/**
	 * Problem II: After robbing those houses on that street, the thief has
	 * found himself a new place for his thievery so that he will not get too
	 * much attention. This time, all houses at this place are arranged in a
	 * circle. That means the first house is the neighbor of the last one.
	 * Meanwhile, the security system for these houses remain the same as for
	 * those in the previous street.
	 * 
	 * Given a list of non-negative integers representing the amount of money of
	 * each house, determine the maximum amount of money you can rob tonight
	 * without alerting the police.
	 */
	/**
	 * @logic:
	 * Since you cannot rob both the first and last house, just create two
	 * separate vectors, one excluding the first house, and another excluding
	 * the last house.
	 */
	public int rob2(int[] nums) {
        int len = nums.length;
        if(len == 0) return 0;
        if(len == 1) return nums[0];
        int[] nums1 = new int[len - 1];
        int[] nums2 = new int[len - 1];
        for(int i = 0; i < len - 1; i++) {
            nums1[i] = nums[i];
        }
        for(int i = 1; i < len; i++) {
            nums2[i - 1] = nums[i];
        }
        return Math.max(robHelper(nums1), robHelper(nums2));
    }
    public int robHelper(int[] nums) {
        int n1 = 0, n2 = 0, res = 0;
        for(int i = 0; i < nums.length; i++) {
            res = Math.max(n2 + nums[i], n1);
            n2 = n1;
            n1 = res;
        }
        return res;
    }
}
