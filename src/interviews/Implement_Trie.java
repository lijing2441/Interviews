package interviews;

// TrieNode storing value example
public class Implement_Trie {
	public class Node {
		private int ALPHABET_SIZE = 26;
		// use only 'a' through 'z', lowercase
		public char val; // char_to_index(c){ c = (int) c - (int)'a'}
		public Node[] children;
		public boolean isEnd;

		public Node(char c, boolean end) {
			val = c;
			isEnd = end;
			children = new Node[ALPHABET_SIZE];
		}

		public void insert(Node root, String word) {
			int l = word.length();
			char[] ch = word.toCharArray();
			Node cur = root;

			for (int i = 0; i < l; i++) {
				if (cur.children[ch[i] - 'a'] == null)
					cur.children[ch[i] - 'a'] = new Node(ch[i], false);
				cur = cur.children[ch[i] - 'a'];
			}
			cur.isEnd = true;
		}

		public boolean find(Node root, String word) {
			char[] ch = word.toCharArray();
			int l = word.length();
			Node cur = root;

			int i = 0;
			for (i = 0; i < l; i++) {
				if (cur == null)
					return false;
				cur = cur.children[ch[i] - 'a'];
			}
			if (i == l && !cur.isEnd)
				return false;
			return true;
		}
	}
	// print trie, start from level 0
	static void printTree(Node root, int level, char[] branch) {
		if (root == null)
			return;

		for (int i = 0; i < root.children.length; i++) {
			branch[level] = root.val;
			printTree(root.children[i], level + 1, branch);
		}

		if (root.isEnd) {
			for (int j = 1; j <= level; j++)
				System.out.print(branch[j]);
			System.out.println();
		}
	} 	
}
//TrieNode not storing value example 
class TrieNode {
    // Initialize your data structure here.
    public boolean isEnd;
    public TrieNode[] children;
    public TrieNode() {
        isEnd = false;
        children = new TrieNode[26];
    }
}

class MyTrie {
    private TrieNode root;

    public MyTrie() {
        root = new TrieNode();
    }

    // Inserts a word into the trie.
    public void insert(String word) {
        if(word == null) return;
        char[] arr = word.toCharArray();
        TrieNode tmp = root;
        for(int i = 0; i < arr.length; i++) {
            char c = arr[i];
            if(tmp.children[c - 'a'] == null) {
                tmp.children[c - 'a'] = new TrieNode();
            }
            tmp = tmp.children[c - 'a'];
        }
        tmp.isEnd = true;
    }

    // Returns if the word is in the trie.
    public boolean search(String word) {
        if(word == null) return false;
        char[] arr = word.toCharArray();
        TrieNode tmp = root;
        for(int i = 0; i < arr.length; i++) {
            char c = arr[i];
            if(tmp.children[c - 'a'] == null) {
                return false;
            }
            tmp = tmp.children[c - 'a'];
        }
        if(tmp.isEnd == true) return true;
        else return false;
    }

    // Returns if there is any word in the trie
    // that starts with the given prefix.
    public boolean startsWith(String prefix) {
        if(prefix == null) return false;
        char[] arr = prefix.toCharArray();
        TrieNode tmp = root;
        for(int i = 0; i < arr.length; i++) {
            char c = arr[i];
            if(tmp.children[c - 'a'] == null) {
                return false;
            }
            tmp = tmp.children[c - 'a'];
        }
        return true;
    }
}
/**
 * Data structure design, Add and Search Word
 * @author skydragon
 *
 */
class WordDictionary {
    private TrieNode root;

    public WordDictionary() {
        root = new TrieNode();
    }
    
    // Adds a word into the data structure.
    public void addWord(String word) {
        if(word == null || word.length() == 0) return;
        char[] arr = word.toCharArray();
        TrieNode tmp = root;
        for(int i = 0; i < arr.length; i++) {
            char c = arr[i];
            if(tmp.children[c - 'a'] == null) {
                tmp.children[c - 'a'] = new TrieNode();
            }
            tmp = tmp.children[c - 'a'];
        }
        tmp.isEnd = true;
    }

    // Returns if the word is in the data structure. A word could
    // contain the dot character '.' to represent any one letter.
    public boolean search(String word) {
        return search(word, root);
    }
    public boolean search(String word, TrieNode root) {
        char[] arr = word.toCharArray();
        for(int i = 0; i < arr.length; i++) {
            if(root != null && arr[i] != '.') {
                root = root.children[arr[i] - 'a'];
            } else if (root != null && arr[i] == '.') {
                TrieNode tmp = root;
                for(int j = 0; j < 26; j++) {
                    root = tmp.children[j];
                    if(search(word.substring(i + 1), root)) return true;
                }
            } else {
                break;
            }
        }
        return root != null && root.isEnd;
    }
}