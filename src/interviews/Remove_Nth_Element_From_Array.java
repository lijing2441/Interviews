package interviews;

public class Remove_Nth_Element_From_Array {
	/**
	 * Remove Nth Node From End of List
	 */
	public class Solution {
	    public ListNode removeNthFromEnd(ListNode head, int n) {
	        if(head == null) return null;
	        ListNode dummy = new ListNode(0);
	        dummy.next = head;
	        ListNode fast = dummy;
	        ListNode slow = dummy;
	        
	        while(n > 0){
	            fast = fast.next;
	            n--;
	        }
	        while(fast != null && fast.next != null){
	            fast = fast.next;
	            slow = slow.next;
	        }
	        slow.next = slow.next.next;
	        return dummy.next;
	    }
	}
	/**
	 * Remove element with value of elem;
	 */
	public int removeElement(int[] A, int elem) {
		if (A == null || A.length == 0)
			return 0;
		int cur = 0;
		for (int i = 0; i < A.length; i++) {
			if (A[i] != elem)
				A[cur++] = A[i];
		}
		return cur;
	}
}
