package interviews;

public class Reverse_LL_Between_m_and_n {
	/**
	 * Reverse a linked list from position m to n. 
	 * Do it in-place and in one-pass.
	 * 
	 * For example: Given 1->2->3->4->5->NULL, m = 2 and n = 4,
	 * return 1->4->3->2->5->NULL.
	 * 
	 * Note: Given m, n satisfy the following condition: 1 ≤ m ≤ n ≤ length of list.
	 */
	public ListNode reverseBetween(ListNode head, int m, int n) {
		if (head == null)
			return null;
		if (m == n)
			return head;
		ListNode dummy = new ListNode(0);
		dummy.next = head;
		ListNode node = dummy;
		for (int i = 1; i < m; i++) {
			node = node.next;
		}
		ListNode p = node.next;
		for (int i = m; i < n; i++) {
			ListNode tmp = p.next.next;
			p.next.next = node.next;
			node.next = p.next;
			p.next = tmp;
		}
		return dummy.next;
	}
}
