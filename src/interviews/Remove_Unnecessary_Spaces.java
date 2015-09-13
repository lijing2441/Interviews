package interviews;

public class Remove_Unnecessary_Spaces {
	
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
