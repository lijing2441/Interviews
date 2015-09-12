package interviews;

import java.util.List;

public class Minimal_Path_In_Triangle {
	/**
	 * Given a triangle, find the minimum path sum from top to bottom. Each step
	 * you may move to adjacent numbers on the row below.
	 * 
	 * For example, given the following triangle 
	 * [ 
	 * 	 [2], 
	 * 	[3,4], 
	 * [6,5,7],
	 *[4,1,8,3] 
	 *] 
	 *
	 *The minimum path sum from top to bottom is 11 (i.e., 2 + 3 + 5 + 1 = 11).
	 *
	 *Note: Bonus point if you are able to do this using only O(n) extra space, 
	 *		where n is the total number of rows in the triangle.
	 */
	/**
	 * The triangle has a tree-like structure, which would lead people to think 
	 * about traversal algorithms such as DFS. However, if you look closely, 
	 * you would notice that the adjacent nodes always share a 'branch'. 
	 * In other word, there are overlapping subproblems. 
	 * Also, suppose x and y are 'children' of k. Once minimum paths from x and y 
	 * to the bottom are known, the minimum path starting from k can be decided in O(1),
	 * that is optimal substructure. 
	 * 
	 * Therefore, dynamic programming would be the best solution 
	 * to this problem in terms of time complexity.
	 * 
	 * What I like about this problem even more is that the difference between 'top-down'
	 * and 'bottom-up' DP can be 'literally' pictured in the input triangle. 
	 * 
	 * For 'top-down' DP, starting from the node on the very top, we recursively find 
	 * the minimum path sum of each node. When a path sum is calculated, we store it in 
	 * an array (memorization); the next time we need to calculate the path sum of the same node, 
	 * just retrieve it from the array. However, you will need a cache that is at least 
	 * the same size as the input triangle itself to store the path sum, which takes O(N^2) space. 
	 * 
	 * 'Bottom-up' DP, on the other hand, is very straightforward: 
	 * we start from the nodes on the bottom row; the min path sums for these nodes are 
	 * the values of the nodes themselves. From there, the min path sum at the ith node 
	 * on the kth row would be the smaller one of the pathsums of its two children plus the value of itself:
	 * 
	 * Recurrence: minpath[k][i] = min {minpath[k+1][i], minpath[k+1][i+1]} + triangle[k][i];
	 * 
	 * Or even better, since the row minpath[k+1] would not be used after minpath[k] is computed, 
	 * we can simply set minpath as a 1D array, and iteratively update itself:
	 * 
	 * For the kth level:
	 * minpath[i] = min {minpath[i], minpath[i+1]} + triangle[k][i]; 
	 */
	public int minimumTotal(List<List<Integer>> triangle) {
        int n = triangle.size();
        int[] dp = new int[n];
        for(int i = 0; i < n; i++){
            dp[i] = triangle.get(n - 1).get(i);
        }
		for (int row = n - 2; row >= 0; row--) {
			for (int col = 0; col <= row; col++) {
				// this will not change the future value
				// since we just use the minimal
				// but not change the value of dp[i + 1]
				dp[col] = Math.min(dp[col], dp[col + 1]) + triangle.get(row).get(col);
			}
		}
		 //finally, we only have this value left in the first row
        return dp[0]; 
    }
}
