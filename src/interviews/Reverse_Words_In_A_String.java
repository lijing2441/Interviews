package interviews;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Reverse_Words_In_A_String {
	/**
	 * Given an input string, reverse the string word by word.
	 * 
	 * For example, Given s = "the sky is blue", return "blue is sky the".
	 */
	// O(n) and O(n)
	// from beginning
	public static String reverseWords(String s) {
        if(s == null || s.length() == 0) return "";
        String res = "";
        int start = 0;
        int n = s.length();
        for(int i = 0; i < n; i++){
            if(s.charAt(i) == ' ') start = i + 1; // ignore this whitespace 
            else if(i == n-1 || (i < n-1 && s.charAt(i + 1) == ' ')){
                if(res != ""){
                	res = " " + res; // if normal order, use stringBuilder is enough
                }
                res = s.substring(start, i + 1) + res;
                start = i + 1;
            }
        }
        return res;
    }
	// from end
	public String reverseWords2(String s) {
        int n = s.length();
        int j = n;
        StringBuilder reverse = new StringBuilder();
        for(int i = n-1; i >= 0; i--){
        	// move the end to the whitespace
            if(s.charAt(i) == ' '){
                j = i;
            }else if(i == 0 || s.charAt(i-1) == ' '){
                if(reverse.length() != 0){
                    reverse.append(' ');
                }
                reverse.append(s.substring(i, j));
            }
        }
        return new String(reverse);
    }
	
	// Another way
	public String reverseWordsSB(String s) {
        // write your code
        if(s == null || s.length() == 0) return "";
        int len = s.length();
        List<String> list = new ArrayList<String>();
        int start = 0, end = 0;
        while(start < len && end < len) {
            while(start < len && s.charAt(start) == ' ') {
                start++;
            }
            if(start >= len) break;
            end = start;
            while(end < len && s.charAt(end) != ' ') {
                end++;
            }
            if(start != end) {
                String cur = s.substring(start, end);
                list.add(cur);
                start = end;
            }
        }
        String res = "";
        for(int i = 0; i < list.size(); i++) {
            if(i > 0) res = " " + res;
            res = list.get(i) + res;
        }
        return res;
    }
	
	public static void main(String[] args){
		//String x = "   Hello  Dear";
		String withSpace = "This,,,is.a word";
//		System.out.println(reverseWords(x));
		//System.out.println(reverseWithSpaces(withSpace));
		System.out.println(reverseWithOpStay(withSpace));
	}
	
	/**
	 * 保留符号位置
	 * 
	 * "this,,,is.a word" => "word,,,a.is this"
	 */
	public static String reverseWithOpStay(String s) {
		if (s == null || s.length() < 2) return s;
		int start = 0, end = 0, len = s.length();
		Stack<String> stack = new Stack<String>();
		// 第一遍把所有是word的先push进栈
		while(start < len && end < len) {
			while (start < len && !Character.isLetterOrDigit(s.charAt(start))) start++;
			end = start;
			while (end < len && Character.isLetterOrDigit(s.charAt(end))) {
				end++;
			}
			String str = s.substring(start, end);
			stack.push(str);
			start = end;
		}
		// 开始替换
		String res = "";
		start = 0;
		end = 0;
				
		while (start < len && end < len) {
			// starting operators
			while (end < len && !Character.isLetterOrDigit(s.charAt(end))) end++;
			res += s.substring(start, end);
			start = end;
			// add the one in the stack
			if (!stack.isEmpty()) res += stack.pop();
			// skip the current one
			while (end < len && Character.isLetterOrDigit(s.charAt(end))) end++;
			start = end;
		}
		if (start < end) {
			res += s.substring(start, end);
		}
		return res;
	}
	
	/**
	 * 保留空格数量及位置
	 */
	public static String reverseWithSpaces(String s) {
		if (s == null || s.length() < 2) return s;
		int len = s.length();
		char[] arr = s.toCharArray();
		reverse(arr, 0, len - 1);
		int start = 0, end = 0;
		while (end < len && start < len) {
			// 如果要求连符号也reverse，就用comment内的
			while (start < len && arr[start] == ' '/* !Character.isLetterOrDigit(arr[start])*/) start++;
			end = start;
			while (end < len && Character.isLetterOrDigit(arr[end])) {
				end++;
			}
			if (end - 1 > start) {
				reverse(arr, start, end - 1);
			}
			start = end;
		}
		if (start != end) {
			reverse(arr, start, len - 1);
		}
		return new String(arr);
	}
	
	
	public static void reverse(char[] arr, int start, int end) {
		if (start >= end) return;
		//char[] arr = s.toCharArray();
		while (start < end) {
			char tmp = arr[start];
			arr[start] = arr[end];
			arr[end] = tmp;
			start++;
			end--;
		}
		//return new String(arr);
	}
}
