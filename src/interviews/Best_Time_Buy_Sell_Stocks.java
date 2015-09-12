package interviews;

public class Best_Time_Buy_Sell_Stocks {
	/**
	 * Problem I: Say you have an array for which the ith element is the price
	 * of a given stock on day i. Only permitted to complete at most one
	 * transaction (ie, buy one and sell one share of the stock), find the
	 * maximum profit.
	 */
	public int maxProfit(int[] prices) {
		if (prices.length < 2) return 0;
		int buy = 0;
		int maxProfit = 0;
		int curProfit = 0;
		for (int i = 1; i < prices.length; i++) {
		if (prices[buy] > prices[i])
				buy = i;
			curProfit = prices[i] - prices[buy];
			if (curProfit > maxProfit)
				maxProfit = curProfit;
		}
		return maxProfit;
	}
	/**
	 * II: You may complete as many transactions as you like. However, you may not engage in
	 * multiple transactions at the same time.
	 * 
	 * @analysis Easy case: As long as there is profit, sell to obtain. 
	 * 						If price is lower, set it to buy price.
	 */
	public int maxProfitII(int[] prices){
		if(prices.length < 2) return 0;
		int buy = 0;
		int max = 0;
		for(int i = 1; i < prices.length; i++){
			if(prices[i] > prices[buy]){
				max += (prices[i] - prices[buy]);
			}
			buy = i;
		}
		return max;
	}
	/**
	 * More extension, problem III: You may complete at most two transactions.
	 * @analysis use an array to record the firstProfit for each day and count the secondProfit
	 * 			 from the right to left, using the firstProfit array. 
	 */
	public int maxProfitIII(int[] prices){
		if(prices.length < 2) return 0;
		int n = prices.length;
		int[] firstProfit = new int[n];
		int buy = prices[0];
		for(int i = 0; i < n; i++){
			buy = Math.min(buy, prices[i]);
			if(i > 0) firstProfit[i] = Math.max(prices[i] - buy, firstProfit[i - 1]);
		}
		int maxSoFar = prices[n - 1];
		int maxProfit = 0;
		int secondProfit = 0;
		for(int i = n - 1; i >= 0; i--){
			maxSoFar = Math.max(maxSoFar, prices[i]);
			secondProfit = Math.max(maxSoFar - prices[i], secondProfit);
			if(secondProfit + firstProfit[i] > maxProfit) maxProfit = secondProfit + firstProfit[i];
		}
		return maxProfit;
	}
	// Method2: Only O(1) space:
	// profit = hold + release.
	// hold is negative (give money to buy) and release is positive (get money by selling)
	public class Solution {
		public int maxProfit(int[] prices) {
	        int hold1 = Integer.MIN_VALUE, hold2 = Integer.MIN_VALUE;
	        int release1 = 0, release2 = 0;
	        for(int i : prices){                              // Assume we only have 0 money at first
	            release2 = Math.max(release2, hold2+i);     // The maximum if we've just sold 2nd stock so far.
	            hold2    = Math.max(hold2,    release1-i);  // The maximum if we've just buy  2nd stock so far.
	            release1 = Math.max(release1, hold1+i);     // The maximum if we've just sold 1nd stock so far.
	            hold1    = Math.max(hold1,    -i);          // The maximum if we've just buy  1st stock so far. 
	        }
	        return release2; ///Since release1 is initiated as 0, so release2 will always higher than release1.
	    }
	}
	
	/**
	 * Final problem: generalize to k transactions
	 * 
	 * @logic Time complexity is O(kn), space complexity can be O(n) because
	 *        this DP only uses the result from last step.
	 * 
	 *        local[i][j] = max(global[i-1][j-1] + max(diff,0), local[i-1][j]+diff) 
	 *        global[i][j] = max(local[i][j], global[i-1][j])
	 * 
	 *		  Wrong one: profit[i][j] = max(profit[i – 1][j], profit[i – 1][j – 1] + diff) 
	 *        看起来很有道理，但其实不对，因为diff是第i天和第i-1天的差额收益，如果第i-1天当天本身也有交易，这两次交易就可以合为一次
	 *        这样profit[i – 1][j – 1] + diff 实际上只进行了j-1次交易，而非j次，这样得到的最大收益就小了。
	 *        
	 *        而local[i – 1][j] + diff就是为了避免第i天交易和第i-1天交易合并成一次交易而少一次交易收益（当i天价格大于i-1天价格时有效）
	 * 
	 *        到第i天时进行j次交易的最大收益，要么等于到第i-1天时进行j次交易的最大收益（第i天价格低于第i-1天的价格），
	 *        要么等于到第i-1天时进行j-1次交易，然后第i天进行一次交易（第i天价格高于第i-1天价格时）。
	 */
	public int maxProfitK(int k, int[] prices) {
		int len = prices.length;
		if (k <= 0 || prices.length < 2)
			return 0;
		if (k >= len / 2) {
			int res = 0;
			for (int i = 1; i < len; i++) {
				res += Math.max(prices[i] - prices[i - 1], 0);
			}
			return res;
		}

		int[] local = new int[k + 1];
		int[] global = new int[k + 1];
		for (int i = 0; i < len - 1; i++) {
			int diff = prices[i + 1] - prices[i];
			for (int j = k; j > 0; j--) {
				local[j] = Math.max(global[j - 1] + Math.max(diff, 0), local[j] + diff);
				global[j] = Math.max(local[j], global[j]);
			}
		}
		return global[k];
	}
}
