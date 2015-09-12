package interviews;

public class Reorder_List {
	/**
	 * Given a singly linked list L: L0→L1→…→Ln-1→Ln, reorder it to:
	 * L0→Ln→L1→Ln-1→L2→Ln-2→…
	 * 
	 * You must do this in-place without altering the nodes' values.
	 * 
	 * For example, Given {1,2,3,4}, reorder it to {1,4,2,3}.
	 * 
	 */
	// first find the second half
	// reverse the second half
	// insert the second half nodes into the first half one by one
	// O(n), O(1)
	public static void reorderList(ListNode head) {
		if (head == null || head.next == null || head.next.next == null)
			return;
		ListNode slow = head;
		ListNode fast = head;

		while (fast != null && fast.next != null) {
			slow = slow.next;
			fast = fast.next.next;
		}

		ListNode reverseHead = slow.next; // the last two do not need to be re-ordered
		slow.next = null; // make the first half tail to null

		reverseHead = reverseList(reverseHead);
		ListNode cur = head;
		while (reverseHead != null) {
			ListNode tmp = reverseHead.next;
			reverseHead.next = cur.next;
			cur.next = reverseHead;
			cur = cur.next.next;
			reverseHead = tmp;
		}
	}

	public static ListNode reverseList(ListNode head) {
		if (head == null || head.next == null)
			return head;
		ListNode cur = head;
		ListNode prev = null;
		ListNode next = null;
		while (cur != null) {
			next = cur.next;
			cur.next = prev;
			prev = cur;
			cur = next;
		}
		return prev;
	}
	
	//driver method
	public static void main(String[] args) {
		ListNode n = new ListNode(1);
		n.next = new ListNode(2);
		n.next.next = new ListNode(3);
		n.next.next.next = new ListNode(4);
		reorderList(n);
		while (n != null) {
			System.out.print(n.val + " ");
			n = n.next;
		}
	}
}
