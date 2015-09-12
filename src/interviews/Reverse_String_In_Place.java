package interviews;

public class Reverse_String_In_Place {
	
	/**
	 * String reversed = new StringBuilder(s).reverse().toString();
	 * if stringbuilder is allowed
	 * 
	 * 如果是Java，应该跟面试官指出String是immutable，所以需要用char array来做。
	 */
	public static String reverse(String orig) {
		char[] s = orig.toCharArray();
		int n = s.length;
		int halfLength = n / 2;
		for (int i = 0; i < halfLength; i++) {
			char temp = s[i];
			s[i] = s[n - 1 - i];
			s[n - 1 - i] = temp;
		}
		return new String(s);
	}
}
