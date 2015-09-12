package interviews;

public class is_Subtree {
	/**
	 * You have two very large binary trees: T1 with millions of nodes and T2 with hundreds.
	 * Decide whether T2 is a subtree of T1.
	 * 
	 * @logic Method I: get the pre-order and in-order traversal of each tree, if T2 is a substring
	 * 					of T1 in both case, then T2 is a subtree of T1
	 * 		  Method II: search through T1. Each time a node in T1 matches the root of T2, call treeMatch.
	 * 					 Worst case complexity is O(n + km) -> k is the number of occurrences of T2's root in T1.
	 * 					 we exit treeMatch when we find a mismatch.
	 * 					 
	 */
	public boolean isSubtree(TreeNode t1, TreeNode t2){
		if(t2 == null) return true;
		if(t1 == null) return false;
		if(t1.val == t2.val){
			if(treeMatch(t1, t2)) return true;
		}
		return isSubtree(t1.left, t2) || isSubtree(t1.right, t2);
	}
	public boolean treeMatch(TreeNode t1, TreeNode t2){
		if(t1 == null && t2 == null) return true;
		if(t1 == null || t2 == null) return false;
		if(t1.val != t2.val) return false;
		return treeMatch(t1.left, t2.left) && treeMatch(t1.right, t2.right);
	}
}
