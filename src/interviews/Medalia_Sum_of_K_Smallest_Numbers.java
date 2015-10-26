package interviews;

public class Medalia_Sum_of_K_Smallest_Numbers {
	// one way is to use heap: nlogk
	// another way is to find the k-th smallest X and then iterate through the list
	// adding only the numbers less than X: O(n)
	public static int getSumOfKSmallest(int[] arr, int k) {
		int kth = getKthSmallest(arr, k);
		System.out.println(kth);
		int res = kth;
		int count = 1;
		for (int i : arr) {
			if (i < kth) {
				res += i;
				count++;
			}
		}
		while (count < k) {
			res += kth;
			count++;
		}
		return res;
	}
	public static int getKthSmallest(int[] arr, int k) {
		return kthHelper(arr, 0, arr.length - 1, k);
	}
	public static int kthHelper(int[] arr, int start, int end, int size) {
		int pivot = arr[start];
		int left = start + 1, right = end;
		while (left <= right) {
			while (left <= right && arr[left] < pivot) {
				left++;
			}
			while (left <= right && arr[right] >= pivot) {
				right--;
			}
			if (left < right) {
				swap(arr, left, right);
			}
		}
		swap(arr, start, right);
		if (size == right + 1) {
			return arr[right];
		} else if (size > right + 1) {
			return kthHelper(arr, right + 1, end, size);
		} else {
			return kthHelper(arr, start, right - 1, size);
		}
	}
	
	public static void swap(int[] arr, int i, int j) {
		int tmp = arr[i];
		arr[i] = arr[j];
		arr[j] = tmp;
	}
	
	public static void main(String[] args) {
		int[] arr = {3, 4, 2, 7, 9, 1, 3};
		System.out.println(getSumOfKSmallest(arr, 5) + ", which should be 13.");
	}
}
