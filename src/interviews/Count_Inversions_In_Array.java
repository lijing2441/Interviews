package interviews;

public class Count_Inversions_In_Array {
	/**
	 * Given an array of integer, find the number of unordered pairs in that array. 
	 * Examples:
	 * {1, 3, 2}, the answer is 1 because {3, 2} is unordered,
	 * {3, 2, 1}, the answer is 3 because {3, 2}, {3, 1}, {2, 1}.
	 * 
	 * Follow-up: Can you do it in O(nlogn) time?
	 * 
	 * @idea
	 * Method 1: Divide and Conquer to sort the array
	 * 
	 * Suppose we know the number of inversions in the left and right half of the array. 
	 * We just need to count the inversions between the left and right during merge.
	 * 
	 * In merge process, let i is used for indexing left sub-array and j for right sub-array. 
	 * At any step in merge(), if a[i] is greater than a[j], then there are (mid – i + 1) inversions, 
	 * because left and right sub-arrays are sorted, so all the remaining elements in left sub-array 
	 * (a[i+1], a[i+2] … a[mid]) will be greater than a[j].
	 * 
	 * Modified version of merge sort - O(nlogn)
	 * 
	 */
	public int countInversions(int[] arr){
		if(arr.length < 2) return 0;
		return mergeSort(arr, 0, arr.length - 1);
	}
	public int mergeSort(int[] arr, int left, int right){
		if(left >= right) return 0;
		int count = 0;
		int mid = (left + right) / 2;
		count += mergeSort(arr, left, mid);
		count += mergeSort(arr, mid + 1, right);
		count += merge(arr, left, mid, right);
		return count;
	}
	// in this merge, mid is in the left array
	public int merge(int[] arr, int left, int mid, int right){
		int leftPtr = left;
		int rightPtr = mid + 1;
		// this is the pointer for the new array
		int ptr = left;
		// maintain a count for the reversions
		int count = 0;
		// temp array for merge
		int[] tmp = new int[right - left + 1];
		while(leftPtr <= mid && rightPtr <= right){
			if(arr[leftPtr] <= arr[rightPtr]){
				tmp[ptr++] = arr[leftPtr++];
			}else{
				// we find a reversion here, then all the remaining elements in left
				// should be greater than the current right element, reversions
				tmp[ptr++] = arr[rightPtr++];
				count += (mid - leftPtr + 1);
			}
		}
		// here we need to copy the remaining part to the tmp array
		// and copy the tmp array to the arr, since we are not returning the array here
		while(leftPtr <= mid){
			tmp[ptr++] = arr[leftPtr++];
		}
		while(rightPtr <= right){
			tmp[ptr++] = arr[rightPtr++];
		}
		for(int i = left; i <= right; i++){
			arr[i] = tmp[i];
		}
		return count;
	}
	
	
	/**
	 * Method 2: It is possible to solve this problem in O(n log n) time using a
	 * self-balanced binary search tree. Pseudo-code of this algorithm:
	 * 
	 * tree = an empty self-balanced binary search tree (or sorted dynamic array)
	 * answer = 0 
	 * for each element in the array: 
	 * 		answer += number of the elements in the tree greater then this element 
	 * 		add this element to the tree
	 */
	
	/**
	 * brute force version: for each element, get the number of elements larger smaller than the current element
	 * 
	 * O(n^2)
	 */
	public int bruteForce(int[] arr){
		if(arr.length < 2) return 0;
		int count = 0;
		for(int i = 0; i < arr.length; i++){
			for(int j = i + 1; j < arr.length; j++){
				if(arr[i] > arr[j]) count++;
			}
		}
		return count;
	}
	
}
