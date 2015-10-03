package interviews;

public class Snapchat_找整数数组加符号后最大值 {
	/**
	 * 给一个整数数组，其中所有数字都大于等于1。这些数字可以用加号，乘号和括号连接成一个表达式，求表达式的最大值。
	 * 
	 * 二维DP搞定。Follow up是考虑0和负数。这时需要维护两个DP表，同时跟踪表达式的最大值和最小值。
	 */
	public static int getMaxFromArray(int[] arr) {
		int n = arr.length;
		int[][] dp = new int[n][n];
		for (int i = 0; i < n; i++) {
			dp[i][i] = arr[i];
		}
		for (int len = 1; len < n; len++) {
			for (int i = 0; i + len < n; i++) {
				int max = Integer.MIN_VALUE;
				for (int j = i; j < i + len; j++) {
					int plus = dp[i][j] + dp[j + 1][i + len];
					//System.out.println(i + " " + j + " " + plus);
					int mult = dp[i][j] * dp[j + 1][i + len];
					//System.out.println(i + " " + j + " " + mult);
					max = Math.max(Math.max(plus, mult), max);
				}
				dp[i][i + len] = max;
			}
		}
		//System.out.println(dp[3][3]);
		return dp[0][n - 1];
	}
	public static void main(String[] args) {
		int[] arr = {1, 1, 2, 1};
		System.out.println(getMaxFromArray(arr));
	}
}