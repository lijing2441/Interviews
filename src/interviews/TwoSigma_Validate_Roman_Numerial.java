package interviews;
import java.util.regex.Pattern;

public class TwoSigma_Validate_Roman_Numerial {
	static Pattern pattern = Pattern.compile("M{0,4}(CM|CD|D?C{0,3})(XC|XL|L?X{0,3})(IX|IV|V?I{0,3})");
	public static void validateRomanNumber(String input) {	
		if (isMatch(input)) {
			int res = romanToInt(input);
			System.out.println(res);
		} else {
			System.out.println("invalid");
		}
	}
	
	public static boolean isMatch(String s) {
		return s!=null && !"".equals(s) && pattern.matcher(s.toUpperCase()).matches();
	}
	
	public static int romanToInt(String input) {
		int res = 0;
		for (int i = input.length() - 1; i >= 0; i--) {
			char c = input.charAt(i);
			if (c == 'I') {
				if (res >= 5) res -= 1;
				else res += 1;
			} else if (c == 'V') res += 5;
			else if (c == 'X') {
				if (res >= 50) res -= 10;
				else res += 10;
			} else if (c == 'L') res += 50;
			else if (c == 'C') {
				if (res >= 500) res -= 100;
				else res += 100;
			} else if (c == 'D') res += 500;
			else if (c == 'M') res += 1000;
		}
		return res;
	}
	
	public static void main(String[] args) {
		String input = "VI";
		
		validateRomanNumber(input);
	}
}
