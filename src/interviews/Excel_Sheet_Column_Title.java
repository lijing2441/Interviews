package interviews;

public class Excel_Sheet_Column_Title {
/**
 * Given a non-zero positive integer, return its corresponding column title as appear in an Excel sheet.
 * 
 * For example:
 * 
 * 1 -> A
 * 2 -> B
 * 3 -> C
 *  ...
 * 26 -> Z
 * 27 -> AA
 * 28 -> AB 
 */
	public String convertToTitle(int n){
		String res = "";
		while(n > 0){ // current position should be > 0 and then we need to calculate the letter, so minus 1.
			n--;
			res = (char)(n%26 + 'A') + res;
			n/=26;
		}
		return res;
	}
	
	/**
	 * Problem II: 反过来 Excel Sheet Column Number -> Given a String, return the related number
	 */
	public int titleToNumber(String s) {
        if(s == null || s.length() == 0) return -1;
        //int digit = 0;
        int n = s.length();
        int res = 0;
        for(int i = 0; i < n; i++){
            int index = s.charAt(i) - 'A' + 1;
            res = res * 26 + index;
        }
        return res;
    }
}
