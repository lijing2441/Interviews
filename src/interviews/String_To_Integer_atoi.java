package interviews;

public class String_To_Integer_atoi {
	/**
	 * Implement atoi to convert a string to an integer.
	 * 
	 * Hint: Carefully consider all possible input cases. If you want a
	 * challenge, please do not see below and ask yourself what are the possible
	 * input cases.
	 * 
	 * Question for interviewers: 
	 * 1. whitespace in the beginning and tailing whitespace?
	 * 2. sign "+" and "-"?
	 * 3. if the first char is not digit, but alphabet or other char, ignore or break
	 * 4. chars after numbers?
	 * 5. empty string? what to return?
	 * 6. out of boundary? 
	 * 
	 * If no valid conversion could be performed, a zero value is returned. If
	 * the correct value is out of the range of representable values, INT_MAX
	 * (2147483647) or INT_MIN (-2147483648) is returned.
	 */
	
	public int INT_MAX = 2147483647;
	public int INT_MIN = -2147483648;
	
	public int atoi(String str){
		
		if(str == null || str.length() == 0) return 0;
		int res = 0;
		int sign = 1;
		int len = str.length();
		// index of the char we are dealing with currently
		int i = 0;
		// ignore the whitespace
		while(str.charAt(i) == ' ') i++;
		// check the sign
		if(str.charAt(i) == '+'){
			sign = 1;
			i++;
		}
		else if(str.charAt(i) == '-'){
			sign = -1;
			i++;
		}
		
		//check the valid digit, if no digit shows up, go to the last step
		while(i < len && str.charAt(i) <= '9' && str.charAt(i) >= '0'){
			// check the boundary, if the res is MIN_VALUE or MAX_VALUE
			if(res > INT_MAX / 10 || (res == INT_MAX / 10 && str.charAt(i) >= '8')){
				if(sign == -1) return INT_MIN;
				else return INT_MAX;
			}
			// if not exceed boundary, proceed to convert the current char to digit
			res = res * 10 + (str.charAt(i) - '0');
			i++;
		}
		res = res * sign;
		if(res > INT_MAX) return INT_MAX;
		return res;
	}
}
