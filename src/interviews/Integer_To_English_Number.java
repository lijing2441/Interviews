package interviews;

public class Integer_To_English_Number {
	/**
	 * Given a number write a function to convert that number to a string that
	 * would be seen on a check. 
	 * Example: 2376 -> two thousand three hundred and seventy six
	 */
	private String[] ones = {"", "One", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine", "Ten", "Eleven", "Twelve", "Thirteen", "Fourteen", "Fifteen", "Sixteen", "Seventeen", "Eighteen", "Nineteen"};
    private String[] tens = {"Twenty", "Thirty", "Forty", "Fifty", "Sixty", "Seventy", "Eighty", "Ninety"};
    private String[] bigs = {"Thousand", "Million", "Billion"};
    
    public String numberToWords(int num) {
        if (num == 0) return "Zero";
        if (num < 1000) return convertUnderThousand(num);
        int bigIndex = 0;
        String res = "";
        while (num > 0) {
            if (num % 1000 > 0) {
                int remain = num % 1000;
                String cur = convertUnderThousand(remain);
                if (bigIndex > 0) {
                    cur += (" " + bigs[bigIndex - 1]); 
                } 
                if (res.length() > 0) res = " " + res;
                res = cur + res;
            }
            num /= 1000;
            bigIndex++;
        }
        return res;
    }
    public String convertUnderThousand(int num) {
        String hundred = ones[num / 100] + " Hundred";
        String small = "";
        if (num % 100 > 0) {
            int under = num % 100;
            if (under < 20) {
                small = ones[under];
            } else {
                small = tens[under / 10 - 2];
                if (num % 10 > 0) {
                    small += (" " + ones[under % 10]);
                }
            }
        }
        
        if (num >= 100 && small.length() > 0) return hundred + " " + small;
        else if (num >= 100) return hundred;
        else return small;
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
