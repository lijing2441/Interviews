package interviews;

import java.util.ArrayList;

public class Minimal_Subarray {
	/**
	 * For [1, -1, -2, 1], return -3
	 * 
	 */
	public int minSubArray(ArrayList<Integer> nums) {
        // write your code
        if (nums == null || nums.size() == 0) return 0;
        int len = nums.size();
        int minSoFar = nums.get(0), minEndHere = nums.get(0);
        for (int i = 1; i < len; i++) {
            if(minEndHere > 0) {
                minEndHere = nums.get(i);
            } else {
                minEndHere += nums.get(i);
            }
            if(minEndHere < minSoFar) minSoFar = minEndHere;
        }
        return minSoFar;
    }
}
