package interviews;

public class Unique_Mapping_Nut_and_Bolt {
	/**
	 * Given an array of object A, and an array of object B. All A's have different sizes, 
	 * and all B's have different sizes. Any object A is of the same size as exactly one object B. 
	 * We have a function f(A, B) to compare the size of one A and one B. But we cannot compare 
	 * between two A's or two B's. Give an algorithm to match each A with each B.
	 * 
	 * @analysis This problem is basically [matching Nuts and bolts]. 
	 * 
	 * Solution to this problem is with help of quick-sort. 
	 * 1. pick any object A randomly, then compare it with all B to find the exact match for it. 
	 * 	  This pivot in quicksort.
	 * 2. While searching for match divide B into three parts with respect to pivot: 
	 * 	  (a) all smaller object (of type B) than pivot on left side (call it B_small)
	 * 	  (b) the matching B
	 *    (c) and all larger object (of type B) on right side (B_large)
	 *    
	 * 3. Then we can use the matching B to partition A into A_small, A and A_large
	 * 
	 * Complexity will be (n log n), worst case O(n^2)
	 * 
	 */
	// we sort them, then the elements should be paired one by one
	public void uniqueMapping(int[] A, int[] B){
		if(A.length != B.length) return;
		int left = 0;
		int right = A.length - 1;
		quickSortTwoArrays(A, B, left, right);
	}
	public void quickSortTwoArrays(int[] A, int[] B, int left, int right){
		if (left >= right)
			return;
		// partition B first, using A[left] as pivot
		int Bindex = partition(B, A[left], left, right);
		// partition A using B[Bindex] as pivot
		partition(A, B[Bindex], left, right);
		// since there must be a matching between each object in A and B
		// the relative position must be same
		quickSortTwoArrays(A, B, left, Bindex);
		quickSortTwoArrays(A, B, Bindex + 1, right);
	}
	
	// return the pivot position in the array
	public int partition(int[] arr, int pivot, int left, int right) {
		int i = left;
		int j = right;
		int matchingIdx = -1;
		while (i <= j) {
			while (arr[i] <= pivot) {
				if(arr[i] == pivot){
					matchingIdx = i;
				}
				i++;
				if(i == right) break;
			}
			while (arr[j] >= pivot) {
				if(arr[j] == pivot){
					matchingIdx = j;
				}
				j--;
				if(j == left) break;
			}
			if (i <= j) {
				swap(arr, i, j);
				i++;
				j--;
			}
		}
		// since we pass the pivot during the partition
		// we need to adjust it to the right the position
		// (1) it's bigger than the smaller one and smaller than the bigger one
		// we do not need to swap any more.
		if(matchingIdx < i && matchingIdx > j) return matchingIdx;  
		// (2) it's in the smaller side
		else if(matchingIdx < i && matchingIdx < j){
			swap(arr, j, matchingIdx);
			return j;
		}
		// (3) it's in the bigger side
		else{
			swap(arr, i, matchingIdx);
			return i;
		}
	}	
	
	public void swap(int[] a, int i, int j){
		int tmp = a[i];
		a[i] = a[j];
		a[j] = tmp;
	}

	// return positive, a>b; negative, a<b; 0, a==b
	public int f(int a, int b) {
		return 0;
	}
}
