package interviews;

public class Morris_Traversal {
	//O(1) space and O(n) time
	/**
	 * 直觉上，认为它的复杂度是O(nlgn)，因为找单个节点的前驱节点与树的高度有关。
	 * 但事实上，寻找所有节点的前驱节点只需要O(n)时间。n个节点的二叉树中一共有n-1条边，
	 * 整个过程中每条边最多只走2次，一次是为了定位到某个节点，另一次是为了寻找上面某个节点的前驱节点，
	 * 如下图所示，其中红色是为了定位到某个节点，黑色线是为了找到前驱节点。所以复杂度为O(n)。
	 */
	
	public void inorderMorrisTraversal(TreeNode root) {
	    TreeNode cur = root;
	    TreeNode prev = null;
	    while (cur != null){
	        if (cur.left == null){          // 1.
	            System.out.println(cur.val);
	            cur = cur.right;
	        }
	        else{
	            // find predecessor
	            prev = cur.left;
	            while (prev.right != null && prev.right != cur)
	                prev = prev.right;

	            if (prev.right == null){   // 2.a)
	                prev.right = cur;
	                cur = cur.left;
	            }else{                      // 2.b)
	                prev.right = null;
	                System.out.println(cur.val);
	                cur = cur.right;
	            }
	        }
	    }
	}
	public void preorderMorrisTraversal(TreeNode root) {
	    TreeNode cur = root;
	    TreeNode prev = null;
	    while (cur != null){
	        if (cur.left == null){
	        	System.out.println(cur.val);
	            cur = cur.right;
	        }else{
	            prev = cur.left;
	            while (prev.right != null && prev.right != cur)
	                prev = prev.right;

	            if (prev.right == null){
	            	System.out.println(cur.val); // the only difference with inorder-traversal
	                prev.right = cur;
	                cur = cur.left;
	            }else{
	                prev.right = null;
	                cur = cur.right;
	            }
	        }
	    }
	}
}
