package interviews;

import java.util.List;

public class Merge_K_Sorted_Lists {

	// recursive method: O(logK * n) K - # of lists; n - # of elements in all lists
	public ListNode mergeKLists(List<ListNode> lists) {
		int k = lists.size();
		if (k == 0)
			return null;
		if (k == 1)
			return lists.get(0);
		if (k == 2)
			return merge2Lists(lists.get(0), lists.get(1));
		else {
			return merge2Lists(mergeKLists(lists.subList(0, k / 2)),
					mergeKLists(lists.subList(k / 2, k)));
		}
	}

	public ListNode merge2Lists(ListNode l1, ListNode l2) {
		ListNode dummy = new ListNode(0);
		ListNode node = dummy;
		while (l1 != null && l2 != null) {
			if (l1.val < l2.val) {
				node.next = l1;
				l1 = l1.next;
			} else {
				node.next = l2;
				l2 = l2.next;
			}
			node = node.next;
		}
		if (l1 != null) {
			node.next = l1;
		}
		if (l2 != null) {
			node.next = l2;
		}
		return dummy.next;
	}

	// iterative way, recursive method: O(nK) K - # of lists; n - # of elements in all lists
	public ListNode mergeKListsIterative(List<ListNode> lists) {
		if (lists == null)
			return null;
		if (lists.isEmpty())
			return null;
		ListNode head = new ListNode(0);
		ListNode tail = head;
		while (lists.size() > 0) {
			int toRemove = -1;
			int emptyCount = 0;
			for (int i = 0; i < lists.size(); i++) {
				if (lists.get(i) != null) {
					if (toRemove == -1)
						toRemove = i;
					else if (lists.get(i).val < lists.get(toRemove).val) {
						toRemove = i;
					}
				} else {
					emptyCount++;
				}
			}
			if (emptyCount == lists.size())
				break;
			ListNode toDelete = lists.remove(toRemove);
			tail.next = toDelete;
			tail = tail.next;
			lists.add(toDelete.next);
		}
		tail.next = null;
		return head.next;
	}
}
