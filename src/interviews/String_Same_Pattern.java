package interviews;

import java.util.HashMap;
import java.util.Map;

public class String_Same_Pattern {
	/**
	 * Given a pattern and a string input, find if the string follows the same pattern.
	 * 
	 * Examples:
	 * (1) pattern: "abba", input "redbluebluered" should return true;
	 * (2) pattern: "aaaa", input "asdasdasdasd" should return true;
	 * (3) pattern: "aabb", input "xyzabcxzyabc" should return false;
	 * 
	 * @logic Recursion and backtracking.
	 * 			(1) Match the first letter in the pattern with the first n letters of the string. 
	 * 		 	    (use a HashMap to record them). 
	 * 			(2) Take the remaining pattern and the remainder of string and recurse, 
	 * 			    except you must make sure to pass in the hash map as well. 
	 *        		a) If the next character in the pattern is not in the hash map, then do the same. 
	 *        		b) If it is, then check to see if the n length key exists in the string.
	 *        
	 *        This solution will have an O(n^m) time, where n is the length of input 
	 *        and m is the number of distinct characters in the pattern.
	 */
	
	public boolean wordPatternMatch(String pattern, String str) {
        if (pattern == null && str == null) return true;
        if (pattern == null || str == null) return true;
        int pLen = pattern.length(), sLen = str.length();
        if (sLen < pLen) return false;
        Map<Character, String> patternMap = new HashMap<Character, String>();
        //Set<String> checked = new HashSet<String>();
        return matchHelper(pattern, 0, str, 0, patternMap);
    }
    public boolean matchHelper(String p, int pIndex, String s, int sStart, Map<Character, String> patternMap) {
        if (pIndex == p.length() && sStart == s.length()) return true;
        if (pIndex == p.length() || sStart == s.length()) return false;
        // case that the patternMap already has a pattern char for pIndex
        if (patternMap.containsKey(p.charAt(pIndex))) {
            String curMatch = patternMap.get(p.charAt(pIndex));
            // check whether the curMatch is consistent with the previous matches in the patternMap
            if (!s.startsWith(patternMap.get(p.charAt(pIndex)), sStart)) return false;
            else return matchHelper(p, pIndex + 1, s, sStart + curMatch.length(), patternMap); // match the following
        } else {
        	// case that the patternMap does not contains a pattern char for pIndex
            for (int sEnd = sStart + 1; sEnd <= s.length(); sEnd++) {
                String curMatch = s.substring(sStart, sEnd);
                // another pattern char has matched this 
                if (patternMap.containsValue(curMatch)) {
                    break;
                }
                patternMap.put(p.charAt(pIndex), curMatch);
                //set.add(curMatch);
                if (matchHelper(p, pIndex + 1, s, sEnd, patternMap)) return true;
            }
            // we still cannot find any match for this pIndex
            patternMap.remove(p.charAt(pIndex));
        }
        return false;
    }
	
	/**
	 * simple version: pattern = "abba", str = "dog cat cat dog" should return true.
	 */
	public boolean wordPattern(String pattern, String str) {
        if (pattern == null && str == null) return true;
        if (pattern == null || str == null) return false;
        if (pattern.length() > str.length()) return false;
        String[] arr = str.split(" ");
        if (arr.length != pattern.length()) return false;
        
        Map<Character, String> map = new HashMap<Character, String>();
        for (int i = 0; i < arr.length; i++) {
            if (map.containsKey(pattern.charAt(i)) && !map.get(pattern.charAt(i)).equals(arr[i])) {
                return false;
            } else if (!map.containsKey(pattern.charAt(i))) {
                if (map.containsValue(arr[i])) return false;
                else {
                    map.put(pattern.charAt(i), arr[i]);
                }
            }
        }
        return true;
    }
}
