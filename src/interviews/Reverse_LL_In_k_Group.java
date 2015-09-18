package interviews;

public class Reverse_LL_In_k_Group {
	/**
	 * Given a linked list, reverse the nodes of a linked list k at a time and
	 * return its modified list.
	 * 
	 * If the number of nodes is not a multiple of k then left-out nodes in the
	 * end should remain as it is.
	 * 
	 * You may not alter the values in the nodes, only nodes itself may be
	 * changed.
	 * 
	 * Only constant memory is allowed.
	 * 
	 * For example, Given this linked list: 1->2->3->4->5
	 * 
	 * For k = 2, you should return: 2->1->4->3->5
	 * 
	 * For k = 3, you should return: 3->2->1->4->5
	 * 
	 * @logic keep track of a pilot node
	 */
	public static ListNode reverseKGroup(ListNode head, int k) {
		if (head == null || head.next == null || k <= 1)
			return head;
		ListNode dummy = new ListNode(0);
		dummy.next = head;
		ListNode pre = dummy;
		ListNode cur = head;
		while (cur != null) {
			ListNode pilot = cur;
			int remaining = k;
			while (pilot != null && remaining > 0) {
				remaining--;
				pilot = pilot.next;
			}
			if (remaining > 0)
				break;
			// reverse
			while (cur.next != pilot) {
				ListNode tmp = cur.next.next;
				cur.next.next = pre.next;
				pre.next = cur.next;
				cur.next = tmp;
			}
			pre = cur;
			cur = cur.next;
		}
		return dummy.next;
	}

	/**
	 * Medalia: Reverse a singly-linkedlist in blocks of k in place. An
	 * iterative approach is preferred.
	 * 
	 * The first block of theresulting list should be maximal with regards to k.
	 * If the list containsn elements, the last block will either be full
	 * (containing k elements) orcontain n mod k elements.
	 * 
	 * For example:
	 * 
	 * For k = 2 and input list= [1, 2, 3, 4, 5, 6, 7, 8, 9], then the resulting
	 * blocks are (8, 9), (6, 7),(4, 5), (2, 3) and (1), so the actual reversed
	 * list is [8, 9, 6, 7, 4, 5, 2, 3,1].
	 * 
	 * For k = 3 and input list = [1, 2, 3, 4, 5, 6, 7, 8, 9], then the
	 * resulting blocks are (7, 8, 9), (4, 5, 6) and (1, 2, 3), so the actual
	 * reversed list is [7, 8, 9, 4, 5, 6,1, 2, 3].
	 */
	public static ListNode reverseKGroup2(ListNode head, int k) {
		if (head == null || head.next == null || k <= 1) return head;
		
		// reverse all of them first
		ListNode reverseHead = reverse(head);
		
		// reverse the group correspondingly like before
		return reverseKGroup(reverseHead, k);
	}
	
	public static ListNode reverse(ListNode head) {
		ListNode cur = head;
		ListNode pre = null;
		ListNode next = null;
		while (cur != null) {
			next = cur.next;
			cur.next = pre;
			pre = cur;
			cur = next;
		}
		return pre;
	}

	public static void main(String[] args) {
		ListNode node1 = new ListNode(1);
		ListNode node2 = new ListNode(2);
		ListNode node3 = new ListNode(3);
		ListNode node4 = new ListNode(4);
		ListNode node5 = new ListNode(5);
		ListNode node6 = new ListNode(6);
		ListNode node7 = new ListNode(7);
		ListNode node8 = new ListNode(8);
		ListNode node9 = new ListNode(9);
		node1.next = node2;
		node2.next = node3;
		node3.next = node4;
		node4.next = node5;
		node5.next = node6;
		node6.next = node7;
		node7.next = node8;
		node8.next = node9;
		ListNode node = reverseKGroup2(node1, 3);
		while (node != null) {
			System.out.print(node.val + "->");
			node = node.next;
		}
	}
}
