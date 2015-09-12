package interviews;

public class Count_And_Say {
	/**
	 * The count-and-say sequence is the sequence of integers beginning as
	 * follows: 1, 11, 21, 1211, 111221, ...
	 * 
	 * 1 is read off as "one 1" or 11. 
	 * 11 is read off as "two 1s" or 21. 
	 * 21 is read off as "one 2, then one 1" or 1211. 
	 * Given an integer n, generate the nth sequence.
	 * 
	 * Note: The sequence of integers will be represented as a string.
	 */
	// running should be exponential to n
	public String countAndSay(int n) {
        if(n == 0) return "";
        // start of the print
        String cur = "1";
        for(int i = 1; i < n; i++){
            String pre = cur;
            StringBuffer sb = new StringBuffer();
            int count = 0;
            for(int j = 0; j < pre.length(); j++){
            	//count the number of duplicate of current character
                count++;
                // if we have a difference here, append the count and character, and then re-count
                if(j < pre.length() - 1 && pre.charAt(j) != pre.charAt(j + 1)){
                    sb.append(count).append(pre.charAt(j));
                    count = 0;
                    // if we encounter the end of the pre string
                }else if(j == pre.length() - 1){
                    sb.append(count).append(pre.charAt(j));
                }
            }
            cur = sb.toString();
        }
        return cur;
    }
	
	/**
	 * recursive method
	 */
	public String countAndSayRecur(int n) {
		if (n <= 1) {
			return String.valueOf(1);
		} else {
			return say(countAndSay(n - 1));
		}
	}

	private String say(String s) {
		int i = 0;
		int count = 1;
		StringBuffer sb = new StringBuffer();
		while (i < s.length()) {
			int j = i + 1;

			while (j < s.length() && s.charAt(j) == s.charAt(i)) {
				count++;
				j++;
			}
			sb.append(count);
			sb.append(s.charAt(i));

			i = j;
			count = 1;
		}
		return sb.toString();
	}
}
