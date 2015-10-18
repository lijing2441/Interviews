package interviews;

import java.util.HashSet;
import java.util.Set;

public class Find_Well_Formed_Strings {
	/**
	 * Write an algo­rithm to Print All the Well Ordered Per­mu­ta­tions of a
	 * Given String.
	 * 
	 * Well Ordered String: When all the alpha­bets in a string occur in the
	 * increas­ing order irre­spec­tive of Lower Case or Upper case.
	 * 
	 * Example:
	 * 
	 * "Sumit" - Not Well Ordered . 'u' occurred after 'S'.
	 * 
	 * "Now" - Well Ordered. N<o<W.
	 * 
	 * Get the input string. Find out all the per­mu­ta­tions of a String.
	 * Before print­ing check if the per­mu­ta­tion is well formed.
	 */
	public static Set<String> wellFormedStrings(String s) {
		Set<String> res = new HashSet<String>();
		permutationHelper(res, s.toCharArray(), 0);
		return res;
	}
	public static void permutationHelper(Set<String> res, char[] charArr, int start) {
		if (start == charArr.length) {
			if (isWellFormed(charArr)) {
				res.add(new String(charArr));
			}
			return;
		}
		for (int i = start; i < charArr.length; i++) {
			swap(charArr, start, i);
			permutationHelper(res, charArr, start + 1);
			swap(charArr, start, i);
		}
	}
	
	public static boolean isWellFormed(char[] arr) {
		for (int i = 0; i < arr.length - 1; i++) {
			if (Character.toLowerCase(arr[i]) > Character.toLowerCase(arr[i + 1])) {
				return false;
			}
		}
		return true;
	}
	
	public static void swap(char[] arr, int i, int j) {
		char tmp = arr[i];
		arr[i] = arr[j];
		arr[j] = tmp;
	}
	
	public static void main(String[] args) {
		Set<String> res = wellFormedStrings("Interview");
		for (String s: res) {
			System.out.println(s);
		}
	}
}
