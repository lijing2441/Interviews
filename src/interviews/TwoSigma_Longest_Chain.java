package interviews;

import java.util.HashSet;
import java.util.Map;
import java.util.HashMap;
import java.util.Set;

public class TwoSigma_Longest_Chain {
	/**
	 * longest chain
	 * 
	 * 给一个字符串数组, 以任意一个单词开始，删除一个字母，如果形成的新字符串还在数组的单词堆里面，则是合法的，
	 * chain长度增加1.然后继续往下删，每删一个则长度增加1。
	 * 
	 * 举些例子吧：(a, abcd, bcd, abd, cd, c)： abcd 删除一个字母可以变成 bcd,abd,acd,abc。
	 * 但是只有bcd,acd 可以往下走，所以下面只要考虑这两个。bcd可以变成cd再变成c。
	 * 
	 * 但是abd删除一个单词不能变成数组的一个单词。所以停止。 
	 * 
	 * 返回最长的chain含有单词的数目：abcd--bcd--cd--c,返回4.
	 */
	static int longest_chain(String[] w) {
        Set<String> dict = new HashSet<String>();
        for(String s : w){
            dict.add(s);
        }
        int longest = 0;
        Map<String, Integer> lenMap = new HashMap<String, Integer>();
        for(String s : w){
        	if(lenMap.containsKey(s)){ // must be put before
        		continue;
        	}
            int len = getLength(s, dict, lenMap) + 1;
            lenMap.put(s, len);
            if(len > longest){
                longest = len;
            }
        }
        return longest;
    }

    static int getLength(String word, Set<String> dict, Map<String, Integer> lenMap) {
        for(int i = 0; i < word.length(); i++){
        	// delete each char to test
        	String next = word.substring(0, i) + word.substring(i + 1);
//            StringBuilder sb = new StringBuilder(word);
//            sb.deleteCharAt(i); // delete one
//            String next = sb.toString();
            if(dict.contains(next)){
                if(lenMap.containsKey(next)){
                    return lenMap.get(next);
                }
                else{
                    int len = getLength(next, dict, lenMap) + 1;
                    lenMap.put(next, len);
                    return len;
                }
            }
        }
        return 0;
    }
    
    public static void main(String[] args) {
    	String[] strings = {"a", "b", "ba", "bca", "bda", "bdca"};
//    	System.out.println(a.substring(0,0) + a.substring(1));
    	System.out.println(longest(strings));
    }
    
    static int longest(String[] w) {
        if (w == null || w.length == 0) return 0;
        Set<String> set = new HashSet<String>();
        for (String str : w) {
            set.add(str);
        }
        int maxLen = 0;
        // string and corresponding length pair
        Map<String, Integer> map = new HashMap<String, Integer>();
        for (String str : w) {
            if (map.containsKey(str)) continue; // must be put before
            int curLen = removeToCount(str, map, set) + 1;
            map.put(str, curLen);
            if (curLen > maxLen) {
                maxLen = curLen;
            }
        }
        return maxLen;
    }
    static int removeToCount(String s, Map<String, Integer> map, Set<String> set) {
        for (int i = 0; i < s.length(); i++) {
            String deleted = s.substring(0, i) + s.substring(i + 1);
            if (set.contains(deleted)) {
                if (map.containsKey(deleted)) {
                    return map.get(deleted);
                } else {
                    int nextLen = removeToCount(deleted, map, set) + 1;
                    map.put(deleted, nextLen);
                    return nextLen;
                }
            }
        }
        return 0;
    }
}
