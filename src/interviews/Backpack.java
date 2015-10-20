package interviews;

public class Backpack {
	/**
	 * Given n items with size Ai, an integer m denotes the size of a backpack.
	 * How full you can fill this backpack?
	 * 
	 * Example If we have 4 items with size [2, 3, 5, 7], the backpack size is
	 * 11, we can select [2, 3, 5], so that the max size we can fill this
	 * backpack is 10. If the backpack size is 12. we can select [2, 3, 7] so
	 * that we can fulfill the backpack.
	 * 
	 * You function should return the max size we can fill in the given
	 * backpack.
	 * 
	 * Note You can not divide any item into small pieces.
	 * 
	 * Challenge: O(n x m) time and O(m) memory.
	 * 
	 * O(n x m) memory is also acceptable if you do not know how to optimize
	 * memory.
	 */
	public int backPack(int m, int[] A) {
		// dp[i][j] means the backpack can hold size j with the items A[0]-A[i];
		boolean[][] dp = new boolean[A.length + 1][m + 1];
		dp[0][0] = true;
		for (int i = 0; i < A.length; i++) {
			for (int j = 0; j <= m; j++) {
				dp[i + 1][j] = dp[i][j];
				if (j >= A[i] && dp[i][j - A[i]]) {
					dp[i + 1][j] = true;
				}
			}
		}
		for (int i = m; i >= 0; i--) {
			if (dp[A.length][i]) {
				return i;
			}
		}
		return 0;
	}
	/**
	 * Given n items with size Ai and value Vi, and a backpack with size m. 
	 * What's the maximum value can you put into the backpack?
	 * 
	 * Given 4 items with size [2, 3, 5, 7] and value [1, 5, 2, 4], 
	 * and a backpack with size 10. The maximum value is 9.
	 * 
	 * 
	 * 用子问题定义状态：即f[i][v]表示前 i 件物品恰放入一个容量为 j 的背包可以获得的最大价值。则其状态转移方程便是：
	 * 
	 * f[i][j] = max{f[i-1][j], j>=A[i-1]? f[i-1][j-A[i-1]]+V[i-1] : 0}
	 */
	
	
	
	
	
}
