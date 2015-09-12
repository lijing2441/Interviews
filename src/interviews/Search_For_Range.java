package interviews;

public class Search_For_Range {
	// O(logn)
	// space O(logn) if stack counted
	public int[] searchRange(int[] A, int target) {
		int[] res = new int[2];
		int first = searchFirst(A, target, 0, A.length - 1);
		int last = searchLast(A, target, 0, A.length - 1);
		res[0] = first;
		res[1] = last;
		return res;
	}

	public int searchFirst(int[] a, int target, int left, int right) {
		if (left > right) {
			return -1;
		}
		int mid = left + (right - left) / 2;
		if (a[mid] == target && (mid == 0 || a[mid - 1] < target)) {
			return mid;
		} else if (a[mid] >= target) {
			return searchFirst(a, target, left, mid - 1);
		} else {
			return searchFirst(a, target, mid + 1, right);
		}
	}

	public int searchLast(int[] a, int target, int left, int right) {
		if (left > right) {
			return -1;
		}
		int mid = left + (right - left) / 2;
		if (a[mid] == target && (mid == a.length - 1 || a[mid + 1] > target)) {
			return mid;
		} else if (a[mid] <= target) {
			return searchLast(a, target, mid + 1, right);
		} else {
			return searchLast(a, target, left, mid - 1);
		}
	}
}
