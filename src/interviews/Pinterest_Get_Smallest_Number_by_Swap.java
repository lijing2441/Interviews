package interviews;

public class Pinterest_Get_Smallest_Number_by_Swap {
	/**
	 * 给你一个number，只会是正数，比如3251， 你可以给它相邻两个digit做swap。
	 * 
	 * 给你一个n位的number，可以交换m次。 求能得到的最小的数。
	 * 
	 * 例如，3251的话 m=1，得到的结果应该是2351
	 * 
	 * 两种情况: if m >= n: 找最小的换到第一位，然后循环往复
	 * 		   otherwise, 在前m中找最下的换到第一位。
	 * 保持两个指针，现在可以换的位置，已经换完的位置下一位
	 * 
	 */
	/**
	 * @param input
	 * @param m   number of swaps between the neighbor digits.
	 */
	public static int getSmallestNumWithSwap(int input, int m) {
		String res = getSmallestNumHelper(String.valueOf(input), 0, m);
		int i = 0;
		// get rid of the leading 0s
		while (res.charAt(i) == '0') {
			i++;
		}
		return Integer.parseInt(res.substring(i));
	}
	// recursive helper method
	public static String getSmallestNumHelper(String input, int start, int m) {
		if (m == 0 || start == input.length()) return input;
		// find the smallest digit to swap to the start
		int smallestDigit = getSmallestDigitInRange(input, start, m);
		if (smallestDigit == start) { // in this case, we do not need to swap at this digit
			return getSmallestNumHelper(input, start + 1, m);
		}
		// swap to get the smallestDigit to the leading position
		String swaped = swapInPair(input.toCharArray(), smallestDigit, start);
		return getSmallestNumHelper(swaped, start + 1, m - smallestDigit + start);
	}
	public static String swapInPair(char[] arr, int end, int start) {
		if (end == start) return new String(arr);
		for (int i = end; i > start; i--) {
			char tmp = arr[i];
			arr[i] = arr[i - 1];
			arr[i - 1] = tmp;
		}
		return new String(arr);
	}
	// get the smallest digit in the range from start to Math.min(input.length, m + start)
	public static int getSmallestDigitInRange(String input, int start, int m) {
		char[] arr = input.toCharArray();
		int smallestDigit = start;
		for (int i = start; i < input.length() && i <= start + m; i++) {
			if (arr[smallestDigit] > arr[i]) {
				smallestDigit = i;
			}
		}
		return smallestDigit;
	}
	
	
	public static void main(String[] arg) {
		int num = 32516;
		System.out.println(getSmallestNumWithSwap(num, 4));
	}
}
