package interviews;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TwoSigma_Fibonacci_Encoding {
	/**
	 * Fibonacci coding， 就是给一个数，求出它的string形式的Fibonacci coding。比如4，它的Fibonacci
	 * coding就是“101”。Google一下Fibonacci coding，wikipedia第一个就是)，就是实现一个函数 string
	 * encode(int n);
	 */
	private static String seq = "";

	static String encodeFibonacci(int n) {
		if (n == 1) {
			return "1";
		}
		// get the fibonacci sequence first
		List<Integer> fibonacci = new ArrayList<Integer>();
		fibonacci.add(0);
		fibonacci.add(1);
		int size = 2;
		while (fibonacci.get(size - 1) <= n) {
			fibonacci.add(fibonacci.get(size - 1) + fibonacci.get(size - 2));
			size++;
		}

		List<Integer> candidates = new ArrayList<Integer>();
		// from most significant digit to the least
		for (int i = size - 2; i > 1; i--) {
			candidates.add(fibonacci.get(i));
		}

		char[] res = new char[candidates.size()];
		Arrays.fill(res, '0');
		findSeq(res, n, 0, candidates);
		return seq;
	}

	static boolean findSeq(char[] res, int target, int ptr, List<Integer> candidates) {
		if (target == 0) {
			seq = new String(res);
			return true;
		}
		if (ptr == candidates.size()) {
			return false;
		}

		for (int i = ptr; i < candidates.size(); i++) {
			int curr = candidates.get(i);
			if (curr <= target) {
				res[i] = '1';
				// recursively add the digits
				if (findSeq(res, target - curr, i + 1, candidates)) {
					return true;
				}
				res[i] = '0';
			}
		}
		return false;
	}
	
	// driver
	public static void main(String[] args) {
		System.out.println(encodeF(100));
	}
	public static String toReturn = "";
    static String encodeF(int n) {
        if (n == 1) return "1";
        // get the fibonacci sequence
        List<Integer> fibonacci = new ArrayList<Integer>();
        fibonacci.add(0);
        fibonacci.add(1);
        int len = 2;
        while (fibonacci.get(len - 1) <= n) {
            int last1 = fibonacci.get(len - 1);
            int last2 = fibonacci.get(len - 2);
            fibonacci.add(last1 + last2);
            len++;
        }
        // reverse to get the most significant digit
        // ignore the two first
        List<Integer> list = new ArrayList<Integer>();
        for (int i = len - 2; i >= 2; i--) {
            list.add(fibonacci.get(i));
        }
        //int[] resArr = new int[];
        char[] resArr = new char[list.size()];
        for (int i = 0; i < resArr.length; i++) {
            resArr[i] = '0';
        }
        encode(n, 0, list, resArr);
        return toReturn;
    }
    static boolean encode(int n, int curPos, List<Integer> list, char[] resArr) {
        if (n == 0) {
            toReturn = new String(resArr);
            return true;
        }
        if (curPos == list.size()) return false;
        for (int i = curPos; i < list.size(); i++) {
            int num = list.get(i);
            if (num > n) continue;
            else {
                resArr[i] = '1';
                if (encode(n - num, i + 1, list, resArr)) {
                    return true;
                }
                resArr[i] = '0';
            }
        }
        return false;
    }
}
