package interviews;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

public class Stobogrammatic_Number {
	/**
	 * A strobogrammatic number is a number that looks the same when rotated 180
	 * degrees (looked at upside down).
	 * 
	 * Write a function to determine if a number is strobogrammatic. The number
	 * is represented as a string.
	 * 
	 * For example, the numbers "69", "88", and "818" are all strobogrammatic.
	 */
	public boolean isStrobogrammatic(String num) {
		int len = num.length();
		if (len == 0)
			return true;
		Map<Character, Character> map = new HashMap<Character, Character>();
		map.put('1', '1');
		map.put('6', '9');
		map.put('0', '0');
		map.put('9', '6');
		map.put('8', '8');
		int left = 0, right = len - 1;
		char[] arr = num.toCharArray();
		while (left <= right) {
			if (!map.containsKey(arr[left]) || !map.containsKey(arr[right]))
				return false;
			else if (left == right) {
				if (arr[left] != '6' && arr[left] != '9')
					return true;
				else
					return false;
			} else {
				if (map.get(arr[left]) != arr[right])
					return false;
				else {
					left++;
					right--;
				}
			}
		}
		return true;
	}

	/**
	 * II: Find all strobogrammatic numbers that are of length = n.
	 */
	public Map<Character, Character> map = new HashMap<Character, Character>();

	public List<String> findStrobogrammatic(int n) {
		List<String> res = new ArrayList<String>();
		map.put('0', '0');
		map.put('1', '1');
		map.put('6', '9');
		map.put('8', '8');
		map.put('9', '6');
		helper(res, n, "");
		return res;
	}

	public void helper(List<String> res, int remain, String cur) {
		if (remain == 0) {
			res.add(cur);
			return;
		}
		// remove the middle one in the first round if n is odd
		if (remain % 2 == 1) {
			for (char c : map.keySet()) {
				if (c != '6' && c != '9') {
					helper(res, remain - 1, cur + c);
				}
			}
		} else {
			for (char c : map.keySet()) {
				if (remain > 2) {
					helper(res, remain - 2, c + cur + map.get(c));
				} else {
					if (c != '0') {
						helper(res, remain - 2, c + cur + map.get(c));
					}
				}
			}
		}
	}

	/**
	 * A strobogrammatic number is a number that looks the same when rotated 180
	 * degrees (looked at upside down).
	 * 
	 * Write a function to count the total strobogrammatic numbers that exist in
	 * the range of low <= num <= high.
	 * 
	 * For example, Given low = "50", high = "100", return 3. Because 69, 88,
	 * and 96 are three strobogrammatic numbers.
	 * 
	 * Note: Because the range might be a large number, the low and high numbers
	 * are represented as string.
	 */
	
	// use the length of low and high
	public Map<Character, Character> map2 = new HashMap<Character, Character>();
    public String low;
    public String high;
    public int strobogrammaticInRange(String low, String high) {
        map2.put('0', '0');
        map2.put('1', '1');
        map2.put('6', '9');
        map2.put('8', '8');
        map2.put('9', '6');
        int res = 0;
        this.low = low;
        this.high = high;
        for(int n = low.length(); n <= high.length(); n++) {
            int[] cur = new int[1];
            getNumberCount(cur, "", n);
            res += cur[0];
        }
        return res;
    }
    public void getNumberCount(int[] count, String cur, int remain) {
        if(remain == 0) {
            if(compare(low, cur) && compare(cur, high)) count[0]++;
            return;
        }
        if(remain % 2 == 1) {
            for(char c : map2.keySet()) {
                if(c != '6' && c != '9') getNumberCount(count, cur + c, remain - 1);
            }
        } else {
            for(char c: map2.keySet()) {
                if(remain > 2) {
                    getNumberCount(count, c + cur + map.get(c), remain - 2);
                } else {
                    if(c != '0') {
                        getNumberCount(count, c + cur + map.get(c), remain - 2);
                    }
                }
            }
        }
    }
    // if s1 <= s2, return true; otherwise, return false
    public boolean compare(String s1, String s2) {
        if(s1.length() != s2.length()) {
            return s1.length() < s2.length();
        }
        int i = 0;
        while(i < s1.length() && s1.charAt(i) == s2.charAt(i)) {
            i++;
        }
        if(i == s1.length() || s1.charAt(i) <= s2.charAt(i)) return true;
        return false;
    }
}