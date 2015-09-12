package interviews;

public class Add_Without_Plus {
	public static int add(int x, int y) {
		if (x == 0 || y == 0)
			return x == 0 ? y : x;
		int carry = x & y; // mark the position where should have carry
		int set = x ^ y; // mark the addition without carry
		return add(set, carry << 1);
	}

	public static void main(String[] args) {
		int a = 67;
		int b = 102;
		System.out.print(add(a, b));
	}
}
