package interviews;

public class Facebook_Insert_Circular_LL {
	public static ListNode insertIntoList(ListNode head, int val) {
		ListNode pre = head;
		// insert in the head
		if (val <= head.val) {
			while (pre.next != head) {
				pre = pre.next;
			}
			ListNode newNode = new ListNode(val);
			pre.next = newNode;
			newNode.next = head;
			return newNode;
		}
		// insert into the body other than head
		while (pre.next.val < val) {
			pre = pre.next;
			if (pre.next == head) {
				break;
			}
		}
		// either pre.next = head, or pre.next.val >= val
		ListNode tmp = pre.next;
		ListNode newNode = new ListNode(val);
		pre.next = newNode;
		newNode.next = tmp;
		return head;
	}
	
	public static void main(String[] args) {
		ListNode head = new ListNode(1);
		head.next = new ListNode(2);
		head.next.next = new ListNode(3);
		head.next.next.next = new ListNode(4);
		head.next.next.next.next = head;
		int val = 3;
		ListNode node = insertIntoList(head, val);
		for (int i = 0; i < 10; i++) {
			System.out.print(node.val + " ");
			node = node.next;
		}
	}
	
}
class CircularLinkedList {
	ListNode head;
	ListNode tail;
	public CircularLinkedList(int val) {
		
	}
}