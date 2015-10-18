package interviews;

public class Quick_Sort {
	// Arrays.sort() default method. in place
	// time O(nlogn), space O(1) if stack not included; 
	// space O(logn ~ n) if stack counted
	// the partition makes it unstable, the equal numbers might change positions
	
	public void quickSort(int[] a) {
		quickSort(a, 0, a.length - 1);
	}
	// use the mid point as pivot
	public void quickSortTraditional(int[] a, int left, int right) {
		if (left > right)
			return;
		int i = left;
		int j = right;
		//randomly pick a pivot and we pick midpoint here
		int pivot = left + (right - left) / 2;
		while (i <= j) {
			while (a[i] < a[pivot]) {
				i++;
			}
			while (a[j] > a[pivot]) {
				j--;
			}
			if (i <= j) {
				swap(a, i, j);
				i++;
				j--;
			}
		}
		quickSortTraditional(a, left, j);
		quickSortTraditional(a, i, right);
	}
	
	// improved version, break in the loop
	public void quickSort(int[] a, int left, int right) {
		if (left >= right)
			return;
		int pivot = partition(a, left, right);
		quickSort(a, left, pivot - 1);
		quickSort(a, pivot + 1, right);
	}

	public int partition(int[] a, int left, int right) {
		int i = left;
		int j = right + 1;
		while (true) {
			//use a[left] as pivot
			while (a[++i] < a[left]) {
				if (i == right) break;
			}
			while (a[--j] > a[left]) {
				if (j == left) break;
			}
			if (i >= j) break;
			swap(a, i, j);
		}
		swap(a, left, j);
		return j;
	}

	public void swap(int[] a, int i, int j) {
		int tmp = a[i];
		a[i] = a[j];
		a[j] = tmp;
	}
}
