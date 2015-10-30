package interviews;

import java.util.Stack;

public class Basic_Calculator {
	/** 
	 * Implement a basic calculator to evaluate a simple expression string.
	 * 
	 * The expression string may contain open ( and closing parentheses ), the
	 * plus + or minus sign -, non-negative integers and empty spaces .
	 * 
	 * You may assume that the given expression is always valid.
	 * 
	 * Some examples: "1 + 1" = 2 
	 * 				  " 2-1 + 2 " = 3 
	 * 				  "(1+(4+5+2)-3)+(6+8)" = 23
	 * 
	 * @logic using stack to store the latest plus or minus, using the previous as the sign of the current value
	 * 		  Notice: '(' and ')' are differently dealt with.
	 */
	public int calculate(String s) {
        int pos = 0;
        int sign = 1;
        int res = 0;
        Stack<Character> stack = new Stack<Character>();
        char[] arr = s.toCharArray();
        while(pos < arr.length) {
            if(arr[pos] == ' ') pos++;
            else if(arr[pos] == '(') {
                if(pos > 0) {
                    stack.push(arr[pos - 1]);
                }
                if(!stack.isEmpty() && stack.peek() == '-') {
                    sign = -sign;
                }
                pos++;
            } else if (arr[pos] == ')') {
                if(!stack.isEmpty() && stack.pop() == '-') {
                    sign = -sign;
                }
                pos++;
            } else {
                int i = Character.isDigit(arr[pos]) ? pos : pos + 1;
                int curRes = 0;
                while(i < arr.length && Character.isDigit(arr[i])) {
                    curRes = curRes * 10 + (arr[i] - '0');
                    i++;
                }
                res += curRes * (arr[pos] == '-' ? -1 : 1) * sign;
                pos = i;
            }
        }
        return res;
    }
	
	/**
	 * Basic Calculator II: 
	 * Implement a basic calculator to evaluate a simple expression string.
	 * 
	 * The expression string contains only non-negative integers, +, -, *, / operators and empty spaces . 
	 * The integer division should truncate toward zero.
	 * 
	 * You may assume that the given expression is always valid.
	 * 
	 * Some examples:
	 * 
	 * "3+2*2" = 7
	 * " 3/2 " = 1
	 * " 3+5 / 2 " = 5
	 */
	public int calculateWithMultiplyDivide(String s) {
		if(s == null || s.length() == 0) return 0;
        Stack<Integer> nums = new Stack<Integer>();
        Stack<Character> ops = new Stack<Character>();
        char[] arr = s.toCharArray();
        // mark whether we have number to store / calculate
        boolean isLastNum = false;
        int count = 0;
        for(int i = 0; i <= arr.length; i++) {
            if(i < arr.length && arr[i] == ' ') continue;
            
            if(i < arr.length && Character.isDigit(arr[i])) {
                count = count * 10 + (arr[i] - '0');
                isLastNum = true;
            } else if(isLastNum) {
                nums.push(count);
                count = 0;
                isLastNum = false;
                // Deal with * and / with priority
                if(!ops.isEmpty() && (ops.peek() == '*' || ops.peek() == '/')) {
                    char operator = ops.pop();
                    int num2 = nums.pop();
                    int num1 = nums.pop();
                    if(operator == '*') {
                        nums.push(num1 * num2);
                    } else if(operator == '/') {
                        nums.push(num1 / num2);
                    }
                }
            }
            if(i < arr.length && !Character.isDigit(arr[i])) ops.push(arr[i]);
        }
        
        // the last + and -, so far, we should have n operators and (n + 1) numbers to calculate
        if(!ops.isEmpty()) {
            int res = 0;
            while(!ops.isEmpty()) {
                char c = ops.pop();
                int n = nums.pop();
                if(c == '+') res += n;
                else if(c == '-') res -= n;
            }
            // the first number with no - in front of it, thus must be a +
            nums.push(res + nums.pop());
        }
        // the last number should be result
        return nums.pop();
	}
	
	/**
	 * 用expression evaluation的方法写
	 */
	public int calculateIII(String s) {
		if(s == null || s.length() == 0) return 0;
        Stack<Integer> numStack = new Stack<Integer>();
        Stack<Character> opStack = new Stack<Character>();
        char[] arr = s.toCharArray();
        int len = arr.length;
        int ptr = 0;
        while (ptr < len) {
            if (arr[ptr] == ' ') ptr++;
            else if (arr[ptr] == '+' || arr[ptr] == '-' || arr[ptr] == '*' || arr[ptr] == '/') {
                while (!opStack.isEmpty() && isHigherPriority(opStack.peek(), arr[ptr])) {
                    numStack.push(calculate(opStack.pop(), numStack.pop(), numStack.pop()));
                }
                opStack.push(arr[ptr++]);
            } else {
                int num = 0;
                while (ptr < len && Character.isDigit(arr[ptr])) {
                    num = 10 * num + (int)(arr[ptr++] - '0');
                }
                numStack.push(num);
            }
        }
        while (!opStack.isEmpty()) {
            numStack.push(calculate(opStack.pop(), numStack.pop(), numStack.pop()));
        }
        if (numStack.isEmpty()) return 0;
        else return numStack.pop();
	}
	public int calculate (char op, int num2, int num1) {
	    if (op == '+') return num1 + num2;
	    else if (op == '-') return num1 - num2;
	    else if (op == '*') return num1 * num2;
	    else return num1 / num2;
	}
	public boolean isHigherPriority (char op1, char op2) {
	    if (op1 == '*' || op1 == '/') return true;
	    else {
	        if (op2 == '+' || op2 == '-') return true;
	        else return false;
	    }
	}
}
