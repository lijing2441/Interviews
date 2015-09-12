package interviews;

public class First_Missing_Positive {
	/**
	 * Given an unsorted integer array, find the first missing positive integer.
	 * 
	 * For example, Given [1,2,0] return 3, and [3,4,-1,1] return 2.
	 * 
	 * Your algorithm should run in O(n) time and uses constant space.
	 * 
	 * @logic in-line, two passes
	 */
	public int firstMissingPositive(int[] A) {
		int n = A.length;
		// get all the numbers into their positions, make A[i] = i+1, A[j-1] = j (j = A[i])
		// give them correct positions, 即A[0] = 1, A[1] = 2, ...
		for (int i = 0; i < n; i++) {
			if (A[i] > 0 && A[i] <= n && A[i] != i + 1 && A[A[i] - 1] != A[i]) {
				swap(A, i, A[i] - 1);
				// still need to check whether the current i is in the right position or not
				i--;
			}
		}
		for (int i = 0; i < n; i++) {
			if (A[i] != i + 1)
				return i + 1;
		}
		return n + 1;
	}

	public void swap(int[] num, int i, int j) {
		int tmp = num[i];
		num[i] = num[j];
		num[j] = tmp;
	}
}
