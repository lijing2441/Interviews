package interviews;

public class Swap_Nodes_In_Pair {
	/**
	 * Given a linked list, swap every two adjacent nodes and return its head.
	 * 
	 * For example, Given 1->2->3->4, you should return the list as 2->1->4->3.
	 * 
	 * Your algorithm should use only constant space. You may not modify the
	 * values in the list, only nodes itself can be changed.
	 */
	public ListNode swapPairs(ListNode head) {
		if (head == null || head.next == null)
			return head;
		ListNode dummy = new ListNode(0);
		dummy.next = head;

		for (ListNode cur = dummy; cur.next != null && cur.next.next != null; cur = cur.next.next) {
			cur.next = swap(cur.next, cur.next.next);
		}
		return dummy.next;
	}

	public ListNode swap(ListNode n1, ListNode n2) {
		n1.next = n2.next;
		n2.next = n1;
		return n2;
	}

	// another method
	public ListNode swapPairs2(ListNode head) {
		if (head == null || head.next == null)
			return head;
		ListNode dummy = new ListNode(0);
		dummy.next = head;
		ListNode cur = head;
		ListNode prev = dummy;
		while (cur != null && cur.next != null) {
			ListNode tmp = cur.next;
			cur.next = cur.next.next;
			tmp.next = cur;
			prev.next = tmp;

			prev = cur;
			cur = cur.next;
		}
		return dummy.next;
	}
}
