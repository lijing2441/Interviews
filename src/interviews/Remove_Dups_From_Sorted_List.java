package interviews;

public class Remove_Dups_From_Sorted_List {
	/**
	 * Given a sorted linked list, delete all duplicates such that each element
	 * appear only once.
	 * 
	 * For example, Given 1->1->2, return 1->2. Given 1->1->2->3->3, return
	 * 1->2->3.
	 */
	public ListNode deleteDuplicates(ListNode head) {
		if (head == null)
			return null;
		ListNode cur = head;
		while (cur.next != null) {
			if (cur.next.val == cur.val) {
				cur.next = cur.next.next;
			} else {
				cur = cur.next;
			}
		}
		return head;
	}

	/**
	 * Problem II:
	 * 
	 * Given a sorted linked list, delete all nodes that have duplicate numbers,
	 * leaving only distinct numbers from the original list.
	 * 
	 * For example, Given 1->2->3->3->4->4->5, return 1->2->5. Given
	 * 1->1->1->2->3, return 2->3.
	 */
	public class Solution {
	    public ListNode deleteDuplicates(ListNode head) {
	        if(head == null || head.next == null) return head;
	        ListNode dummy = new ListNode(0);
	        dummy.next = head;
	        ListNode pre = dummy;
	        ListNode cur = head;
	        while(cur != null && cur.next != null){
	            if(cur.val == cur.next.val){
	                ListNode pilot = cur.next;
	                while(pilot != null && pilot.val == cur.val){
	                    pilot = pilot.next;
	                }
	                pre.next = pilot;
	            }else{
	                pre = pre.next;
	            }
	            cur = pre.next;
	        }
	        return dummy.next;
	    }
	}
}
