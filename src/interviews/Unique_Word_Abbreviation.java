package interviews;

import java.util.HashSet;
import java.util.Map;
import java.util.HashMap;
import java.util.Set;

public class Unique_Word_Abbreviation {
	/**
	 * An abbreviation of a word follows the form <first letter><number><last letter>. 
	 * Below are some examples of word abbreviations:

a) it                      --> it    (no abbreviation)

     1
b) d|o|g                   --> d1g

              1    1  1
     1---5----0----5--8
c) i|nternationalizatio|n  --> i18n

              1
     1---5----0
d) l|ocalizatio|n          --> l10n
Assume you have a dictionary and given a word, find whether its abbreviation is unique in the dictionary. A word's abbreviation is unique if no other word from the dictionary has the same abbreviation.

Example: 
Given dictionary = [ "deer", "door", "cake", "card" ]

isUnique("dear") -> false
isUnique("cart") -> true
isUnique("cane") -> false
isUnique("make") -> true
	 */
	public class ValidWordAbbr {
	    public Map<String, Set<String>> map = new HashMap<String, Set<String>>();
	    //public Set<String> set = new HashSet<String>();
	    public ValidWordAbbr(String[] dictionary) {
	        for (String s : dictionary) {
	            String key = encode(s);
	            Set<String> set = null;
	            if (map.containsKey(key)) {
	                set = map.get(key);
	            } else {
	                set = new HashSet<String>();
	            }
	            set.add(s);
	            map.put(key, set);
	        }
	    }

	    public boolean isUnique(String word) {
	        String key = encode(word);
	        if (map.containsKey(key)) {
	            if (map.get(key).contains(word)) {
	                return map.get(key).size() == 1;
	            } else {
	                return false;
	            }
	        } else {
	            return true;
	        }
	    }
	    
	    public String encode(String str) {
	        if (str == null || str.length() <= 2) return str;
	        int len = str.length();
	        return str.charAt(0) + String.valueOf(len - 2) + str.charAt(len - 1);
	    }
	}
}
