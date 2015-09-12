package interviews;

public class Reverse_Integer {
	/**
	 * Reverse digits of an integer.
	 * 
	 * Example1: x = 123, return 321
	 * Example2: x = -123, return -321
	 */
	// O(n) and O(1), n is the input integer length
	// one digit by one digit reverse the integer, check during the process
	public int reverse(int x) {
		// mark the sign
		if(x == 0) return x;
        boolean isNeg = (x < 0? true:false);
        int res = 0;
        x = Math.abs(x);
        while(x > 0){
        	// check boundary every time
            if(res > 214748364) return 0;
            res = res * 10 + x % 10;
            x = x / 10;
        }
        if(isNeg) return -res;
        else return res;
	}
	
	// New version with allowance to use long to avoid overflow
	public int reverseLong(int x) {
		long res = 0;
        while(x != 0) {
            res = res * 10 + (x % 10);
            x = x / 10;
            if(res > Integer.MAX_VALUE || res < Integer.MIN_VALUE) return 0;
        }
        return (int)res;
    }
}
