package interviews;

public class Remove_LL_Elements {
	/**
	 * Remove all elements from a linked list of integers that have value val.
	 * 
	 * Given: 1 --> 2 --> 6 --> 3 --> 4 --> 5 --> 6, val = 6 
	 * Return: 1 --> 2 --> 3 --> 4 --> 5
	 * 
	 */
	public ListNode removeElements(ListNode head, int val) {
        if(head == null) return head;
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode cur = head;
        ListNode prev = dummy;
        while(cur != null) {
            if(cur.val == val) {
                prev.next = cur.next;
            } else {
               prev = prev.next; 
            }
            cur = cur.next;
        }
        return dummy.next;
    }
}
