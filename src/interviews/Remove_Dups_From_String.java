package interviews;

import java.util.Arrays;

public class Remove_Dups_From_String {
	//O(n), O(n)
	public static String removeDups(String s){
		boolean[] map = new boolean[26];
		int k = 0;
		char[] ch = s.toCharArray();
		for(int i = 0; i < s.length(); i++){
			while(i < s.length() && map[ch[i] - 'a'] == true){   // first check if out of bound
				i++;
			}
			if(i < s.length()){
				ch[k] = ch[i];
				map[ch[k] - 'a'] = true;
				k++;
			}
		}
		String toReturn = new String(Arrays.copyOfRange(ch, 0, k));//(inclusive, exclusive)
		return toReturn;
	}
	
	//another way: O(n^2) time, O(1) space, given charArray
	public static String removeDups2(char[] ch){
		if(ch == null) return new String();
		int n = ch.length;
		if(n < 2) return ch.toString();
		
		int tail = 1;
		for(int i = 1; i < n; i++){
			int j = 0;
			for(j = 0; j < tail; j++){
				if(ch[j] == ch[i]) break;
			}
			if(j == tail){
				ch[tail] = ch[i];
				tail++;
			}
		}
		return new String(Arrays.copyOfRange(ch, 0, tail));
	}
	
	
	//driver function
	public static void main(String[] args){
		String s = "sstt";
		char[] ch = s.toCharArray();
		//System.out.println(removeDups(s));
		System.out.println(removeDups2(ch));
	}
}
