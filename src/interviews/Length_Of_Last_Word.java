package interviews;

public class Length_Of_Last_Word {
	/**
	 * Given a string s consists of upper/lower-case alphabets and empty space
	 * characters ' ', return the length of last word in the string.
	 * 
	 * If the last word does not exist, return 0.
	 * 
	 * Note: A word is defined as a character sequence consists of non-space
	 * characters only.
	 * 
	 * For example, Given s = "Hello World", return 5.
	 */
	public int lengthOfLastWord(String s) {
        if(s == null || s.length() == 0) return 0;
        int n = s.length();
        int cur = n - 1;
        int len = 0;
        //ignore the white spaces
        while(cur >= 0 && s.charAt(cur) == ' '){
            cur--;
        }
        while(cur >= 0 && s.charAt(cur) != ' '){
            len++;
            cur--;
        }
        return len;
    }
}
