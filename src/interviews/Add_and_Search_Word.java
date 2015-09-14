package interviews;

public class Add_and_Search_Word {
	private TrieNode2 root;

    public Add_and_Search_Word() {
        root = new TrieNode2();
    }
    
    // Adds a word into the data structure.
    public void addWord(String word) {
        if(word == null || word.length() == 0) return;
        char[] arr = word.toCharArray();
        TrieNode2 tmp = root;
        for(int i = 0; i < arr.length; i++) {
            char c = arr[i];
            if(tmp.children[c - 'a'] == null) {
                tmp.children[c - 'a'] = new TrieNode2();
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
    public boolean search(String word, TrieNode2 root) {
        char[] arr = word.toCharArray();
        for(int i = 0; i < arr.length; i++) {
            if(root != null && arr[i] != '.') {
                root = root.children[arr[i] - 'a'];
            } else if (root != null && arr[i] == '.') {
                TrieNode2 tmp = root;
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

class TrieNode2 {
    // Initialize your data structure here.
    public boolean isEnd;
    public TrieNode2[] children;
    public TrieNode2() {
        isEnd = false;
        children = new TrieNode2[26];
    }
}
