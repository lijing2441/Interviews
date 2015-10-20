package interviews;

import java.util.List;

public class Uber_Weight_Random_Selector {

	/**
	 * Follow-up: Given an array a = {'a', ... 'x'} on n characters and b =
	 * {0.1, 0.2, ..., 0.01} of probability. Write a random generator that
	 * generates the chars according to their probability.
	 * 
	 * @analysis: 1. Take an auxiliary array (say prefix[]) of size n. 
	 * 			  
	 * 			  2. Populate it with prefix sum, such that prefix[i] represents sum of probabilities 
	 * 			     from 0 to i. 
	 *            
	 *            3. Generate a random number(say r) between 0.0 and 1.0. 
	 *            
	 *            4. Find index of Ceil of random number generated in step #3 in the prefix array. 
	 *               Let the index be indexc. 
	 *            
	 *            5. Return the random number arr[indexc], where arr[] contains the input n numbers.
	 */
	// 给概率
	public int myRandomGenerator(int[] arr, double[] probability) {
		if (arr.length != probability.length)
			return -1;
		int n = arr.length;
		// construct the prefix array
		double[] prefix = new double[n];
		prefix[0] = probability[0];
		for (int i = 1; i < n; i++) {
			prefix[i] = prefix[i - 1] + probability[i];
		}
		// generate a double between [0.0, 1.0)
		Double curProbab = Math.random();
		int index = findCeil(prefix, curProbab, 0, n - 1);
		return arr[index];
	}
	
	public int randomGenerator(int[] arr, double[] prob) {
		if (arr.length != prob.length)
			return -1;
		int n = arr.length;
		// construct the prefix array
		double[] prefix = new double[n];
		//prefix[0] = prob[0];
		double random = Math.random();
		for (int i = 0; i < n; i++) {
			prefix[i] = prob[i];
			if (i > 0) prefix[i] += prefix[i - 1];
			if (random < prefix[i]) {
				return arr[i];
			}
		}
		return -1;
	}

	// find ceiling of the cur probability in prefix
	public int findCeil(double[] prefix, double cur, int l, int r) {
		while (l < r) {
			int mid = l + (r - l) / 2;
			if (prefix[mid] < cur) {
				l = mid + 1;
			} else {
				r = mid;
			}
		}
		// find the nearest cap of the current probability => 0.6,
		// 0.8，我们有0.75，找到0.8那个
		if (prefix[l] >= cur)
			return l;
		else
			return -1;
	}

	// 给比重
	/**
	 * Write a weight random select, example: [("a", 2.34), ("b", 4.68), ...],
	 * b出现概率是a两倍
	 */
	public int weightRandomSelector(int[] arr, int[] weight) {
		if (arr.length != weight.length)
			return -1;
		int len = arr.length;
		int sum = 0;
		// 先求出来所有重量
		for (int i : weight) {
			sum += i;
		}
		double[] prefix = new double[len];
		prefix[0] = (double)weight[0] / (double)sum;
		for (int i = 1; i < len; i++) {
			prefix[i] = prefix[i - 1] + (double)weight[i] / (double)sum;
		}
		double randomDouble = Math.random();
		int randomIndex = findCeil(prefix, randomDouble, 0, len - 1);
		return arr[randomIndex];
	}

	/**
	 * 29. List<Pair<int, int>> 第一个int是序号，第二个是weight，根据weight random返回任一node,
	 * ex: (2,3)->(3,5)->(1,7),返回2的概率是3/15
	 */
	public int weightListSelector(List<NumPair> list) {
		if (list == null || list.size() == 0) return -1;
		int len = list.size();
		int sum = 0;
		for (NumPair p : list) {
			sum += p.prob;
		}
		double[] prefix = new double[len];
		prefix[0] = (double)list.get(0).prob / (double)sum;
		for (int i = 1; i < len; i++) {
			prefix[i] = prefix[i - 1] + (double)list.get(i).prob / (double)sum;
		}
		double rand = Math.random();
		int randomIndex = findCeil(prefix, rand, 0, len - 1);
		return randomIndex;
	}
	
}
class NumPair {
	public int index;
	public int prob;
}
