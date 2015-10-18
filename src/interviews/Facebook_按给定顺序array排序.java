package interviews;

public class Facebook_按给定顺序array排序 {
	/**
	 * 2 arrays, 第一个为actual number，第二个array为第一个array的每个元素对应的目标position，
	 * 求如何根据第二个position将第一个array排序
	 * 
	 * arr1: {5, 0, 3, 2, 8}
	 * arr2: {3, 0, 2, 1, 4}
	 * 
	 * => {0, 2, 3, 5, 8}
	 */
	public static int[] getSortedArray(int[] arr1, int[] arr2) {
		if (arr1.length != arr2.length) {
			System.out.println("Illegal input");
			return new int[0];
		}
		int len = arr1.length;
		for (int i = 0; i < len; i++) {
			if (arr2[i] != i) {
				int otherIndex = arr2[i];
				swap(arr2, i, otherIndex);
				swap(arr1, i, otherIndex);
				i--;
			}
		}
		return arr1;
	}
	public static void swap(int[] arr, int i, int j) {
		int tmp = arr[i];
		arr[i] = arr[j];
		arr[j] = tmp;
	}
	public static void main(String[] args) {
		int[] arr1 = {5, 0, 3, 2, 8};
		int[] arr2 = {3, 0, 2, 1, 4};
		for (int i : getSortedArray(arr1, arr2)) {
			System.out.println(i);
		}
	}
}
