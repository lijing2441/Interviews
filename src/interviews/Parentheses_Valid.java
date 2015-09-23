package interviews;

import java.util.Stack;

public class Parentheses_Valid {
	/**
	 * Given a string containing just the characters '(', ')', '{', '}', '[' 
	 * and ']', determine if the input string is valid.
	 * 
	 * The brackets must close in the correct order, "()" and "()[]{}" are all
	 * valid but "(]" and "([)]" are not.
	 * 
	 * O(n), O(n)
	 */
	public boolean isValid(String s) {
		int n = s.length();
		if (n == 0)
			return true;
		if (n % 2 == 1)
			return false;
		Stack<Character> stack = new Stack<Character>();
		for (int i = 0; i < n; i++) {
			if (s.charAt(i) == '(' || s.charAt(i) == '[' || s.charAt(i) == '{')
				stack.push(s.charAt(i));
			else {
				if (stack.isEmpty()) {
					return false;
				} else {
					if ((s.charAt(i) == ']' && stack.peek() != '[') || (s.charAt(i) == ')' && stack.peek() != '(') || (s.charAt(i) == '}' && stack.peek() != '{'))
						return false;
					else stack.pop();
				}
			}
		}
		if (stack.isEmpty()) return true;
		return false;
	}
	/**
	 * second version: 找最长合法括号 
	 * Given a string containing just the characters '(' and ')', find the length of the longest 
	 * valid (well-formed) parentheses substring.
	 * 
	 * "(()", the longest valid parentheses substring is "()", which has length = 2.
	 * 
	 * ")()())", the longest valid parentheses substring is "()()", which has length = 4.
	 *
	 * @logic: 
	 * 用栈存括号在string中所在index，如果最后栈为空，说明整个字符均为legal；
	 * 否则，我们用栈内index来决定是否有最长子串，每两个index之间为合法子串，不包含栈内index
	 */
	public int longestValidParentheses(String s) {
        if(s == null || s.length() < 2) return 0;
        int len = s.length();
        char[] ch = s.toCharArray();
        Stack<Integer> stack = new Stack<Integer>();
        for(int i = 0; i < len; i++){
            if(ch[i] == '(') stack.push(i);
            else{
                if(!stack.isEmpty() && ch[stack.peek()] == '(') stack.pop();
                else stack.push(i);
            }
        }
        if(stack.isEmpty()) return len;
        else{
            int a = len, b = 0, max = 0;
            while(!stack.isEmpty()){
                b = stack.pop();
                // we need to get the index length between a and b, and we need to exclusive a and b.
                // which should be a - b - 1
                max = Math.max(max, a - b - 1);
                a = b;
            }
            max = Math.max(max, a);
            return max;
        }
    }
	
	// DP solution
	public int longestValidDP(String s) {
		/* max[i] = j means subsequence index i-j is longest valid Parentheses */
		int[] max = new int[s.length() + 1];
		max[s.length()] = s.length();
		int sum = 0;
		for (int i = s.length() - 1; i >= 0; i--) {
			if (max[i + 1] != i + 1) {
				if (max[i + 1] + 1 < s.length() && s.charAt(i) == '(' && s.charAt(max[i + 1] + 1) == ')') {
					max[i] = (max[i + 1] + 2 < s.length() + 1 && max[max[i + 1] + 2] != max[i + 1] + 2) ? max[max[i + 1] + 2] : max[i + 1] + 1;
				} else {
					max[i] = i;
				}
			} else if (i + 1 < s.length() && s.charAt(i) == '(' && s.charAt(i + 1) == ')') {
				max[i] = (i + 2 < s.length() + 1 && max[i + 2] != i + 2) ? max[i + 2] : i + 1;
			} else {
				max[i] = i;
			}
				
			sum = Math.max(sum, max[i] - i + 1);
		}
		return (sum == 1) ? 0 : sum;
	}
}
