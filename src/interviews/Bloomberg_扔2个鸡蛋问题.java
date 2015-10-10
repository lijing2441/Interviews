package interviews;

public class Bloomberg_扔2个鸡蛋问题 {
	/**
	 * 一幢 100 层的大楼,给你两个鸡蛋. 如果在第 n 层扔下鸡蛋,鸡蛋不碎,那么从前 n-1 层扔鸡蛋都不碎.
	 * 
	 * 这两只鸡蛋一模一样,不碎的话可以扔无数次. 已知鸡蛋在0层扔不会碎. 提出一个策略, 要保证能测出鸡蛋恰好会碎的楼层,
	 * 并使此策略在最坏情况下所扔次数最少.
	 * 
	 * @idea 第一个瓶子用来试探, 只要它从 k 层楼扔下去没碎, 则目标就在[k+1,100]之间了. 但一旦运气不好碎了, 对于已知的区间,
	 *       我们只能用剩下一个瓶子从小到大一层层试, 因为我们要保证策略必须成功, 不能冒险了.
	 * 
	 *       我们如果把任何一种策略看成一个决策树, 每一次扔瓶子都会有两个子节点, 对应碎与不碎的情况下下一步应该扔的楼层. 那么,
	 *       策略的一次执行, 是树中的一条从根往下走的路, 当且仅当这条路上出现过形如 k 没碎 与 k+1碎了的一对节点时, 路停止,
	 *       当前节点不再扩展. 那么要找的是这么一棵树, 使得所有路里最长者尽量短, 也即, 要找一个最矮的决策树.
	 */
	/* Function to get minimum number of trails needed in worst
	  case with n eggs and k floors */
	
	// 有问题！！
	public static int findSafeFloor(int n, int k) {
		if (k == 0 || k == 1) return k;
		if (n == 1) return k;
		
		int[][] dp = new int[n + 1][k + 1];
		int res = 0;
		// If there are no floors, then no trials needed. 
		// OR if there is one floor, one trial needed.
		for (int i = 1; i <= n; i++) {
			dp[i][0] = 0;
			dp[i][1] = 1;
		}
		// we always need k trials for one egg and k floors
		for (int i = 1; i <= k; i++) {
			dp[1][i] = i;
		}
		
		for (int i = 2; i <= n; i++) {
			for (int j = 2; j <= k; j++) {
				dp[i][j] = Integer.MAX_VALUE;
				for (int l = 1; l <= j; l++) {
					res = 1 + Math.max(dp[i - 1][l - 1], dp[i][j - l]);
					if (res < dp[i][j]) {
						dp[i][j] = res;
					}
				}
			}
		}
		return dp[n][k];
	}
	
	public static void main(String[] args) {
		int n1 = 200;
		int n2 = 100;
		int k = 2;
		System.out.println(findSafeFloor(n1, k));
		System.out.println(findSafeFloor(n2, k));	
	}
}
