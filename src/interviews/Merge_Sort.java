package interviews;

/**
 * Merge sort can be used in external sorting 
 * (sorting of very large data files where it is not possible to read in
 * all data records into the main memory of the computer).
 *
 */

/**
 * Sorting in-place (without the extra storage) is possible if the data are
 * organized as linked lists instead of arrays.
 * 
 * @author Tina
 */
public class Merge_Sort {
	// space O(n), time O(nlogn)
	// if already sorted, it makes nlogn/2 compares
	// if check a[mid] <= a[mid + 1], only required n compares
	public void mergeSort(int[] a) {
		mergeSort(a, 0, a.length - 1);
	}

	public void mergeSort(int[] a, int left, int right) {
		if (left > right)
			return;
		int mid = left + (right - left) / 2;
		mergeSort(a, left, mid);
		mergeSort(a, mid + 1, right);
		if (a[mid] < a[mid + 1]) return; // optimized!!
		merge(a, left, mid, right);
	}

	public void merge(int[] a, int left, int mid, int right) {
		int i = left;
		int j = mid;
		int k = left;
		int[] tmp = new int[a.length];
		while (i <= mid && j <= right) {
			if (a[i] <= a[j]) {
				tmp[k++] = a[i++];
			} else {
				tmp[k++] = a[j++];
			}
		}
		while (i <= mid) {
			tmp[k++] = a[i++];
		}
		while (j <= right) {
			tmp[k++] = a[j++];
		}
		for (int index = left; index <= right; index++) {
			a[index] = tmp[index];
		}
	}

	// iterative method, industrial-strength method, if you have the space
	public void mergeSortIte(int[] a) {
		int n = a.length;
		for (int sz = 1; sz < n; sz += sz) {
			for (int left = 0; left < n - sz; left += 2 * sz) {
				merge(a, left, left + sz - 1, Math.min(left + 2 * sz - 1, n - 1));
			}
		}
	}
}
