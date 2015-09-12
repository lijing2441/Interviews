package interviews;
/**
 * Implement pow(x, n).
 * 
 * @logic every time we double the base (radix), and divide the exponent by 2;
 * 		  if the 1 digit has a 1 there, we times the current radix with res
 * 
 * @complexity O(logn) and O(1)
 */
public class Pow_x_n {
	public double pow(double x, int n) {
        if(n == 0) return 1.0;
        if(n == 1) return x;
        if(n < 0) return 1 / helper(x, -n);
        else return helper(x, n);
    }
    public double helper(double x, int n){
        double res = 1.0;
        while(n > 0){
            if((n & 1) > 0) res *= x;
            x *= x;
            n >>= 1;
        }
        return res;
    }
}
