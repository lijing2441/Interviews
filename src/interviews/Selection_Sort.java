package interviews;

// in iteration i, find index min of smallest remaining entry
// swap a[i] and a[min]
// n^2 / 2
// for almost sorted, it still costs that much time
public class Selection_Sort {
	public void selectionSort(int[] a) {
		for (int i = 0; i < a.length; i++) {
			int min = i;
			for (int j = i + 1; j < a.length; j++) {
				if (a[min] > a[j]) {
					min = j;
				}
			}
			int tmp = a[i];
			a[i] = a[min];
			a[min] = tmp;
		}
	}
}
