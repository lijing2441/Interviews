package interviews;

import java.util.HashSet;
import java.util.Set;
import java.util.Stack;

public class test {
	public static String alphabet = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
	public static int BASE = 62;
	public static String encode(int dbID) {
		StringBuilder sb = new StringBuilder();
		while (dbID > 0) {
			int index = dbID % BASE;
			sb.insert(0, alphabet.charAt(index));
			dbID /= BASE;
		}
		return sb.toString();
	}
	public static int decode(String url) {
		//int digit = url.length() - 1;
		int res = 0;
		for (int i = 0; i < url.length(); i++) {
			res = res * 62 + (alphabet.indexOf(url.charAt(i)));
		}
		return res;
	}
	
	public static int evaluateExpression(String[] expression) {
        if (expression == null || expression.length == 0) return 0;
        int len = expression.length;
        Stack<Integer> nums = new Stack<Integer>();
        Stack<String> ops = new Stack<String>();
        Set<String> opSet = new HashSet<String>();
        opSet.add("+");opSet.add("-");opSet.add("*");opSet.add("/");opSet.add("(");opSet.add(")");
        for (int i = 0; i < len; i++) {
        	if (opSet.contains(expression[i])) {
        		if (expression[i].equals("(")) {
        			ops.push("(");
        		} else if (expression[i].equals(")")) {
        			while (!ops.peek().equals("(")) {
        				nums.push(evaluate(ops.pop(), nums.pop(), nums.pop()));
        			}
        			ops.pop();
        		} else {
        			while (!ops.isEmpty() && isHigherPriority(ops.peek(), expression[i])) {
        				nums.push(evaluate(ops.pop(), nums.pop(), nums.pop()));
        			}
        			ops.push(expression[i]);
        		}
        	} else {
        		nums.push(Integer.parseInt(expression[i]));
        	}
        }
        while (!ops.isEmpty()) {
        	nums.push(evaluate(ops.pop(), nums.pop(), nums.pop()));
        }
        if (nums.isEmpty()) return 0;
        else return nums.pop();
    }
	public static int evaluate(String op, int num2, int num1) {
		if (op.equals("+")) return num1 + num2;
		else if (op.equals("-")) return num1 - num2;
		else if (op.equals("*")) return num1 * num2;
		else return num1 / num2;
	}
    public static boolean isHigherPriority(String op1, String op2) {
    	if (op1.equals("*") || op1.equals("/")) return true;
    	if (op1.equals("+") || op1.equals("-")) {
    		if (op2.equals("*") || op2.equals("/")) return false;
    		else if (op2.equals("+") || op2.equals("-")) return true;
    	}
    	return false;
    }
    
	public static void main(String[] args) {
		int dbID = 2423557;
		String res = encode(dbID);
		System.out.println(res);
		int num = decode(res);
		System.out.println(num);
//		String[] ex = {"2","*","6","-","(","23","+","7",")","/","(","1","+","2",")"};
//		System.out.println(evaluateExpression(ex));
	}
}