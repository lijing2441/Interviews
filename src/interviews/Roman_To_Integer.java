package interviews;

public class Roman_To_Integer {
	public static int romanToInt(String s) {
		int res = 0;
		for (int i = s.length() - 1; i >= 0; i--) {
			char c = s.charAt(i);
			switch (c) {
			case 'I':
				res += res >= 5 ? -1 : 1;
				break;
			case 'V':
				res += 5;
				break;
			case 'X':
				res += res >= 50 ? -10 : 10;
				break;
			case 'L':
				res += 50;
				break;
			case 'C':
				res += res >= 500 ? -100 : 100;
				break;
			case 'D':
				res += 500;
				break;
			case 'M':
				res += 1000;
				break;
			}
		}
		return res;
	}

	public static void main(String[] args) {
		String s = "MCMLIV";
		System.out.println(romanToInt(s));
	}

	/**
	 * Integer to Roman
	 */
	public String intToRoman(int num) {
		StringBuffer s = new StringBuffer();
		while (num > 0) {
			if (num >= 1000) {
				s.append("M");
				num -= 1000;
			} else if (num >= 900) {
				s.append("CM");
				num -= 900;
			} else if (num >= 500) {
				s.append("D");
				num -= 500;
			} else if (num >= 400) {
				s.append("CD");
				num -= 400;
			} else if (num >= 100) {
				s.append("C");
				num -= 100;
			} else if (num >= 90) {
				s.append("XC");
				num -= 90;
			} else if (num >= 50) {
				s.append("L");
				num -= 50;
			} else if (num >= 40) {
				s.append("XL");
				num -= 40;
			} else if (num >= 10) {
				s.append("X");
				num -= 10;
			} else if (num >= 9) {
				s.append("IX");
				num -= 9;
			} else if (num >= 5) {
				s.append("V");
				num -= 5;
			} else if (num >= 4) {
				s.append("IV");
				num -= 4;
			} else if (num >= 1) {
				s.append("I");
				num -= 1;
			}
		}
		return new String(s);
	}
}
