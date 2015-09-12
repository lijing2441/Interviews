package interviews;

public class Kth_Largest_Element_In_Array {
	/**
	 * Find the kth largest element in an unsorted array. Note that it is the
	 * kth largest element in the sorted order, not the kth distinct element.
	 * 
	 * For example, Given [3,2,1,5,6,4] and k = 2, return 5.
	 * 
	 * Note: You may assume k is always valid, 1 ≤ k ≤ array's length.
	 */
	public int findKthLargest(int[] nums, int k) {
        int[] heap = new int[k];
        for(int i = 0; i < k; i++) {
            heap[i] = nums[i];
        }
        buildMinHeap(heap, k);
        for(int i = k; i < nums.length; i++) {
            if(nums[i] > heap[0]) {
                heap[0] = nums[i];
                minHeapify(heap, k, 0);
            }
        }
        return heap[0];
    }
    public void buildMinHeap(int[] heap, int size) {
        for(int i = (size - 1) / 2; i >= 0; i--) {
            minHeapify(heap, size, i);
        }
    }
    public void minHeapify(int[] heap, int size, int i) {
        int l = 2 * i;
        int r = 2 * i + 1;
        int largest = i;
        if(l < size && heap[l] < heap[largest]) {
            largest = l;
        }
        if(r < size && heap[r] < heap[largest]) {
            largest = r;
        }
        if(largest != i) {
            swap(heap, largest, i);
            minHeapify(heap, size, largest);
        }
    }
    public void swap(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }
}
