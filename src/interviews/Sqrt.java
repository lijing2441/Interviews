package interviews;

public class Sqrt {
	// return integer, O(logn) time,
	public int sqrt(int x) {
		if (x == 0 || x == 1)
			return x;
		int left = 0;
		int right = x;
		int mid = 0;
		int res = 0;
		while (left <= right) {
			mid = (left + right) / 2;
			if (mid == x / mid) {
				return mid;
			} else if (mid > x / mid) {
				right = mid - 1;
			} else {
				left = mid + 1;
				res = mid;
			}
		}
		return res;
	}                  
                                                                                                                                           
	// Newton's method
	public int sqrt2(int x) {
		if (x == 0)
			return 0;
		double res = 1;
		while (Math.abs(res / 2 + x / (2 * res) - res) >= 0.01) {
			res = res / 2 + x / (2 * res);
		}
		if ((int) res * (int) res > x)
			res--;
		return (int) res;
	}

	// return double, stop until the difference less than epsilon = 0.0001
	public double sqrt(double n, double eps) {
		double left = 0.0;
		double right = Math.max(1.0, n);
		double mid = 0;
		while (left + eps < right) {
			mid = left + (right - left) / 2;
			if (mid > n / mid) {
				right = mid;
			} else {
				left = mid;
			}
		}
		return left;
	}
}
