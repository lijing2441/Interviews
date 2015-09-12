package interviews;

import java.util.Arrays;

public class Maximum_Gap {
	/**
	 * Given an unsorted array, find the maximum difference between the
	 * successive elements in its sorted form.
	 * 
	 * Try to solve it in linear time/space. Return 0 if the array contains less than 2 elements.
	 * 
	 * You may assume all elements in the array are non-negative integers and
	 * fit in the 32-bit signed integer range.
	 * 
	 * @analysis 
	 * The idea is to split the range [min, max] evenly int n-1 intervals. 
	 * The interval length would be (max - min) / (n - 1). 
	 * 
	 * According to the pigeon hole principle, the max range cannot be smaller than (max - min)/(n-1). 
	 * Which means if there are two numbers generates the max difference, they must be in different 
	 * intervals. Then we just need to compute the max difference between successive elements that 
	 * lies in adjacent intervals. 
	 * 
	 * To do that, we just need to record min and max value for each interval.
	 */
	public int maximumGap(int[] num) {
        if(num.length < 2) return 0;
        
        int len = num.length;
        int max = num[0];
        int min = num[0];
        
        for(int i : num){
            min = Math.min(min, i);
            max = Math.max(max, i);
        }
        
        int gap = (int) ((max - min) / (len - 1)) + 1;
        int[] bucketMin = new int[len - 1];
        int[] bucketMax = new int[len - 1];
        
        Arrays.fill(bucketMin, Integer.MAX_VALUE);
        Arrays.fill(bucketMax, Integer.MIN_VALUE);
        
        // fill the min bucket and max bucket
        for(int i : num){
        	// jump the min and max values
            if(i == min || i == max) continue;
            // find the right bucket
            int idx = (i - min) / gap;
            bucketMin[idx] = Math.min(bucketMin[idx], i);
            bucketMax[idx] = Math.max(bucketMax[idx], i);
        }
        
        int previous = min;
        int maxGap = Integer.MIN_VALUE;
        
        for(int i = 0; i < len - 1; i++){
        	// this bucket is empty, ignore
            if(bucketMin[i] == Integer.MAX_VALUE) continue; 
            // get a non-empty bucket, calculate the gap
            maxGap = Math.max(maxGap, bucketMin[i] - previous);
            previous = bucketMax[i];
        }
        maxGap = Math.max(maxGap, max - previous);
        return maxGap;
    }
}
