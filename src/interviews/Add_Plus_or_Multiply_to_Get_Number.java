package interviews;

import java.util.Stack;

public class Add_Plus_or_Multiply_to_Get_Number {
	/**
	 * 给一堆数，加 加减乘除符号 使之成为目标数
	 * 
	 * Examples: "1231231234",11353 -> "12*3+1+23*123*4" "3456237490",1185 ->
	 * "3*4*56+2+3*7+490" "3456237490",9191 -> "no solution"
	 */
	public static String getTarget(String digits, int target) {

	}

	public String calculateToSum(String str, int tar) {
		if (str == null || str.length() == 0) {
			return "";
		}

		StringBuilder ans = new StringBuilder(str);
		return dfsHelper(ans, 1, tar);
	}

	private String dfsHelper(StringBuilder ans, int pos, int tar) {
		if (pos >= ans.length()) {
			if (calculate(ans.toString()) == tar) {
				return ans.toString();
			}
			return null;
		}
		char[] operator = { '+', '-', '*', '/' };

		for (int i = 0; pos + i < ans.length(); i++) {
			for (int j = 0; j < 4; j++) {
				ans.insert(pos + i, operator[j]);
				String temp = dfsHelper(ans, pos + i + 2, tar);
				if (temp != null) {
					return temp;
				}
				ans.deleteCharAt(pos + i);
			}
		}
		return null;
	}

	private long calculate(String e) {
		Stack<Character> operator = new Stack<>();
		Stack<Long> operands = new Stack<>();

		int[] pri = new int[128];
		pri['+'] = 1;
		pri['-'] = 1;
		pri['*'] = 2;
		pri['/'] = 2;

		for (int i = 0; i < e.length(); i++) {
			char c = e.charAt(i);
			if (Character.isDigit(c)) {
				int right = i + 1;
				while (right < e.length() && Character.isDigit(e.charAt(right))) {
					right++;
				}
				operands.push(Long.parseLong(e.substring(i, right)));
				i = right - 1;
			} else {
				if (!operator.isEmpty()
						&& ((pri[c] < pri[operator.peek()]) || (pri[c] == pri[operator
								.peek()] && pri[c] == 2))) {
					operands.push(op(operands.pop(), operands.pop(),
							operator.pop()));
				}

				operator.push(c);
			}
		}

		while (!operator.isEmpty()) {
			operands.push(op(operands.pop(), operands.pop(), operator.pop()));
		}

		return operands.pop();
	}

	private long op(long a, long b, Character operator) {
		long result = 0L;
		if (operator == '+') {
			result = b + a;
		} else if (operator == '-') {
			result = b - a;
		} else if (operator == '*') {
			result = b * a;
		} else if (operator == '/') {
			if (a == 0) {
				result = Long.MAX_VALUE;
			}
			result = b / a;
		}
		result = result > Integer.MAX_VALUE ? Integer.MAX_VALUE + 1 : result;
		result = result < Integer.MIN_VALUE ? Integer.MIN_VALUE - 1 : result;
		return result;
	}

	public static void main(String[] args) {
		String str = "1231231234";
		int target = 11353;
		System.out.println(getTarget(str, target));
	}

	/**
	 * 给一堆数，加+或者*使结果最大。
	 */

}
