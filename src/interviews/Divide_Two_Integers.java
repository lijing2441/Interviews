package interviews;

public class Divide_Two_Integers {
	/**
	 * Divide two integers without using multiplication, division and mod operator.
	 * If it is overflow, return MAX_INT.
	 * 
	 * @analysis
	 * 因为不能用乘除法和取余运算，我们只能使用位运算和加减法。比较直接的方法是用被除数一直减去除数
	 * ，直到为0。这种方法的迭代次数是结果的大小，即比如结果为n，算法复杂度是O(n)。 
	 * 
	 * Optimization: 任何一个整数可以表示成以2的幂为底的一组基的线性组合:
	 * num = a_0 * 2^0 + a_1 * 2^1 + a_2 * 2^2 + ... + a_n * 2^n。
	 * 
	 * Thus, 左移一位相当于乘以2，我们先让除数左移直到大于被除数之前得到一个最大的基, 然后接下来我们每次尝试减去这个基，
	 * 如果可以则结果增加加2^k,然后基继续右移迭代，直到基为0为止。因为这个方法的迭代次数是按2的幂直到超过结果，所以时间复杂度为O(logn)。
	 * 
	 * divisor: 除数； dividend：被除数，求 dividend / divisor
	 */
	public int divide(int dividend, int divisor) {
        if(divisor == 0) return Integer.MAX_VALUE;
        if(divisor == 1) return dividend;
        // check sign
        boolean isNeg = ((dividend > 0 && divisor < 0) || (dividend < 0 && divisor > 0));
        int res = 0;
        // check boundary
        if(dividend == Integer.MIN_VALUE){
            if(divisor == -1) return Integer.MAX_VALUE;
            dividend -= divisor;
            res++;
        }
        if(divisor == Integer.MIN_VALUE){
            return res;
        }
        // since we have checked sign, we can make them positive
        dividend = Math.abs(dividend);
        divisor = Math.abs(divisor);
        
        int digit = 0;
        // find the maximal bit before the dividend 
        // make the divisor as large as possible 
        while(divisor <= (dividend >> 1)){
            divisor <<= 1;
            digit++;
        }
        // start with the highest divisor, 
        // since the divisor can not be larger, we just need to minus one from the dividend
        while(digit >= 0){
            if(dividend >= divisor){
                dividend -= divisor;
                res += (1 << digit);
            }
            divisor >>= 1;
            digit--;
        }
        if(isNeg) return -res;
        else return res;
    }
}
