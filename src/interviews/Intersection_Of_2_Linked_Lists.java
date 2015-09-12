package interviews;

public class Intersection_Of_2_Linked_Lists {
	/**
	 * Write a program to find the node at which the intersection of two singly linked lists begins.
	 */
	public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        int lenA = len(headA);
        int lenB = len(headB);
        while(lenA > lenB){
            headA = headA.next;
            lenA--;
        }
        while(lenB > lenA){
            headB = headB.next;
            lenB--;
        }
        while(headA != headB){
            headA = headA.next;
            headB = headB.next;
        }
        return headA;
    }
    public int len(ListNode head){
        int l = 0;
        while(head != null){
            head = head.next;
            l++;
        }
        return l;
    }
}
