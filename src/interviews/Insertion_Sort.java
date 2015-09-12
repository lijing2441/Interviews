package interviews;

//  in iteration i, swap a[i] with each larger entry to its left
//  worst case, reverse ordered, n^2 / 2 comparisons and swaps
//  best case, sorted, N - 1 comparisons and 0 swap
public class Insertion_Sort {
	public void insertionSort(int[] a) {
		for (int i = 1; i < a.length; i++) {
			int tmp = a[i];
			int j = Integer.MIN_VALUE;
			for (j = i - 1; j >= 0 && a[j] > tmp; j--) {
				a[j + 1] = a[j];
			}
			a[j + 1] = tmp;
		}
	}

	// insertion sort a LinkedList
	public ListNode insertionSortList(ListNode head) {
		if (head == null || head.next == null)
			return head;
		ListNode dummy = new ListNode(Integer.MIN_VALUE);
		ListNode pre = dummy;
		ListNode cur = head;
		while (cur != null) {
			pre = dummy;
			while (pre.next != null && pre.next.val < cur.val) {
				pre = pre.next;
			}
			// find a mis-rank, insert the cur node into pre.next pos
			ListNode next = cur.next;
			cur.next = pre.next;
			pre.next = cur;
			cur = next;
		}
		return dummy.next;
	}
}
