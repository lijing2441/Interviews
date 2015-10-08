package interviews;

import java.util.Map;
import java.util.HashMap;

public class Snapchat_A子串anagram是B {
	// keep a sliding window, O(n)
	public static boolean isSubstringAnagram(String A, String B) {
		if (A.length() < B.length()) return false;
		int aLen = A.length(), bLen = B.length();
		Map<Character, Integer> map = new HashMap<Character, Integer>();
		
		for (int i = 0; i < bLen; i++) {
			int num = 1;
			if (map.containsKey(B.charAt(i))) {
				num += map.get(B.charAt(i));
			}
			map.put(B.charAt(i), num);
		}
		int count = 0;
		Map<Character, Integer> curMap = new HashMap<Character, Integer>();
		for (int i = 0; i < aLen; i++) {
			char c = A.charAt(i);
			if (!map.containsKey(c)) {
				count = 0;
				curMap.clear();
			} else {
				if (curMap.containsKey(c) && curMap.get(c) + 1 > map.get(c)) {
					count = 0;
					curMap.clear();
				} else {
					int num = 1;
					if (curMap.containsKey(c)) num += curMap.get(c);
					curMap.put(c, num);
					count++;
					if (count == bLen) {
						return true;
					}
				}
			}
			if (i >= bLen) {
				if (curMap.containsKey(A.charAt(i - bLen)) && curMap.get(A.charAt(i - bLen)) > 0) {
					curMap.put(A.charAt(i - bLen), curMap.get(A.charAt(i - bLen)) - 1);
				}
			}
		}
		return false;
	}
	
	public static void main(String[] args) {
		String A = "abcdefghi";
		String B = "edihgf";
		if (isSubstringAnagram(A, B)) {
			System.out.println(true);
		} else {
			System.out.println(false);
		}
	}
}
