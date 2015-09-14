package interviews;

public class Additive_sequence {
	/**
	 * Check whether a given string is an additive sequence.
	 * 
	 * Example:
	 * 
	 * 122436 is additive, since 12+24=36
	 * 
	 * brute force: O(n^3)
	 */
	public static boolean isAdditive(String num) {
		for (int i = 1; i < num.length(); i++) {
			int num1 = Integer.parseInt(num.substring(0, i));
			for (int j = i + 1; j < num.length(); j++) {
				int num2 = Integer.parseInt(num.substring(i, j));
				int thirdIndex = j;
				int remaining = Integer.parseInt(num.substring(thirdIndex));
				// 看是否现在配上，还是以后配上
				while (num1 + num2 <= remaining) {
					int num3 = num1 + num2;
					String newNum = (new Integer(num3)).toString();
					int len = newNum.length();
					if (thirdIndex + len > num.length()) break;
					else if (num.substring(thirdIndex, thirdIndex + len).equals(newNum)) {
						thirdIndex += len;
						// find the match!
						if (thirdIndex == num.length()) return true;
						else {
							// 继续配后面
							num1 = num2;
							num2 = num3;
							remaining = Integer.parseInt(num.substring(thirdIndex));
						}
					} else {
						break;
					}
				}
			}
		}
		return false;
	}
	
	public static void main(String[] args) {
		String num = "122436";
		String num2 = "1235";
		System.out.println(isAdditive(num));
		System.out.println(isAdditive(num2));
	}
}
