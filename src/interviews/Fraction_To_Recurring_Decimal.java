package interviews;

import java.util.HashMap;

public class Fraction_To_Recurring_Decimal {
	/**
	 * Given two integers representing the numerator and denominator of a
	 * fraction, return the fraction in string format.
	 * 
	 * If the fractional part is repeating, enclose the repeating part in
	 * parentheses.
	 * 
	 * For example,
	 * 
	 * Given numerator = 1, denominator = 2, return "0.5". 
	 * Given numerator = 2, denominator = 1, return "2". 
	 * Given numerator = 2, denominator = 3, return "0.(6)".
	 * 
	 * Tricks:
	 * 1. use long to avoid overflow 
	 * 2. use StringBuffer to build String 
	 * 3. use res = numerator / denominator;
	 * 		  numerator = (numerator % denominator) * 10 
	 * to simulate division,res is the value to append to buffer.
	 */
	
	public String fractionToDecimal(int numerator, int denominator) {
        long numeratorl = numerator, denoml = denominator;
        StringBuilder sb = new StringBuilder();
        //key: numeratorl, and value: position of the numeratorl
        HashMap<Long, Integer> map = new HashMap<Long, Integer>();
        
        if((numeratorl > 0 && denoml < 0) || (numeratorl < 0 && denoml > 0)) sb.append('-');
        numeratorl = Math.abs(numeratorl);
        denoml = Math.abs(denoml);
        //integral part
        long res = numeratorl/denoml;
        numeratorl = (numeratorl % denoml) * 10;
        sb.append(res);
        if(numeratorl != 0) sb.append('.');
        //floating part
        while(numeratorl != 0){
            if(map.containsKey(numeratorl)){
                //repeating part, 如果这个分子在之前出现过了，那就说明出现了重复，继续下去只能出现一样的序列
                sb.insert(map.get(numeratorl).intValue(), '(');
                sb.append(')');
                break;
            }
            map.put(numeratorl, sb.length());
            res = numeratorl/denoml;
            numeratorl = (numeratorl%denoml)* 10;
            sb.append(res);
        }
        return sb.toString();
    }
}
