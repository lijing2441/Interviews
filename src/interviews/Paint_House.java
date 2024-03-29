package interviews;

public class Paint_House {
	/**
	 * There are a row of n houses, each house can be painted with one of the
	 * three colors: red, blue or green. The cost of painting each house with a
	 * certain color is different. You have to paint all the houses such that no
	 * two adjacent houses have the same color.
	 * 
	 * The cost of painting each house with a certain color is represented by a
	 * n x 3 cost matrix. For example, costs[0][0] is the cost of painting house
	 * 0 with color red; costs[1][2] is the cost of painting house 1 with color
	 * green, and so on... Find the minimum cost to paint all houses.
	 * 
	 */
	public int minCost(int[][] costs) {
		int n = costs.length;
		if (n == 0)
			return 0;
		// since we just need the last round color costs
		// we just need to use O(1) space to do the dp
		int red = 0, blue = 0, green = 0;
		for (int i = 0; i < n; i++) {
			// note the last round costs
			int lastR = red, lastB = blue, lastG = green;
			// update if the current round use red, blue or green
			red = costs[i][0] + Math.min(lastB, lastG);
			blue = costs[i][1] + Math.min(lastR, lastG);
			green = costs[i][2] + Math.min(lastR, lastB);
		}
		return Math.min(Math.min(red, blue), green);
	}
	
	// method O(nk), 只需要每次存下min和second to the min
	public int minCostIINK(int[][] costs) {
        int n = costs.length;
        if (n == 0) return 0;
        int k = costs[0].length;
        if (k == 0) return 0;
        
        int[] dp = new int[k];
        int m1 = 0, m2 = 0;
        for (int i = 0; i < n; i++) {
            int t1 = m1, t2 = m2;
            m1 = Integer.MAX_VALUE;
            m2 = Integer.MAX_VALUE;
            for (int j = 0; j < k; j++) {
                if (dp[j] == t1) dp[j] = t2 + costs[i][j];
                else dp[j] = t1 + costs[i][j];
                if (dp[j] < m1) {
                    m2 = m1;
                    m1 = dp[j];
                } else if (dp[j] < m2) {
                    m2 = dp[j];
                }
            }
        }
        int res = Integer.MAX_VALUE;
        for (int i = 0; i < k; i++) {
            if (dp[i] < res) res = dp[i];
        }
        return res;
    }
	
	// extended to k colors, O(nkk)
	public int minCostII(int[][] costs) {
        int n = costs.length;
        if(n == 0) return 0;
        int k = costs[0].length;
        if(k == 0) return 0;
        int[] lastRound = new int[k];
        for(int i = 0; i < n; i++) {
            int[] last = new int[k];
            for(int j = 0; j < k; j++) last[j] = lastRound[j];
            for(int j = 0; j < k; j++) {
                lastRound[j] = costs[i][j] + getMin(last, j);
            }
        }
        return getMin(lastRound, -1);
    }
    public int getMin(int[] arr, int exceptCol) {
        if(arr.length == 1 && exceptCol != -1) return 0;
        int min = Integer.MAX_VALUE;
        for(int i = 0; i < arr.length; i++) {
            if(i == exceptCol) continue;
            if(arr[i] < min) min = arr[i];
        }
        return min;
    }
}
