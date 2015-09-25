package interviews;

public class Uber_求最大平方数的约数 {
	/**
	 * 给一个数，返回最大平方数的除数
	 * 
	 * sqrt(100) -> 10, 1
	 * sqrt(300) -> 10, 3
	 * sqrt(8) -> 2, 2
	 */
	public static int[] getRemainTimesWithSQRT(int input) {
		if (input <= 0) return new int[0];
		// 先找到最大平方数
		int sqrt = sqrt(input);
		
		// 再向下找可以整除的最大的平方数
		// sqrt(n) time, at most 搜根号n次
		while (true) {
			int square = sqrt * sqrt;
			if (input % square == 0) {
				break;
			} else {
				sqrt--;
			}
		}
		int remain = input / (sqrt * sqrt);
		return new int[] {sqrt, remain};
	}
	// logn
	public static int sqrt(int input) {
		if (input == 0 || input == 1) return input;
		int left = 0, right = input;
		int res = -1;
		while (left <= right) {
			int mid = (left + right) / 2;
			if ((input / mid) == mid) {
				return mid;
			} else if ((input / mid) > mid) {
				left = mid + 1;
				res = left;
			} else {
				right = mid - 1;
			}
		}
		return res;
	}
	public static void main (String[] args) {
		int input = 8;
		int[] res = getRemainTimesWithSQRT(input);
		for (int i : res) {
			System.out.println(i);
		}
	}
	
}
