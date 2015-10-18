package interviews;

public class Bitwise_AND_of_Numbers_Range {
	/**
	 * Given a range [m, n] where 0 <= m <= n <= 2147483647, return the bitwise
	 * AND of all numbers in this range, inclusive.
	 * 
	 * For example, given the range [5, 7], you should return 4.
	 * 
	 * @logic remove all the right part with different digits, only remaining the part in the left.
	 */
	public int rangeBitwiseAnd(int m, int n) {
		if (m == n) return m;
        int count = 0;
        while(m != n){
            m >>= 1;
            n >>= 1;
            count++;
        }
        return m<<count;
    }
	
	public int rangeBitwiseAnd2(int m, int n) {
		while(m < n) {
			n = n & (n - 1);
		}
		return n;
	}
}
