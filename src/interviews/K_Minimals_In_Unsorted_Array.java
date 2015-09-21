package interviews;

import java.util.Arrays;

public class K_Minimals_In_Unsorted_Array {
	// using max heap, O(k) space
	// Time: O(nlogk) time
	public static int[] kthSmallest2(int[] a, int k) {
		int[] maxHeap = new int[k];
		for (int i = 0; i < k; i++) {
			maxHeap[i] = a[i];
		}
		buildMaxHeap(maxHeap, k);

		for (int i = k; i < a.length; i++) {
			if (a[i] < maxHeap[0]) {
				maxHeap[0] = a[i];
				maxHeapify(maxHeap, k, 0);
			}
		}
		return maxHeap;
	}
	// build heap, you have |size| elements and build a[] as a heap
	public static void buildMaxHeap(int[] a, int size) {
		for (int i = (size - 1) / 2; i >= 0; i--) {
			maxHeapify(a, size, i);
		}
	}
	
	// heapify the heap using index = i element as the top
	// build the heap from bottom, so it does not sift down, but sift up
	public static void maxHeapify(int[] a, int size, int i) {
		int l = 2 * i;
		int r = 2 * i + 1;
		int largest = i;
		if (l < size && a[l] > a[largest]) {
			largest = l;
		}
		if (r < size && a[r] > a[largest]) {
			largest = r;
		}
		if (largest != i) {
			swap(a, i, largest);
			maxHeapify(a, size, largest);
		}
	}

	public static void swap(int[] a, int i, int j) {
		int tmp = a[i];
		a[i] = a[j];
		a[j] = tmp;
	}

	// brute force, sort and return
	public static int[] kthSmallest(int[] a, int k) {
		Arrays.sort(a);
		int[] res = new int[k];
		for (int i = 0; i < k; i++) {
			res[i] = a[i];
		}
		return res;
	}

	public static void main(String[] args) {
		int[] a = { 100, 25, 6, 3, 102, 2, 2000, 45, 7, 3, 68 };
		int[] ksmallest = kthSmallest2(a, 5);
		for (int i = 0; i < ksmallest.length; i++) {
			System.out.print(ksmallest[i] + " ");
		}
	}
}
