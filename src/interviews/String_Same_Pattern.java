package interviews;

import java.util.HashMap;

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
	
	public boolean isSamePattern(String pattern, String input){
		//corner cases
		if(pattern == null || input == null) return false;
		if(input.length() < pattern.length()) return false;
		//create the map to correspond each letter in pattern to n letters in input
		HashMap<Character, String> map = new HashMap<Character, String>();
		return patternMatchHelper(pattern, 0, input, 0, 1, map);
	}
	public boolean patternMatchHelper(String pattern, int pIdx, String input, int iStart, int iEnd, HashMap<Character, String> map){
		if(iStart >= input.length() || iEnd > input.length()) return false;
		if(pIdx == pattern.length() - 1){
			//put the last string to test
			//if it can fit, we return true, else we proceed with the other options 
			return putInMap(map, pattern.charAt(pIdx), input.substring(iStart, input.length()));
		}
		while(iEnd < input.length()){
			//if we can put 
			if(putInMap(map, pattern.charAt(pIdx), input.substring(iStart, iEnd))){
				if(patternMatchHelper(pattern, pIdx + 1, input, iEnd, iEnd + 1, map)){
					return true;
				}else{
					//remove the current pattern corresponding from the map
					if(map.containsKey(pattern.charAt(pIdx))){
						map.remove(pattern.charAt(pIdx));
					}
				}
			}
			iEnd++;
		}
		return false;
	}
	public boolean putInMap(HashMap<Character, String> map, char key, String value){
		if(map.containsKey(key)){
			// test whether the existing associated key-value pair is valid for the current key-value checked
			return map.get(key).equals(value);
		}else{
			map.put(key, value);
			return true;
		}
	}
}
