package interviews;

public class Trie_Find_Word_In_Dictionary {
	/**
	 * 首先需要问dict是否sorted，如果是那就按顺序搜索即可；
	 * 其次问是否需要多次利用，若单次，brute force；若多次，建trie
	 * follow-up: 若trie太大没有办法每次开启软件都initialize到RAM中，怎么办？
	 * 	(1) LRU cache 
	 * 	(2) 刚刚开始只initialize前面最常用的几层，比如经过statistically survey, the frequently used words 
	 * 		own at most 7 letters. 那每次开启只initialize前7层trie；即使后面某次我偶然想用一个长为9的词，
	 * 		只用在该方向再深入访问两层即可，而不是全部。
	 * 
	 * Trie: Using trie, search complexities can be brought to optimal limit (key length).  
	 * 		 Using trie, we can search the key in O(M) time. However the penalty is on trie storage requirements.
	 * 	
	 * 	Insert and search costs O(key_length), however the memory requirements of trie is 
	 * 	O(ALPHABET_SIZE * key_length * N) where N is number of keys in trie. 
	 * 
	 * 	There are efficient representation of trie nodes (e.g. compressed trie, ternary search tree, etc.) 
	 * 	to minimize memory requirements of trie.
	 */
	// use only 'a' through 'z', lowercase
	private int ALPHABET_SIZE = 26;
	
	public class Trie_Node {
		public char val; // char_to_index(c){ c = (int) c - (int)'a'}
		public Trie_Node[] children;
		// whether the string has eneded or not
		public boolean isEnd;

		public Trie_Node(char c, boolean end) {
			val = c;
			isEnd = end;
			children = new Trie_Node[ALPHABET_SIZE];
		}
	}
	public class Trie{
		private Trie_Node root;
		//keep track of the size of the trie, in order to not overflow the memory
		@SuppressWarnings("unused")
		private int count;
		public Trie(Trie_Node n, int c){
			root = n;
			count = c;
		}
		
		//initialize trie, root is a dummy node
		public void initialize(Trie_Node root){
			this.root = root;
			this.count = 0;
		}
		
		//if not present, insert key to trie
		//if the key is prefix of trie node, just mark a leaf node
		public void insert(Trie_Node node, String word){
			int len = word.length();
			Trie_Node cur = this.root;
			
			for(int level = 0; level < len; level++){
				int index = word.charAt(level) - 'a';
				if(cur.children[index] == null){
					// it's not end yet
					cur.children[index] = new Trie_Node(word.charAt(level), false); 
					//trie size + 1;
					this.count++;
				}
				cur = cur.children[index];
			}
			//mark the last node as end of the word
			cur.isEnd = true;
		}
		//search the word!
		public boolean search(String word, String[] dict){
			int len = word.length();
			Trie_Node cur = this.root;
			for(int level = 0; level < len; level++){
				int index = word.charAt(level) - 'a';
				if(cur.children[index] == null){
					return false;
				}
				cur = cur.children[index];
			}
			//if we reach a leaf node
			if(cur != null && cur.isEnd == true) return true;
			//if the node is not leaf, that is, the string has not been ended.
			return false;
		}
	}
}
