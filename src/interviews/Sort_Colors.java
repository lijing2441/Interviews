package interviews;

public class Sort_Colors {
	/**
	 * Given an array with n objects colored red, white or blue, sort them so
	 * that objects of the same color are adjacent, with the colors in the order
	 * red, white and blue. Here, we will use the integers 0, 1, and 2 to
	 * represent the color red, white, and blue respectively.
	 **/
	// O(n), O(1)
	// we use j as the index of 0, k as the index of 2, then use the i to sort the list
	public void sortColors(int[] A) {
		if (A == null || A.length < 2)
			return;
		int j = 0;
		int k = A.length;
		for (int i = 0; i < k; i++) {
			if (A[i] == 0) {
				swap(A, j, i);
				j++;
			} else if (A[i] == 2) {
				// since we scan from the beginning
				// we have not check whether the swapped element is 2 or not
				// so i-- to check it again
				k--;
				swap(A, k, i);
				i--;
			}
		}
	}

	public void swap(int[] num, int a, int b) {
		int tmp = num[a];
		num[a] = num[b];
		num[b] = tmp;
	}
}
