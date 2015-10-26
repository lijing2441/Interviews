package interviews;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Airbnb_mini_parser {
	public static IntOrArray miniParser(String s) {
		if (s.charAt(0) != '[') return new IntOrArray(Integer.parseInt(s));
		Stack<IntOrArray> stack = new Stack<>();
		List<IntOrArray> cur = new ArrayList<IntOrArray>();
		int pos = 0;
		while (pos < s.length()) {
			
		}
		return null;
	}
	
	public static void main(String[] args) {
		System.out.println(miniParser("[123,456,[788,799,833],[[]],10,[]]"));
	}
}
class IntOrArray {
	int val;
	IntOrArray[] arr;
	boolean isInt;
	public IntOrArray(int val) {
		this.val = val;
		this.isInt = true;
	}
	public IntOrArray(IntOrArray[] arr) {
		this.arr = arr;
		this.isInt = false;
	}
}