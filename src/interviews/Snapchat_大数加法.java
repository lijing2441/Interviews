package interviews;

public class Snapchat_大数加法 {
	/**
	 * 大数加法, follow up: 负数怎么办 (即减法)
	 */
	public static String stringPlus(String num1, String num2) {
		//ignore edge cases about null/empty strings
		char[] arr1 = num1.toCharArray();
		char[] arr2 = num2.toCharArray();
		int len1 = arr1.length;
		int len2 = arr2.length;
		//int[] res = new int[Math.max(len1, len2) + 1];
		// 找符号 if negative is possible
		int index1 = 0, index2 = 0;
		boolean minus1 = false, minus2 = false, allMinus = false;
		if (arr1[0] == '-' && arr2[0] == '-') {
			index1++;
			index2++;
			allMinus = true;
		} else if (arr1[0] == '-') {
			minus1 = true;
			index1++;
		} else if (arr2[0] == '-') {
			minus2 = true;
			index2++;
		}
		int carry = 0;
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < Math.max(len1 - index1, len2 - index2); i++) {
			int c1 = 0, c2 = 0;
			if (i < len1 - index1) c1 = (int)(arr1[len1 - i - 1] - '0');
			if (i < len2 - index2) c2 = (int)(arr2[len2 - i - 1] - '0');
			if (minus1) {
				c1 = -c1;
			} else if (minus2) {
				c2 = -c2;
			}
			int set = c1 + c2 + carry;
			if (set < 0) {
				carry = -1;
				set += 10;
			} else carry = set / 10;
			set %= 10;
			sb.insert(0, set);
		}
		
		if (carry == -1) {
			int len = sb.length();
			String cur = "1";
			for (int i = 0; i < len; i++) {
				cur += "0";
			}
			sb.insert(0, "-");
			String next = stringPlus(cur, sb.toString());
			int i = 0;
			while (next.charAt(i) == '0') {
				i++;
			}
			return "-" + next.substring(i);
		}
		if (carry == 1) {
			return "1" + sb.toString();
		}
		if (allMinus) return "-" + sb.toString();
		return sb.toString();
	}
	public static void main(String[] args) {
		String num1 = "-240000";
		String num2 = "2000";
		System.out.println(stringPlus(num1, num2));
	}
}
