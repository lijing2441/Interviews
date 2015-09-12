package interviews;

public class MaxSubarray {
	/**
	 * Find the contiguous subarray within an array (containing at least one
	 * number) which has the largest sum.
	 * 
	 * For example, given the array [−2,1,−3,4,−1,2,1,−5,4], the contiguous
	 * subarray [4,−1,2,1] has the largest sum = 6.
	 */
	public int maxSubArray(int[] A) {
		int maxSum = A[0];
		int sum_so_far = A[0];
		for (int i = 1; i < A.length; i++) {
			if (sum_so_far <= 0) {
				sum_so_far = A[i];
			} else {
				sum_so_far += A[i];
			}
			maxSum = Math.max(sum_so_far, maxSum);
		}
		return maxSum;
	}
}
