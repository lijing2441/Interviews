package interviews;
import java.util.*;

public class Serialize_Deserilize_BT {
	/**
	 * An example of test data: Binary tree {3,9,20,#,#,15,7}, denote the following structure:

  		3
 	   / \
	  9  20
  		/  \
 	   15   7
 	   
	 */
	/**
     * This method will be invoked first, you should design your own algorithm 
     * to serialize a binary tree which denote by a root node to a string which
     * can be easily deserialized by your own "deserialize" method later.
     */
    public String serialize(TreeNode root) {
        StringBuilder sb = new StringBuilder();
        serialize(root, sb);
        return sb.toString();
    }
    public void serialize(TreeNode root, StringBuilder sb) {
        if (null == root) sb.append("# ");
        else {
            sb.append(root.val + " ");
            serialize(root.left, sb);
            serialize(root.right, sb);
        }
    }
    
    /**
     * This method will be invoked second, the argument data is what exactly
     * you serialized at method "serialize", that means the data is not given by
     * system, it's given by your own serialize method. So the format of data is
     * designed by yourself, and deserialize it here as you serialize it in 
     * "serialize" method.
     */
    public TreeNode deserialize(String data) {
        if (data == null || data.length() == 0) return null;
        StringTokenizer st = new StringTokenizer(data, " ");
        return deserialize(st);
    }
    public TreeNode deserialize(StringTokenizer st) {
        if (!st.hasMoreTokens()) {
            return null;
        }
        String val = st.nextToken();
        if (val.equals("#")) return null;
        TreeNode root = new TreeNode(Integer.parseInt(val));
        root.left = deserialize(st);
        root.right = deserialize(st);
        return root;
    }
	  
}
// in leetcode
class Codec {
    
    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        StringBuilder sb = new StringBuilder();
        serialize(root, sb);
        return sb.toString();
    }
    public void serialize(TreeNode root, StringBuilder sb) {
        if (root == null) {
            sb.append("# ");
        } else {
            sb.append(root.val + " ");
            serialize(root.left, sb);
            serialize(root.right, sb);
        }
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        if (data == null || data.length() == 0) return null;
        String[] arr = data.split(" ");
        Iterator<String> it = (Arrays.asList(arr)).iterator();
        return deserialize(it);
    }
    public TreeNode deserialize(Iterator<String> it) {
        if (!it.hasNext()) {
            return null;
        }
        String val = it.next();
        if (val.equals("#")) return null;
        TreeNode root = new TreeNode(Integer.parseInt(val));
        root.left = deserialize(it);
        root.right = deserialize(it);
        return root;
    }
}