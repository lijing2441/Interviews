package interviews;

public class Move_Zeroes {
	/**
	 * Given an array nums, write a function to move all 0's to the end of it
	 * while maintaining the relative order of the non-zero elements.
	 * 
	 * For example, given nums = [0, 1, 0, 3, 12], after calling your function,
	 * nums should be [1, 3, 12, 0, 0].
	 * 
	 * Note: You must do this in-place without making a copy of the array.
	 * 		 Minimize the total number of operations.
	 */
	// 把所有非零的挪到左边就行，双指针
	public void moveZeroes(int[] nums) {
        if (nums == null || nums.length < 2) return;
        int len = nums.length;
        int j = 0;
        for (int i = 0; i < len; i++) {
            if (nums[i] != 0) {
                nums[j++] = nums[i];
            }
        }
        for (; j < len; j++) {
            nums[j] = 0;
        }
    }
}
