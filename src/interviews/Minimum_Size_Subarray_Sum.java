package interviews;

public class Minimum_Size_Subarray_Sum {
	/**
	 * Given an array of n positive integers and a positive integer s, find the
	 * minimal length of a subarray of which the sum ≥ s. If there isn't one,
	 * return 0 instead.
	 * 
	 * For example, given the array [2,3,1,2,4,3] and s = 7, the subarray [4,3]
	 * has the minimal length under the problem constraint.
	 */
	public int minSubArrayLen(int s, int[] nums) {
        int start = 0, sum = 0;
        int minLen = Integer.MAX_VALUE;
        for(int i = 0; i < nums.length; i++) {
            sum += nums[i];
            while(sum >= s) {
                if(minLen > i - start + 1) minLen = i - start + 1;
                sum -= nums[start];
                start++;
            }
        }
        if(minLen == Integer.MAX_VALUE) return 0;
        else return minLen;
    }
}
