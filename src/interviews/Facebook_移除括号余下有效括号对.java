package interviews;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;
import java.util.Stack;

public class Facebook_移除括号余下有效括号对 {
	/**
	 * 这个是校验括号是否匹配，这个很简单，用栈去模拟。发现是左括号就入栈，发现是右括号，而且和左括号匹配就出栈。
	 * 同时，总长度+2。扫描一遍结束就是答案。
	 */
	public static int getMaxValidParenthesis(String input) {
		if (input == null || input.length() < 2)
			return 0;
		int len = input.length();
		char[] arr = input.toCharArray();
		int max = 0;
		Stack<Character> stack = new Stack<Character>();
		for (int i = 0; i < len; i++) {
			if (arr[i] == '(')
				stack.push(arr[i]);
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
		System.out.println(isBalanced(test1));
		System.out.println(getMaxValidParenthesis(test2));
		System.out.println(isBalanced(test2));

	}

	// 找到一个合法的
	public static String isBalanced(String s) {
		Stack<CharRef> stack = new Stack<CharRef>();
		char[] bf = s.toCharArray();
		for (int i = 0; i < bf.length; i++) {
			if (bf[i] == '(')
				stack.push(new CharRef('(', i));
			else if (bf[i] == ')') {
				if (stack.isEmpty())
					bf[i] = ' ';
				else {
					CharRef ref = stack.pop();
					if (ref.value != '(') {
						bf[i] = ' ';
						bf[ref.index] = ' ';
					}
				}
			}
		}
		while (!stack.isEmpty()) { // 这个很关键
			bf[stack.pop().index] = ' ';
		}
		String result = new String(bf);
		return result.replace(" ", "");
	}

	// 找到所有合法的
	public List<String> removeInvalidParentheses(String s) {
		List<String> res = new ArrayList<String>();
		if (s == null)
			return res;
		Set<String> visited = new HashSet<String>();
		Queue<String> q = new LinkedList<String>();
		q.offer(s);
		visited.add(s);
		boolean found = false;
		while (!q.isEmpty()) {
			String cur = q.poll();
			if (isValid(cur)) {
				res.add(cur);
				found = true;
			}
			if (found)
				continue; // do not need to remove more
			for (int i = 0; i < cur.length(); i++) {
				if (cur.charAt(i) != '(' && cur.charAt(i) != ')')
					continue;
				StringBuilder sb = new StringBuilder(cur);
				sb.deleteCharAt(i);
				String next = sb.toString();
				if (visited.add(next)) {
					//visited.add(next);
					q.offer(next);
				}
			}
		}
		return res;
	}

	public boolean isValid(String s) {
		int count = 0;
		for (int i = 0; i < s.length(); i++) {
			char c = s.charAt(i);
			if (c == '(')
				count++;
			if (c == ')' && count-- == 0)
				return false;
		}

		return count == 0;
	}
}

class CharRef {
	char value;
	int index;

	public CharRef(char value, int index) {
		this.value = value;
		this.index = index;
	}
}
