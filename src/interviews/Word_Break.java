package interviews;

import java.util.*;
import java.util.HashMap;

public class Word_Break {
	/**
	 * Given a string s and a dictionary of words dict, determine if s can be
	 * segmented into a space-separated sequence of one or more dictionary
	 * words.
	 * 
	 * For example, given s = "leetcode", dict = ["leet", "code"].
	 * 
	 * Return true because "leetcode" can be segmented as "leet code".
	 */
	
	// time O(n^2), space O(n)
	public boolean wordBreak(String s, Set<String> dict) {
		if (s == null || s.length() == 0)
			return false;
		if (dict.size() == 0)
			return false;
		if (dict.contains(s))
			return true;

		List<Integer> list = new LinkedList<Integer>();
		list.add(0);

		for (int end = 1; end <= s.length(); end++) {
			for (int i = 0; i < list.size(); i++) {
				if (dict.contains(s.substring(list.get(i), end))) {
					list.add(0, end);
					break;
				}
			}
		}
		return (list.get(0) == s.length());
	}

	/**
	 *  DP solution can get O(n) time => O(n * dict.size), with O(n) space
	 * dp[i] = true means we can get a valid break until index i
	 */
	public boolean wordBreakDP(String s, Set<String> dict) {
		if (s == null || dict.size() == 0)
			return false;
		if (dict.contains(s))
			return true;
		int n = s.length();
		// whether we can get a valid break until index i - 1
		boolean[] dp = new boolean[n + 1];
		dp[0] = true;
		for (int i = 0; i < n; i++) {
			// if the start is not valid
			if (!dp[i])
				continue;
			// check whether the current word can fit into the s
			for (String str : dict) {
				int len = str.length();
				if (i + len > n)
					continue;
				//if it has been set to true, a valid break has been reached here
				if (dp[i + len])
					continue;
				if (s.substring(i, i + len).equals(str))
					dp[i + len] = true;
			}
		}
		return dp[n];
	}
	/**
	 * Extra Problem: if only need to return one of the solutions!
	 * O(n) and O(n)
	 */
	public ArrayList<String> return1Solution(String s, Set<String> dict){
		ArrayList<String> res = new ArrayList<String>();
		if (s == null || dict.size() == 0)
			return res;
		if (dict.contains(s)){
			res.add(s);
			return res;
		}
		//find the dp[][]
		int n = s.length();
		boolean[] dp = new boolean[n + 1];
		dp[0] = true;
		for (int i = 0; i < n; i++) {
			if (!dp[i])
				continue;
			for (String str : dict) {
				int len = str.length();
				if (i + len > n)
					continue;
				if (dp[i + len])
					continue;
				if (s.substring(i, i + len).equals(str))
					dp[i + len] = true;
			}
		}
		if(dp[n]){
			int end = n; //exclusive
			for(int i = n - 1; i >= 0; i--){
				if(!dp[i]) continue;
				else{
					String str = s.substring(i, end);
					end = i;
					res.add(0, str);
				}
			}
		}
		return res;
	}

	/**
	 * word break II: Given a string s and a dictionary of words dict, add
	 * spaces in s to construct a sentence where each word is a valid dictionary
	 * word.
	 * 
	 * Return all such possible sentences.
	 * 
	 * For example, given s = "catsanddog", dict = ["cat", "cats", "and",
	 * "sand", "dog"].
	 * 
	 * A solution is ["cats and dog", "cat sand dog"]
	 * 
	 */
	// conventional method, time limit exceeded
	// 可以加上一些剪枝，pruning， 加上一个possible array

	public ArrayList<String> wordBreak3(String s, Set<String> dict) {
		ArrayList<String> res = new ArrayList<String>();
		if (s == null || s.length() == 0)
			return res;
		helper(s, dict, 0, "", res);
		return res;
	}

	private void helper(String s, Set<String> dict, int start, String item, ArrayList<String> res) {
		if (start >= s.length()) {
			res.add(item);
			return;
		}
		StringBuilder str = new StringBuilder();
		for (int i = start; i < s.length(); i++) {
			str.append(s.charAt(i));
			if (dict.contains(str.toString())) {
				String newItem = item.length() > 0 ? (item + " " + str.toString()) : str.toString();
				helper(s, dict, i + 1, newItem, res);
			}
		}
	}

	/**
	 * 动态规划的解法，递推式跟Word
	 * Break是一样的，只是现在不只要保存true或者false，还需要保存true的时候所有合法的组合，以便在未来需要的时候可以得到。
	 * 不过为了实现这点，代码量就增大很多，需要一个数据结构来进行存储，同时空间复杂度变得非常高，因为所有中间组合都要一直保存。 时间上还是有提高的，
	 * 就是当我们需要前面到某一个元素前的所有合法组合时，我们不需要重新计算了。不过最终复杂度还是主要取决于结果的数量。
	 */

	// DP method with O(2^n) worst case time， use a hashmap to store the possible results
	// use a recursion to get the result from the end of cur string to the end
	// of the whole given string
	// Hashmap: key: current string, value: the possible string decomposition list
	
	// We assume we have found the last x-1 strings, and we just need to get the last string
	// that is from some position in string to the end of the string
	public Map<String, ArrayList<String>> map = new HashMap<String, ArrayList<String>>();

	public ArrayList<String> wordBreak2Recur(String s, Set<String> dict) {
		ArrayList<String> res = new ArrayList<String>();
		if (dict.size() == 0 || s.length() == 0)
			return res;

		int len = s.length();
		for (int i = len - 1; i >= 0; i--) {
			String last = s.substring(i, len);
			if (dict.contains(last)) {
				if (i == 0)
					res.add(last);
				else {
					String remain = s.substring(0, i);

					ArrayList<String> remainSet = (map.containsKey(remain) ? map.get(remain) : wordBreak2Recur(remain, dict));
					for (String cur : remainSet) {
						res.add(cur + " " + last);
					}
					map.put(remain, remainSet);
				}
			}
		}
		return res;
	}

	// My method, using a 2-D array to record the string method
	// then 1-D boolean array to record the cut positions
	// time limit exceeded

	public ArrayList<String> wordBreak2(String s, Set<String> dict) {
		ArrayList<String> res = new ArrayList<String>();
		if (dict.size() == 0 || s.length() == 0)
			return res;
		int n = s.length();
		boolean[][] map = new boolean[n][n];
		map[0][0] = true;
		initiate(s, dict, map);
		helper(s, 0, map, new boolean[n], res);
		return res;
	}

	public void initiate(String s, Set<String> dict, boolean[][] map) {
		for (int i = 0; i < s.length(); i++) {
			for (int j = i; j < s.length(); j++) {
				String tmp = s.substring(i, j + 1);
				if (dict.contains(tmp))
					map[i][j] = true;
			}
		}
	}

	public void helper(String s, int start, boolean[][] map, boolean[] cutPos,
			ArrayList<String> res) {
		if (start == s.length()) {
			StringBuffer sb = new StringBuffer();
			int cut = 0;
			for (int i = 0; i < cutPos.length; i++) {
				if (cutPos[i]) {
					sb.append(s.substring(cut, i + 1));
					cut = i + 1;
					if (cut < s.length())
						sb.append(" ");
				}
			}
			res.add(sb.toString());
		}
		for (int i = start; i < s.length(); i++) {
			if (map[start][i]) {
				cutPos[i] = true;
				helper(s, i + 1, map, cutPos, res);
				cutPos[i] = false;
			}
		}
	}

}
