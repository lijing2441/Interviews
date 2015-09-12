package interviews;

import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;

public class Subarray_Sum_To_Zero {
	/**
	 * Given [-3, 1, 2, -3, 4], return [0, 2] or [1, 3].
	 */
	public ArrayList<Integer> subarraySum(int[] nums) {
        // write your code here
        ArrayList<Integer> res = new ArrayList<Integer>();
        if (nums == null || nums.length == 0) return res;
        // map stores the sum from start until the value index
        // if we can get the same sum again, the between subarray must have a sum as 0
        Map<Integer, Integer> map = new HashMap<Integer, Integer>();
        int len = nums.length;
        int sum = 0;
        map.put(0, -1); // in case the first element is 0
        for (int i = 0; i < len; i++) {
            sum += nums[i];
            if(map.containsKey(sum)) {
                res.add(map.get(sum) + 1); // get the next index of the one where we get the same sum before, which should be the start
                res.add(i);
                return res;
            }
            map.put(sum, i);
        }
        return res;
	}
	// Another O(n^2) method
	public ArrayList<Integer> subarraySumBad(int[] nums) {
        // write your code here
        ArrayList<Integer> res = new ArrayList<Integer>();
        if (nums == null || nums.length == 0) return res;
        int len = nums.length;
        //int curStart = 0;
        //int curSum = 0;
        for (int i = 0; i < len; i++) {
            int sum = 0;
            for(int j = i; j < len; j++) {
                sum += nums[j];
                if(sum == 0) {
                    res.add(i);
                    res.add(j);
                    return res;
                }
            }
        }
        return res;
    }
}
