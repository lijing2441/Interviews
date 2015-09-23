package interviews;

public class Uber_find_meeting_overlap {
	/**
	 * Find the max element in unsorted array (不sort)
	 */
	public static int maxInUnsortedArr (int[] arr) {
		if (arr == null || arr.length == 0) {
			return Integer.MIN_VALUE;
		}
		int max = Integer.MIN_VALUE;
		for (int i: arr) {
			if (i > max) {
				max = i;
			}
		}
		return max;
	}
	
	/**
	 * 有一大堆meeting， 有start time和end time，都是integer，且在一天之内，代表一天内的第几分钟。
	 * 然后让返回boolean代表这些是否有overlap.
	 * 
	 * 提示了一下说用一个数组。然后楼主恍然大悟，就开了个1440大小的bit的数组写了个O(n)的。
	 */
	public boolean isOverlap(Interval[] meetings) {
		if (meetings == null || meetings.length < 2) {
			return false;
		}
		boolean[] check = new boolean[1440];
		for (Interval interval : meetings) {
			int start = interval.start;
			int end = interval.end;
			for (int i = start; i <= end; i++) {
				if (check[i - 1]) return true;
				else {
					check[i - 1] = true;
				}
			}
		}
		return false;
	}
}

