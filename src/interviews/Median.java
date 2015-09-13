package interviews;

public class Median {
	/**
	 * Example Given [4, 5, 1, 2, 3], return 3
	 * 
	 * Given [7, 9, 4, 5], return 5
	 * 
	 * Challenge O(n) time.
	 */
	/**
     * @param nums: A list of integers.
     * @return: An integer denotes the middle number of the array.
     */
    // quick-selection
    public int median(int[] nums) {
        if (nums == null || nums.length == 0) return -1;
        int len = nums.length;
        if (len % 2 == 0) return helper(nums, 0, len - 1, len / 2);
        else return helper(nums, 0, len - 1, (len + 1) / 2);
    }
    public int helper(int[] nums, int start, int end, int size) {
        // use the start index element as pivot
        int pivot = nums[start];
        int l = start + 1, r = end;
        while (l <= r) {
            while (l <= r && nums[l] < pivot) {
                l++;
            }
            while (l <= r && nums[r] >= pivot) {
                r--;
            }
            if (l < r) {
                swap(nums, l, r);
            }
        }
        swap(nums, start, r);
        if (size == r + 1) return nums[r];
        else if (size > r + 1) {    // selected a smaller pivot than actual median
            return helper(nums, r + 1, end, size);
        } else {
            return helper(nums, start, r - 1, size);
        }
    }
    public void swap(int[] nums, int i, int j) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }
}
