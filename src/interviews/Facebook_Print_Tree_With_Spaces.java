package interviews;

import java.util.ArrayList;
import java.util.List;

public class Facebook_Print_Tree_With_Spaces {
	public static List<String> binaryTreePaths(TreeNode root) {
        List<String> res = new ArrayList<String>();
        if(root == null) return res;
        helper(res, root, "", new ArrayList<Integer>(), 0, 0);
        return res;
    }
    public static void helper(List<String> res, TreeNode root, String cur, List<Integer> indent, int curIndent, int depth) {
        String next = cur + Integer.toString(root.val);
        if (indent.size() > depth) {
        	indent.set(depth, curIndent);
        } else {
        	indent.add(curIndent);
        }
        
        if(root.left == null && root.right == null) {
        	int min = Integer.MAX_VALUE;
            for (int i = 0; i < next.length(); i++) {
            	min = Math.min(min, indent.get(i));
            }
            for (int i = 0; i < next.length(); i++) {
            	if (indent.get(i) > min) {
            		int spaces = indent.get(i) - min;
            		for (int j = 0; j < spaces; j++) {
            			System.out.print(" ");
            		}
            	}
            	System.out.println(next.charAt(i));
            }
            
        } else {
            if(root.left != null) helper(res, root.left, next, indent, curIndent - 1, depth + 1);
            if(root.right != null) helper(res, root.right, next, indent, curIndent + 1, depth + 1);
        }
    }
    public static void main(String[] args) {
    	TreeNode root = new TreeNode(1);
    	root.left = new TreeNode(2);
    	root.right = new TreeNode(3);
    	root.left.left = new TreeNode(4);
    	root.right.left = new TreeNode(5);
    	root.right.right = new TreeNode(6);
    	for (String s : binaryTreePaths(root)) {
    		System.out.println(s);
    	}
    }
}
