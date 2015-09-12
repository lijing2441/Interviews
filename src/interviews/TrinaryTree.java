package interviews;

public class TrinaryTree {
	public class Node {
		char data;
		boolean isEnd; // mark if the string has arrived the end
		Node left;
		Node mid;
		Node right;

		public Node(char ch, boolean end) {
			data = ch;
			isEnd = end;
			left = null;
			mid = null;
			right = null;
		}

		public void insert(Node root, String word, int ptr) {
			char[] s = word.toCharArray();
			if (root == null) {
				root = new Node(s[ptr], false);
			}
			if (s[ptr] < root.data) {
				insert(root.left, word, ptr);
			} else if (s[ptr] > root.data) {
				insert(root.right, word, ptr);
			} else {
				if (ptr == word.length() - 1)
					root.isEnd = true;
				else {
					insert(root.mid, word, ptr + 1);
				}
			}
		}

		public boolean containsKey(Node root, String key) throws Exception {
			if (key == null || key == "")
				throw new Exception();
			int pos = 0;
			Node node = root;
			char[] s = key.toCharArray();
			while (node != null) {
				if (s[pos] < node.data) {
					node = node.left;
				} else if (s[pos] > node.data) {
					node = node.right;
				} else {
					if (pos == key.length() - 1) {
						return node.isEnd;
					}
					node = node.mid;
				}
			}
			return false;
		}

		public void delete(Node root, String word, int ptr) {
			if (null == root)
				return;
			char[] s = word.toCharArray();
			if (s[ptr] < root.data) {
				delete(root.left, word, ptr);
			} else if (s[ptr] > root.data) {
				delete(root.right, word, ptr);
			} else {
				if (root.isEnd == true && ptr == s.length - 1)
					root.isEnd = false; // delete finished
				else if (ptr < s.length - 1)
					delete(root.mid, word, ptr + 1);
			}
		}

		// normal int ternary tree
		public Node insert(char key, Node root) {
			if (null == root)
				root = new Node(key, false);
			if (key < root.data)
				root.left = insert(key, root.left);
			else if (key > root.data)
				root.right = insert(key, root.right);
			else
				root.mid = insert(key, root.mid);
			return root;
		}

		public Node findMin(Node node) {
			if (node != null) {
				while (node.left != null)
					return findMin(node.left);
			}
			return node;
		}

		public Node delete(char key, Node root) throws Exception {
			if (null == root) throw new Exception();
			if (key < root.data)
				root.left = delete(key, root.left);
			else if (key > root.data)
				root.right = delete(key, root.right);
			else {
				if (root.mid != null)
					root.mid = delete(key, root.mid);
				else if (root.right != null) {
					root.data = findMin(root.right).data;
					root.right = delete(findMin(root.right).data, root.right);
				} else {
					root = root.left;
				}
			}
			return root;
		}
	}
}
