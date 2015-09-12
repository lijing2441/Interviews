package interviews;

public class Space_Replacement {
	/**
	 * Given "Mr John Smith", length = 13.
	 * 
	 * The string after replacement should be "Mr%20John%20Smith".
	 */
	public int replaceBlank(char[] string, int length) {
        // Write your code here
        int spaces = 0;
        for(int i = 0; i < length; i++) {
            if(string[i] == ' ') {
                spaces++;
            }
        }
        int newLen = length + (spaces * 2);
        int index = length - 1;
        for(int i = newLen - 1; i >= 0; i--) {
            if(string[index] == ' ') {
                string[i--] = '0';
                string[i--] = '2';
                string[i] = '%';
            } else {
                string[i] = string[index];
            }
            index--;
        }
        return newLen;
    }
}
