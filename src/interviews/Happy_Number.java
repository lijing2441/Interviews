package interviews;

import java.util.HashSet;

public class Happy_Number {
	/**
	 * Write an algorithm to determine if a number is "happy".
	 * 
	 * A happy number is a number defined by the following process: Starting
	 * with any positive integer, replace the number by the sum of the squares
	 * of its digits, and repeat the process until the number equals 1 (where it
	 * will stay), or it loops endlessly in a cycle which does not include 1.
	 * Those numbers for which this process ends in 1 are happy numbers.
	 * 
	 * Example: 19 is a happy number
	 * 
	 * 1^2 + 9^2 = 82 
	 * 8^2 + 2^2 = 68 
	 * 6^2 + 8^2 = 100 
	 * 1^2 + 0^2 + 0^2 = 1
	 */
    private HashSet<Integer> set = new HashSet<Integer>();
    
    public boolean isHappy(int n) {
        if(n < 1) return false;
        String str = Integer.toString(n);
        int curSum = 0;
        for(char c : str.toCharArray()) {
            int cur = (int)(c - '0') * (int)(c - '0');
            curSum += cur;
        }
        if(!set.add(curSum)) {
            return false;
        }
        if(curSum == 1) {
            return true;
        }
        return isHappy(curSum);
    }
}
