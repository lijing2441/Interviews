package interviews;

public class Search_Kth_In_BST {
	/**
	 * You have a binary tree where each node knows the number of nodes in its
	 * sub-tree (including itself). Given a node n and an int k, write a
	 * function to return the kth node in an in order traversal. Can you do this
	 * non recursively?
	 * 
	 * Now, suppose we are at node T:
	 * (1) k == num_elements(left subtree of T), then the answer we're looking for is the value in node T
	 * (2) k > num_elements(left subtree of T), then obviously we can ignore the left subtree, because those elements will also be smaller than the kth smallest. So, we reduce the problem to finding the k - num_elements(left subtree of T) smallest element of the right subtree.
	 * (3) k < num_elements(left subtree of T), then the kth smallest is somewhere in the left subtree, so we reduce the problem to finding the kth smallest element in the left subtree.
	 * This is O(log N) on average (assuming a balanced tree).
	 */
	public NodeWithCount findKthNode(NodeWithCount root, int k){
		if(root == null) return null;
		NodeWithCount node = root;
		int count = k;
		while(node != null){
			// we find the node
			if(node.left.getCount() + 1 == count){
				return node;
			}else if(node.left.getCount() + 1 < count){
				node = node.right;
				count -= (node.left.getCount() + 1);
			}else{
				node = node.left;
			}
		}
		return null;
	}
}

class NodeWithCount {
	int val;
	int count;
	NodeWithCount left;
	NodeWithCount right;

	NodeWithCount(int x) {
		val = x;
		count = 1; 
	}
	public int getCount(){
		if(this.getClass() == null){
			return 0;
		}else{
			return this.count;
		}
	}
}
