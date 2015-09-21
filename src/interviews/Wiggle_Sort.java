package interviews;

public class Wiggle_Sort {
	/**
	 * Given an unsorted array nums, reorder it in-place such that nums[0] <=
	 * nums[1] >= nums[2] <= nums[3]....
	 * 
	 * For example, given nums = [3, 5, 2, 1, 6, 4], one possible answer is [1,
	 * 6, 2, 5, 3, 4].
	 * 
	 * @param nums
	 */
	public void wiggleSort(int[] nums) {
        if (nums == null || nums.length < 2) return;
        for (int i = 0; i < nums.length; i++) {
            if (i % 2 > 0) {
                if (nums[i - 1] > nums[i]) swap(nums, i, i - 1);
            } else if (i > 0 && nums[i - 1] < nums[i]) {
                swap(nums, i, i - 1);
            }
        }
    }
    public void swap(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }
}
