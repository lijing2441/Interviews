package interviews;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Arrays;
import java.util.List;

public class Facebook_Jump_River {
	/**
	 * 给一个数组，1代表可以站，0代表不能站，从速度为1开始往前跳，每次跳的时候可以跳当前速度，也可以跳当前速度+1， 问最少跳几次可以跳过河,
	 * 起始位置R[0]等于1
	 * 
	 * recursion+cache, [1, 0, 1, 0, 1]跳两次就能过河，第一次index=0，速度为1，跳到2，然后速度为2刚好跳出去
	 * 
	 * foo(index, speed) => 到达index所需的最少步数 
	 * 
	 * foo(index,speed) = 1 + min(foo(index + speed, speed), foo(index + speed + 1, speed + 1))
	 */
	public static int smallestJumps(int[] river) {
		if (river == null || river.length == 0) return 0;
		int len = river.length;
		// dp[i][j] is the min step to reach position i at speed j
		int[][] dp = new int[len][len];
		for (int i = 0; i < len; i++) {
			Arrays.fill(dp[i], 1);
		}
		
		for (int i = len - 1; i >= 0; i--) {
			if (river[i] == 0) continue;
			for (int j = 1; j <= len; j++) { // speed can be large to len
				if (i + j + 1 < len) {
					if (river[i + j] == 0 && river[i + j + 1] == 0) {
						dp[i][j] = Integer.MAX_VALUE;
					} else if (dp[i + j][j] != Integer.MAX_VALUE && dp[i + j + 1][j + 1] != Integer.MAX_VALUE) {
						dp[i][j] = 1 + Math.min(dp[i + j][j], dp[i + j + 1][j + 1]);
					} else if (dp[i + j][j] != Integer.MAX_VALUE) {
						dp[i][j] = 1 + dp[i + j][j];
					} else {
						dp[i][j] = 1 + dp[i + j + 1][j + 1];
					}
				}
			}
		}
		return dp[0][1];
	}

	/*
	 * pos: current position 
	 * 
	 * vel: current velocity
	 */
	public static int minSteps(int[] paths) {
		// map对应每个位置上，相应速度对应的步数
		List<HashMap<Integer, Integer>> A = new ArrayList<HashMap<Integer, Integer>>();
		for (int i = 0; i < paths.length; i++) {
			A.add(new HashMap<Integer, Integer>());
		}
		return minStepsCrossRiver(paths, 0, 1, A);
	}

	public static int minStepsCrossRiver(int[] paths, int pos, int vel, List<HashMap<Integer, Integer>> A) {
		if (pos >= paths.length)
			return 0;
		if (paths[pos] == 0)
			return -1;
		if (A.get(pos).containsKey(vel)) {
			return A.get(pos).get(vel);
		}

		int s1 = minStepsCrossRiver(paths, pos + vel, vel, A);
		int s2 = minStepsCrossRiver(paths, pos + vel + 1, vel + 1, A);

		int res = 0;
		if (s1 < 0 && s2 < 0)
			res = -1;
		else if (s1 < 0)
			res = s2 + 1;
		else if (s2 < 0)
			res = s1 + 1;
		else
			res = Math.min(s1, s2) + 1;
		A.get(pos).put(vel, res);
		return res;
	}

	public static void main(String[] args) {
		int[] paths = { 1, 2, 1, 0, 1, 1, 0, 0 };
		System.out.println(minSteps(paths));
		System.out.println(smallestJumps(paths));
	}
}
