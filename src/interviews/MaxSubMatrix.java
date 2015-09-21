package interviews;

public class MaxSubMatrix {
	/**
	 * Kadane can reach O(n^3)，就是把maxsubarray应用到了matrix每一行中
	 */
	public static int MaxSumMatrixImprove(int[][] matrix) {
		// O(n^4)
		int maxSum = 0;
		// start row
		for (int i = 0; i < matrix.length; i++) {
			// end row
			for (int j = i; j < matrix.length; j++) {
				// we need a tmp 1-D array to store the sums per column
				int[] tmpSums = new int[matrix[0].length];
				// per column
				for (int m = 0; m < tmpSums.length; m++) {
					// per row selected
					for (int p = i; p <= j; p++) {
						tmpSums[m] += matrix[p][m];
					}
				}
				// check the largest sum for the 1-D array tmpSum
				int tmpLargest = maxSubArray(tmpSums);
				if (tmpLargest > maxSum)
					maxSum = tmpLargest;
			}
		}
		return maxSum;
	}

	// return the max sub-array
	public static int maxSubArray(int[] arr) {
		// initialize sum, maxSum and
		int tmpSum = 0;
		int maxSum = Integer.MIN_VALUE;

		for (int i = 0; i < arr.length; i++) {
			if (tmpSum + arr[i] > 0) {
				tmpSum += arr[i];
				if (tmpSum > maxSum)
					maxSum = tmpSum;
			} else {
				tmpSum = 0;
			}
		}
		return maxSum;
	}

	// also return the positions of the max submatrix
	// O(n^4)
	public static void findMaxSum(int M[][]) {
		// Variables to store the final output
		int col = M[0].length;
		int row = M.length;
		int maxSum = Integer.MIN_VALUE, finalLeft = 0, finalRight = 0, finalTop = 0, finalBottom = 0;

		int temp[] = new int[row];
		int sum = 0;
		int start = 0;
		int finish = -1;

		// Set the left column
		for (int left = 0; left < col; left++) {
			// Set the right column for the left column set by outer loop
			for (int right = left; right < col; right++) {
				// Calculate sum between current left and right for every row
				// 'i'
				for (int i = 0; i < row; i++)
					temp[i] += M[i][right];

				// Find the maximum sum subarray in temp[]. The kadane()
				// function
				// also sets values of start and finish. So 'sum' is sum of
				// rectangle between (start, left) and (finish, right) which is
				// the
				// maximum sum with boundary columns strictly as left and right.

				// local variable
				int local_start = 0;

				for (int i = 0; i < temp.length; i++) {
					sum += temp[i];
					if (sum < 0) {
						sum = 0;
						local_start = i + 1;
					} else if (sum > maxSum) {
						maxSum = sum;
						start = local_start;
						finish = i;
					}
				}

				// There is at-least one non-negative number
				if (finish != -1)
					break;

				// Special Case: When all numbers in arr[] are negative
				maxSum = temp[0];
				start = finish = 0;

				// Find the maximum element in array
				for (int i = 1; i < temp.length; i++) {
					if (temp[i] > maxSum) {
						maxSum = temp[i];
						start = finish = i;
					}
				}

				// Compare sum with maximum sum so far. If sum is more, then
				// update
				// maxSum and other output values
				if (sum > maxSum) {
					maxSum = sum;
					finalLeft = left;
					finalRight = right;
					finalTop = start;
					finalBottom = finish;
				}
			}
		}

		// Print final values
		System.out.println("(Top, Left): (" + finalTop + ", " + finalLeft + ")");
		System.out.println("(Bottom, Right): (" + finalBottom + ", " + finalRight + ")");
		System.out.println("Max sum is: " + maxSum);
	}

	// Driver program to test above functions
	public static void main(String[] args) {
		int[][] M = { { 1, 2, -1, -4, -20 }, 
				      { -8, -3, 4, 2, 1 },
				      { 3, 8, 10, 1, 3 }, 
				      { -4, -1, 1, 7, -6 }, };
		// findMaxSum(M);
		System.out.print(MaxSumMatrixImprove(M));
	}
}
