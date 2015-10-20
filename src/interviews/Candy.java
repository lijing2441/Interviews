package interviews;

public class Candy {
	/**
	 * There are N children standing in a line. Each child is assigned a rating
	 * value.
	 * 
	 * You are giving candies to these children subjected to the following
	 * requirements:
	 * 
	 * Each child must have at least one candy. Children with a higher rating
	 * get more candies than their neighbors.
	 * 
	 * What is the minimum candies you must give?
	 * 
	 * 非常明显的一维DP问题（满足“重叠子问题”＋“最优子结构”的条件）递推条件如下： Candy[i] = Candy[i-1]+1 if
	 * ratings[i] > ratings[i-1] = 1 if ratings[i] == ratings[i-1] =
	 * Candy[i-1]-1 if ratings[i] < ratings[i-1]
	 * 
	 * 其中，第二个递推条件是比较隐晦的，两个挨着的小孩的rating一样多，后面的小孩可以只给一颗糖。这也是为什么求解的是最小值。
	 * 第三个递推条件有缺陷，需要考虑到 a）任何一个小孩至少一颗糖 b）rating低的小孩可以少拿更多的糖。
	 * 因此，定义一个adjust函数，当rating到达波谷的时候（对应局部得到糖最少的小孩），将小孩的得到candy数量设置为1。
	 */
	public int candy(int[] ratings) {
		int len = ratings.length;
		// base case
		if (len < 2)
			return len;

		int[] num = new int[len];
		for (int i = 0; i < len; i++) {
			num[i] = 1;
			if (i > 0 && ratings[i] > ratings[i - 1]) {
				num[i] = num[i - 1] + 1;
			}
		}
		// then backwards scan once, give the ones with higher rank with one
		// more candy
		for (int i = len - 2; i >= 0; i--) {
			if (ratings[i] > ratings[i + 1] && num[i] <= num[i + 1]) {
				num[i] = num[i + 1] + 1;
			}
		}
		int sum = 0;
		for (int i : num)
			sum += i;
		return sum;
	}

	// O(1) space, one pass
	/** 
	 * There are three conditions,>/==/<.Two of them,< and == is easy to understand.
	 * 
	 * When it comes to >, we need to get the index where start descending, and when 
	 * it turns back to ascend, we can add the D-value.
	 * 
	 */
	public int candy2(int[] ratings) {
		int res = 1;
		int cur = 1;
		int len = ratings.length;
		int low = 0, high = 1;
		for (int i = 1; i < len; i++) {
	        if (ratings[i] > ratings[i - 1]) {
	            low = 0;
	            cur++;
	            res += cur;
	            high = cur;
	        } else if (ratings[i] == ratings[i - 1]) {
	            low = 0;
	            cur = 1;
	            res += cur;
	            high = cur;
	        } else {
	            ++low;
	            if (low == high) {
	                high++;
	                res += high;
	            } else {
	                res += low;
	            }
	            cur = 1;
	        }
	    }
	    return res;
	}
}
