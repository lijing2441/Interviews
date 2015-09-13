package interviews;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.HashMap;
import java.util.Map;

public class Group_Shifted_Strings {
	/**
	 * Given a string, we can "shift" each of its letter to its successive
	 * letter, for example: "abc" -> "bcd". We can keep "shifting" which forms
	 * the sequence:
	 * 
	 * "abc" -> "bcd" -> ... -> "xyz" Given a list of strings which contains
	 * only lowercase alphabets, group all strings that belong to the same
	 * shifting sequence.
	 * 
	 * For example, 
	 * 
	 * given: ["abc", "bcd", "acef", "xyz", "az", "ba", "a", "z"],
	 * 
	 * Return:
	 * 
	 * [ ["abc","bcd","xyz"], ["az","ba"], ["acef"], ["a","z"] ] 
	 * 
	 * Note: For the return value, each inner list's elements must follow the 
	 * lexicographic order.
	 */
	public List<List<String>> groupStrings(String[] strings) {
        List<List<String>> res = new ArrayList<List<String>>();
        int len = strings.length;
        if(len == 0) return res;
        // <Pattern, List>
        Map<String, List<String>> map = new HashMap<String, List<String>>();
        for(String str: strings) {
            String code = encode(str);
            List<String> l = null;
            if(map.containsKey(code)) {
                l = map.get(code);
            } else {
                l = new ArrayList<String>();
            }
            l.add(str);
            map.put(code, l);
        }
        for(List<String> list : map.values()) {
            Collections.sort(list);
            res.add(list);
        }
        return res;
    }
    public String encode(String s) {
        char[] ch = s.toCharArray();
        int offset = ch[0] - 'a';
        String res = "";
        for(int i = 0; i < ch.length; i++) {
            char c = (char)(ch[i] - offset);
            if(c < 'a') {
                c += 26;
            }
            res += c;
        }
        return res;
    }
}
