package interviews;

import java.util.ArrayList;

public class Airbnb_K_Edit_Distance {
	/**
	 * 给定一个word list和⼀个target word,要求输出在word list里跟target word的edit distance
	 * 相差不⼤于k的所有words。给⼀个list,找出所有和target相似的words。
	 * 
	 * 设计⼀个数据结构,不遍历list,也能找到所有。
	 */
	public static void main (String[] args) {
        String[] dict = new String[]{"ab","dfs", "a","fsa"};
        Trie trie = new Trie();
        for (String s: dict) {
        	trie.add(s);
        }
        ArrayList<String> res = trie.getWords("a", 2);
        for(String str : res)
                 System.out.println(str);
        //System.out.println("end");
	}
}
class TrieNode3 {
    public boolean isEnd;
    public TrieNode3[] children;
    public TrieNode3(){
    	isEnd = false;
    	children = new TrieNode3[26];
    }
}        
class Trie {
	public TrieNode3 root;
	public Trie() {
		this.root = new TrieNode3();
	}
	public void add(String word) {
		TrieNode3 tmp = root;
		for (int i = 0; i < word.length(); i++) {
			if (tmp.children[(int)(word.charAt(i) - 'a')] == null) {
				tmp.children[(int)(word.charAt(i) - 'a')] = new TrieNode3();
			}
			tmp = tmp.children[(int)(word.charAt(i) - 'a')];
		}
		tmp.isEnd = true;
	}
	public ArrayList<String> getWords(String target, int k) {
		ArrayList<String> res = new ArrayList<String>();
		// this records the edit distance between empty + other length of the string
		int[] pre = new int[target.length() + 1];
		for (int i = 0; i < pre.length; i++) {
			pre[i] = i;
		}
		dfs(k, target, "", pre, root, res);
		return res;
	}
	private void dfs(int k, String target, String cur, int[] pre, TrieNode3 root, ArrayList<String> res) {
		if (root.isEnd) {
			if (pre[target.length()] <= k) res.add(cur); // 不能从这return，可能后面还有配的
			//return;
		}
		for (int i = 0; i < 26; i++) {
			if (root.children[i] == null) continue;
			int[] next = new int[target.length() + 1];
			next[0] = cur.length() + 1; // 全都补上
			for (int j = 1; j < pre.length; j++) {
				if (target.charAt(j - 1) == (char)('a' + i)) {
					next[j] = pre[j - 1]; // a match
				} else {
					// cost = 1
					next[j] = Math.min(pre[j], Math.min(pre[j - 1], next[j - 1])) + 1;
				}
				dfs(k, target, cur + (char)('a' + i), next, root.children[i], res);
			}
		}
	}
}