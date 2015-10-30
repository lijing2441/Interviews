package interviews;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.Set;

public class Palindrome_Permutation {
	/**
	 * Given a string, determine if a permutation of the string could form a
	 * palindrome.
	 * 
	 * For example, "code" -> False, "aab" -> True, "carerac" -> True.
	 */
	public boolean canPermutePalindrome(String s) {
		int len = s.length();
		boolean isOdd = false;
		if (len % 2 == 1)
			isOdd = true;
		Map<Character, Integer> map = new HashMap<Character, Integer>();
		char[] arr = s.toCharArray();
		for (char c : arr) {
			int num = 1;
			if (map.containsKey(c))
				num += map.get(c);
			map.put(c, num);
		}
		for (int count : map.values()) {
			if (count % 2 == 1) {
				if (isOdd)
					isOdd = false;
				else
					return false;
			}
		}
		return true;
	}
	/**
	 * Given a string s, return all the palindromic permutations (without
	 * duplicates) of it. Return an empty list if no palindromic permutation
	 * could be form.
	 * 
	 * For example:
	 * 
	 * Given s = "aabb", return ["abba", "baab"].
	 * 
	 * Given s = "abc", return [].
	 */
	public List<String> generatePalindromes(String s) {
        List<String> res = new ArrayList<String>();
        char[] arr = s.toCharArray();
        int len = arr.length;
        boolean isOdd = false;
        if(len % 2 == 1) {
            isOdd = true;
        }
        if(len == 0) return res;
        if(len == 1) {
            res.add(s);
            return res;
        }
        Map<Character, Integer> map = new HashMap<Character, Integer>();
        for(char c: arr) {
            int num = 1;
            if(map.containsKey(c)) num += map.get(c);
            map.put(c, num);
        }
        //check whether forming palindrome is possible first
        StringBuilder sb = new StringBuilder();
        boolean isMiddle = isOdd;
        Character middleLetter = null;
        for(Map.Entry<Character, Integer> entry: map.entrySet()) {
            char curChar = entry.getKey();
            int count = entry.getValue();
            if(count % 2 == 1) {
                if(isMiddle) {
                    middleLetter = curChar;
                    isMiddle = false;
                } else return res; // return the empty list
            }
            for(int i = 0; i < (count / 2); i++) {
                sb.append(curChar);
            }
        }
        List<String> perms = getPermutations(sb.toString());
        for(String str : perms) {
            StringBuilder sb2 = new StringBuilder(str).reverse();
            String curStr = str + (isOdd? middleLetter : "") + sb2.toString();
            res.add(curStr);
        }
        //if(res.size() == 0 && isOdd) res.add()
        return res;
    }
    // get the unique permutations
    public List<String> getPermutations(String s) {
        List<String> res = new ArrayList<String>();
        char[] arr = s.toCharArray();
        int len = arr.length;
        if(len == 0) return res;
        Set<String> set = new HashSet<String>();
        res.add("");
        for(int i = 0; i < len; i++) {
            List<String> tmp = new ArrayList<String>();
            for(String l : res) {
                for(int j = 0; j <= l.length(); j++) {
                    StringBuilder newL = new StringBuilder(l);
                    newL.insert(j, arr[i]);
                    if(set.add(newL.toString())) tmp.add(newL.toString());
                }
            }
            res = tmp;
        }
        return res;
    }
}
