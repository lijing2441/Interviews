package interviews;

public class Add_Binary_Numbers {
	/**
	 * Given two binary strings, return their sum (also a binary string).
	 * 
	 * For example, a = "11" b = "1" Return "100".
	 */
	// O(word length), O(1)
	public static String addBinary(String a, String b) {
		if (a == null || b == null)
			return a == null ? b : a;
		int len_a = a.length();
		int len_b = b.length();
		int carry = 0;
		int maxLen = Math.max(len_a, len_b);
		String res = "";
		for (int i = 0; i < maxLen; i++) {
			// convert char to integer!
			int _a = i < len_a ? a.charAt(len_a - i - 1) - '0' : 0;
			// remember to start from end
			int _b = i < len_b ? b.charAt(len_b - i - 1) - '0' : 0;
			int set = carry + _a + _b;
			carry = set / 2;
			res = String.valueOf(set % 2) + res;
		}
		return carry == 0 ? res : "1" + res;
	}

	public static void main(String[] args) {
		String a = "1010101010101";
		String b = "11101110";
		System.out.println(addBinary(a, b));
	}
}
