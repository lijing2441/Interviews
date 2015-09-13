package interviews;

import java.util.Arrays;

public class Three_Sum_Smaller {
	/**
	 * Given an array of n integers nums and a target, find the number of index
	 * triplets i, j, k with 0 <= i < j < k < n that satisfy the condition
	 * nums[i] + nums[j] + nums[k] < target.
	 * 
	 * For example, given nums = [-2, 0, 1, 3], and target = 2.
	 * 
	 * Return 2. Because there are two triplets which sums are less than 2:
	 * 
	 * [-2, 0, 1] [-2, 0, 3]
	 */
	// O(n^2)
	public int threeSumSmaller(int[] nums, int target) {
        int n = nums.length;
        int count = 0;
        Arrays.sort(nums);
        for(int i = 0; i + 2 < n; i++) {
            int j = i + 1, k = n - 1, sum = target - nums[i];
            
            while(j < k) {
                if(nums[j] + nums[k] < sum) {
                    count += (k - j);
                    j++;
                } else {
                    k--;
                }
            }
        }
        return count;
    }
}
