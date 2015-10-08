package interviews;

public class Reservior_Sampling_Selector {
	/**
	 * 随机选1百万query（从几亿或更多query中）,使每个query被选择的机会 等概率
	 * 
	 * @analysis we are given an array (or stream) of numbers, write an efficient function to 
	 *           randomly select k numbers where 1 <= k <= n. Let the input array be stream[].
	 * 
	 * Method 1: A simple solution is to create an array arr[] of maximum size k. 
	 * 			 One by one randomly select an item from stream[0..n-1]. If the selected 
	 *  		 item is not previously selected, then put it in arr[]. 
	 * 			
	 * 			 To check if an item is previously selected or not, we need to search the item in 
	 * 			 arr[]. The time complexity of this algorithm will be O(k^2). 
	 * 			 This can be costly if k is big. Also, it is not efficient if the input is a stream.
	 * 
	 * Method 2: Reservoir Sampling ()
	 * 
	 * Limitation: the sample must fit into memory, k需为常数，我们才能判断是否fit in memory.
	 * 
	 * Pseudo-code:
	 * 
	 * array R[k];    // result
	 * integer i, j;
	 * 
	 * // fill the reservoir array
	 * for each i in 1 to k do
	 * 		R[i] := S[i]
	 * 
	 * // replace elements with gradually decreasing probability
	 * for each i in k+1 to length(S) do
	 * 		j := random(1, i);   // important: inclusive range
	 * 		if j <= k then
	 * 			R[j] := S[i]
	 * 
	 * Finally: the probability that any item in stream[i] where 0 <= i < n will 
	 * be in final reservoir[] is k/n.
	 * 
	 * 证明见最下
	 */
	public int[] selectKItems(int[] stream, int k){
		int n = stream.length;
		int[] reservior = new int[k];
		for(int i = 0; i < Math.min(k, n); i++){
			reservior[i] = stream[i];
		}
		if(n <= k) return reservior;
		for(int i = k; i < n; i++){
			int random = (int) (Math.random() * (i + 1)); // select an integer from 0 to i
			if(random < k){
				reservior[random] = stream[i];
			}
		}
		return reservior;
	}
	/**
	 * 要证明stream[i]中每个数字{0 <= i < n}被选的概率都是k/n.
	 * 
	 * Case 1: k-n个数字, k <= i < n
	 * 我们从0到i之间任选一个，然后如果选上，就替换被选位置为stream[i]
	 * 倒数第一个被包括的概率：选到前k个位置的机会为k/n
	 * 倒数第二个被包括的概率：倒数第二个选上了一个,而倒数第一个不会再选上去替换，使这个一定被包括：
	 * [k/(n-1)]*[(n-1)/n] = k/n.
	 * 别的类似
	 * 
	 * Case 2: 前k个数字
	 * 前k个可能在后面被移除，而他们一直被保持到最后的概率为 第k，k+1，…. n-1个 考虑时没有被选掉 
	 * = [k/(k+1)] x [(k+1)/(k+2)] x [(k+2)/(k+3)] x … x [(n-1)/n] = k/n
	 */
}
