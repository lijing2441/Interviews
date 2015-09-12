package interviews;

public class Count_1_In_Binary {
	/**
	 * Given 32, return 1
	 * 
	 * Given 5, return 2
	 * 
	 * Given 1023, return 9
	 */
	public int countOnes(int num) {
		// write your code here
		int res = 0;
		while (num != 0) {
			num = num & (num - 1);
			res++;
		}
		return res;
	}
}
