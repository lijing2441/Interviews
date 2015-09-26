package interviews;

public class Implement_HashTable_Using_BST {
	/**
	 * BST的优点 compare with hashing, TreeMap中是红黑树，self-balanced tree
	 * 
	 * 1. 可以中序得到key的order，hashmap不行
	 * 
	 * 2. 在做order statistics时，找lower higher key都很容易，range query也很容易
	 * 
	 * 3. BST更容易实现，而hashing依赖库和编程语言
	 * 
	 * 4. BST中所有操作保证O(logn)的复杂度，而hashing中O(1)是average，对于一些特殊操作，
	 * 	  尤其是table resizing，这样就很昂贵
	 */
	public TreeMapNode root;
	public int size;
	public Implement_HashTable_Using_BST () {
		size = 0;
	}
	
	public int get(int key) {
		if (size == 0) return -1; // treemap为空
		TreeMapNode node = root;
		while (node != null) {
			if (node.key == key) {
				return node.val;
			} else if (node.key < key) {
				node = node.left;
			} else {
				node = node.right;
			}
		}
		return -1;
	}
	// TreeMap用key来比较，决定所放位置
	public void put(int key, int val) {
		TreeMapNode node = new TreeMapNode(key, val);
		if (root == null) {
			root = node;
			return;
		}
		TreeMapNode curRoot = root;
        boolean inserted = false;
        while (!inserted) {
            if (key == curRoot.key) {
            	// 用新的val替换他
            	curRoot.val = val;
            	return;
            } else if (key < curRoot.key) {
                if(curRoot.left != null) {
                    curRoot = curRoot.left;
                } else {
                    curRoot.left = node;
                    inserted = true;
                }
            } else {
                if (curRoot.right != null) {
                    curRoot = curRoot.right;
                } else {
                    curRoot.right = node;
                    inserted = true;
                }
            }
        }
	}
}
class TreeMapNode {
	public int key;
	public int val;
	public TreeMapNode left;
	public TreeMapNode right;
	public TreeMapNode(int key, int val) {
		this.key = key;
		this.val = val;
	}
}