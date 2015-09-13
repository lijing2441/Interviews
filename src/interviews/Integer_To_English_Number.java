package interviews;

public class Integer_To_English_Number {
	/**
	 * Given a number write a function to convert that number to a string that
	 * would be seen on a check. 
	 * Example: 2376 -> two thousand three hundred and seventy six
	 */
	public String[] ones = {"", "one", "two", "three", "four", "five", "six", "seven", "eight", "nine", "ten", "eleven", "twelve", "thirteen", "fourteen", "fifteen", "sixteen", "seventeen", "eighteen", "ninteen"};
	public String[] tens = {"", "ten", "twenty", "thirty", "fourty", "fifty", "sixty", "seventy", "eighty", "ninty"};
	public String[] bigs = {"thousand", "million", "billion"};
	
	public String pronounceNumber(int num){
		if(num < 0) return "";
		if(num == 0) return "zero";
		
		String res = "";
		int count = 0;
		
 		while(num != 0){
 			int remaining = num % 1000;
 			res = convertHundreds(remaining) + res;
 			num = num / 1000;
 			// these is the count for the bigs, increase per three digits
 			count++;
 			if(num != 0){
 				res = " " + bigs[count - 1] + " " + res;
 			}
 		}
 		return res;
	}
	// convert the number less than 1000
	public String convertHundreds(int num){
		if(num == 0) return "";
		String res = "";
		if(num % 100 < 20){
			res = ones[num % 100];
			num /= 100;
		}else{
			res = ones[num % 10];
			num /= 10;
			if(num % 10 != 0){
				res = tens[num % 10] + " " + res;
				num /= 10;
			}
		}
		if(num == 0) return res;
		else{
			if(res.length() == 0){
				return ones[num] + " hundred";
			}else{
				return ones[num] + " hundred and " + res;
			}
		}
	}
}
