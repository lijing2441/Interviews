package interviews;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class Best_Time_Buy_Sell_Stocks {
	/**
	 * Problem I: Say you have an array for which the ith element is the price
	 * of a given stock on day i. Only permitted to complete at most one
	 * transaction (ie, buy one and sell one share of the stock), find the
	 * maximum profit.
	 */
	public int maxProfit(int[] prices) {
        int n = prices.length;
        if(n <= 1) return 0;
        int curProfit = 0;
        int maxProfit = 0;
        int minPrice = prices[0];
        for(int i = 1; i < n; i++) {
            if(prices[i] < minPrice) {
                minPrice = prices[i];
            } else {
                curProfit = prices[i] - minPrice;
                if(curProfit > maxProfit) maxProfit = curProfit;
            }
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
	 *        而local[i – 1][j] + diff就是为了避免第i天交易和第i-1天交易合并成一次交易而少一次交易收益（当i天价格
	 *        大于i-1天价格时有效）
	 * 
	 *        到第i天时进行j次交易的最大收益，要么等于到第i-1天时进行j次交易的最大收益（第i天价格低于第i-1天的价格），
	 *        要么等于到第i-1天时进行j-1次交易，然后第i天进行一次交易（第i天价格高于第i-1天价格时）。
	 */
	public int maxProfitK(int k, int[] prices) {
		int len = prices.length;
		if (k <= 0 || prices.length < 2)
			return 0;
		// in case of large k
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
	
	/** Facebook: 
	 * 
	 * 每次交易收取$3 手续费, 无限次交易
	 * 首先，我们肯定不会在非“谷值”的地方买入，也不会在非“峰值”的地方卖出。
	 * 所以，为了简化问题，我们先提取出这个序列中所有的“谷值”和“峰值”。分别存放于队列A和B中。
	 *
	 * 接下来，这个问题和Stock II的唯一区别就在于每次交易要收取佣金，所以使得有时候多次连续交易来得不如合并成一次划算。
	 * 比如[1->20, 18->30]两次交易=>19-3 + 12-3 = 25，就不如[1->30]一次交易=>30-1-3=26来得划算。
	 * 
	 * 假设第i次交易的始终价格分别为(Ai, Bi)，而第i+1次交易为(Ai+1, Bi+1)；那么，
	 * 当两交易的profit都>=3时，分开交易的收益是 B(i+1)-A(i+1) + Bi-Ai - 6而合起来交易的收益是B(i+1)-Ai -3
	 * 
	 * 所以，如果两次交易应该合并，则必有：Bi+1-Ai -3 >= Bi+1-Ai+1 + Bi-Ai - 6
	 * 即 Bi - A(i+1) <= 3 ....... (a)
	 * 
	 * 如果仅有第二个交易的profit小于3，那么上述条件(a)是不充分的，比如[1 -> 100]，[98 -> 99]这种情况本来不应该合并。
	 * 所以，此时还应检查是否有 B(i+1) >= Bi ...... (b)
	 * 
	 * 如果仅有第一个交易的profit小于3，那么(a), (b)也是不充分的，比如[2 -> 3], [1 -> 100]这种情况不应合并。
	 * 所以，此时应检查是否有 A(i+1) >= Ai ...... (c)
	 * 
	 * 如果两个交易的profit都<=3，那么此时无论两个交易的始末值如何，总是可以合并。因为，如果合并后的profit依然<3，
	 * 甚至变为负值，那么我们可以在最后的postprocessing步骤忽略掉这次交易；如果合并后的profit>3，
	 * 那么我们本来就应该合并这两次交易。所以合并总是正确的选择。
	 * 
	 * 综上所说，我们能够合并两次交易的充分条件是(a) & (b) & (c).
	 * 
	 * 所以，我们依次取出A和B中每一对元素(Ai, Bi)，检查它们和上一对元素(Ai-1, Bi-1)是否满足这个关系。
	 * 如果是的话，则合并二者为(Ai-1, Bi)，不是的话则二者均保留。然后继续检查下一对元素。
	 * 
	 * 在合并时，每个范围最多会被合并一次。比如(A3, B3)如果已经被合并到(A4, B4)中去，那么以后就不会再
	 * 单独检查(A3, B3)了。相当于每个范围最多被删除一次。所以最后总时间应该仍然是O(N)。
	 * 
	 * 最后，统计没有被合并的范围的个数。如果其中出现profit<3的交易，则认为其profit为0（即不进行此次交易）。
	 */
	public static int getMaxProfitV(int[] prices) {
		int len = prices.length;
		// 峰值 谷值找出来
		Queue<Integer> highs = new LinkedList<Integer>();
		Queue<Integer> lows = new LinkedList<Integer>();
		for (int i = 0; i < len; i++) {
			int left = (i == 0 ? Integer.MAX_VALUE : prices[i - 1]);
			int right = (i == len - 1 ? Integer.MIN_VALUE : prices[i + 1]);
			if (left <= prices[i] && prices[i] > right) {
				highs.offer(prices[i]);
			} else if (left > prices[i] && prices[i] <= right) {
				lows.offer(prices[i]);
			}
		}
		// 找high和low的pair
		Stack<int[]> res = new Stack<int[]>();
		res.push(new int[] {lows.poll(), highs.poll()});
		while (!lows.isEmpty()) {
			int low = lows.poll();
			int high = highs.poll();
			
			while (!res.isEmpty() && res.peek()[0] <= low && res.peek()[1] - low <= 3 && res.peek()[1] <= high) {
				low = res.pop()[0];
			}
			res.push(new int[] {low, high});
		}
		int sum = 0;
		while (!res.isEmpty()) {
			int[] cur = res.pop();
			if (cur[1] - cur[0] > 3) {
				sum += cur[1] - cur[0] - 3;
			}
		}
		return sum;
	}
	public static void main(String[] args) {
		int[] arr = {1, 9, 8, 100};
		System.out.println(getMaxProfitV(arr));
	}
}
