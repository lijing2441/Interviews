package interviews;

public class Ugly_Number {
	/**
	 * Find whether a number is ugly number or not
	 */
	public boolean isUgly(int num) {
        if(num <= 0) return false;
        if(num == 1) return true;
        while(num % 2 == 0) {
            num = num / 2;
        }
        while(num % 3 == 0) {
            num = num / 3;
        }
        while(num % 5 == 0) {
            num = num / 5;
        }
        if(num > 1) return false;
        else return true;
    }
	/**
	 * Write a program to check whether a given number is an ugly number.
	 * 
	 * Ugly numbers are positive numbers whose prime factors only include 2, 3,
	 * 5. For example, 6, 8 are ugly while 14 is not ugly since it includes
	 * another prime factor 7.
	 * 
	 * Note that 1 is typically treated as an ugly number.
	 * 
	 * @logic The ugly-number sequence is 1, 2, 3, 4, 5, 6, 8, 9, 10, 12, 15, …
	 *        because every number can only be divided by 2, 3, 5, one way to
	 *        look at the sequence is to split the sequence to three groups as
	 *        below:
	 * 
	 *        (1) 1×2, 2×2, 3×2, 4×2, 5×2, … 
	 *        (2) 1×3, 2×3, 3×3, 4×3, 5×3, … 
	 *        (3) 1×5, 2×5, 3×5, 4×5, 5×5, …
	 * 
	 *        We can find that every subsequence is the ugly-sequence itself (1,
	 *        2, 3, 4, 5, …) multiply 2, 3, 5.
	 * 
	 *        Then we use similar merge method as merge sort, to get every ugly
	 *        number from the three subsequence.
	 * 
	 *        Every step we choose the smallest one, and move one step
	 *        after,including nums with same value.
	 */
	public int nthUglyNumber(int n) {
        int[] ugly = new int[n];
        ugly[0] = 1;
        int index2 = 0, index3 = 0, index5 = 0;
        int factor2 = 2, factor3 = 3, factor5 = 5;
        for(int i = 1; i < n; i++) {
            int min = Math.min(Math.min(factor2, factor3), factor5);
            ugly[i] = min;
            if(min == factor2) {
                index2++;
                factor2 = 2 * ugly[index2];
            }
            // cannot be else if, since the min can be equal to more than one factor
            if(min == factor3) {
                index3++;
                factor3 = 3 * ugly[index3];
            }
            if(min == factor5) {
                index5++;
                factor5 = 5 * ugly[index5];
            }
        }
        return ugly[n - 1];
    }
	
	// long casting, 3, 5, 7
	public long kthPrimeNumber(int k) {
        if (k <= 0) return -1;
        long[] ugly = new long[k + 1];
        ugly[0] = 1;
        long factor3 = 3, factor5 = 5, factor7 = 7;
        int index3 = 0, index5 = 0, index7 = 0;
        for (int i = 1; i <= k; i++) {
            long min = Math.min(Math.min(factor3, factor5), factor7);
            ugly[i] = min;
            if (min == factor3) {
                index3++;
                factor3 = (long)3 * ugly[index3];
            } 
            if (min == factor5) {
                index5++;
                factor5 = (long)5 * ugly[index5];
            } 
            if (min == factor7) {
                index7++;
                factor7 = (long)7 * ugly[index7];
            }
        }
        return ugly[k];
    }
}
