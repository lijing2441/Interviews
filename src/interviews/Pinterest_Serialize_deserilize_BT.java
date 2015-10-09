package interviews;
import java.util.*;

public class Pinterest_Serialize_deserilize_BT {
	/*
	 * To execute Java, please define "static void main" on a class
	 * named Solution.
	 *
	 * If you need more classes, simply define them inline.
	 */

	  public static void main(String[] args) {
	    //ArrayList<String> strings = new ArrayList<String>();
	    //strings.add("Hello, World!");
	    //strings.add("Welcome to CoderPad.");
	    //strings.add("This pad is running Java 8.");
	    
	    TreeNode root = new TreeNode(1);
	    // 1 2 4 # # # 3 6 # # 7 # # 
	  }
	  
	  public static String serializeTree(TreeNode root) {
	    StringBuilder sb = new StringBuilder();
	    serialize(root, sb);
	    return sb.toString();
	  }
	  
	  public static void serialize(TreeNode root, StringBuilder sb) {
	    if (null == root) sb.append("# ");
	    //String res = root.val;
	    //Queue<TreeNode> q = new LinkedList<TreeNode>();
	    //q.offer(root);
	    //while (!q.isEmpty()) {
	      //TreeNode node = q.poll();
	      //res +
	    //}
	    sb.append(root.val + " ");
	    serialize(root.left, sb);
	    serialize(root.right, sb);
	    //return res + "#" left + "#" + right;
	    
	    
	    // 1 2 # # 3#
	    //      1
	    //    2  3
	    //   4  6 7
	  }
	  
	  public static TreeNode deserializeTree(String input) {
	    if (input == null || input.length() == 0) return null;
	    StringTokenizer st = new StringTokenizer(input, " ");
	    return deserializeTokenizer(st);
	  }
	  public static TreeNode deserializeTokenizer(StringTokenizer st) {
	    if (!st.hasMoreTokens()) {
	      return null;
	    }
	    String val = st.nextToken();
	    if (val.equals("#")) return null;
	    
	    TreeNode root = new TreeNode(Integer.parseInt(val));
	    root.left = deserializeTokenizer(st);
	    root.right = deserializeTokenizer(st);
	    return root;
	  }
	  
}
