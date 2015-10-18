package interviews;

public class Facebook_Reverse_K_nodes_every_other_K_nodes {
	/**
	 * 输入一个链表和一个整数k
	 * 要求每隔k个元素把下k个元素reverse k是任意非负数
	 * 比如 1 2 3 4 5 6 7 8 9， k=5
	 * 返回 5 4 3 2 1 6 7 8 9
	 */
	public static ListNode reverseEveryOtherKNodes(ListNode head, int k) {
		if (head == null || head.next == null || k < 2) return head;
		ListNode dummy = new ListNode(0);
		dummy.next = head;
		ListNode prev = dummy;
		ListNode cur = head;
		boolean flag = true;
		while (cur != null) {
			ListNode prePilot = null;
			ListNode pilot = cur;
			int remaining = k;
			while (pilot != null && remaining > 0) {
				prePilot = pilot;
				pilot = pilot.next;
				remaining--;
			}
			if (remaining > 0) break;
			if (flag) {
				// reverse
				while (cur.next != pilot) {
					ListNode tmp = cur.next.next;
					cur.next.next = prev.next;
					prev.next = cur.next;
					cur.next = tmp;
				}
				prev = cur;
				cur = pilot;
				flag = false;
			} else {
				prev = prePilot;
				cur = pilot;
				flag = true;
			}
		}
		return dummy.next;
	}
	
	public static void main(String[] args) {
		ListNode l1 = new ListNode(1);
		l1.next = new ListNode(2);
		l1.next.next = new ListNode(3);
		l1.next.next.next = new ListNode(4);
		l1.next.next.next.next = new ListNode(5);
		l1.next.next.next.next.next = new ListNode(6);
		l1.next.next.next.next.next.next = new ListNode(7);
		l1.next.next.next.next.next.next.next = new ListNode(8);
		l1.next.next.next.next.next.next.next.next = new ListNode(9);
		ListNode node = l1;
		while (node != null) {
			System.out.println(node.val);
			node = node.next;
		}
		ListNode res = reverseEveryOtherKNodes(l1, 5);
		while (res != null) {
			System.out.println(res.val);
			res = res.next;
		}
	}
}
