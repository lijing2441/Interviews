package interviews;

/**
 * You are given an array of n+2 elements. All elements of the array are in
 * range 1 to n. And all elements occur once except two numbers which occur
 * twice. Find the two repeating numbers.
 * 
 * For example, array = {4, 2, 4, 5, 2, 3, 1} and n = 5
 * 
 * The above array has n + 2 = 7 elements with all elements occurring once
 * except 2 and 4 which occur twice. So the output should be 4 2.
 */
public class Two_Repeating_Elements {
	/**
	 * Method 1: Use two loops.
	 * 
	 * In the outer loop, pick elements one by one and count the number of
	 * occurrences of the picked element in the inner loop.
	 * 
	 * @complexity O(n^2), apparently it's not an ideal solution since it does
	 *             not use all data
	 */
	public int[] findTwoRepeats(int[] A) {
		if (A.length < 4)
			return null;
		int[] res = new int[2];
		int num = 0;
		for (int i = 0; i < A.length; i++) {
			for (int j = i + 1; j < A.length; j++) {
				if (A[i] == A[j]) {
					res[num] = A[i];
					num++;
					if (num == 2)
						return res;
				}
			}
		}
		return res;
	}

	/**
	 * Method 2 (Use Count array)
	 * 
	 * Traverse the array once. While traversing, keep track of count of all
	 * elements in the array using a temp array count[] of size n, when you see
	 * an element whose count is already set, print it as duplicate.
	 * 
	 * This method uses the range given in the question to restrict the size of
	 * count[], but doesn’t use the data that there are only two repeating
	 * elements.
	 * 
	 * @complexity: O(n), O(n)
	 */

	public void printRepeats(int[] A) {
		int[] count = new int[A.length - 2];
		for (int i = 0; i < A.length; i++) {
			if (count[A[i]] > 0)
				System.out.print(A[i]);
			else
				count[A[i]]++;
		}
	}

	/**
	 * Method 3 (Make two equations) Let the numbers which are being repeated
	 * are X and Y. We make two equations for X and Y and the simple task left
	 * is to solve the two equations.
	 * 
	 * We know the sum of integers from 1 to n is n(n+1)/2 and product is n!.
	 * 
	 * We calculate the sum of input array, and **sum - n(n+1)/2 = X + Y**
	 * because X and Y are the two numbers missing from set [1..n].
	 * 
	 * Similarly calculate product of input array, and **product / n! = X*Y **.
	 * Given sum and product of X and Y, we can find easily out X and Y.
	 * 
	 * Let summation of all numbers in array be S and product be P
	 * 
	 * X + Y = S – n(n+1)/2 XY = P/n!
	 * 
	 * Using above two equations, we can find out X and Y. For array = 4 2 4 5 2
	 * 3 1, we get S = 21 and P as 960. X + Y = 21 – 15 = 6 XY = 960/5! = 8 X –
	 * Y = sqrt((X+Y)^2 – 4*XY) = sqrt(4) = 2 Using below two equations, we
	 * easily get X = (6 + 2)/2 and Y = (6-2)/2
	 * 
	 * Note: addition and multiplication overflow problem might exist
	 * 
	 * @complexity: O(n), O(1)
	 */
	@SuppressWarnings("unused")
	public void printRepeatsMath(int[] A) {
		int sum = 0;
		int product = 1;
		int x = -1, y = -1;
		int diff = 0;
		int n = A.length - 2;
		for (int i = 0; i < A.length; i++) {
			sum += A[i];
			product *= A[i];
		}

		int fact = 1;
		for (int i = 1; i <= n; i++)
			fact *= i;

		sum -= n * (n + 1) / 2;
		product /= fact;

		diff = (int) Math.sqrt(sum * sum - 4 * product);

		x = (sum + diff) / 2;
		y = (sum - diff) / 2;
	}

	/**
	 * Method 4: Use XOR
	 * 
	 * Let the repeating numbers be X and Y, if we xor all the elements in the
	 * array and all integers from 1 to n, then the result is X xor Y.
	 * 
	 * The 1’s in binary representation of X xor Y is corresponding to the
	 * different bits between X and Y.
	 * 
	 * Suppose that the kth bit of X xor Y is 1, we can xor all the elements in
	 * the array and all integers from 1 to n, whose kth bits are 1. The result
	 * will be one of X and Y.
	 * 
	 * @complexity O(n), O(1)
	 */
	public void printRepeatsXOR(int[] A) {
		int x = 0, y = 0;
		int n = A.length - 2;
		int xor = A[0]; // hold xor of all elements
		int magic = 0;

		/* Get the xor of all elements in arr[] and {1, 2 .. n} */
		for (int i = 1; i < A.length; i++) {
			xor ^= A[i];
		}
		for (int i = 1; i <= n; i++) {
			xor ^= i;
		}

		// get the rightmost set bit (bit = 1) in set_bit_no
		magic = xor & ~(xor - 1);
		// this rightmost set bit should exist in either x or y, but not both,
		// say it's x
		// otherwise it cannot equal to 1
		for (int i = 0; i < A.length; i++) {
			if ((A[i] & magic) > 0) {
				x = x ^ A[i];
			} else {
				y = y ^ A[i];
			}
		}
		for (int i = 1; i <= n; i++) {
			if ((i & magic) > 0) {
				x = x ^ i;
			} else {
				y = y ^ i;
			}
		}
		System.out.print(x + " " + y);
	}
	/**
	 * Method 5: Use array element as index
	 * 
	 * Traverse the array. Do following for every index i { 
	 * 	check for sign of A[abs(A[i])] ; 
	 * 	if positive then 
	 * 		make it negative by A[abs(A[i])]=-A[abs(A[i])]; 
	 * 	else  
	 * 		this element (ith element of list) is a repetition } 
	 * 
	 * Example: A[] = {1, 1, 2, 3, 2} 
	 * 
	 * i=0; Check sign of A[abs(A[0])] which is A[1]. 
	 * A[1] is positive, so make it negative.  Array now becomes {1, -1, 2, 3, 2}
	 * 
	 * i=1; Check sign of A[abs(A[1])] which is A[1]. 
	 * A[1] is negative, so A[1] is a repetition.
	 * 
	 * i=2; Check sign of A[abs(A[2])] which is A[2]. 
	 * A[2] is positive, so make it negative.  Array now becomes {1, -1, -2, 3, 2}
	 * 
	 * i=3; Check sign of A[abs(A[3])] which is A[3]. 
	 * A[3] is positive, so make it negative. Array now becomes {1, -1, -2, -3, 2}
	 * 
	 * i=4; Check sign of A[abs(A[4])] which is A[2]. 
	 * A[2] is negative, so A[4] is a repetition.
	 * 
	 * @complexity O(n), O(1)
	 */
	public void printRepeatsFinal(int[] A){
		int count = 0;
		for(int i = 0; i < A.length; i++){
			if(A[Math.abs(A[i])] > 0){
				A[Math.abs(A[i])] = -A[Math.abs(A[i])];
			}else{
				count++;
				System.out.println("Repeat " + count + " is " + (-A[Math.abs(A[i])]));
				if(count == 2) break;
			}
		}
	}
}
