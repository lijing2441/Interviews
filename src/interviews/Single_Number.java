package interviews;

public class Single_Number {
	/**
	 * Given an array of integers, every element appears twice except for one. Find that single one.
	 */
	public int singleNumberI(int[] A) {
        if(A == null || A.length == 0) return -1;
        int res = A[0];
        if(A.length == 1) return res;
        for(int i = 1; i < A.length; i++){
            res = res ^ A[i];
        }
        return res;
    }
	
	/**
	 * Problem II: from k ~ (k + n) unsorted array, we need to find the only one appear twice
	 * 
	 * @logic xor all the elements in the array and k to k+n
	 */
	public int singleNumberII(int[] A, int k){
		if(A == null || A.length == 0) return -1;
        int res = A[0];
        for(int i = 1; i < A.length; i++){
        	res = res ^ A[i];
        }
        for(int i = k; i <= k + A.length; i++){
        	res = res ^ i;
        }
        return res;
	}
	
	/**
	 * Problem III: from 1-n unsorted array, we need to find the absent one and duplicate one
	 * 
	 * @logic Similar to the idea in "Find two repeated elements in an array"
	 * Let the repeating numbers be X and Y, if we xor all the elements in the array 
	 * and all integers from 1 to n, then the result is X xor Y.
	 * 
	 * The 1’s in binary representation of X xor Y is corresponding to the different 
	 * bits between X and Y. 
	 * 
	 * Suppose that the kth bit of X xor Y is 1, we can xor all the elements in the array 
	 * and all integers from 1 to n, whose kth bits are 1. The result will be one of X and Y.
	 * 
	 * At last, after finding the two elements, we can run the array again to check which one exists in array
	 */
	
	public void findTheTwoNumber(int[] A){
		int x = 0, y = 0; 
		int xor = A[0];
		int magic = 0;
		int n = A.length;
		
		/* Get the xor of all elements in arr[] and {1, 2 .. n} */
		for(int i = 1; i < n; i++){
			xor ^= A[i];
		}
		for(int i = 1; i <= n; i++){
			xor ^= i;
		}
		//get the rightmost set bit (bit = 1) in set_bit_no
		magic = xor & ~(xor-1);
		// this rightmost set bit should exist in either x or y, but not both, say it's x
		// otherwise it cannot equal to 1
		for(int i = 0; i < A.length; i++){
			if((A[i] & magic) > 0){
				x = x ^ A[i];
			}else{
				y = y ^ A[i];
			}
		}
		for(int i = 1; i <= n; i++){
			if((i & magic) > 0){
				x = x ^ i;
			}else{
				y = y ^ i;
			}
		}
		// we find the two numbers so far
		// int absent = -1;
		
		int duplicate = -1;
		for(int i = 0; i < n; i++){
			if(A[i] == x){
				duplicate = x;
				break;
			}
			if(A[i] == y){
				duplicate = y;
				break;
			}
		}
		if(duplicate == x){
			System.out.println("Duplicate is: " + x);
			System.out.println("Absence is: " + y);
		}else{
			System.out.println("Duplicate is: " + y);
			System.out.println("Absence is: " + x);
		}
	}
	
	/**
	 * Problem IV: Given an array of integers, every element appears three times except for
	 * one. Find that single one.
	 *
	 * O(n), O(1)
	 *
	 * @logic every number can be represented as a 32-bit number (0 or 1 in each bit). Thus if one number shows
	 * three times for its one bit, its digit[i] % 3 should be zero, otherwise it's not
	 */
	public int singleNumberIII(int[] A) {
		int[] digits = new int[32];
		for (int i = 0; i < 32; i++) {
			digits[i] = 0;
		}
		int res = 0;
		for (int i = 0; i < 32; i++) {
			for (int j = 0; j < A.length; j++) {
				if (((A[j] >> i) & 1) > 0) {
					digits[i]++;
				}
			}
			if(digits[i] % 3 > 0){
				res |= (1 << i); // set a bit
			}
		}
		return res;
	}
	
	public int singleNumber(int[] A) {
	     int x1 = 0;   
	     int x2 = 0; 
	     int mask = 0;

	     for (int i : A) {
	        x2 ^= x1 & i;
	        x1 ^= i;
	        mask = ~(x1 & x2);
	        x2 &= mask;
	        x1 &= mask;
	     }

	     return x1;  // p = 1, in binary form p = '01', then p1 = 1, so we should return x1; 
	                 // if p = 2, in binary form p = '10', then p2 = 1, so we should return x2.
	}
	/**
	 *  Given an array of numbers nums, in which exactly two elements appear only once and all 
	 *  the other elements appear exactly twice. Find the two elements that appear only once.
	 *  
	 *  For example:
	 *  Given nums = [1, 2, 1, 3, 2, 5], return [3, 5]. 
	 *  
	 *  @logic
	 *  Two passes:
	 *   
	 *  -> In the first pass, we XOR all elements in the array, and get the XOR of the two 
	 *     numbers we need to find. Note that since the two numbers are distinct, so there must 
	 *     be a set bit in the XOR result. Find out the bit.
	 *  -> In the second pass, we divide all numbers into two groups, one with the aforementioned 
	 *     bit set, another with the aforementioned bit unset. Two different numbers we need to 
	 *     find must fall into the two distinct groups. XOR numbers in each group, we can find 
	 *     a number in either group.
	 */
	public int[] singleNumberTwoSingles(int[] nums) {
        if(nums.length < 2) return new int[2];
        int diff = 0;
        // get the XOR of the two elements
        for(int i = 0; i < nums.length; i++) {
            diff ^= nums[i];
        }
        // get the last set bit, which can only belong to one of the two elements
        // diff &= ~(diff - 1)
        diff &= -diff;
        
        int[] res = new int[2];
        for(int i = 0; i < nums.length; i++) {
            if((nums[i] & diff) == 0) res[0] ^= nums[i]; // the bit is set
            else res[1] ^= nums[i]; // the bit is unset
        }
        return res;
    }
	
}
