package interviews;

public class Convert_Sorted_LinkedList_To_BT {
	// recursively build the binary search tree
	// first find the midpoint and then recursively build the two halves as left and right
	// Node: remember to null both the left side and right side next pointer
	public TreeNode sortedListToBST(ListNode head) {
        if(head == null) return null;
        if(head.next == null) return new TreeNode(head.val);
        ListNode slow = head;
        ListNode fast = head;
        ListNode pre = head;
        //find the mid point, remember to keep track of the prev node of the midpoint
        while(fast != null && fast.next != null){
            fast = fast.next.next;
            pre = slow;
            slow = slow.next;
        }
        // null the tail of the first half, left subtree
        pre.next = null;
        // this is the start of the right subtree
        ListNode right = slow.next;
        // null the next point of the root;
        slow.next = null;
        // root node
        TreeNode cur = new TreeNode(slow.val);
        // recursively get the left and right subtree
        cur.left = sortedListToBST(head);
        cur.right = sortedListToBST(right);
        return cur;
    }
}
