package interviews;

import java.util.Stack;

public class Construct_BT_From_Preorder_and_Inorder {
	/**
	 * 中序和前序遍历来找树, if inorder is not given and we are building a BST, 
	 * 					 we can sort the preorder and get the inorder
	 * 
	 * @idea 
	 * Algorithm: buildTree()
	 * 1) Pick an element from Preorder. Increment a Preorder Index Variable to 
	 * 	  pick next element in next recursive call.
	 * 2) Create a new tree node tNode with the data as picked element.
	 * 3) Find the picked element’s index in Inorder. Let the index be inIndex.
	 * 4) Call buildTree for elements before inIndex and make the built tree as left subtree of tNode.
	 * 5) Call buildTree for elements after inIndex and make the built tree as right subtree of tNode.
	 * 6) return tNode.
	 * 
	 * @complexity: Time Complexity: O(n^2). 
	 * Worst case occurs when tree is totally skewed. 
	 * Example Preorder and Inorder traversals for worst case are {A, B, C, D} and {D, C, B, A}.
	 */
	public TreeNode buildTreePI(int[] preorder, int[] inorder) {
        return helper1(preorder, 0, preorder.length - 1, inorder, 0, inorder.length - 1);
    }
    public TreeNode helper1(int[] preorder, int preStart, int preEnd, int[] inorder, int inStart, int inEnd){
        if(preStart > preEnd || inStart > inEnd) return null;
        // use the preorder to get the root index
        TreeNode root = new TreeNode(preorder[preStart]);
        int mid = 0;
        // find the position of root in the inorder traversal
        for(int i = inStart; i <= inEnd; i++){
            if(inorder[i] == root.val){
                mid = i;
                break;
            }
        }
        // get the number of nodes in the right subtree
        int right = inEnd - mid;
        // the first half subtree => left subtree
        root.left = helper(preorder, preStart + 1, preEnd - right, inorder, inStart, mid - 1);
        // the second half subtree => right subtree
        root.right = helper(preorder, preEnd - right + 1, preEnd, inorder, mid + 1, inEnd);
        return root;
    }
    
    // Iterative method
	public TreeNode buildTreePIIte(int[] preorder, int[] inorder) {
        if(preorder.length == 0 || preorder.length != inorder.length) return null;
        int preorderIndex = 0;
        int inorderIndex = 0;
        Stack<TreeNode> stack = new Stack<TreeNode>();
        TreeNode root = new TreeNode(preorder[preorderIndex++]);
        stack.push(root);
        TreeNode node = null;
        while(!stack.isEmpty()){
            if(inorder[inorderIndex] == stack.peek().val){
                inorderIndex++;
                if(inorderIndex >= inorder.length) break;
                node = stack.pop();
                if(!stack.isEmpty() && inorder[inorderIndex] == stack.peek().val) continue;
                node.right = new TreeNode(preorder[preorderIndex]);
                stack.push(node.right);
            }else{
                node = new TreeNode(preorder[preorderIndex]);
                stack.peek().left = node;
                stack.push(node);
            }
            preorderIndex++;
        }
        return root;
	}
	
	/**
	 * 中序和后序遍历 来找树
	 * Given inorder and postorder traversal of a tree, construct the binary tree.
	 */
	public TreeNode buildTreeIP(int[] inorder, int[] postorder) {
        return helper(inorder, 0, inorder.length - 1, postorder, 0, postorder.length - 1);
    }
    public TreeNode helper(int[] inorder, int inStart, int inEnd, int[] postorder, int postStart, int postEnd){
        if(inStart > inEnd || postStart > postEnd) return null;
        // use the index of postorder to find the root index
        TreeNode root = new TreeNode(postorder[postEnd]);
        int inMid = 0;
        // find the position of the root index in the inorder traversal
        for(int i = inStart; i <= inEnd; i++){
            if(inorder[i] == root.val){
                inMid = i;
                break;
            }
        }
        // get the number of nodes in the left subtree
        int left = inMid - inStart;
        root.left = helper(inorder, inStart, inMid - 1, postorder, postStart, postStart + left - 1);
        root.right = helper(inorder, inMid + 1, inEnd, postorder, postStart + left, postEnd - 1);
        return root;
    }
    
    //Iterative
    public TreeNode buildTreeIte(int[] inorder, int[] postorder) {
        if(inorder == null || inorder.length == 0 || inorder.length != postorder.length) return null;
        int postIndex = postorder.length - 1;
        int inIndex = inorder.length - 1;
        Stack<TreeNode> stack = new Stack<TreeNode>();
        TreeNode root = new TreeNode(postorder[postIndex--]);
        TreeNode node = null;
        stack.push(root);
        while(true){
            if(inorder[inIndex] == stack.peek().val){
                inIndex--;
                if(inIndex < 0) break;
                node = stack.pop();
                if(!stack.isEmpty() && inorder[inIndex] == stack.peek().val) continue;
                node.left = new TreeNode(postorder[postIndex]);
                stack.push(node.left);
            }else{
                node = new TreeNode(postorder[postIndex]);
                stack.peek().right = node;
                stack.push(node);
            }
            postIndex--;
        }
        return root;
    }
}
