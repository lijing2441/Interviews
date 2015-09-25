package interviews;

public class TwoSigma_PowerOf4 {
	// logn 时间
	public boolean isPowerOfFour(int n) {
		int count = 0;

		/* Check if there is only one bit set in n */
		// power of 4应该只有一个 bit 被set
		if (n > 0 && (n & (n - 1)) == 0) {
			/* count 0 bits before set bit */
			while (n > 1) {
				n >>= 1;
				count += 1;
			}

			/* If count is even then return true else false */
			return (count % 2 == 0) ? true : false;
		}
		/*
		 * If there are more than 1 bit set then n is not a power of 4
		 */
		return false;
	}
}
