package interviews;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class Uber_n_ary_Tree_Serialization_Deserialization {
	/**
	 * 
	 */
	public int childrenNum;
	// This function stores the given N-ary tree in a file
	void serialize(N_ary_TreeNode root, File fileToWrite) {
	    // Base case
	    if (root == null) return;
	 
	    // Else, store current node and recur for its children
	    FileWriter fw;
	    try {
	    	fw = new FileWriter(fileToWrite);
	    	fw.write(root.key);
	    	for (int i = 0; i < childrenNum && root.children.get(i) != null; i++)
		         serialize(root.children.get(i), fileToWrite);
	    } catch (IOException ex) {
	    	ex.printStackTrace();
	    }
	}
	 
	// This function constructs N-ary tree from a file pointed by 'fp'.
	// This functionr returns 0 to indicate that the next item is a valid
	// tree key. Else returns 0
	int deSerialize(N_ary_TreeNode root, File fileToRead)
	{
	    // Read next item from file. If there are no more items or next
	    // item is marker, then return 1 to indicate same
	    char val;
		try {
			val = (char) (new FileReader(fileToRead)).read();
			root = new N_ary_TreeNode(val);
		    for (int i = 0; i < childrenNum; i++)
		    	deSerialize(root.children.get(i), fileToRead);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	 
	    // Else create node with this item and recur for children
	    
	    // Finally return 0 for successful finish
	    return 0;
	}
}
class N_ary_TreeNode {
	char key;
	List<N_ary_TreeNode> children;
	public N_ary_TreeNode (char key) {
		this.key = key;
	}
}