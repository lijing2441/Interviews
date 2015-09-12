package interviews;

import java.util.HashMap;

public class Longest_Substring_Without_Repeating_Char {
	/**
	 * Given a string, find the length of the longest substring without repeating characters. 
	 * 
	 * For example, the longest substring without repeating letters for "abcabcbb" is "abc", 
	 * with a length of 3. 
	 * 
	 * First, make sure it's ASCII or unicode.
	 */
	//O(n^2)
	public int lengthOfLongestSubstring(String s){
		//assume ascii
		boolean[] flag = new boolean[256];
		int max = 0;
		int start = 0;
		char[] arr = s.toCharArray();
		
		for(int i = 0; i < arr.length; i++){
			char c = arr[i];
			//find repeats
			if(flag[c]){
				// check update if applicable
				// if we need to return the string, record the start and end pos here
				if(i - start > max){
					max = i - start;
				}
				for(int j = start; j < i; j++){
					if(arr[j] == c){
						start = j + 1;
						break;
					}
					//reset the unused character for the next string along the way
					flag[arr[j]] = false;
				}
			}else{
				flag[c] = true;
			}
		}
		return Math.max(arr.length - start, max);
	}
	// or we can use a hashmap to record the positions, O(n) 
	public int lengthOfLongestSubstring2(String s) {
        if(s == null) return 0;
        int n = s.length();
        if(n == 1 || n == 0) return n;
        int max = 0;
        int start = 0;
        HashMap<Character, Integer> last = new HashMap<Character, Integer>();
        for(int i = 0; i < n; i++){
            Character cur = s.charAt(i);
            if(last.containsKey(cur) && last.get(cur) >= start){
                max = Math.max(max, i - start);
                start = last.get(cur) + 1;
            }
            last.put(cur, i);
        }
        max = Math.max(max, n - start);
        return max;
    }
}
