package interviews;

import java.util.ArrayList;
import java.util.List;

public class Different_Ways_to_Add_Parentheses {
	/**
	 * Given a string of numbers and operators, return all possible results from
	 * computing all the different possible ways to group numbers and operators.
	 * The valid operators are +, - and *.
	 * 
	 * Example 1
	 * 
	 * Input: "2-1-1".
	 * 
	 * ((2-1)-1) = 0 
	 * (2-(1-1)) = 2
	 * 
	 * Output: [0, 2]
	 * 
	 * Example 2
	 * 
	 * Input: "2*3-4*5"
	 * 
	 * (2*(3-(4*5))) = -34 
	 * ((2*3)-(4*5)) = -14 
	 * ((2*(3-4))*5) = -10 
	 * (2*((3-4)*5)) = -10 
	 * (((2*3)-4)*5) = 10
	 * 
	 * Output: [-34, -14, -10, -10, 10]
	 * 
	 * O(Catalan(n)) time -> depending on the possible answer number 
	 */
	public static List<Integer> diffWaysToCompute(String input) {
        List<Integer> res = new ArrayList<Integer>();
        int len = input.length();
        if(len == 0) return res;
        
        for(int i = 0; i < len; i++) {
            if(Character.isDigit(input.charAt(i))) continue;
            
            List<Integer> lefts = diffWaysToCompute(input.substring(0, i));
            List<Integer> rights = diffWaysToCompute(input.substring(i + 1));
            
            for(int left : lefts) {
                for(int right : rights) {
                    int cur = 0;
                    if(input.charAt(i) == '+') cur = left + right;
                    else if(input.charAt(i) == '-') cur = left - right;
                    else if(input.charAt(i) == '*') cur = left * right;
                    res.add(cur);
                }
            }
        }
        if(res.size() == 0) {
            res.add(Integer.parseInt(input));
        }
        return res;
    }
	public static void main(String[] args) {
		String s = "1+2+3";
		List<Integer> res = diffWaysToCompute(s);
		for(int num : res) {
			System.out.println(num);
		}
	}
}
