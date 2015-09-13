 package interviews;

import java.util.ArrayList;
import java.util.List;

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
		String x = "   Hello  Dear";
		//System.out.println(removeSpaces(x));
		System.out.println(reverseWords(x));
	}
}
