package interviews;

import java.util.ArrayList;
import java.util.List;

public class Restore_IP_Address {
	/**
	 * Given a string containing only digits, restore it by returning all
	 * possible valid IP address combinations.
	 * 
	 * For example: Given "25525511135",
	 * 
	 * return ["255.255.11.135", "255.255.111.35"]. (Order does not matter)
	 * 
	 * @analysis backtracking
	 * This runtime is definitely constant time O(1) since the number of steps 
	 * in the algorithm is independent of the input size for large N. 
	 */
	public List<String> restoreIpAddresses(String s) {
        List<String> res = new ArrayList<String>();
        int len = s.length();
        for(int i = 1; i < 4 && i < len - 2; i++){
            for(int j = i + 1; j - i < 4 && j < len - 1; j++){
                for(int k = j + 1; k - j < 4 && k < len; k++){
                    String s1 = s.substring(0, i);
                    String s2 = s.substring(i, j);
                    String s3 = s.substring(j, k);
                    String s4 = s.substring(k, len);
                    if(isValid(s1) && isValid(s2) && isValid(s3) && isValid(s4)){
                        res.add(s1 + "." + s2 + "." + s3 + "." + s4);
                    }
                }
            }
        }
        return res;
    }
    public boolean isValid(String s){
        if(s.length() == 0 || s.length() >= 4 || (s.charAt(0) == '0' && s.length() > 1) || Integer.parseInt(s) > 255){
            return false;
        }
        return true;
    }
    
    // use backtracking
    public List<String> restoreIpAddresses2(String s) {
        if (s == null || s.length() < 4 || s.length() > 12) return new ArrayList<String>();
        List<String> res = new ArrayList<String>();
        getValidIP(s, 0, new ArrayList<String>(), res);
        return res;
    }
    
    public void getValidIP(String s, int step, List<String> cur, List<String> res) {
        if (step == 3) {
            if (isValid(cur.get(0)) && isValid(cur.get(1)) && isValid(cur.get(2)) && isValid(s)) {
                res.add(cur.get(0) + "." + cur.get(1) + "." + cur.get(2) + "." + s);
            }
            return;
        }
        for (int i = 1; i < 4 && i <= s.length(); i++) {
            String next = s.substring(0, i);
            cur.add(next);
            getValidIP(s.substring(i), step + 1, cur, res);
            cur.remove(cur.size() - 1);
        }
    }
}
