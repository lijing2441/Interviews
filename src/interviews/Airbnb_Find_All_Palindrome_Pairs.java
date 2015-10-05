package interviews;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class Airbnb_Find_All_Palindrome_Pairs {
	public List<String[]> find_pairs(Set<String> words) {
		List<String[]> res = new ArrayList<String[]>();
		for (String str : words) {
			List<String> prefixes = findPalindromePrefix(str);
			List<String> suffixes = findPalindromeSuffix(str);
			for (String prefix: prefixes) {
				String remaining = str.substring(prefix.length());
				String reversed = (new StringBuilder(remaining)).reverse().toString();
				if (words.contains(reversed)) {
					String[] curPair = new String[2];
					curPair[0] = str;
					curPair[1] = reversed;
					res.add(curPair);
				}
			}
			for (String suffix: suffixes) {
				String remaining = str.substring(0, str.length() - suffix.length());
				String reversed = (new StringBuilder(remaining)).reverse().toString();
				if (words.contains(reversed)) {
					String[] curPair = new String[2];
					curPair[0] = str;
					curPair[1] = reversed;
					res.add(curPair);
				}
			}
			// for the case bag + gab
			String selfReversed = (new StringBuilder(str)).reverse().toString();
			if (words.contains(selfReversed)) {
				String[] curPair = new String[2];
				curPair[0] = str;
				curPair[1] = selfReversed;
				res.add(curPair);
			}
		}
		return res;
	}

	public List<String> findPalindromePrefix(String str) {
		List<String> res = new ArrayList<String>();
		if (str == null || str.length() == 0)
			return res;
		for (int i = 1; i < str.length(); i++) {
			String curStr = str.substring(0, i);
			if (isPalindrome(curStr))
				res.add(curStr);
		}
		return res;
	}

	public List<String> findPalindromeSuffix(String str) {

		List<String> res = new ArrayList<String>();
		if (str == null || str.length() == 0)
			return res;
		for (int i = str.length() - 1; i >= 0; i--) {
			String curStr = str.substring(i, str.length());
			if (isPalindrome(curStr))
				res.add(curStr);
		}
		return res;
	}

	public boolean isPalindrome(String str) {
		if (str == null)
			return false;
		if (str.length() < 2)
			return true;
		int left = 0, right = str.length() - 1;
		while (left < right) {
			if (str.charAt(left) != str.charAt(right))
				return false;
			else {
				left++;
				right--;
			}
		}
		return true;
	}
}
