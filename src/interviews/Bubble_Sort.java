package interviews;

public class Bubble_Sort {
	/**
	 * Bubble up
	 */
	public void bubbleSort1(int[] arr) {
		int n = arr.length;
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n - i - 1; j++) {
				if (arr[j] > arr[j + 1]) { /* For descending order use */
					int tmp = arr[j];
					arr[j] = arr[j + 1];
					arr[j + 1] = tmp;
				}
			}
		}
	}

	// use a flag
	public void bubbleSort2(int[] arr) {
		int n = arr.length;
		boolean swapped = true;
		for (int i = 1; i < n && swapped; i++) {
			swapped = false;
			for (int j = 0; j < n - i; j++) {
				if (arr[j] > arr[j + 1]) {
					swapped = true;
					int tmp = arr[j];
					arr[j] = arr[j + 1];
					arr[j + 1] = tmp;
				}
			}
		}
	}
}
