package interviews;

import java.util.Scanner;

/**
 * Decimal to octal conversion
 */
public class Conversion_Numerial {
	public static int convert_From_10_To_Octal(int numberToConvert){
		int pos = 1;
		int res = 0;
		while(numberToConvert != 0){
			int tmp = (numberToConvert % 8) * pos;
			pos *= 10;
			numberToConvert /= 8;
			res += tmp;
		}
		return res;
	}
	
	// other conversion, using ERIC as example,
	// E,R,I,C corresponds to 0,1,2,3
	// given a number, convert it to ERIC numeral, vice versa
	public static char[] numerial = {'E', 'R', 'I', 'C'};
	
	public static String convert_From_10_To_ERIC(int numberToConvert){
		StringBuilder sb = new StringBuilder();
		while(numberToConvert != 0){
			// find the each digit one by one from the right
			int index = numberToConvert % 4;
			// equal to res = numerial[index] + res
			sb.insert(0, numerial[index]);
			numberToConvert /= 4;
		}
		return sb.toString();
	}
	
	/**
	 * Below: 
	 * (1) from octal to decimal
	 * (2) from ERIC to decimal
	 */
	
	public static int convert_From_Octal_to_Decimal(String oct){
		int res = 0;
		for(int i = 0; i < oct.length(); i++){
			int cur = (int)oct.charAt(i) - '0';
			if(cur < 0 || cur > 7){
				System.out.print("error");
				return -1;
			}
			res *= 8;
			res += cur;
		}
		return res;
	}
	public static int convert_From_ERIC_to_Decimal(String eric){
		int res = 0;
		for(int i = 0; i < eric.length(); i++){
			int cur = 0;
			char c = eric.charAt(i);
			if(c == 'R') cur = 1; 
			else if(c == 'I') cur = 2; 
			else if(c == 'C') cur = 3;
			res *= 4;
			res += cur;
		}
		return res;
	}
	//driver function
	@SuppressWarnings("resource")
	public static void main (String args[]) {
        Scanner input = new Scanner(System.in);
        System.out.print("Enter number: ");
        int oct  = input.nextInt();
        String result = String.valueOf(convert_From_10_To_ERIC(oct));
        System.out.println(result);
    }
}
