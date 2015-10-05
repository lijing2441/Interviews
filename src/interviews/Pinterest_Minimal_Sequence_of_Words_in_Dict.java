package interviews;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Pinterest_Minimal_Sequence_of_Words_in_Dict {
	/**
	 * Given an input string and a dictionary of words, find out if the input
	 * string can be segmented into a space-separated sequence of dictionary
	 * words. You need to output the minimum number of words. For example,
	 * input: "aaaisaname" dict: ("a", "aaa", "is", "name") output:
	 * "aaa is a name" Wrong output: "a a a is a name"
	 */
	// simiar to work break
	public static void segmentWords(String input, Set<String> dict) {
		if (input == null || input.length() == 0) System.out.println("Empty string");
		if (dict.contains(input)) System.out.println(input);
		
		int len = input.length();
		int[] dp = new int[len + 1];
		Arrays.fill(dp, Integer.MAX_VALUE);
		dp[0] = 0;
		for (int i = 0; i < len; i++) {
			if(dp[i] == Integer.MAX_VALUE) continue;
			for (String str: dict) {
				if (i + str.length() > len) continue;
				if (input.substring(i, i + str.length()).equals(str)) {
					dp[i + str.length()] = Math.min(dp[i + str.length()], dp[i] + 1);
				}
			}
		}
		if (dp[len] != Integer.MAX_VALUE) {
			// we have a solution here
			List<String> res = new ArrayList<String>();
			int curCount = dp[len];
			int end = len;
			for (int i = len - 1; i >= 0; i--) {
				if (dp[i] == curCount - 1) {
					String curStr = input.substring(i, end);
					res.add(0, curStr);
					end = i;
					curCount--;
				}
			}
			for (String s: res) {
				System.out.print(s + " ");
			}
			System.out.println();
		} else {
			System.out.println("No solution!");
		}
	}
	public static void main(String[] args) {
		Set<String> set = new HashSet<String>();
		set.add("a");
		set.add("aaa");
		set.add("is");
		set.add("name");
		String input = "aaaisaname";
		segmentWords(input, set);
	}
}
