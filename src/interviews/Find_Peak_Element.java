package interviews;

/**
 * A peak element is an element that is greater than its neighbors.
 * 
 * Given an input array where num[i] ≠ num[i+1], find a peak element and return
 * its index.
 * 
 * The array may contain multiple peaks, in that case return the index to any
 * one of the peaks is fine.
 * 
 * You may imagine that num[-1] = num[n] = -∞.
 * 
 * For example, in array [1, 2, 3, 1], 3 is a peak element and your function
 * should return the index number 2.
 *
 * @analysis
 * Binary Search
 * O(logn)
 */
public class Find_Peak_Element {
	public int findPeakElement(int[] num) {
		if (num.length == 0)
			return -1;
		if (num.length == 1)
			return 0;
		int left = 0;
		int right = num.length - 1;
		while (left < right - 1) {
			int mid = (left + right) / 2;
			if (((mid > 0 && num[mid] > num[mid - 1]) || mid == 0) && ((mid < num.length - 1 && num[mid] > num[mid + 1]) || mid == num.length - 1))
				return mid;
			else if ((mid > 0 && num[mid] < num[mid - 1]))
				right = mid;
			else
				left = mid;
		}
		if (num[left] > num[right])
			return left;
		else
			return right;
	}
}
