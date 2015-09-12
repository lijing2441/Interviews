package interviews;

public class K_Sort {
	/**
	 * Mathod1: Use insertion sort. The inner loop will run at most k times. 
	 * To move every element to its correct place, at most k elements need to be moved. 
	 * So overall complexity will be O(nk)
	 */
	public void insertionSort(int[] a) {
		for (int i = 1; i < a.length; i++) {
			int tmp = a[i];
			int j = Integer.MIN_VALUE; // any value to initialize it
			/*
			 * Move elements of A[0..i-1], that are greater than key, to one
			 * position ahead of their current position. This loop will run at
			 * most k times
			 */
			for (j = i - 1; j >= 0 && a[j] > tmp; j--) {
				a[j + 1] = a[j];
			}
			a[j + 1] = tmp;
		}
	}

	/**
	 * Method 2: Heap
	 * 既然是k sort，则k+1个neighbor元素中，最小的那个必定为correct position中的第一个，
	 * 把它放到correct position依次。
	 * 
	 * 1. build a Min Heap of size k+1 with first k+1 elements. This will take O(k) time. 
	 * 2. One by one remove min element from heap, put it in result
	 * array, and add a new element to heap from remaining elements.
	 * 
	 * Removing an element and adding a new element to min heap will take Logk
	 * time. So overall complexity will be O(k) + O((n-k)*logK)
	 */

	public static int[] sortK(int arr[], int n, int k) {
		// Create a Min Heap of first (k+1) elements from input array
		int[] heap = new int[k + 1];
		for (int i = 0; i <= k && i < n; i++)
			// i < n condition is needed when k > n
			heap[i] = arr[i];
		// 为前k+1个元素建最小堆，现在最小的在堆顶，以下的都比top大
		buildMinHeap(heap, k + 1);

		// i is index for remaining elements in arr[] and ti is target index of current top of MinHeap.
		// we make the current top to the target index of the arr, which should be its correct position
		for (int i = k + 1, ti = 0; ti < n; i++, ti++) {
			// array内仍有元素没有remaining，没有入过堆，把堆顶放到依次ti的位置，然后把当前元素入堆，此时堆体积不变
			// place root of heap at target index and add arr[i] to Min Heap
			// In this process, we deal with the remaining elements after we build the k-size minHeap
			if (i < n)
				arr[ti] = replaceMin(heap, arr[i]);

			// Otherwise place root at its target index and reduce heap size
			else
				arr[ti] = extractMin(heap);
		}
		return arr;
	}
	/**
	 * 以下两个function均返回堆顶，即当前最小值。replace中堆size不变； extract中size-1
	 */
	
	public static int extractMin(int[] heap) {
		int root = heap[0];
		// 把叶子结点换上来做堆顶，然后return 原来的堆顶value
		if (heap.length > 1) {
			heap[0] = heap[heap.length-1];
			minHeapify(heap, heap.length, 0);
		}
		return root;
	}
	// return 当前堆顶，然后把given x入堆
	public static int replaceMin(int[] heap, int x) {
		int root = heap[0];
		heap[0] = x;
		// 当前x比原来root大，则new top可能不是真正堆顶，需调整
		if (root < x) {
			minHeapify(heap, heap.length, 0);
		}
		return root;
	}

	public static void buildMinHeap(int[] a, int size) {
		for (int i = (size - 1) / 2; i >= 0; i--) {
			minHeapify(a, size, i);
		}
	}

	public static void minHeapify(int[] a, int size, int i) {
		int l = 2 * i;
		int r = 2 * i + 1;
		int smallest = i;
		if (l < size && a[l] < a[smallest]) {
			smallest = l;
		}
		if (r < size && a[r] < a[smallest]) {
			smallest = r;
		}
		if (smallest != i) {
			swap(a, i, smallest);
			minHeapify(a, size, smallest);
		}
	}

	public static void swap(int[] a, int i, int j) {
		int tmp = a[i];
		a[i] = a[j];
		a[j] = tmp;
	}

	public static void printArray(int arr[]) {
		for (int i = 0; i < arr.length; i++)
			System.out.print(arr[i] + " ");
	}

	public static void main(String[] args) {
		int k = 3;
		int[] arr = { 2, 6, 3, 12, 56, 8 };
		int n = arr.length;
		sortK(arr, n, k);

		printArray(arr);
	}
}
