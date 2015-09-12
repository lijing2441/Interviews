package interviews;

public class Fibonacci {
	// O(n), O(n)

	public static int Fib(int n) {
		if (n < 2) {
			return n;
		}
		return Fib(n - 1) + Fib(n - 2);
	}

	public static int Fib2(int n) {
		int[] f = new int[n + 1];
		f[0] = 0;
		f[1] = 1;
		for (int i = 2; i <= n; i++) {
			f[i] = f[i - 1] + f[i - 2];
		}
		return f[n];
	}

	public static void main(String[] args) {
		int n = Fib2(6);
		System.out.print(n);
	}
}
