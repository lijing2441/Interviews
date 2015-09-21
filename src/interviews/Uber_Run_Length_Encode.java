package interviews;

public class Uber_Run_Length_Encode {
	/**
	 * Run length encode, aaaccccbbbbb->3a4c5b->CaDcEb
	 */
	public static String runLengthEncode(String input) {
		if (input == null || input.length() == 0) return "";
		StringBuilder sb = new StringBuilder();
		int count = 1;
		char curChar = input.charAt(0); 
		for (int i = 1; i <= input.length(); i++) {
			if (i == input.length() || input.charAt(i) != curChar) {
				char countChar = (char)(count + 'A' - 1);
				sb.append(countChar);
				sb.append(curChar);
				if (i < input.length()) {
					count = 1;
					curChar = input.charAt(i);
				}
			} else {
				count++;
			}
		}
		return sb.toString();
	}
	
	public static void main(String[] args) {
		String input = "aaaccccbbbbb";
		System.out.println(runLengthEncode(input));
	}
}
