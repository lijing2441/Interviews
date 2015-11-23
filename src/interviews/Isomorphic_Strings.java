package interviews;
import java.util.HashMap;
public class Isomorphic_Strings {
	/**
	 * Given two strings s and t, determine if they are isomorphic.
	 * 
	 * Two strings are isomorphic if the characters in s can be replaced to get
	 * t.
	 * 
	 * All occurrences of a character must be replaced with another character
	 * while preserving the order of characters. No two characters may map to
	 * the same character but a character may map to itself.
	 * 
	 * For example, Given "egg", "add", return true.
	 * 
	 * Given "foo", "bar", return false.
	 * 
	 * Given "paper", "title", return true.
	 */
	public boolean isIsomorphic(String s, String t) {
        if(s.length() < 2) return true;
        HashMap<Character, Character> map = new HashMap<Character, Character>();
        char[] arr1 = s.toCharArray();
        char[] arr2 = t.toCharArray();
        
        for(int i = 0; i < s.length(); i++) {
            char cs = arr1[i];
            
            if(!map.containsKey(cs)) {
                if(map.containsValue(arr2[i])) {
                    return false;
                }
                map.put(cs, arr2[i]);
            } else if (map.get(cs) != arr2[i]) {
                 return false;
            }
        }
        return true;
    }
}
