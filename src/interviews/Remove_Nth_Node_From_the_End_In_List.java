package interviews;

public class Remove_Nth_Node_From_the_End_In_List {
	/**
	 * Given linked list: 1->2->3->4->5->null, and n = 2.
	 * 
	 * After removing the second node from the end, the linked list becomes
	 * 1->2->3->5->null. Note The minimum number of nodes in list is n.
	 * 
	 * Challenge O(n) time
	 */
	/**
	 * @param head
	 *            : The first node of linked list.
	 * @param n
	 *            : An integer.
	 * @return: The head of linked list.
	 */
	ListNode removeNthFromEnd(ListNode head, int n) {
		if (head == null)
			return null;
		ListNode dummy = new ListNode(0);
		dummy.next = head;
		ListNode fast = head, slow = head;
		for (int i = 0; i < n && fast != null; i++) {
			fast = fast.next;
		}
		ListNode pre = dummy;
		while (fast != null) {
			fast = fast.next;
			pre = slow;
			slow = slow.next;
		}
		if (slow != null) {
			pre.next = slow.next;
		}
		return dummy.next;
	}
}
