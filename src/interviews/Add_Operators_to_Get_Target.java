package interviews;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Add_Operators_to_Get_Target {
	/**
	 * Given a string that contains only digits 0-9 and a target value, 
	 * return all possibilities to add binary operators (not unary) +, -, or * 
	 * between the digits so they evaluate to the target value.
	 * 
	 * "123", 6 -> ["1+2+3", "1*2*3"] 
	 * "232", 8 -> ["2*3+2", "2+3*2"]
	 * "105", 5 -> ["1*0+5","10-5"]
	 * "00", 0  -> ["0+0", "0-0", "0*0"]
	 * "3456237490", 9191 -> []
	 */
	// 每个分隔开的数字第一位不能为0
	// 要记住上一次multiply的数字，以免出现的还是乘法，需要进行优先级操作
	public List<String> addOperators(String num, int target) {
        List<String> res = new ArrayList<String>();
        if (num == null || num.length() == 0) return res;
        helper(res, "", num, target, 0, 0, 0);
        return res;
    }
    public void helper(List<String> res, String path, String num, int target, int pos, long eval, long multed) {
        if (pos == num.length()) {
        	// 如果是要最大值，可以在这里存上最大值，如果比最大值还大，就更新
            if (target == eval) {
                res.add(path);
            }
            return;
        }
        for (int i = pos; i < num.length(); i++) {
            if (i != pos && num.charAt(pos) == '0') break; // 除了‘0’不可以形成其他‘07’, etc
            long cur = Long.parseLong(num.substring(pos, i + 1));
            if (pos == 0) {
                helper(res, path + cur, num, target, i + 1, cur, cur);
            } else {
                helper(res, path + "+" + cur, num, target, i + 1, eval + cur, cur);
                helper(res, path + "-" + cur, num, target, i + 1, eval - cur, -cur);
                helper(res, path + "*" + cur, num, target, i + 1, eval - multed + multed * cur, multed * cur);
            }
        }
    }
	/**
	 * 给一堆数，加 加减乘除符号 使之成为目标数, 四种符号
	 * 
	 * Examples: "1231231234",11353 -> "12*3+1+23*123*4" 
	 * 			 "3456237490",1185  -> "3*4*56+2+3*7+490" 
	 * 			 "3456237490",9191  -> "no solution"
	 */
	/** with all the operators, other methods */
	public static String calculateToSum(String str, int tar) {
		if (str == null || str.length() == 0) {
			return "";
		}
		StringBuilder ans = new StringBuilder(str);
		return dfsHelper(ans, 1, tar);
	}

	private static String dfsHelper(StringBuilder ans, int pos, int target) {
		if (pos >= ans.length()) {
			if (calculate(ans.toString()) == target) {
				return ans.toString();
			}
			return null;
		}
		char[] operator = { '+', '-', '*', '/' };

		for (int i = 0; pos + i < ans.length(); i++) {
			for (int j = 0; j < 4; j++) {
				ans.insert(pos + i, operator[j]);
				String temp = dfsHelper(ans, pos + i + 2, target);
				if (temp != null) {
					return temp;
				}
				ans.deleteCharAt(pos + i);
			}
		}
		return null;
	}
	// calculate the value of the expression shown as string
	private static long calculate(String e) {
		Stack<Character> operator = new Stack<>();
		Stack<Long> numbers = new Stack<>();

		int[] pri = new int[128];
		// priority of the operators
		pri['+'] = 1;
		pri['-'] = 1;
		pri['*'] = 2;
		pri['/'] = 2;

		for (int i = 0; i < e.length(); i++) {
			char c = e.charAt(i);
			// numbers
			if (Character.isDigit(c)) {
				int right = i + 1;
				while (right < e.length() && Character.isDigit(e.charAt(right))) {
					right++;
				}
				numbers.push(Long.parseLong(e.substring(i, right)));
				i = right - 1;
			} else { // 符号的话，如果优先级别比栈内低，先算栈内的
				if (!operator.isEmpty() && ((pri[c] < pri[operator.peek()]) || (pri[c] == pri[operator.peek()] && pri[c] == 2))) {
					numbers.push(op(numbers.pop(), numbers.pop(), operator.pop()));
				}
				operator.push(c);
			}
		}

		while (!operator.isEmpty()) {
			numbers.push(op(numbers.pop(), numbers.pop(), operator.pop()));
		}
		return numbers.pop();
	}
	// 运算
	private static long op(long a, long b, Character operator) {
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
	
	// driver 
	public static void main(String[] args) {
		String str = "1231231234";
		int target = 11353;
		System.out.println(calculateToSum(str, target));
	}
}
