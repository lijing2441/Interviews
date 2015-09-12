package interviews;

public class Populate_Next_Right_Pointers_In_Each_Node {
	/**
	 * Given a binary tree.
	 * Populate each next pointer to point to its next right node. 
	 * If there is no next right node, the next pointer should be set to NULL.
	 * 
	 * Initially, all next pointers are set to NULL.
	 * 
	 * You may only use constant extra space.
	 * You may assume that it is a perfect binary tree 
	 * 
	 * @analysis using DFS, 当前层处理完next指针的连接以后，再调用下一级节点。
	 */
	public void connect(TreeLinkNode root) {
        if(root == null) return;
        TreeLinkNode pre = root;
        TreeLinkNode cur = root;
        while(pre.left != null){
            cur = pre;
            while(cur != null){
                cur.left.next = cur.right;
                if(cur.next != null){
                    cur.right.next = cur.next.left;
                }
                cur = cur.next;
            }
            pre = pre.left;
        }
    }
	
	// recursive way
	public static void connectRe(TreeLinkNode root) {
		// 空节点就直接返回
		if (root == null) {
			return;
		}
		// 左节点非空，连接右节点
		if (root.left != null) {
			root.left.next = root.right;
		}
		// 借助root.next处理5连6的情况
		if (root.right != null && root.next != null) {
			root.right.next = root.next.left;
		}
		connectRe(root.left);
		connectRe(root.right);
    }  
	/**
	 * Problem II:
	 * 
	 * What if the given tree could be any binary tree? Would your previous solution still work?
	 */
	public void connectII(TreeLinkNode root) {
        if(root == null) return;
        TreeLinkNode cur = root;
        TreeLinkNode nextHead = null;
        TreeLinkNode nextLeading = null;
        while(cur != null){
            while(cur != null){
                if(cur.left != null){
                    if(nextLeading == null){
                        nextHead = cur.left;
                    }else{
                        nextLeading.next = cur.left;
                    }
                    nextLeading = cur.left;
                }
                if(cur.right != null){
                    if(nextLeading == null){
                        nextHead = cur.right;
                    }else{
                        nextLeading.next = cur.right;
                    }
                    nextLeading = cur.right;
                }
                cur = cur.next;
            }
            cur = nextHead;
            nextHead = null;
            nextLeading = null;
        }
    }
}
class TreeLinkNode {
    TreeLinkNode left;
    TreeLinkNode right;
    TreeLinkNode next;
}

