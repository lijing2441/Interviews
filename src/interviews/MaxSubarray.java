package interviews;

import java.util.ArrayList;
import java.util.Arrays;

public class MaxSubarray {
	/**
	 * Find the contiguous subarray within an array (containing at least one
	 * number) which has the largest sum.
	 * 
	 * For example, given the array [−2,1,−3,4,−1,2,1,−5,4], the contiguous
	 * subarray [4,−1,2,1] has the largest sum = 6.
	 */
	public int maxSubArray(int[] A) {
		int maxSum = A[0];
		int sum_so_far = A[0];
		for (int i = 1; i < A.length; i++) {
			if (sum_so_far <= 0) {
				sum_so_far = A[i];
			} else {
				sum_so_far += A[i];
			}
			maxSum = Math.max(sum_so_far, maxSum);
		}
		return maxSum;
	}
	// 若要返回起始和终止位置
	public static void findMaxSubArray(int[] inputArray){
	    int maxStartIndex = 0;
	    int maxEndIndex = 0;
	    int maxSum = inputArray[0]; 

	    int cumulativeSum = inputArray[0];
	    int maxStartIndexUntilNow = 0;

	    for (int i = 1; i < inputArray.length; i++) {
	    	if (cumulativeSum < 0){
	            maxStartIndexUntilNow = i;
	            cumulativeSum = inputArray[i];
	        } else {
	        	cumulativeSum += inputArray[i];
	        }
	    	
	        if(cumulativeSum > maxSum){
	            maxSum = cumulativeSum;
	            maxStartIndex = maxStartIndexUntilNow;
	            maxEndIndex = i;
	        }
	    }

	    System.out.println("Max sum         : "+maxSum);
	    System.out.println("Max start index : "+maxStartIndex);
	    System.out.println("Max end index   : "+maxEndIndex);
	}

	/**
	 * Given an array of integers, find two non-overlapping subarrays which have
	 * the largest sum.
	 * 
	 * The number in each subarray should be contiguous.
	 * 
	 * Return the largest sum.
	 * 
	 * For given [1, 3, -1, 2, -1, 2], the two subarrays are [1, 3] and [2, -1,
	 * 2] or [1, 3, -1, 2] and [2], they both have the largest sum 7.
	 */
	// 用best time to buy and sell stock III 的思路
	public int maxTwoSubArrays(ArrayList<Integer> nums) {
        if (nums == null || nums.size() < 2) return Integer.MIN_VALUE;
        int len = nums.size();
        int[] firstSum = new int[len];
        Arrays.fill(firstSum, Integer.MIN_VALUE);
        firstSum[0] = nums.get(0);
        int maxSoFar = nums.get(0);
        for (int i = 1; i < len - 1; i++) {
            maxSoFar = Math.max(maxSoFar + nums.get(i), nums.get(i));
            firstSum[i] = Math.max(maxSoFar, firstSum[i - 1]); 
        }
        int secondSum = nums.get(len - 1);
        int maxInTotal = secondSum + firstSum[len - 2];
        maxSoFar = nums.get(len - 1);
        for (int i = len - 2; i >= 0; i--) {
            if (secondSum + firstSum[i] > maxInTotal) {
                maxInTotal = secondSum + firstSum[i];
            }
            maxSoFar = Math.max(maxSoFar + nums.get(i), nums.get(i));
            secondSum = Math.max(maxSoFar, secondSum);
        }
        return maxInTotal;
    }
	
	public static void main(String[] args) {
	    //int[] intArr={3, -1, -1, -1, -1, -1, 2, 0, 0, 0 };
	    int[] intArr = {-1, 3, -5, 4, 6, -1, 2, -7, 13, -3};
	    //int[] intArr={-6,-2,-3,-4,-1,-5,-5};
	    findMaxSubArray(intArr);
	}
}
