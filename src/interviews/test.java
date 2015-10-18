package interviews;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Stack;

public class test {
	public static String alphabet = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
	public static int BASE = 62;
	public static String encode(int dbID) {
		List<Integer> list = new ArrayList<Integer>();
		while (dbID > 0) {
			int remain = dbID % BASE;
			list.add(remain);
			dbID /= BASE;
		}
		StringBuilder sb = new StringBuilder();
		for (int i = list.size() - 1; i >= 0; i--) {
			sb.append(alphabet.charAt(list.get(i)));
		}
		return sb.toString();
	}
	public static int decode(String url) {
		//List<Integer> list = new ArrayList<Integer>();
		int j = url.length() - 1;
		int res = 0;
		for (int i = 0; i < url.length(); i++) {
			int index = alphabet.indexOf(url.charAt(i));
			res += (Math.pow(BASE, j) * index);
			j--;
		}
		return res;
	}
	
	public static int evaluateExpression(String[] expression) {
        if (expression == null || expression.length == 0) return 0;
        int len = expression.length;
        Stack<Integer> numStack = new Stack<Integer>();
        Stack<String> opStack = new Stack<String>();
        int pos = 0;
        Set<String> ops = new HashSet<String>();
        ops.add("+");
        ops.add("-");
        ops.add("*");
        ops.add("/");
        ops.add("(");
        ops.add(")");
        while (pos < len) {
            if (ops.contains(expression[pos])) {
                if (expression[pos].equals("(")) {
                    opStack.push(expression[pos]);
                } else if (expression[pos].equals(")")) {
                    while (!opStack.peek().equals("(")) {
                        numStack.push(calculate(opStack.pop(), numStack.pop(), numStack.pop()));
                    }
                    opStack.pop();
                } else {
                    while (!opStack.isEmpty() && isHighPriority(opStack.peek(), expression[pos])) {
                        numStack.push(calculate(opStack.pop(), numStack.pop(), numStack.pop()));
                    }
                    opStack.push(expression[pos]);
                }
            } else {
                numStack.push(Integer.parseInt(expression[pos]));
            }
            pos++;
        }
        while (!opStack.isEmpty()) {
            numStack.push(calculate(opStack.pop(), numStack.pop(), numStack.pop()));
        }
        if (numStack.isEmpty()) return 0;
        else return numStack.pop();
    }
    public static int calculate(String op, int num2, int num1) {
        if (op.equals("+")) return num1 + num2;
        else if (op.equals("-")) return num1 - num2;
        else if (op.equals("*")) return num1 * num2;
        else return num1 / num2;
    }
    public static boolean isHighPriority(String op1, String op2) {
        if (op1.equals("*") || op1.equals("/")) {
            return true;
        } else {
            if (op2.equals("+") || op2.equals("-")) return true;
            else return false;
        }
    }
    
	public static void main(String[] args) {
//		int dbID = 2423557;
//		String res = encode(dbID);
//		System.out.println(res);
//		int num = decode(res);
//		System.out.println(num);
		String[] ex = {"2","*","6","-","(","23","+","7",")","/","(","1","+","2",")"};
		System.out.println(evaluateExpression(ex));
	}
}