package interviews;

import java.util.ArrayList;
import java.util.Stack;

public class Parenthesis_Generate {
	// recursively
	// depends on the size() of the answer, O(n!)
	public ArrayList<String> generateParenthesis(int n) {
		ArrayList<String> res = new ArrayList<String>();
		helper(0, 0, n, res, "");
		return res;
	}

	public void helper(int l, int r, int n, ArrayList<String> res, String s) {
		if (l == n && r == n) {
			res.add(s);
		} else {
			if (l < n) {
				helper(l + 1, r, n, res, s + "(");
			}
			if (r < l) {
				helper(l, r + 1, n, res, s + ")");
			}
		}
	}

	// Iteratively, using two stacks, one for current string,
	// one for current close parenthesis number
	public ArrayList<String> generateParenthesisIte(int n) {
		ArrayList<String> res = new ArrayList<String>();

		Stack<String> stack = new Stack<String>();
		Stack<Integer> closeNum = new Stack<Integer>();
		stack.push("(");
		closeNum.push(0);

		while (!stack.isEmpty()) {
			String s = stack.pop();
			int num = closeNum.pop();

			if (s.length() == n * 2) {
				res.add(s);
				continue;
			}
			if (s.length() - num < n) {
				stack.push(s + "(");
				closeNum.push(num);
			}
			if (num * 2 < s.length()) {
				stack.push(s + ")");
				closeNum.push(num + 1);
			}
		}
		return res;
	}
}
