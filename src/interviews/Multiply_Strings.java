package interviews;

public class Multiply_Strings {
	/**
	 * Given two numbers represented as strings, return multiplication of the
	 * numbers as a string.
	 * 
	 * Note: The numbers can be arbitrarily large and are non-negative.
	 */
	public String multiply(String num1, String num2) {
        int m = num1.length();
        int n = num2.length();
        
        char[] ch1 = num1.toCharArray();
        char[] ch2 = num2.toCharArray();
        int[] res = new int[m + n];

        for(int i = m - 1; i >= 0; i--){
            for(int j = n - 1; j >= 0; j--){
                res[m + n - i - j - 2] += (ch1[i] - '0')*(ch2[j] - '0');
                res[m + n - i - j - 1] += res[m + n - i - j - 2]/10;
                res[m + n - i - j - 2] = res[m + n - i - j - 2] % 10;
            }
        }
        String result = "";
        for(int i = m + n - 1; i >= 0; i--){
            if(res[i] != 0){
                for(int j = i; j >= 0; j--){
                    result += String.valueOf(res[j]);
                }
                return result;
            }
        }
        return "0";
    }
	
}
