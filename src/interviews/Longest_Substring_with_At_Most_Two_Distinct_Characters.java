package interviews;

import java.util.HashSet;
import java.util.Scanner;

public class Longest_Substring_with_At_Most_Two_Distinct_Characters {
	/**
	 * Problem: Given a string, find the longest substring that contains only two unique characters. 
	 * For example, given "abcbbbbcccbdddadacb", the solution is "bcbbbbcccb".
	 */
	//Method with only O(n) - one pass and O(1) space
	public String lengthOfLongestSubstringTwoDistinct(String s) {
		int n = s.length();
		if (n == 0) return "";
		char[] arr = s.toCharArray();
		int maxEndHere = 1, maxSoFar = 1, lastCharLen = 1;
		int maxStart = 1, maxEnd = 1;
		int curStart = 0;
		char preChar = '\0', lastChar = arr[0];
		for (int i = 1; i < n; i++) {
			if (arr[i] == lastChar) {
				maxEndHere++;
				lastCharLen++;
			}
			else if (arr[i] == preChar) {
				maxEndHere++;
				//re-define the lastChar
				preChar = lastChar;
				lastChar = arr[i];
				lastCharLen = 1;
			}
			else {
				maxEndHere = lastCharLen + 1;
				curStart = i - lastCharLen;
				preChar = lastChar;
				lastChar = arr[i];
				lastCharLen = 1;
			}
			if(maxEndHere > maxSoFar){
				maxSoFar = maxEndHere;
				maxStart = curStart;
				maxEnd = i;
			}
			
		}
		return s.substring(maxStart, maxEnd + 1);
	}

	/**
	 * Method I:  Naive solution. We use two pointers to track the start of the substring and 
	 * 			  the iteration position.
	 * worst case: O(n^2)
	 */
	@SuppressWarnings("unused")
	public static String substringWith2Chars(String str){
		if(str == null || str.length() <= 2) return str;
		char[] arr = str.toCharArray();
		int maxStart = 0, maxEnd = 0, maxLen = 1;
		int curStart = 0;
		//use a HashSet to record the characters passed
		HashSet<Character> set = new HashSet<Character>();
		set.add(arr[0]);
		//traverse to find the ending point
		for(int i = 1; i < arr.length; i++){
			// if the current set does not contain the current character
			// only the first character is effective
			if(set.add(arr[i])){
				//the addition of the ith character is the 3rd.
				if(set.size() > 2){
					String s = str.substring(curStart, i);
					if(i - curStart > maxLen){
						maxStart = curStart;
						maxEnd = i;
						maxLen = i - curStart;
					}
					//update the current string start to add new characters
					curStart = i - LenWith1Chars(str);
					// reset the set
					set.clear();
					set.add(arr[i -1]);
				}
			}
		}
		if(arr.length - maxStart > maxLen) return str.substring(maxStart, arr.length); // the last case
		return str.substring(maxStart, maxEnd);
	}
	//This helper method returns the length that contains only one character from the right side.
	public static int LenWith1Chars(String str){
		if(str == null || str.length() == 0) return 0;
		if(str.length() == 1) return 1;
		char[] arr = str.toCharArray();
		char r = arr[arr.length - 1];
		int len = 1;
		for(int i = arr.length - 2; i >= 0; i--){
			if(r == arr[i]){
				len++;
			}else{
				break;
			}
		}
		return len;
	}
	
	@SuppressWarnings("resource")
	public static void main(String[] args){
		Scanner it = new Scanner(System.in);
		System.out.println("Enter a string to start: ");
		String input = it.next();
		System.out.println(substringWith2Chars(input));
	}
	
	/**
	 * Method II: Scalable Solution to k 
	 * 
	 * use a hashset to note down the distinct char numbers so far
	 */
	public static String findMaxKCharSubstring(String str, int k){
		if(str == null || str.length() <= 2) return str;
		char[] arr = str.toCharArray();
		
		int maxLen = 0, maxStart = 0, maxEnd = 0;
		int curStart = 0;		
		
		HashSet<Character> set = new HashSet<Character>();
		set.add(arr[0]);
		
		for(int curEnd = 1; curEnd < arr.length; curEnd++){
			char tmp = arr[curEnd];
			// if we can add the char
			if(set.add(tmp)){
				// the addition of the one more char make it > k
				if(set.size() > k){
					if(maxLen < curEnd - curStart){
						maxStart = curStart;
						maxEnd = curEnd;
						maxLen = curEnd - curStart;
					}
					//remove the left most one character
					set.remove(arr[curStart]);
					
					//find the newStart for the next substring with k distinct chars
					curStart = removeFirst(arr, curStart, curEnd);
				}
			}
		}
		if(arr.length - maxStart > maxLen) return str.substring(maxStart, arr.length);
		return str.substring(maxStart, maxEnd);
	}
	public static int removeFirst(char[] arr, int first, int end){
		for(int i = end - 1; i >= first; i--){
			if(arr[i] == arr[first]){
				return i + 1;
			}
		}
		return first + 1;
	}
}
