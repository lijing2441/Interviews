package interviews;

public class Coins_In_A_Line {
	/**
	 * There are n coins in a line. Two players take turns to take a coin from
	 * one of the ends of the line until there are no more coins left. The
	 * player with the larger amount of money wins. Assume that you go first,
	 * describe an algorithm to compute the maximum amount of money you can win.
	 * 
	 * 就是取钱币
	 * [分析] 题目要求就是依次拿硬币，但是拿哪个需要参考子问题的结果，典型的DP问题。
	 * 
	 * dp[i][j]代表从第i个硬币到第j个硬币这个子问题的结果，也就是可以拿到的最大钱数。
	 * 
	 * 那么对于每一个子问题，你有两种选择
	 * 1）如果你取A[i]的话，剩下的子问题:(i+1,j), 对方会取A[i+1] or A[j]，then you have min{dp[i+2][j], dp[i+1][j-1]};
	 * 2）如果你取A[j]的话，剩下的子问题:(i,j-1), 对方会取A[i] or A[j-1]，then you have min{dp[i+1][j-1], dp[i][j-2]};
	 * 
	 * 递推公式为：
	 * dp[i][j] = max (1) A[i] + min{ dp[i+2][j], dp[i+1][j-1] }
	 * 				  (2) A[j] + min{ dp[i+1][j-1], dp[i][j-2] }
	 * remember to check the boundaries
	 * 
	 * O(n^2) and O(n^2)
	 */
	public int getMaxCoins(int[] A){
		//base cases
		int n = A.length;
		if(n == 0) return 0;
		if(n == 1) return A[0];
		if(n == 2) return A[0] > A[1]? A[0] : A[1];
		int[][] dp = new int[n][n];
		//if only one coin left
		for(int i = 0; i < n; i++){
			dp[i][i] = A[i];
		}
		//if two left, get the bigger one
		for(int i = 0; i < n- 1; i++){
			dp[i][i+1] = A[i] > A[i + 1]? A[i] : A[i+1];
		}
		//the other cases, diagonally
		for(int len = 2; len < n; len++){
			for(int i = 0; i + len < n; i++){
				int j = i + len;
				int a = dp[i+1][j-1];
				int b = dp[i+2][j];
				int c = dp[i][j-2];
				dp[i][j] = Math.max(A[i] + Math.min(a, b), A[j] + Math.min(a, c));
			}
		}
		return dp[0][n-1];
	}
	
}
