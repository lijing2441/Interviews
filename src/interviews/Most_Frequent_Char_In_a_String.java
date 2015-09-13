package interviews;

import java.util.Scanner;

public class Most_Frequent_Char_In_a_String {
	/**
	 * Problem: find the most frequent character in a string
	 * 
	 * @logic This problem is equivalent to finding the most frequent element in an unsorted array:
	 * 
	 * Questions for interviewer:
	 * 
	 * First: does the string contain only ascii or unicode? Alphabetic or Digit or punctuation?
	 * 		  Here I assume only ASCII -> 128. Unicode -> Character.MAX_VALUE + 1.
	 * 		Moreover: Digit, uppercase and lowercase are not connected in ASCII code.
	 * 
	 * Second: long string or short string? 
	 * 
	 * Third: Validation of the input?
	 * 
	 * O(n) - one pass
	 */
	
	public static char getMax(String str) throws Exception{
		if(str == null || str.length() == 0) throw new Exception();
		char maxChar = ' ';
		int maxCount = 0;
		int[] count = new int[128];
		for(int i = 0; i < str.length(); i++){
			int index = (int) str.charAt(i);
			count[index]++;
			if(count[index] > maxCount){
				maxCount = count[index];
				maxChar = (char) index;
			}
		}
		return maxChar;
	}
	
	@SuppressWarnings("resource")
	public static void main(String[] args) throws Exception{
		Scanner it = new Scanner(System.in);
		System.out.print("Enter a string to start: ");
		String input = it.next();
		System.out.println(getMax(input));
	}
}
