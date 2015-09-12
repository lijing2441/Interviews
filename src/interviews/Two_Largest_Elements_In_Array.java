package interviews;

public class Two_Largest_Elements_In_Array {
	/**
	 * Find top two biggest elements in array.
	 */
	// this method use O(2N - 3N) comparison
	public int[] findTopTwo_Basic(int[] arr) {
		int[] res = new int[2];
		int max1 = arr[0];
		int max2 = arr[1];
		for (int i = 1; i < arr.length; i++) {
			if (arr[i] > max1) {
				max2 = max1;
				max1 = arr[i];
			} else if (arr[i] > max2 && arr[i] != max1) {
				max2 = arr[i];
			} else if (max1 == max2) {
				max2 = arr[i];
			}
		}
		res[0] = max1;
		res[1] = max2;
		return res;
	}
	
	/** 
	 * Follow up: Number of comparisons need to be n + O(log n).
	 * 
	 * @analysis Think of elements as competitors, and a tournament is going to rank them.
	 * 
	 * First, compare the elements, as in the tree
	 * 
	 * 	   	   |
	 * 		  / \
	 * 		 |   |
	 * 		/ \ / \
	 * 	   x   x   x
	 * 
	 * this takes n-1 comparisons and each element is involved in comparison at most log n times. 
	 * You will find the largest element as the winner.The second largest element must have lost 
	 * a match to the winner (he can't lose a match to a different element), so he's one of the 
	 * log n elements the winner has played against.  You can find which of them using log n - 1 comparisons.
	 * 
	 * => Recursively split the array, find the largest element in each half, then find the largest 
	 * element that the largest element was ever compared against. 
	 * 
	 * That first part requires n compares, the last part requires O(log n).
	 * Essentially, we would like to associate with the largest element of A an
	 * array Compared[] of elements A was compared to. This, however, needs to
	 * be done carefully. We do not know upfront which array element is going to
	 * be the largest, so we will carry information about all comparisons an array
	 * element won until this element loses a comparison.
	 * 
	 * =>We can use a tournament data structure to do it and for the FindMaxRecursive() algorithm return array Compared[]
	 * 
	 * • Compared[0] = K (i.e., the 0th element of the array holds the length of the array);
	 * • Compared[1] = max (i.e., the first element of the array is the number that FindMaxRecursive() would return;
	 * • Compared[2],... , Compared[K] are all numbers with which max has been compared thus far.
	 * 
	 * Pseudo-code of algorithm:
	 * Algorithm FindSecondMax(N, A[1..N]) returns
	 * 		Compared←FindMaxTournament(1,N,A[1..N]);
	 * 		Compared2←FindMaxTournament(2,Compared[0],Compared[2..Compared[0]]);
	 * 		return Compared2[1]
	 * 
	 * Function FindMaxTournament(I,J, A[I..J], N) returns Compared[0..K]
	 * 		if I = J then //base case
	 * 			Compared[0..N];
	 * 			Compared[0]← 1;
	 * 			Compared[1]← A[I];
	 * 			return Compared;
	 * 		Compared1← FindMaxTournament(I, I+(J-I)/2, A,N);
	 * 		Compared2← FindMaxTournament(1+I+(J-I)/2,J, A,N);
	 * 		if Compared1[1]>Compared2[1] then
	 * 			K←Compared1[0]+1;
	 * 			Compared1[0]←K;
	 * 			Compared1[K]←Compared2[1];
	 * 			return Compared1;
	 * 		else
	 * 			K←Compared2[0]+1;
	 * 			Compared2[0]←K;
	 * 			Compared2[K]←Compared1[1];
	 * 			return Compared2;
	 */
	public int[] findTopTwo(int[] arr){
		int[] res = new int[2];
		// base case
		if(arr.length < 2){
			res[0] = -1;
			res[1] = -1;
			return res;
		}
		//find the largest element
		int[] compared = findMaxRecur(arr, 0, arr.length - 1); 
		// find the 2nd from the logn elements, compared[0] is the length of the elements compared with largest
		int[] compared2 = findMaxRecur(compared, 2, compared[0]); 
		res[0] = compared[1];
		res[1] = compared2[1];
		return res;
	}
	public int[] findMaxRecur(int[] a, int left, int right){
		int[] compared = new int[a.length];
		if(left == right){
			compared[0] = 1;
			compared[1] = a[left];
			return compared;
		}
		int mid = left + (right - left) / 2;
		// recursively find the largest part from the two halves
		int[] compared1 = findMaxRecur(a, left, mid);
		int[] compared2 = findMaxRecur(a, mid + 1, right);
		if(compared1[1] > compared2[1]){
			int len = compared1[0] + 1; // add one element that has been compared with largest
			compared1[0] = len;
			compared1[len] = compared2[1];
			return compared1;
		}else{
			int len = compared2[0] + 1;
			compared2[0] = len;
			compared2[len] = compared1[1];
			return compared2;
		}
	}
}
