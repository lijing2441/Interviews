package interviews;

public class Product_Of_Array_Except_Self {
	/**
	 * Given an array of n integers where n > 1, nums, return an array output
	 * such that output[i] is equal to the product of all the elements of nums
	 * except nums[i].
	 * 
	 * Solve it without division and in O(n).
	 * 
	 * For example, given [1,2,3,4], return [24,12,8,6].
	 * 
	 * Follow up: Could you solve it with constant space complexity? (Note: The
	 * output array does not count as extra space for the purpose of space
	 * complexity analysis.)
	 */
	// 分析：可以用两个辅助数组，一个left，一个right
	// if no additional space: 可以在最终返回结果的基础上原地计算左右两半的结果
	public int[] productExceptSelf(int[] nums) {
        if(nums == null || nums.length < 2) return nums;
        int n = nums.length;
        int[] res = new int[n];
        res[0] = 1;
        for(int i = 1; i < n; i++) {
            res[i] = res[i - 1] * nums[i - 1];
        }
        int tmp = 1;
        for(int i = n - 1; i >= 0; i--) {
            res[i] *= tmp;
            tmp *= nums[i];
        }
        return res;
    }
}
