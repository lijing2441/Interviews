package interviews;

import java.util.ArrayList;
import java.util.List;

public class Quora_Fibonacci_To_Target {
	public static int getFibonacciToTarget(int target) {
		if (target <= 1) return target;
		List<Integer> fibo = fibonacci(target);
		// the final number in fibo should be larger or equals to target
		int res = 0;
		
		// get the first element done
		if (fibo.get(fibo.size() - 1) == target) {
			res = fibo.size() - 1;
			return res;
		} else {
			res = fibo.size() - 2;
			target -= fibo.get(fibo.size() - 2);
		}
		// get the others one by one, since we have already get the fibo, it's easy to get them
		while (target > 0) {
			int cur = findFiboNum(fibo, target); // log(size of fibo list)
			res += 1;
			target -= cur;
		}
		return res;
	}
	
	public static List<Integer> fibonacci(int target) {
		List<Integer> res = new ArrayList<Integer>();
		int num1 = 1;
		int num2 = 1;
		res.add(1);
		while (num2 < target) {
			int cur = num1 + num2;
			res.add(cur);
			num1 = num2;
			num2 = cur;
		}
		return res;
	}
	public static int findFiboNum(List<Integer> list, int upperLimit) {
		int left = 0, right = list.size() - 1;
		int minDist = Integer.MAX_VALUE;
		int res = 1;
		while (left <= right) {
			int mid = (left + right) / 2;
			if (list.get(mid) == upperLimit) {
				return upperLimit;
			} else if (list.get(mid) > upperLimit) {
				right = mid - 1;
			} else {
				int curDist = upperLimit - list.get(mid);
				if (curDist < minDist) {
					minDist = curDist;
					res = list.get(mid);
				}
				left = mid + 1;
			}
		}
		return res;
	}
	// driver
	public static void main(String[] args) {
		int target = 22;
		System.out.println(getFibonacciToTarget(target));
	}
}
