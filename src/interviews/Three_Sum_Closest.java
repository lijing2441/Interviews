package interviews;

import java.util.Arrays;

public class Three_Sum_Closest {
	/**
	 * Given an array S of n integers, find three integers in S such that the
	 * sum is closest to a given number, target. Return the sum of the three
	 * integers. You may assume that each input would have exactly one solution.
	 * 
	 * For example, given array S = {-1 2 1 -4}, and target = 1.
	 * 
	 * The sum that is closest to the target is 2. (-1 + 2 + 1 = 2).
	 */
	public int threeSumClosest(int[] num, int target) {
        Arrays.sort(num);
        int n = num.length;
        int curClosest = Integer.MAX_VALUE;
        int curDist = Integer.MAX_VALUE;
        for(int i = 0; i < n - 2; i++){
            int j = i + 1;
            int k = n - 1;
            while(j < k){
                int sum = num[i] + num[j] + num[k];
                if(sum == target){
                    return sum;
                }else if(sum > target){
                    if(Math.abs(sum - target) < curDist){
                        curDist = Math.abs(sum - target);
                        curClosest = sum;
                    }
                    k--;
                }else{
                    if(Math.abs(sum - target) < curDist){
                        curDist = Math.abs(sum - target);
                        curClosest = sum;
                    }
                    j++;
                }
            }
        }
        return curClosest;
    }
}
