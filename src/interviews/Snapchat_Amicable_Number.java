package interviews;

public class Snapchat_Amicable_Number {
	/**
	 * Amicable numbers are two different numbers so related that the sum of the
	 * proper divisors of each is equal to the other number.
	 * 
	 * 找一个数的amicable number
	 */
	// 计算n的所有小于n的因素和
	public static int amicable_pair(int n) {
		int i = 0;
		int sum = 1;
		int half = n / 2; // 最大factor为half
		for (i = 2; i <= half; i++) {
			if (n % i == 0)
				sum += i;
		}
		return sum;
	}
}
