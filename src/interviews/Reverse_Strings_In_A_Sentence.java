 package interviews;

public class Reverse_Strings_In_A_Sentence {
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
	public static void main(String[] args){
		String x = "   Hello  Dear";
		System.out.println(removeSpaces(x));
		System.out.println(reverseWords(x));
	}
	
	/**
	 * remove all the unnecessary spaces
	 */
	public static String removeSpaces(String s) {
        if(s == null || s.length() == 0) return "";
        String res = "";
        int start = 0;
        int n = s.length();
        for(int i = 0; i < n; i++){
            if(s.charAt(i) == ' ') start = i + 1; // ignore this whitespace 
            else{
            	if(i > 0 && s.charAt(i - 1) == ' '){
            		res = res + " ";
            	}
                // if normal order, use stringBuilder is enough
                res = res + s.substring(start, i + 1);
                start = i + 1;
            }
        }
        return res;
    }
}
