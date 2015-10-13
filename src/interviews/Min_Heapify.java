package interviews;

public class Min_Heapify {
	// 最小堆heapify
	public void heapify(int[] A) {
        for (int i = A.length / 2; i >= 0; i--) {
            heapify(A, i);
        }
    }
    public void heapify(int[] num, int i) {
        while (i < num.length) {
            int left = 2 * i + 1;
            int right = 2 * i + 2;
            int smallest = i;
            if (left < num.length && num[smallest] > num[left]) {
                smallest = left;
            }
            if (right < num.length && num[smallest] > num[right]) {
                smallest = right;
            }
            if (i == smallest) break;
            else {
                swap(num, i, smallest);
                i = smallest;
            }
        }
    }
    public void swap(int[] num, int i, int j) {
        int tmp = num[i];
        num[i] = num[j];
        num[j] = tmp;
    }
}
