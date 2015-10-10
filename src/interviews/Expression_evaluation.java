package interviews;

import java.util.HashSet;
import java.util.Set;
import java.util.Stack;

public class Expression_evaluation {
	/**
	 * For the expression 2*6-(23+7)/(1+2), input is
	 * 
	 * [ "2", "*", "6", "-", "(", "23", "+", "7", ")", "/", "(", "1", "+", "2", ")" ]
	 * 
	 * return 2
	 * 
	 * 
	 * 思路就是两个stack，一个存数字一个存符号。如果遇到数字直接存到数字stack；如果遇到符号，有几种情况：
	 * 
	 * 1.当前符号比上一个符号优先级高，比如* 高于+，那么直接进栈
	 * 
	 * 2.当前符号低于上一个，那么就要把所有已经在stack里面优先于当前符号的全算完，再推进当前符号
	 * 
	 * 3.当前符号是“(”，直接push
	 * 
	 * 4.当前符号是“)”，就要把所有“(”以前的符号全部算完
	 */
	public int evaluateExpression(String[] expression) {
        if (expression == null || expression.length == 0) return 0;
        int len = expression.length;
        Stack<Integer> numStack = new Stack<Integer>();
        Stack<String> opStack = new Stack<String>();
        Set<String> ops = new HashSet<String>();
        ops.add("+");
        ops.add("-");
        ops.add("*");
        ops.add("/");
        ops.add("(");
        ops.add(")");
        int ptr = 0;
        while (ptr < len) {
            if (ops.contains(expression[ptr])) {
               if (expression[ptr].equals("(")) {
                   opStack.push("(");
               } else if (expression[ptr].equals(")")) {
                   // calculate until "("
                   while (!opStack.peek().equals("(")) {
                       numStack.push(calculate(opStack.pop(), numStack.pop(), numStack.pop()));
                   }
                   // pop out the "("
                   opStack.pop();
               } else {
                   // calculate if the previous op's priority is higher or equal to the current op
                   while (!opStack.isEmpty() && isPriority(expression[ptr], opStack.peek())) {
                       numStack.push(calculate(opStack.pop(), numStack.pop(), numStack.pop()));
                   }
                   opStack.push(expression[ptr]);
               }
            } else {
                // numbers直接入栈
                numStack.push(Integer.parseInt(expression[ptr]));
            }
            ptr++;
        }
        while (!opStack.isEmpty()) {
            numStack.push(calculate(opStack.pop(), numStack.pop(), numStack.pop()));
        }
        if (numStack.isEmpty()) return 0; // in case null or no number
        else return numStack.pop();
    }
    public int calculate(String op, int num2, int num1) {
        if (op.equals("+")) {
            return num1 + num2;
        } else if (op.equals("-")) {
            return num1 - num2;
        } else if (op.equals("*")) {
            return num1 * num2;
        } else {
            return num1 / num2;
        }
    }
    // check whether the prev op has higher priority than the cur op
    public boolean isPriority(String cur, String prev) {
        if (prev.equals("*") || prev.equals("/")) return true;
        if (prev.equals("+") || prev.equals("-")) {
            if (cur.equals("*") || cur.equals("/")) return false;
            else return true;
        }
        return false;
    }
}
