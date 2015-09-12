package interviews;

public class Add_Digits {
	/**
	 * Given a non-negative integer num, repeatedly add all its digits until the
	 * result has only one digit.
	 * 
	 * For example:
	 * 
	 * Given num = 38, the process is like: 3 + 8 = 11, 1 + 1 = 2. Since 2 has
	 * only one digit, return it.
	 */
	// Basic loop version
	public int addDigitsLoop(int num) {
        if(num < 10) return num;
        while(num >= 10) {
            char[] arr = Integer.toString(num).toCharArray();
            int cur = 0;
            for(char c : arr) {
                cur += (int)(c - '0');
            }
            num = cur;
        }
        return num;
    }
	// Math version O(1) runtime
	public int addDigits(int num) {
        if(num < 10) return num;
        if(num % 9 == 0) return 9;
        return num % 9;
    }
}
