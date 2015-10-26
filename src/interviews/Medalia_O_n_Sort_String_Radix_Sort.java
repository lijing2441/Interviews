package interviews;

import java.util.Arrays;

public class Medalia_O_n_Sort_String_Radix_Sort {
	/**
	 * 必须先知道alphabet size，ex 26
	 */
	public static String sortStringRadix(String s) {
		int[] sorted = new int[26];
		char[] arr = s.toCharArray();
		for (char c : arr) {
			int index = (int)(c - 'a');
			sorted[index]++;
		}
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < 26; i++) {
			for (int j = 0; j < sorted[i]; j++) {
				sb.append((char)('a' + i));
			}
		}
		return sb.toString();
	}
	
	public static void main(String[] args) {
		System.out.println(sortStringRadix("zdcbacdca"));
		char[] arr = "zdcbacdca".toCharArray();
		Arrays.sort(arr);
		System.out.println(new String(arr));
	}
}
