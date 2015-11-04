package interviews;

import java.util.ArrayList;
import java.util.List;

public class Pinterest_找最长的公共前缀 {
	/**
	 * 给一串字符，找最长的公共前缀，至少两个字符串公共
	 */
	public static String longestCommonPrefixInArray(List<String> list) {
		String res = "";
		int max = 0;
		for (int i = 0; i < list.size() - 1; i++) {
			for (int j = i + 1; j < list.size(); j++) {
				String prefix = getLongestPrefix(list.get(i), list.get(j));
				if (prefix.length() > max) {
					max = prefix.length();
					res = prefix;
				}
			}
		}
		return res;
	}
	public static String getLongestPrefix(String word1, String word2) {
		//int end = 0;
		for (int i = 0; i < Math.min(word1.length(), word2.length()); i++) {
			if (word1.charAt(i) != word2.charAt(i)) {
				return word1.substring(0, i);
			}
		}
		return "";
	}
	
	// 第二种做法，用trie, 修改trie的结构，如果有一个子串有大于一个string使用，则说明是公共prefix
	// 找最深的公共节点就可以了
	public static String longestCommonPrefixTrie(List<String> list) {
		// every time check whether the current word has been in the trie or not
		// if not we insert and update the max common depth if needed
		// if exists, the current word length should be the length, update if necessary
		int maxLen = -1;
		String maxLenPrefix = "";
		TrieWithDepth trie = new TrieWithDepth();
		for (int i = 0; i < list.size(); i++) {
			String cur = list.get(i);
			if (trie.startsWith(cur)) {
				if (cur.length() > maxLen) {
					maxLen = cur.length();
					maxLenPrefix = cur;
				}
			} else {
				int curLen = trie.insert(cur);
				if (curLen > maxLen) {
					maxLen = curLen;
					maxLenPrefix = cur.substring(0, maxLen);
				}
			}
		}
		return maxLenPrefix;
	}
	
	
	public static void main(String[] args) {
		List<String> l = new ArrayList<String>();
		l.add("harrypotterab");
		l.add("harrypottercd");
		l.add("harrypottef");
		l.add("harry");
		l.add("harrypotterag");
		l.add("larrypotterab");
		System.out.println(longestCommonPrefixInArray(l));
		System.out.println(longestCommonPrefixTrie(l));
	}
}
class TrieWithDepth {
	TrieNode root;
		
	public TrieWithDepth() {
		this.root = new TrieNode();
	}
	public int insert(String word) {
		char[] arr = word.toCharArray();
		TrieNode node = root;
		int commonDepth = -1;
		for (int i = 0; i < arr.length; i++) {
			int index = (int)(arr[i]-'a');
			if (node.children[index] == null) {
				if (commonDepth == -1) commonDepth = i;
				node.children[index] = new TrieNode();
			}
			node = node.children[index];
		}
		if (commonDepth == -1) commonDepth = arr.length; // the whole word matches
		return commonDepth;
	}
	public boolean startsWith(String word) {
		char[] arr = word.toCharArray();
		TrieNode node = root;
		for (int i = 0; i < arr.length; i++) {
			int index = (int)(arr[i]-'a');
			if (node.children[index] == null) return false;
			node = node.children[index];
		}
		return true;
	}
}