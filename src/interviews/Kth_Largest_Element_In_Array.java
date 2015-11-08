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
	public int findKthLargestHeap(int[] nums, int k) {
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
        int l = 2 * i + 1;
        int r = 2 * i + 2;
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
    
    /**
     * Method 2: Quick Selection. Average O(n) time, worst case O(n^2), O(1) extra space
     */
    public int findKthLargest(int[] nums, int k) {
        return helper(nums, 0, nums.length - 1, nums.length - k + 1);
    }
    public int helper(int[] nums, int start, int end, int k) {
        int pivot = nums[start];
        int l = start + 1, r = end;
        while (l <= r) {
            while (l <= r && nums[l] <= pivot) {
                l++;
            }
            while (l <= r && nums[r] > pivot) {
                r--;
            }
            if (l < r) {
                swap(nums, l, r);
                l++;
                r--;
            }
        }
        swap(nums, start, r);
        if (r + 1 == k) return nums[r];
        else if (r + 1 < k) {
            return helper(nums, r + 1, end, k);
        } else {
            return helper(nums, start, r - 1, k);
        }
    }
}
