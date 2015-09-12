package interviews;

public class Decode_Ways {
	/**
	 * A message containing letters from A-Z is being encoded to numbers using
	 * the following mapping:
	 * 
	 * 'A' -> 1 'B' -> 2 ... 'Z' -> 26 Given an encoded message containing
	 * digits, determine the total number of ways to decode it.
	 * 
	 * For example, Given encoded message "12", it could be decoded as "AB" (1
	 * 2) or "L" (12).
	 * 
	 * The number of ways decoding "12" is 2.
	 */
	// O(n), O(n)
	public int numDecodings(String s) {
		int len = s.length();
		if (len == 0)
			return 0;

		int[] dp = new int[len + 1];
		dp[len] = 1;
		dp[len - 1] = s.charAt(len - 1) == '0' ? 0 : 1;
		for (int i = len - 2; i >= 0; i--) {
			if (s.charAt(i) == '0')
				continue;
			else {
				String cur = s.substring(i, i + 2); // the current node
				int value = Integer.parseInt(cur);
				if (value <= 26)
					dp[i] = dp[i + 1] + dp[i + 2]; // res = oneStep + twoStep;
				else
					dp[i] = dp[i + 1];
			}
		}
		return dp[0];
	}
}
