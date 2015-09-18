package interviews;

import java.util.Stack;

public class Reverse_Polish_Notation {
	// O(n), O(n)
	public int evalRPN(String[] tokens) {
		Stack<String> numbers = new Stack<String>();
		for (int i = 0; i < tokens.length; i++) {
			if (!tokens[i].equals("+") && !tokens[i].equals("-")
					&& !tokens[i].equals("*") && !tokens[i].equals("/")) {
				numbers.push(tokens[i]);
			} else {
				int right = Integer.parseInt(numbers.pop());
				int left = Integer.parseInt(numbers.pop());
				int res = 0;
				if (tokens[i].equals("+")) {
					res = left + right;
				} else if (tokens[i].equals("-")) {
					res = left - right;
				} else if (tokens[i].equals("*")) {
					res = left * right;
				} else if (tokens[i].equals("/")) {
					res = (int) (left / right);
				}
				String result = String.valueOf(res);
				numbers.push(result);
			}
		}
		int val = Integer.parseInt(numbers.pop());
		return val;
	}
}
