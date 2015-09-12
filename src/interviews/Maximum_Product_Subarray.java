package interviews;

public class Maximum_Product_Subarray {
	/**
	 * Find the contiguous sub-array within an array (containing at least one
	 * number) which has the largest product.
	 * 
	 * For example, given the array [2,3,-2,4], the contiguous sub-array [2,3]
	 * has the largest product = 6.
	 */
	public int maxProduct(int[] A) {
		if (A == null || A.length == 0)
			return 0;
		int max = A[0], min = A[0], res = A[0];
		for (int i = 1; i < A.length; i++) {
			//because I first calculate max, the value will change
			//I need to store the original value first in this way
			int tmp = max;
			max = Math.max(Math.max(tmp * A[i], min * A[i]), A[i]);
			min = Math.min(Math.min(tmp * A[i], min * A[i]), A[i]);
			if (max > res)
				res = max;
		}
		return res;
	}
}
