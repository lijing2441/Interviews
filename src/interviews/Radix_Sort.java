package interviews;

import java.util.LinkedList;

public class Radix_Sort {
	/**
	 * Radix sort complexity is O(w * n) for n keys which are integers of word size w. (百位为3，千位为4)
	 * 非比较性排序，O(w + n) complexity
	 * @param data
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void radixSort(int[] data) {
		boolean flag = true;
		int divisor = 1;
		LinkedList[] buckets = new LinkedList[10];
		for (int i = 0; i < 10; i++)
			buckets[i] = new LinkedList();

		while (flag) {
			flag = false;
			// first copy the values into buckets
			for (int i = 0; i < data.length; i++) {
				int hashIndex = (data[i] / divisor) % 10;
				if (hashIndex > 0)
					flag = true;
				buckets[hashIndex].addLast(new Integer(data[i]));
			}
			// then copy the values back into vector
			divisor *= 10;
			int i = 0;
			for (int j = 0; j < 10; j++) {
				while (!buckets[j].isEmpty()) {
					Integer ival = (Integer) buckets[j].getFirst();
					buckets[j].removeFirst();
					data[i++] = ival.intValue();
				}
			}
		}
	}
}
