package interviews;

public class Partition_Array_by_Odd_and_Even {
	/**
	 * Given [1, 2, 3, 4], return [1, 3, 2, 4]
	 * 
	 * Challenge:Do it in-place.
	 */
	public void partitionArray(int[] nums) {
		if (nums == null || nums.length < 2)
			return;
		int len = nums.length;
		int indexOdd = -1, indexEven = len;
		for (int i = 0; i < indexEven; i++) {
			if (nums[i] % 2 == 1) {
				indexOdd++;
				swap(nums, indexOdd, i);
			} else {
				indexEven--;
				swap(nums, indexEven, i);
				i--;
			}
		}
	}

	public void swap(int[] arr, int i, int j) {
		int tmp = arr[i];
		arr[i] = arr[j];
		arr[j] = tmp;
	}
}
