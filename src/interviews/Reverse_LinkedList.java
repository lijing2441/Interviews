package interviews;

public class Reverse_LinkedList {
	// recursive method
	public static ListNode rev_LL(ListNode head) {
		if (head == null || head.next == null)
			return head;
		ListNode rev_head = rev_LL(head.next);
		head.next.next = head;
		head.next = null;
		return rev_head;
	}

	// iterative way
	// O(n), O(1)
	public static ListNode rev_LL_ite(ListNode head) {
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

	// reversely print a LL, O(n) time and O(1) space
	public static void rev_LL_print2(ListNode head) {
		ListNode revHead = rev_LL(head);
		ListNode node = revHead;
		while (node != null) {
			System.out.print(node.val + " ");
			node = node.next;
		}
		System.out.println();
		// reverse it back to the original list
		ListNode toReturn = rev_LL(revHead);
		// return toReturn;

		ListNode n = toReturn;
		while (n != null) {
			System.out.print(n.val + " ");
			n = n.next;
		}
		System.out.println();
	}

	// reversely print a LL, O(n) and O(n)
	public static void rev_LL_print(ListNode head) {
		if (head != null) {
			rev_LL_print(head.next);
			System.out.print(head.val + " ");
		}
	}
	
	// driver method
	public static void main(String[] args) {
		ListNode head = new ListNode(1);
		head.next = new ListNode(2);
		head.next.next = new ListNode(3);
		head.next.next.next = new ListNode(4);
		rev_LL_print2(head);
	}
}
