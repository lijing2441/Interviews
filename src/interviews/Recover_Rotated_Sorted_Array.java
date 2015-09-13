package interviews;

import java.util.ArrayList;

public class Recover_Rotated_Sorted_Array {
	/**
	 * Given a rotated sorted array, recover it to sorted array in-place.
	 * 
	 * Example [4, 5, 1, 2, 3] -> [1, 2, 3, 4, 5]
	 * 
	 * Challenge In-place, O(1) extra space and O(n) time.
	 * 
	 * Clarification What is rotated array?
	 * 
	 * For example, the original array is [1,2,3,4], The rotated array of it can
	 * be [1,2,3,4], [2,3,4,1], [3,4,1,2], [4,1,2,3]
	 * 
	 * @param nums
	 */
	public void recoverRotatedSortedArray(ArrayList<Integer> nums) {
		if (nums == null || nums.size() <= 1)
			return;
		int len = nums.size();
		// find the smallest element and reverse
		for (int i = 0; i < len - 1; i++) {
			if (nums.get(i) > nums.get(i + 1)) {
				reverse(nums, 0, i);
				reverse(nums, i + 1, len - 1);
				reverse(nums, 0, len - 1);
			}
		}
	}

	public void reverse(ArrayList<Integer> list, int start, int end) {
		while (start < end) {
			int tmp = list.get(start);
			list.set(start, list.get(end));
			list.set(end, tmp);
			start++;
			end--;
		}
	}
}
