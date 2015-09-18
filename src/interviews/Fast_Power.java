package interviews;

public class Fast_Power {
	/**
	 * Calculate the a^n % b where a, b and n are all 32bit integers.
	 * 
	 * Example 
	 * 
	 * For 231 % 3 = 2
	 * 
	 * For 1001000 % 1000 = 0
	 */
	public int fastPower(int a, int b, int n) {
        if(n == 0) return 1 % b;
        if(n == 1) return a % b;
        // use long to avoid overflow
        // divide and conquer to get the half power mod first
        long product = fastPower(a, b, n / 2);
        // then get the full power mod
        product = (product * product) % b;
        // in case of odd, n / 2 is 1 less then the actual result
        if(n % 2 != 0) product = (product * a) % b;
        return (int)product;
    }
}
