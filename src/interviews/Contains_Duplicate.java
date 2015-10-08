package interviews;

import java.util.Map;
import java.util.HashMap;
import java.util.TreeSet;

public class Contains_Duplicate {
	/**
	 * Given an array of integers and an integer k, find out whether there there
	 * are two distinct indices i and j in the array such that nums[i] = nums[j]
	 * and the difference between i and j is at most k.
	 */
	public boolean containsNearbyDuplicate(int[] nums, int k) {
		Map<Integer, Integer> map = new HashMap<Integer, Integer>();
		for (int i = 0; i < nums.length; i++) {
			if (map.containsKey(nums[i]) && (i - map.get(nums[i]) <= k)) {
				return true;
			}
			map.put(nums[i], i);
		}
		return false;
	}

	/**
	 * Follow-up: Given an array of integers, find out whether there are two
	 * distinct indices i and j in the array such that the difference between
	 * nums[i] and nums[j] is at most t and the difference between i and j is at
	 * most k.
	 */
	public boolean containsNearbyAlmostDuplicate(int[] nums, int k, int t) {
        if(nums == null || nums.length == 0 || k <= 0 || t < 0) return false;
        
        TreeSet<Integer> set = new TreeSet<Integer>();
        
        for(int i = 0; i < nums.length; i++) {
            int cur = nums[i];
            Integer floor = set.floor(cur + t);
            Integer ceiling = set.ceiling(cur - t);
            if((floor != null && floor >= cur) || (ceiling != null && ceiling <= cur)) {
                return true;
            }
            set.add(cur);
            if(i >= k) {
                set.remove(nums[i - k]);
            }
        }
        return false;
    }
}
