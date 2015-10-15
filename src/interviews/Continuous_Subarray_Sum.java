package interviews;

import java.util.ArrayList;

public class Continuous_Subarray_Sum {
	// find the biggest sum subarray, return the start and end index
	public ArrayList<Integer> continuousSubarraySum(int[] A) {
        if (A == null || A.length == 0) return new ArrayList<Integer>();
        int len = A.length;
        int maxSoFar = A[0], maxUntilHere = A[0];
        int maxStart = 0, maxEnd = 0, start = 0;
        for (int i = 1; i < len; i++) {
            if (maxUntilHere < 0) {
                maxUntilHere = A[i];
                start = i;
            } else {
                maxUntilHere += A[i];
            }
            if (maxUntilHere > maxSoFar) {
                maxSoFar = maxUntilHere;
                maxStart = start;
                maxEnd = i;
            }
        }
        ArrayList<Integer> res = new ArrayList<Integer>();
        res.add(maxStart);
        res.add(maxEnd);
        return res;
    }
	
	/**
	 * Problem II: find a continuous rotate subarray where the sum of numbers is the biggest.
	 */
	public ArrayList<Integer> continuousSubarraySumII(int[] A) {
        if (A == null || A.length == 0) return new ArrayList<Integer>();
        int len = A.length;
        int local = A[0], global = A[0];
        int maxStart = 0, maxEnd = 0, start = 0;
        int sum = A[0];
        for (int i = 1; i < len; i++) {
            sum += A[i];
            if (local < 0) {
                start = i;
                local = A[i];
            } else {
                local += A[i];
            }
            if (local > global) {
                global = local;
                maxStart = start;
                maxEnd = i;
            }
        }
        // 再扫一遍，找最小的subarray，若减去比global大，则更新
        local = 0;
        start = 0;
        int end = -1;
        for (int i = 0; i < len; i++) {
            if (local > 0) {
                start = end = i;
                local = A[i];
            } else {
                local += A[i];
                end = i;
            }
            if (start == 0 || end == len - 1) continue;
            if (sum - local > global) { // 找余下最大
                global = sum - local;
                maxStart = (end + 1) % len;
                maxEnd = (len + start - 1) % len; // 注意避免负数
            }
        }
        ArrayList<Integer> res = new ArrayList<Integer>();
        res.add(maxStart);
        res.add(maxEnd);
        return res;
    }
}
