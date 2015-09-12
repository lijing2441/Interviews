package interviews;

public class Climbing_Stairs {
	// O(n), O(1)
	public int climbStairs(int n) {
		if (n == 0 || n == 1) return n;

		int res = 0, oneStep = 1, twoStep = 1;
		for (int i = 2; i <= n; i++) {
			res = oneStep + twoStep;
			twoStep = oneStep; // update oneStep
			oneStep = res; // update twoStep
		}
		return res;
	}

	// O(n), O(n) method
	public int climbStairsBad(int n) {
		if (n < 2) return n;
		
		int[] helper = new int[n + 1];
		helper[0] = 1;
		helper[1] = 1;
		for (int i = 2; i <= n; i++) {
			helper[i] = helper[i - 1] + helper[i - 2];
		}
		return helper[n];
	}
}
