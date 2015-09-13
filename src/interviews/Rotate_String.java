package interviews;

public class Rotate_String {
	/**
	 * Given "abcdefg".
	 * 
	 * offset=0 => "abcdefg" 
	 * offset=1 => "gabcdef" 
	 * offset=2 => "fgabcde"
	 * offset=3 => "efgabcd"
	 */
	public void rotateString(char[] str, int offset) {
        // write your code here
        int len = str.length;
        if (len <= 1) return;
        offset = offset % len;
        for (int i = 0; i < offset; i++) {
            rotate(str);
        }
    }
    public void rotate(char[] str) {
        char last = str[str.length - 1];
        for (int i = str.length - 1; i > 0; i--) {
            str[i] = str[i - 1];
        }
        str[0] = last;
    }
}
