package interviews;

import java.util.Stack;

public class Facebook_移除括号余下有效括号对 {
	/**
	 * 这个是校验括号是否匹配，这个很简单，用栈去模拟。发现是左括号就入栈，发现是右括号，而且和左括号匹配就出栈。
	 * 同时，总长度+2。扫描一遍结束就是答案。
	 */
	public static int getMaxValidParenthesis(String input) {
		if (input == null || input.length() < 2) return 0;
		int len = input.length();
		char[] arr = input.toCharArray();
		int max = 0;
		Stack<Character> stack = new Stack<Character>();
		for (int i = 0; i < len; i++) {
			if (arr[i] == '(') stack.push(arr[i]);
			else if (arr[i] == ')' && !stack.isEmpty()) {
				stack.pop();
				max += 2;
			}
		}
		return max;
	}
	
	public static void main(String[] args) {
		String test1 = "(())()";
		String test2 = "(()";
		System.out.println(getMaxValidParenthesis(test1));
		System.out.println(getMaxValidParenthesis(test2));
		
	}
}
