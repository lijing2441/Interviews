package interviews;

public class Quora_Two_Missing_Numbers {
	// O(n) time, constant space
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
		System.out.println(x + y);		
	}
}
