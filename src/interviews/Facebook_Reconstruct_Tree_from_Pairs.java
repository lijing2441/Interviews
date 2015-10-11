package interviews;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

public class Facebook_Reconstruct_Tree_from_Pairs {
	/**
	 * Given an array that saves lots of pairs, each pair represents child ->
	 * parent relationship in tree. Construct tree from pairs. 
	 * 
	 * n-ary tree
	 */
	public static Nary_TreeNode constructTreeFromPairs(List<ParentChildPair> parentChildPairs){
		Map<Integer, Integer> childParentMap = new HashMap<Integer, Integer>();
	    for (ParentChildPair p: parentChildPairs) {
	    	childParentMap.put(p.child, p.parent);
	    }
	    Nary_TreeNode root = null;
	    for (Integer p: childParentMap.values()) {
	    	if (!childParentMap.keySet().contains(p)) {
	    		root = new Nary_TreeNode(p);
	    	}
	    }
	    if (root == null) {
	    	System.out.println("No root found");
	    }
	    
	    Map<Integer, Nary_TreeNode> valueNodeMap = new HashMap<Integer, Nary_TreeNode>();
	    valueNodeMap.put(root.val, root);
	    for (int child : childParentMap.keySet()) {
	    	Nary_TreeNode node = new Nary_TreeNode(child);
	    	valueNodeMap.put(child, node);
	    }
	    // associate the children with the parent
	    for (int child : childParentMap.keySet()) {
	    	Nary_TreeNode cNode = valueNodeMap.get(child);
	    	int parent = childParentMap.get(child);
	    	Nary_TreeNode pNode = valueNodeMap.get(parent);
	    	pNode.children.add(cNode);
	    }
	    return root;
	}
	public static void main(String[] args) {
		ParentChildPair p1 = new ParentChildPair(2, 5);
		ParentChildPair p2 = new ParentChildPair(2, 6);
		ParentChildPair p3 = new ParentChildPair(2, 7);
		ParentChildPair p4 = new ParentChildPair(3, 8);
		ParentChildPair p5 = new ParentChildPair(4, 9);
		ParentChildPair p6 = new ParentChildPair(4, 10);
		ParentChildPair p7 = new ParentChildPair(1, 2);
		ParentChildPair p8 = new ParentChildPair(1, 3);
		ParentChildPair p9 = new ParentChildPair(1, 4);
		
		List<ParentChildPair> res = new ArrayList<ParentChildPair>();
		res.add(p1);
		res.add(p2);
		res.add(p3);
		res.add(p4);
		res.add(p5);
		res.add(p6);
		res.add(p7);
		res.add(p8);
		res.add(p9);
		Nary_TreeNode root = constructTreeFromPairs(res);
		System.out.println("root: " + root.val);
		System.out.println("root's chidlren size: " + root.children.size());
	}
}
class Nary_TreeNode {
	int val;
	List<Nary_TreeNode> children;
	public Nary_TreeNode(int val) {
		this.val = val;
		children = new ArrayList<Nary_TreeNode>();
	}
}
class ParentChildPair {
	int parent;
	int child;
	public ParentChildPair(int p, int c) {
		this.parent = p;
		this.child = c;
	}
}