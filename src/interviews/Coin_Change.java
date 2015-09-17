package interviews;

public class Coin_Change {
	/**
	 * Find the minimum no. of coins to make up the given sum from the list of
	 * the denomination of coins. non-duplicates
	 * 
	 * @DP: the subproblem is either we use the jth coin or not
	 */
	public static int getMinCoins(int[] coins, int change){
		int[] counts = new int[change + 1];
		//initialize to MAX_VALUE
		counts[0] = 0;
		for(int i = 1; i <= change; i++){
			counts[i] = Integer.MAX_VALUE;
		}
		for(int i = 1; i <= change; i++){
			//since we need to reuse the last column, we keep a variable here to record
			int curCount = Integer.MAX_VALUE;
			for(int j = 0; j < coins.length; j++){
				if(coins[j] <= i){
					curCount = Math.min(curCount, counts[i - coins[j]] + 1);
				}
			}
			counts[i] = curCount;
		}
		return counts[change];
	}
	

	/**
	 * Given a value N, if we want to make change for N cents, and we have
	 * infinite supply of each of S = {S1, S2, .. , Sm} valued coins, how many
	 * ways can we make the change? The order of coins doesn’t matter.
	 * 
	 * @DP: To count total number solutions, we can divide all set solutions in two sets.
	 * 		1) Solutions that do not contain mth coin (or Sm).
	 * 		2) Solutions that contain at least one Sm.
	 * 		Let count(S[], m, amount) means the ways we use the m types of coins to give change of "amount" 
	 * 		then it can be written as sum of count(S[], m-1, amount) and count(S[], m, amount-Sm).
	 */
	//coins means the value of each type of coin, O(mn)
	public int count(int[] coins, int change){
		int types = coins.length;
		// We need n+1 rows as the table is constructed in bottom up manner using 
	    // the base case 0 value case (n = 0)
		int[][] dp = new int[change + 1][types];
		
		// Fill the entries for 0 value case (n = 0)
	    for (int i = 0; i < types; i++){
	        dp[0][i] = 1;
	    }
	    // Fill rest of the table entries in bottom up manner  
	    for (int i = 1; i <= change; i++){
	        for (int j = 0; j < types; j++){
	        	
	            // Count of solutions including S[j], next round, we can still use this type of coin
	            int x = (i - coins[j] >= 0)? dp[i - coins[j]][j]: 0;
	 
	            // Count of solutions excluding S[j]
	            int y = (j >= 1)? dp[i][j-1]: 0;
	 
	            // total count
	            dp[i][j] = x + y;
	        }
	    }
	    return dp[change][types - 1];
	}
	/**
	 * O(n) space method, n is the amount of money, m is the number of coin types
	 * reuse the same array
	 */
	public int count(int S[], int m, int n){
	    // table[i] will be storing the number of solutions for
	    // value i. We need n+1 rows as the table is constructed
	    // in bottom up manner using the base case (n = 0)
		int[] table = new int[n + 1];
	 
	    // Base case (If given value is 0)
	    table[0] = 1;
	 
	    // Pick all coins one by one and update the table[] values
	    // after the index greater than or equal to the value of the
	    // picked coin
		for (int i = 0; i < m; i++)
			for (int j = S[i]; j <= n; j++)
				table[j] += table[j - S[i]];

		return table[n];
	}
	
	/**
	 * recursive method
	 */
	public int countRecur(int[] coins, int change){
		return countHelper(coins, coins.length - 1, change);
	}
	public int countHelper(int[] coins, int index, int change){
		// If change is 0 then there is 1 solution (do not include any coin)
		if(change == 0) return 1;
		// If change is less than 0 then no solution exists
		if(change < 0) return 0;
		// If no coin left and change is larger than 1, so solution exist
		if(index < 0 && change > 0){
			return 0;
		}
		//return the sum of the two situations 
		return countHelper(coins, index - 1, change) + countHelper(coins, index, change - coins[index]);
	}
	
	public static void main(String[] args){
		int[] coins = {1, 2, 3, 5};
		int x = 9;
		System.out.println(getMinCoins(coins, x));
	}
}
