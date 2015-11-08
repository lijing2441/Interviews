package interviews;

public class Sqrt {
	// return integer, O(logn) time,
	public int sqrt(int x) {
		long res = 0;
        if (x <= 1) return x;
        long l = 0, r = x;
        while (l <= r) {
            long mid = (l + r) / 2;
            if (mid * mid == x) return (int)mid;
            else if (mid * mid > x) {
                r = mid - 1;
            } else {
                l = mid + 1;
                res = mid;
            }
        }
        return (int)res;
	}                  
                                                                                                                                           
	// Newton's method
	public int sqrt2(int x) {
		long res = x;
		while (res * res > x) {
			res = (res + x / res) / 2;
		}
		return (int)res;
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
