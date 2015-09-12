package interviews;

import java.util.HashMap;

public class Minimal_Window_Substring {
	/**
	 * Given a string S and a string T, find the minimum window in S which will
	 * contain all the characters in T in complexity O(n).
	 * 
	 * For example, S = "ADOBECODEBANC" T = "ABC" Minimum window is "BANC".
	 * 
	 * Note: If there is no such window in S that covers all characters in T,
	 * return the empty string "".
	 * 
	 * If there are multiple such windows, you are guaranteed that there will
	 * always be only one unique minimum window in S.
	 * 
	 * @analysis
	 * 建立一个字典，然后维护一个窗口。在这道题目中，可以跳过没在字典里面的字符
	 * （也就是这个串不需要包含且仅仅包含字典里面的字符，有一些不在字典的仍然可以满足要求），
	 * 所以遇到没在字典里面的字符可以继续移动窗口右端，而移动窗口左端的条件是当找到满足条件的串之后，
	 * 一直移动窗口左端直到有字典里的字符不再在窗口里。在实现中就是维护一个HashMap，
	 * 一开始key包含字典中所有字符，value就是该字符的数量，然后遇到字典中字符时就将对应字符的数量减一。
	 * 
	 * 时间复杂度是O(n),其中n是字符串的长度，因为每个字符再维护窗口的过程中不会被访问多于两次。
	 * 空间复杂度则是O(字典的大小)，也就是代码中T的长度。
	 */
	public String minWindow(String S, String T) {
        if(S == null || T == null || S.length() == 0 || T.length() == 0) return "";
        char[] s = S.toCharArray(), t = T.toCharArray();
        int sLen = s.length, tLen = t.length;
        // make a map for t, the target strings - which should be seen as 
        // a char dictionary for searching
        HashMap<Character, Integer> map = new HashMap<Character, Integer>();
        for(char c: t){
            int num = 1;
            if(map.containsKey(c)){
            	num += map.get(c);
            }
            map.put(c, num);
        }
        // window sliding to find the length
        int start = 0;
        // the pointers that mark the minStart and length
        // since we need to return the string
        int minStart = start;
        int minLen = Integer.MAX_VALUE;
        int count = 0;
        // we move the end pointer to find whether we have the chars in T
        for(int end = start; end < sLen; end++){
            if(map.containsKey(s[end])){
                map.put(s[end], map.get(s[end]) - 1);
                // if the map still have things left
                // we can add one count as a match, 
                // if not, this is just a duplicate
                if(map.get(s[end]) >= 0) count++;
            }
            // move the start pointer until we find a char is not in window
            while(count == tLen){
            	// check whether we need to update
                if(end - start + 1 < minLen){
                    minLen = end - start + 1;
                    minStart = start;
                }
                // we move the start one more step to give a new window
                if(map.containsKey(s[start])){
                    map.put(s[start], map.get(s[start]) + 1);
                    if(map.get(s[start]) > 0) count--;
                }
                // move the start pointer until we find a char is not in window
                start++;
            }
        }
        // the minLen is still MAX, we did not find a window in S
        if(minLen > sLen) return "";
        else return S.substring(minStart, minStart + minLen);
    }
}
