package interviews;

public class Power_Of_2 {
	/**
	 * Given an integer, write a function to determine if it is a power of two.
	 */
	public boolean isPowerOfTwo(int n) {
        if(n <= 0) return false;
        
        if((n & (n - 1)) != 0) return false;
        else return true;
    }
}
