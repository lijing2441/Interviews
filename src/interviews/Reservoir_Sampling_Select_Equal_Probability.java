package interviews;

public class Reservoir_Sampling_Select_Equal_Probability {
	/**
	 * @problem All Google search queries from all over the world are logged into a file.
	 * 			Now this file has billions of search queries in all languages which are interleaved.
	 * 
	 * 			Select 1 million queries from this file, such that all the entries in the file have 
	 * 			equal probability of getting picked.
	 * 
	 * @analysis So we are given a big array (or stream) of numbers, and we need to write an efficient 
	 * 			 function to randomly select k numbers where 1 <= k <= n. Let the input array be stream[].
	 * 
	 * Method 1: A simple solution is to create an array reservoir[] of maximum size k. 
	 * 			 One by one randomly select an item from stream[0..n-1]. If the selected item is not 
	 * 			 previously selected, then put it in reservoir[]. 
	 * 			
	 * 			 To check if an item is previously selected or not, we need to search the item in 
	 * 			 reservoir[]. The time complexity of this algorithm will be O(k^2). 
	 * 			 This can be costly if k is big. Also, it is not efficient if the input is a stream.
	 * 
	 * Method 2: Reservoir Sampling. 
	 * 
	 * Limitation: Assumption: the sample fits into memory, implying that k is a constant independent of n.
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
	 * Finally: the probability that any item in stream[i] where 0 <= i < n will be in final reservoir[] is k/n.
	 */
	public int[] selectKItems(int[] stream, int k){
		int n = stream.length;
		int[] reservior = new int[k];
		for(int i = 0; i < Math.min(k, n); i++){
			reservior[i] = stream[i];
		}
		if(n <= k) return reservior;
		for(int i = k; i < n; i++){
			int random = (int) (Math.random() * (i + 1)); // return an int from 0 to i
			if(random < k){
				reservior[random] = stream[i];
			}
		}
		return reservior;
	}
	/**
	 * Follow-up: Given an array a = {'a', ... 'x'} on n characters and b = {0.1, 0.2, ..., 0.01} of true
	 * probability. Write a random generator that generates the chars according to their probability.
	 * 
	 * @analysis:
	 * 1. Take an auxiliary array (say prefix[]) of size n.
	 * 2. Populate it with prefix sum, such that prefix[i] represents sum of probabilities from 0 to i.
	 * 3. Generate a random number(say r) between 0.0 and 1.0.
	 * 4. Find index of Ceil of random number generated in step #3 in the prefix array. 
	 * 	  Let the index be indexc.
	 * 5. Return the random number arr[indexc], where arr[] contains the input n numbers.
	 */
	public int myRandomGenerator(int[] arr, int[] probability){
		if(arr.length != probability.length) return -1;
		int n = arr.length;
		// construct the prefix array
		int[] prefix = new int[n];
		prefix[0] = probability[0];
		for(int i = 1; i < n; i++){
			prefix[i] = prefix[i-1] + probability[i];
		}
		//generate a double between [0.0, 1.0)
		Double curProbab = Math.random();
		int index = findCeil(prefix, curProbab, 0, n-1);
		return arr[index];
	}
	//find ceiling of the cur probability in prefix
	public int findCeil(int[] prefix, double cur, int l, int r){
		while(l < r){
			int mid = l + (r-l)/2;
			if(prefix[mid] < cur){
				l = mid + 1;
			}else{
				r = mid;
			}
		}
		if(prefix[l] >= cur) return l;
		else return -1;
	}
	
	/**
	 * To prove that this solution works perfectly, we must prove that the
	 * probability that any item stream[i] where 0 <= i < n will be in final
	 * reservoir[] is k/n. Let us divide the proof in two cases as first k items
	 * are treated differently.
	 * 
	 * Case 1: For last n-k stream items, i.e., for stream[i] where k <= i < n
	 * For every such stream item stream[i], we pick a random index from 0 to i
	 * and if the picked index is one of the first k indexes, we replace the
	 * element at picked index with stream[i]
	 * 
	 * To simplify the proof, let us first consider the last item. The
	 * probability that the last item is in final reservoir = The probability
	 * that one of the first k indexes is picked for last item = k/n (the
	 * probability of picking one of the k items from a list of size n)
	 * 
	 * Let us now consider the second last item. The probability that the second
	 * last item is in final reservoir[] = [Probability that one of the first k
	 * indexes is picked in iteration for stream[n-2]] X [Probability that the
	 * index picked in iteration for stream[n-1] is not same as index picked for
	 * stream[n-2] ] = [k/(n-1)]*[(n-1)/n] = k/n.
	 * 
	 * Similarly, we can consider other items for all stream items from
	 * stream[n-1] to stream[k] and generalize the proof.
	 * 
	 * Case 2: For first k stream items, i.e., for stream[i] where 0 <= i < k
	 * The first k items are initially copied to reservoir[] and may be removed
	 * later in iterations for stream[k] to stream[n]. The probability that an
	 * item from stream[0..k-1] is in final array = Probability that the item is
	 * not picked when items stream[k], stream[k+1], …. stream[n-1] are
	 * considered = [k/(k+1)] x [(k+1)/(k+2)] x [(k+2)/(k+3)] x … x [(n-1)/n] =
	 * k/n
	 */
}
