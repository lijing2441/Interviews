package interviews;

import java.util.*;

public class BTreePrinter {
	/**
	 * Given a binary tree of integers, give an ascii representation of the tree, 
	 * so that people can visually see the structure of the tree.
	 * 
	 * @logic binary tree level order traversal, but be aware of the whitespaces
	 */
	public static void printNode(TreeNode root) {
        int maxLevel = BTreePrinter.maxLevel(root); // find the level number first
        printNodeInternal(Collections.singletonList(root), 1, maxLevel);
    }

    private static void printNodeInternal(List<TreeNode> nodes, int level, int maxLevel) {
        if (nodes.isEmpty() || BTreePrinter.isAllElementsNull(nodes))
            return;
        //find the correct level we need to start
        int floor = maxLevel - level; 
        
        // the correct number of spaces needed for the next level, when the new level added
        int edgeLines = (int) Math.pow(2, (Math.max(floor - 1, 0)));
        // the correct number of spaces needed from the leftmost
        int firstSpaces = (int) Math.pow(2, (floor)) - 1;  
        // the correct number of spaces needed between the nodes
        int betweenSpaces = (int) Math.pow(2, (floor + 1)) - 1;
        
        //start to print the nodes level
        BTreePrinter.printWhitespaces(firstSpaces);
        
        //get the next level nodes and at the same time print the nodes at the current level
        List<TreeNode> newNodes = new ArrayList<TreeNode>();
        for (TreeNode node : nodes) {
            if (node != null) {
                System.out.print(node.val);
                newNodes.add(node.left);
                newNodes.add(node.right);
            } else {
            	//make sure add the null to give space
                newNodes.add(null);
                newNodes.add(null);
                System.out.print(" ");
            }
            BTreePrinter.printWhitespaces(betweenSpaces);
        }
        System.out.println("");
        
        //start to print the edge level
        for (int i = 1; i <= edgeLines; i++) {
        	//print the current nodes size of the edges
            for (int j = 0; j < nodes.size(); j++) {
            	//save i spaces before the nodes to print the edges
                BTreePrinter.printWhitespaces(firstSpaces - i);
                // if the current node is null, we do not need to continue with this branch
                // the white spaces still need to be printed for the next node at the same level
                if (nodes.get(j) == null) {
                    BTreePrinter.printWhitespaces(edgeLines + edgeLines + i + 1);
                    continue;
                }
                // if the left child exists, print the left edges
                if (nodes.get(j).left != null)
                    System.out.print("/");
                else
                    BTreePrinter.printWhitespaces(1);
                //print the white spaces between the left edge and right edge 
                BTreePrinter.printWhitespaces(i + i - 1);
             
                // if the left child exists, print the left edges
                if (nodes.get(j).right != null)
                    System.out.print("\\");
                else
                    BTreePrinter.printWhitespaces(1);

                BTreePrinter.printWhitespaces(edgeLines + edgeLines - i);
            }

            System.out.println("");
        }
        //traverse to the next level
        printNodeInternal(newNodes, level + 1, maxLevel);
    }

    private static void printWhitespaces(int count) {
        for (int i = 0; i < count; i++)
            System.out.print(" ");
    }

    private static int maxLevel(TreeNode node) {
        if (node == null)
            return 0;
        return Math.max(BTreePrinter.maxLevel(node.left), BTreePrinter.maxLevel(node.right)) + 1;
    }

    @SuppressWarnings("rawtypes")
	private static boolean isAllElementsNull(List list) {
        for (Object object : list) {
            if (object != null)
                return false;
        }
        return true;
    }
	public static void main(String[] args){
		TreeNode root = new TreeNode(1);
		root.left = new TreeNode(2);
		root.right = new TreeNode(3);
		root.left.left = new TreeNode(4);
		root.left.right = new TreeNode(5);
		root.right.right = new TreeNode(6);
		root.right.right.left = new TreeNode(7);
		printNode(root);
	}
}
