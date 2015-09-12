package interviews;

public class Valid_Number {
	/**
	 * Validate if a given string is numeric.
	 * 
	 * Some examples: 
	 * "0" => true 
	 * " 0.1 " => true 
	 * "abc" => false 
	 * "1 a" => false
	 * "2e10" => true
	 *
	 * Note: It is intended for the problem statement to be ambiguous. 
	 * You should gather all requirements up front before implementing one.
	 */
	public boolean isNumber(String s) {
        if(s == null || s.length() == 0) return false;
        int i = 0;
        
        //the leading whitespace
        while(i < s.length() && s.charAt(i) == ' ') i++;
        if(i == s.length()) return false;
        
        //consider the sign
        if(s.charAt(i) == '-' || s.charAt(i) == '+') i++;
        
        //consider the basic numbers, including the integral and floating parts
        int num_num = 0, pt_num = 0;
        
        while(i < s.length() && (s.charAt(i) == '.' || Character.isDigit(s.charAt(i)))){
            if(s.charAt(i) == '.') pt_num++;
            else num_num++;
            i++;
        }
        if(pt_num > 1 || num_num < 1) return false;
        
        //check whether exponent exists or not
        if(i == s.length()) return true;
        if(i < s.length() && s.charAt(i) == 'e'){
            i++;
            num_num = 0;
            // check sign in the exponent
            if(i < s.length() && (s.charAt(i) == '-' || s.charAt(i) == '+')) i++;
            //check the exponent format (cannot have floating number here)
            while(i < s.length() && Character.isDigit(s.charAt(i))){
                num_num++;
                i++;
            }
            if(num_num < 1) return false;
        }
        
        //check the trailing whitespace 
        while(i < s.length() && s.charAt(i) == ' ') i++;
        
        if(i == s.length()) return true;
        return false;
    }
}
