package interviews;

public class Snapchat_字符串比较 {
	/**
	 * 很多用户名都会重复，通过后面的数字来区分，但是在排序的时候严格按照字符串排序就会出现 abc10 排在 abc2
	 * 前面（因为‘1’比‘2’要小），但是事实上他们想要的是 abc10 排在 abc2
	 * 后面（10比2要大），于是让写一个字符串比较函数。注意转成int会有溢出问题。
	 * 
	 * => 字符串少的时候，使用insertion sort，减少stack
	 * => 字符串多的时候，使用merge sort
	 * 两个string比较的时候，字母部分比较按字母表顺序。数字部分比较去掉前导0，比较长度，长度相同，按字母表顺序比较。
	 */
	// negative: id1小; positive: id2小
	public static int compare(String id1, String id2) {
		int end1 = findStringPartEnd(id1);
		int end2 = findStringPartEnd(id2);
		String str1 = id1.substring(0, end1);
		String str2 = id2.substring(0, end2);
		if (str1.compareTo(str2) != 0) return str1.compareTo(str2);
		String num1 = removeLeadingZeros(id1.substring(end1));
		String num2 = removeLeadingZeros(id2.substring(end2));
		if (num1.length() != num2.length()) {
			return num1.length() - num2.length();
		} else {
			for (int i = 0; i < num1.length(); i++) {
				if (num1.charAt(i) != num2.charAt(i)) {
					return num1.charAt(i) - num2.charAt(i);
				}
			}
		}
		return 0;
	}
	public static int findStringPartEnd(String id) {
		int index = 0;
		while (Character.isLetter(id.charAt(index))) {
			index++;
		}
		return index;
	}
	public static String removeLeadingZeros(String num) {
		int index = 0;
		while (num.charAt(index) == '0') {
			index++;
		}
		return num.substring(index);
	}
	public static void main(String[] args) {
		String i1 = "abc2";
		String i2 = "abc10";
		int res = compare(i1, i2);
		if (res < 0) System.out.println("i1 < i2");
		else System.out.println("i1 > i2");
	}
}
