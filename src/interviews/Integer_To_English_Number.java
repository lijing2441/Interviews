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
		// 百位和十位分开
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
	
	
	/**
	 * Recursive way
	 */
	public class Solution {
		String[] lows = "Zero One Two Three Four Five Six Seven Eight Nine Ten Eleven Twelve Thirteen Fourteen Fifteen Sixteen Seventeen Eighteen Nineteen"
				.split(" ");
		String[] tens = "Twenty Thirty Forty Fifty Sixty Seventy Eighty Ninety"
				.split(" ");
		String[] bigs = "Hundred Thousand Million Billion".split(" ");

		public String convert(int n) {
			if (n < 20)
				return lows[n];

			if (n < 100)
				return tens[n / 10 - 2] + helper(n % 10);

			if (n < 1000)
				return lows[n / 100] + " " + bigs[0] + helper(n % 100);

			int m = 1000;

			for (int i = 1; i < bigs.length; i++, m *= 1000)
				if (n / 1000 < m)
					return convert(n / m) + " " + bigs[i] + helper(n % m);

			return convert(n / m) + " " + bigs[bigs.length - 1] + helper(n % m);
		}

		public String helper(int n) {
			return n == 0 ? "" : " " + convert(n);
		}
	}
}
