package interviews;

public class Factorial_Trailing_Zeros {
	/**
	 * @problem Given an integer n, return the number of trailing zeroes in n!.
	 * 
	 *          Note: Your solution should be in polynomial time complexity.
	 * 
	 * @logic You just need to count how many times 5 appears in n! during multiplication 
	 * 		  in different forms: 5, 25, 125, 625, ... . when any 5 is multiplied by 2 (we 
	 * 		  have many of them) then we get 0 at the end. That's it
	 */
	
	public int trailingZeroes(int n) {
        if(n < 5) return 0;
        int res = 0;
        while(n > 0){
            n /= 5;
            res += n;
        }
        return res;
    }
}
