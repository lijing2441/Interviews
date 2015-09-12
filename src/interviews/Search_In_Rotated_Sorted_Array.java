package interviews;

public class Search_In_Rotated_Sorted_Array {
	// O(logn), binary search, constant space, worse case O(n).
	// rotated => at least half is sorted
	public int search(int[] A, int target) {
		int left = 0;
		int right = A.length - 1;
		while (left <= right) {
			int mid = left + (right - left) / 2;
			// find the target
			if (A[mid] == target)
				return mid;
			// if left <= mid, then left to mid is sorted
			if (A[left] <= A[mid]) {
				if (A[left] <= target && A[mid] >= target) {
					right = mid - 1;
				} else {
					left = mid + 1;
				}
			} else {
				// mid to right is sorted
				if (A[mid] <= target && A[right] >= target) {
					left = mid + 1;
				} else {
					right = mid - 1;
				}
			}
		}
		return -1;
	}
	
	/**
	 * II: if duplicates allowed
	 */
	public boolean searchII(int[] A, int target) {
        if(A.length == 0) return false;
        int l = 0;
        int r = A.length - 1;
        while(l <= r){
            int mid = (l + r) / 2;
            if(A[mid] == target) return true;
            if(A[l] < A[mid]){
                if(A[l] <= target && target <= A[mid]){
                    r = mid;
                }else{
                    l = mid + 1;
                }
            }else if(A[mid] < A[l]){
                if(A[mid] <= target && target <= A[r]){
                    l = mid;
                }else{
                    r = mid - 1;
                }
            }else{
                l++;
            }
        }
        return false;
    }

	// find the offset of the rotated sorted array
	public static int findPivot(int[] A) {
		return findPivot(A, 0, A.length - 1);
	}

	public static int findPivot(int[] a, int left, int right) {
		if (left > right)
			return -1;
		if (left == right)
			return left;
		int mid = left + (right - left) / 2;
		if (a[mid] > a[mid + 1] && mid < right) {
			return mid;
		} else if (a[mid] < a[mid - 1] && mid > left) {
			return mid;
		}
		if (a[left] > a[mid])
			return findPivot(a, left, mid - 1);
		else
			return findPivot(a, mid + 1, right);
	}

	public static void main(String[] args) {
		int[] a = { 4, 5, 1, 2, 3 };
		System.out.println(findPivot(a));
	}

}
