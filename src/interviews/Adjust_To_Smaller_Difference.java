package interviews;

public class Adjust_To_Smaller_Difference {
	/**
	 * Given an integer array, adjust each integers so that the difference of
	 * every adjacent integers are not greater than a given number target.
	 * 
	 * If the array before adjustment is A, the array after adjustment is B, you
	 * should minimize the sum of |A[i]-B[i]|
	 * 
	 * You can assume each number in the array is a positive integer and not
	 * greater than 100
	 * 
	 * Given [1,4,2,3] and target=1, one of the solutions is [2,3,2,3], the
	 * adjustment cost is 2 and it's minimal. Return 2.
	 * 
	 * @logic DP might be a better idea. dp[i][j] -> A[i]处从original值设为j时所需要的cost.
	 * 		 
	 *   	We need to minimize the cost and at the same time, maintain the difference between the
	 * 		adjacent element cannot be greater than target. 
	 * 		
	 * 		We compare each element with the left neighbor, trying each possible combination of two numbers between 
	 * 		1 and 100. 
	 * 
	 * 		At last, we need to find the smallest one from the 1 - 100 grids
	 * 		 
	 */
	// O(n * 100 * 100)
	public int getAdjust(int[] A, int target){
		int n = A.length;
		if(n < 2) return 0;
		// since the number in the array is a positive integer and not great than 100 -> 1<=x<=100
		int[][] dp = new int[n][101];
		
		for(int i = 0; i < n; i++){
			// the value can be 1 <= j <= 100
			for(int j = 1; j <= 100; j++){
				//base case, A[0] cannot compare with the left element
				if(i == 0){
					dp[i][j] = Math.abs(A[0] - j);
				}
				else{
					// set it to some value larger than 100
					dp[i][j] = 200; 
					//compare the current grid value with the last one
					for(int k = 1; k <= 100; k++){
						// we assume the last element is k, if the difference can not fit, skip this k
						if(Math.abs(k - j) > target) continue;
						// fit the requirement
						// we just need to consider the difference between j and original value
						int diff = Math.abs(A[i] - j) + dp[i - 1][k];
						if(diff < dp[i][j]) dp[i][j] = diff;
					}
				}
			}
		}
		//check the last row with the last element in A[], we have 100 choices
		int min = dp[n - 1][1];
		for(int i = 2; i <= 100; i++){
			if(dp[n - 1][i] < min) min = dp[n - 1][i];
		}
		return min;
	}
}
