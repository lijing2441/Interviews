package interviews;

import java.util.Arrays;

public class Subarray_Sum_II {
	/**
	 * Given [1,2,3,4] and interval = [1,3], return 4. The possible answers are:
	 * 
	 * [0, 0] [0, 1] [1, 1] [2, 2]
	 */
	public int subarraySumII(int[] A, int start, int end) {
		if (A == null || A.length == 0)
			return 0;
		int len = A.length;
		int[] sum = new int[len + 1];
		for (int i = 1; i <= len; i++) {
			sum[i] = sum[i - 1] + A[i - 1];
		}
		int count = 0;
		for (int i = 0; i < len; i++) {
			for (int j = i + 1; j <= len; j++) {
				int cur = sum[j] - sum[i];
				if (cur >= start && cur <= end) {
					count++;
				}
			}
		}
		return count;
	}

	// second method O(nlogn)
	int find(int[] A, int len, int value) {
		if (A[len - 1] < value)
			return len;

		int l = 0, r = len - 1, ans = 0;
		while (l <= r) {
			int mid = (l + r) / 2;
			if (value <= A[mid]) {
				ans = mid;
				r = mid - 1;
			} else
				l = mid + 1;
		}
		return ans;
	}

	public int subarraySumIISort(int[] A, int start, int end) {
		int len = A.length;
		for (int i = 1; i < len; ++i)
			A[i] += A[i - 1];

		Arrays.sort(A);
		int cnt = 0;
		for (int i = 0; i < len; ++i) {
			if (A[i] >= start && A[i] <= end)
				cnt++;
			int l = A[i] - end;
			int r = A[i] - start;
			cnt += find(A, len, r + 1) - find(A, len, l);
		}
		return cnt;
	}
}
