package interviews;

import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

public class Merge_K_Sorted_Lists {

	// recursive method: O(nklogK) K - # of lists; n - # of elements in one of the lists
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
	// Merge k lists through iteration
	// iterative way, recursive method: O(nK^2) K - # of lists; n - # of elements in one of the lists
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
				// if necessary, using iterator here, such as Iterator<Integer> it = lists.iterator();
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
	// PriorityQueue method, O(nkLogK)
	/**
	 * 1. Create an output array of size n*k (or n if n is the total number of all elements in the lists).
	 * 2. Create a min heap of size k and insert 1st element in all the arrays into the heap
	 * 3. Repeat following steps n*k times.
	 * a) Get minimum element from heap (minimum is always at root) and store it in output array.
	 * b) Replace heap root with next element from the array from which the element is extracted. 
	 *    If the array doesn’t have any more elements, then replace root with infinite. 
	 *    After replacing the root, heapify the tree.
	 */
	public ListNode mergeKListsPQ(List<ListNode> lists) {  
        // write your code here
        if (lists == null || lists.size() == 0) return null;
        PriorityQueue<ListNode> pq = new PriorityQueue<ListNode>(lists.size(), new Comparator<ListNode>() {
            public int compare(ListNode node1, ListNode node2) {
                return node1.val - node2.val;
            }
        });
        for (ListNode node : lists) {
            if (node != null) pq.offer(node); // in case of the null node in the list
        }
        ListNode newHead = null, newPtr = null;
        while (!pq.isEmpty()) {
            ListNode cur = pq.poll();
            if (cur.next != null) {
                pq.offer(cur.next);
            }
            if (newHead == null) {
                newHead = cur;
                newPtr = cur;
            } else {
                newPtr.next = cur;
                newPtr = newPtr.next;
            }
        }
        return newHead;
    }
}
