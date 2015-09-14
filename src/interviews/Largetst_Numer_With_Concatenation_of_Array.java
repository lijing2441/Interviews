package interviews;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Largetst_Numer_With_Concatenation_of_Array {
	/**
	 * Given a list of non negative integers, arrange them such that they form
	 * the largest number.
	 * 
	 * For example, given [3, 30, 34, 5, 9] => 9534330.
	 * 
	 * Note: The result may be very large, so you need to return a string
	 * instead of an integer.
	 */
	
	public String largestNumber(int[] nums) {
        int len = nums.length;
        if(len == 0) return "0";
        ArrayList<String> list = new ArrayList<String>();
        for(int i : nums) list.add(i + "");
        Collections.sort(list, new Comparator<String>() {
            public int compare(String s1, String s2) {
                if(s1.equals(s2)) return 0;
                if(s1.length() != s2.length()) return compare(s1 + s2, s2 + s1);
                for(int i = 0; i < s1.length(); i++) {
                    if(s1.charAt(i) != s2.charAt(i)) {
                        return s1.charAt(i) > s2.charAt(i) ? 1 : -1;
                    }
                }
                return 0;
            }
        });
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < len; i++) {
            sb.insert(0, list.get(i));
        }
        String res = sb.toString();
        if(res.charAt(0) == '0') return "0";
        return res;
    }
	
	// 用另一种comparator
	public static String largestNumber2(int[] nums) {
		List<Integer> list = new ArrayList<Integer>();
		for (int i: nums) list.add(i);
        Collections.sort(list, new Comparator<Integer>() {
            public int compare(Integer n1, Integer n2) {
		        String str1 = Integer.toString(n1);
	    	    String str2 = Integer.toString(n2);
	    	    int i = 0;
		        int len1 = str1.length();
		        int len2 = str2.length();
		        for(i = 0; i < Math.min(len1, len2); i++) {
		            char c1 = str1.charAt(i);
		            char c2 = str2.charAt(i);
		            if(c1 == c2) continue;
		            else {
		                return c1 > c2 ? 1 : -1;
		            }
		        }
		        if(i < len1) {
		            int tmp = str2.charAt(0);
		            for(int j = i; j < len1; j++) {
		                char cur = str1.charAt(j);
		                if(cur == tmp) continue;
		                else {
		                    return cur > tmp ? 1 : -1;
		                }
		            }
		        }
		        if(i < len2) {
		            int tmp = str1.charAt(0);
		            for(int j = i; j < len2; j++) {
		                char cur = str2.charAt(j);
		                if(cur == tmp) continue;
		                else {
		                    return tmp > cur ? 1 : -1;
		                }
		            }
		        }
		        return 0;
	        }
        });
        
        StringBuilder sb = new StringBuilder();
        for(int i = nums.length - 1; i >= 0; i--) {
            sb.append(Integer.toString(list.get(i)));
        }
        return sb.toString();
    }
	
	public static void main(String[] args) {
		int[] num = {3, 30, 34, 5, 9};
		String res = largestNumber2(num);
		System.out.println(res);
	}
}
