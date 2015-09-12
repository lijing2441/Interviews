package interviews;

public class String_To_Long {
	public long stringToLong(String s) throws NumberFormatException {
		long res = 0;
		long factor = 1;
		for (int i = s.length() - 1; i > 0; i--) {
			char c = s.charAt(i);
			if (c < '0' || c > '9')
				throw new NumberFormatException("invalid format");
			int k = c - '0';
			res = res + factor * k;
			factor = factor << 1; // factor *= 10;
		}
		char c = s.charAt(0);
		if (c == '-')
			return -res;
		else
			res += (c - '0') * factor;
		return res;
	}
}
