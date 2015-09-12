package interviews;

public class Sort_A_List_Merge {
	// O(nlogn), O(1)
	public ListNode sortList(ListNode head) {
		int len = 0;
		// find the length of the list
		for (ListNode cur = head; cur != null; cur = cur.next) {
			len++;
		}
		if (len < 2) {
			return head;
		}
		// find the mid point of the list
		ListNode firstHead = head;
		ListNode secondHead = head;
		ListNode firstTail = head;
		for (int pos = 0; pos < (len / 2 - 1); pos++) {
			firstTail = firstTail.next;
		}
		// separate the list
		secondHead = firstTail.next;
		firstTail.next = null;
		firstHead = sortList(firstHead);
		secondHead = sortList(secondHead);
		ListNode res = merge(firstHead, secondHead);
		return res;
	}

	public ListNode merge(ListNode firstHead, ListNode secondHead) {
		ListNode tail = null;
		ListNode head = null;
		while (firstHead != null && secondHead != null) {
			if (head == null) {
				if (firstHead.val <= secondHead.val) {
					tail = firstHead;
					head = firstHead;
					firstHead = firstHead.next;
				} else {
					head = secondHead;
					tail = secondHead;
					secondHead = secondHead.next;
				}
			} else {
				if (firstHead.val <= secondHead.val) {
					tail.next = firstHead;
					firstHead = firstHead.next;
				} else {
					tail.next = secondHead;
					secondHead = secondHead.next;
				}
				tail = tail.next;
			}
		}
		if (firstHead == null) {
			tail.next = secondHead;
		} else if (secondHead == null) {
			tail.next = firstHead;
		}

		return head;
	}
}
