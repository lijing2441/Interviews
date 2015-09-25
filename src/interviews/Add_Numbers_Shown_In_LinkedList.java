package interviews;

public class Add_Numbers_Shown_In_LinkedList {
	/**
	 * Add two numbers each represented by a linked list. The digits are stored
	 * in reverse order, such that the 1's digit is at the head of the list.
	 * Return their sum as a linked list。
	 * 
	 * @logic Traverse both lists. One by one pick nodes of both lists and add
	 *        the values. If sum is more than 10 then make carry as 1 and reduce
	 *        sum. If one list has more elements than the other then consider
	 *        remaining values of this list as 0.
	 * 
	 * @complexity O(m + n), where m and n are number of nodes in first and
	 *             second lists respectively.
	 */

	public ListNode addLists(ListNode l1, ListNode l2) {
		ListNode dummy = new ListNode(0);
        ListNode node = dummy;
        int carry = 0;
        while(l1 != null || l2 != null){
            int set = carry + (l1 == null ? 0 : l1.val) + (l2 == null ? 0 : l2.val);
            node.next = new ListNode(set % 10);
            carry = set / 10;
            l1 = (l1 == null? null : l1.next);
            l2 = (l2 == null? null : l2.next);
            node = node.next;
        }
        if(carry > 0) node.next = new ListNode(carry);
        return dummy.next;
	}

	/**
	 * Follow-up: suppose the digits are stored in forward order. Repeat.
	 * 
	 * @logic I: Reverse the two lists and calculate their sum, then reverse the resultant list to return
	 * 
	 * 		  II: If reverse the list is not allowed, recursion can be used to calculate sum from right to left.
	 * 
	 *        1) Calculate sizes of given two linked lists. 
	 *        2) If sizes are same, then calculate sum using recursion. Hold all nodes in recursion call stack 
	 *        	 till the rightmost node, calculate sum of rightmost nodes and forward carry to left side. 
	 *        3) If size is not same, then follow below steps: 
	 *        
	 *        	a) Calculate difference of sizes of two lists. 
	 *        	b) Pad the shorter list with zeros to let the size of two lists equal to each other
	 *          c) Recursively add the lists, based on assumption that we have added the l1.next and l2.next.
	 *             ** since we need to pass the carry with the recursion calls, we need to design a node with carry
	 * 
	 * @complexity O(m + n), where m and n are number of nodes in first and
	 *             second lists respectively.
	 */
	public ListNode addListsReverse(ListNode l1, ListNode l2) {
		if(l1 == null  || l2 == null) return l1 == null? l2 : l1;
		
		int len1 = listLen(l1);
		int len2 = listLen(l2);
		
		if(len1 > len2){
			l2 = padList(l2, len1-len2);
		}else if(len1 < len2){
			l1 = padList(l1, len2-len1);
		}
		
		NodeWithCarry res = addSameSize(l1, l2);
		
		//be careful of the current carry
		if(res.carry == 0) return res.node;
		else{
			ListNode result = insertBeforeHead(res.node, res.carry);
			return result;
		}
	}
	
	/*
	 * Adds two linked lists of same size represented by head1 and head2 and returns head of
	 * the resultant linked list. Carry is propagated while returning from the recursion.
	 */
	public NodeWithCarry addSameSize(ListNode l1, ListNode l2){
		// Since the function assumes linked lists are of same size,
	    // check any of the two head pointers
		if(l1 == null) return null;
		
		NodeWithCarry res = addSameSize(l1.next, l2.next);
		int curValue = res.carry + l1.val + l2.val;
		//add a new node before the result obtained from l1.next and l2.next
		ListNode curHead = insertBeforeHead(res.node, curValue % 10);
		res.carry = curValue / 10;
		res.node = curHead;
		return res;
	}
	
	public ListNode insertBeforeHead(ListNode node, int val){
		ListNode insert = new ListNode(val);
		if(node != null){
			insert.next = node;
		}
		return insert;
	}
	
	public ListNode padList(ListNode l, int padLen){
		ListNode head = l;
		for(int i = 0; i < padLen; i++){
			head = insertBeforeHead(head, 0);
		}
		return head;
	}
	
	public int listLen(ListNode node){
		int len = 0;
		while(node != null){
			len++;
			node = node.next;
		}
		return len;
	}
}
class NodeWithCarry{
	public ListNode node;
	public int carry;
	public NodeWithCarry(){
		node = null;
		carry = 0;
	}
}
