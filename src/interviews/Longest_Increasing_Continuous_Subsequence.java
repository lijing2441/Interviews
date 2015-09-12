package interviews;

public class Longest_Increasing_Continuous_Subsequence {
	/**
	 * Give you an integer array (index from 0 to n-1, where n is the size of
	 * this array)，find the longest increasing continuous subsequence in this
	 * array. (The definition of the longest increasing continuous subsequence
	 * here can be from right to left or from left to right)
	 * 
	 * Example For [5, 4, 2, 1, 3], the LICS is [5, 4, 2, 1], return 4.
	 * 
	 * For [5, 1, 2, 3, 4], the LICS is [1, 2, 3, 4], return 4.
	 * 
	 * Note O(n) time and O(1) extra space.
	 */
	public int longestIncreasingContinuousSubsequence(int[] A) {
        // Write your code here
        if (A == null) return 0;
        int len = A.length;
        if (len <= 2) return len;
        boolean increasing = (A[0] < A[1]);
        int maxSoFar = 2, maxEndHere = 2;
        for (int i = 2; i < len; i++) {
            int cur = A[i];
            if (increasing) {
                if(cur >= A[i - 1]) maxEndHere++;
                else {
                    maxEndHere = 2;
                    increasing = false;
                }
            } else {
                if (cur <= A[i - 1]) maxEndHere++;
                else {
                    maxEndHere = 2;
                    increasing = true;
                }
            }
            if(maxEndHere > maxSoFar) maxSoFar = maxEndHere;
        }
        return maxSoFar;
    }
}
