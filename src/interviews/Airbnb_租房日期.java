package interviews;

import java.util.ArrayList;
import java.util.List;

public class Airbnb_租房日期 {
	/**
	 * 给一堆租房的request，作为输入数组，找一个array
	 * subset，其中任意两个元素不能相邻，求使得子集里所有元素之和最大。一维DP解之，另外可以使用滚动数组让空间开销为常数。
	 * 
	 * 给一个数组代表reservation request，suppose start date, end date back to back.
	 * 
	 * 比如[5,1,1,5]代表如下预定： Jul1-Jul6 Jul6-Jul7 Jul7-Jul8 jul8-Jul13
	 * 
	 * 当然最开始那个Jul 1是随便选就好的啦。 现在input的意义搞清楚了。
	 * 
	 * 还有一个限制，就是退房跟开始不能是同一天，比如如果接了Jul1-Jul6，Jul6-Jul7就不能接了。那问题就是给你个数组，
	 * 算算最多能把房子租出去多少天。这个例子的话就是10天。
	 * 
	 * [4,9,6]=10
	 * 
	 * [4,10,3,1,5]=15
	 * 
	 * follow up是求得到最优解时的request具体情况，类似于求出最短路径的长度后,给出最短路径。
	 */
	public static int findMax(int[] requests) {
		int first = requests[0];
		int second = Math.max(requests[0], requests[1]);
		for (int i = 2; i < requests.length; i++) {
			int tmp = Math.max(second, first + requests[i]);
			first = second;
			second = tmp;
		}
		return second;
	}
	
	// 找出其中一条最优解法，用二维数组表示dp过程就可以了 O(n), 空间O(2n) => O(n)
	public static List<Integer> findOneBest(int[] requests) {
		int len = requests.length;
		// dp[i][0] -> 本sum包含第i个数； dp[i][1] -> 本sum不包含第i个数
		int[][] dp = new int[len][2];
		
		dp[0][1] = requests[0];
		dp[1][0] = requests[0];
		
		for (int i = 1; i < len; i++) {
			dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1]);
			dp[i][1] = dp[i - 1][0] + requests[i];
		}
		int max = Math.max(dp[len - 1][0], dp[len - 1][1]);
		List<Integer> res = new ArrayList<Integer>();
		int sum = max;
		for (int i = len - 1; i >= 0; i--) {
			if (dp[i][1] == sum) {
				res.add(requests[i]);
				sum -= requests[i];
			}
		}
		return res;
	}
	
	// 找出所有可能的path
	public static List<List<Integer>> findBest(int[] requests) {
		int len = requests.length;
		int[] dp = new int[len];
		dp[0] = requests[0];
		dp[1] = Math.max(requests[0], requests[1]);
		for (int i = 2; i < len; i++) {
			dp[i] = Math.max(dp[i - 1], dp[i - 2] + requests[i]);
		}
		
		List<List<Integer>> res = new ArrayList<List<Integer>>();
		helper(res, new ArrayList<Integer>(), requests, 0, 0, dp[len - 1]);
		
		return res;
	}
	public static void helper(List<List<Integer>> res, List<Integer> cur, int[] nums, int pos, int curSum, int target) {
		if (pos >= nums.length) {
			if (curSum == target) {
				res.add(new ArrayList<Integer>(cur));
			}
			return;
		}
		cur.add(nums[pos]);
		helper(res, cur, nums, pos + 2, curSum + nums[pos], target);
		cur.remove(cur.size() - 1);
		helper(res, cur, nums, pos + 1, curSum, target);
	}
	
	// driver
	public static void main(String[] args) {
		int[] A = {4, 10, 3, 1, 5};
		System.out.println(findMax(A));
		List<List<Integer>> list = findBest(A);
		List<Integer> oneSolution = findOneBest(A);
		for (List<Integer> l : list) {
			for (int i : l) {
				System.out.print(i + " ");
			}
			System.out.println();
		}
		for (int i : oneSolution) {
			System.out.print(i + " ");
		}
	}
}
