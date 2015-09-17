package interviews;

public class Delete_Digits {
	/**
	 * Given string A representative a positive integer which has N digits,
	 * remove any k digits of the number, the remaining digits are arranged
	 * according to the original order to become a new positive integer.
	 * 
	 * Find the smallest integer after remove k digits.
	 * 
	 * N <= 240 and k <= N.
	 * 
	 * Example Given an integer A = "178542", k = 4
	 * 
	 * return a string "12"
	 */
	// O(k * N)
	public String DeleteDigits(String A, int k) {
        if (A.length() == k) return "";
        for (int i = 0; i < k; i++) {
            for (int j = 0; j < A.length(); j++) {
            	// 从最高位开始找，找到比下一位大的数字就delete
                if (j == A.length() - 1 || A.charAt(j) > A.charAt(j + 1)) {
                    A = delete(A, j);
                    break;
                }
            }
        }
        int i = 0;
        while (i < A.length() && A.charAt(i) == '0') {
            i++;
        }
        return A.substring(i);
    }
	// 每次remove一个char
    public String delete(String A, int i) {
        return A.substring(0, i) + A.substring(i + 1);
    }
}
