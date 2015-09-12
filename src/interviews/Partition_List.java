package interviews;

public class Partition_List {
	/**
	 * Given a linked list and a value x, partition it such that all nodes less
	 * than x come before nodes greater than or equal to x.
	 * 
	 * You should preserve the original relative order of the nodes in each of
	 * the two partitions.
	 * 
	 * For example, Given 1->4->3->2->5->2 and x = 3, return 1->2->2->4->3->5.
	 */
	// O(n), O(1)
	public ListNode partition(ListNode head, int x) {
		ListNode dummy1 = new ListNode(0);
		ListNode dummy2 = new ListNode(0);
		ListNode n1 = dummy1;
		ListNode n2 = dummy2;
		ListNode n = head;
		while (n != null) {
			if (n.val < x) {
				n1.next = n;
				n1 = n1.next;
			} else {
				n2.next = n;
				n2 = n2.next;
			}
			n = n.next;
		}
		n2.next = null;
		n1.next = dummy2.next;
		return dummy1.next;
	}
}
