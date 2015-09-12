package interviews;

public class Add_and_Search_Word {
	private TrieNode root;

    public Add_and_Search_Word() {
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

class TrieNode {
    // Initialize your data structure here.
    public boolean isEnd;
    public TrieNode[] children;
    public TrieNode() {
        isEnd = false;
        children = new TrieNode[26];
    }
}