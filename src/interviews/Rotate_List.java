package interviews;

public class Rotate_List {
	/**
	 * Given a list, rotate the list to the right by k places, where k is
	 * non-negative.
	 * 
	 * For example: Given 1->2->3->4->5->NULL and k = 2, return
	 * 4->5->1->2->3->NULL.
	 * 
	 * @analysis use two pointers
	 */
	public ListNode rotateRight(ListNode head, int n) {
        if(head == null) return null;
        int len = 0;
        ListNode node = head;
        // find the length of the list
        while(node != null){
            node = node.next;
            len++;
        }
        //find the length we need to rotate
        int k = n % len;
        if(k == 0) return head;
        ListNode fast = head;
        ListNode slow = head;
        // find the position of the start of the new list
        for(int i = 0; i < k; i++){
            fast = fast.next;
        }
        while(fast.next != null){
            fast = fast.next;
            slow = slow.next;
        }
        // make the new list
        ListNode newHead = slow.next;
        slow.next = null;
        fast.next = head;
        return newHead;
    }
}
