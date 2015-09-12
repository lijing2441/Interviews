package interviews;

public class Rotate_Array {
	/**
	 * Rotate an array of n elements to the right by k steps.
	 * 
	 * For example, with n = 7 and k = 3, the array [1,2,3,4,5,6,7] is rotated
	 * to [5,6,7,1,2,3,4].
	 * 
	 * Note: Try to come up as many solutions as you can, there are at least 3
	 * different ways to solve this problem.
	 */
	public void rotate(int[] nums, int k) {
        if(nums.length < 2) return;
        int len = nums.length;
        k = k % len;
        if(k == 0) return;
        
        int lastStored = nums[0];
        int curIndex = 0;
        int orig = 0;
        for(int i = 0; i < len; i++) {
            int toReplace = lastStored;
            curIndex = (curIndex + k) % len;
            lastStored = nums[curIndex];
            nums[curIndex] = toReplace;
            if(orig == curIndex) {
                curIndex++;
                orig = curIndex;
                lastStored = nums[curIndex];
            }
        }
    }
}
