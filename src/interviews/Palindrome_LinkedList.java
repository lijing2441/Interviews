package interviews;

import java.util.Stack;

/**
 * @Problem Implement a function to check if a linked list is a palindrome
 */
public class Palindrome_LinkedList {
	/**
	 * Method I: Reverse the second half of the linked list 
	 * This method takes O(n) time and O(1) extra space. 
	 * 
	 * (1) Get the middle of the linked list. 
	 * (2) Reverse the second half of the linked list. 
	 * (3) Check if the first half and second half are identical. 
	 * (4) **REMEMBER!!!! Construct the original linked list by reversing the second half again 
	 * and attaching it back to the first half
	 */
	public boolean isPalindrome(ListNode head){
		if(head == null || head.next == null) return true;
		//get the middle
		ListNode fast = head;
		ListNode slow = head;
		ListNode pre_slow = head;
		while(fast != null && fast.next != null){
			fast = fast.next.next;
			pre_slow = slow;
			slow = slow.next;
		}
		/* fast_ptr would become NULL when there are even elements in list. And not NULL for odd elements. 
		 * We need to skip the middle node for odd case and store it somewhere so that we can restore the
		 * original list
		 */
		ListNode mid = null;
		if(fast != null){
			mid = slow; // store this single node
			slow = slow.next;
		}
		// NULL terminate first half
		pre_slow.next = null;
		ListNode secondHead = reverse(slow);
		//compare the two lists
		boolean res = compare(head, secondHead);
		
		/* Construct the original list back */
		//reverse the second half back to the original shape
		secondHead = reverse(secondHead);
		if(mid != null){
			pre_slow.next = mid;
			mid.next = secondHead;
		}else{
			pre_slow.next = secondHead;
		}
		return res;
	}

	public ListNode reverse(ListNode head){
		ListNode cur = head;
		ListNode prev = null;
		while(cur != null){
			ListNode tmp = cur.next;
			cur.next = prev;
			prev = cur;
			cur = tmp;
		}
		return prev;
	}
	public boolean compare(ListNode n1, ListNode n2){
		ListNode tmp1 = n1;
		ListNode tmp2 = n2;
		while(tmp1 != null && tmp2 != null){
			if(tmp1.val != tmp2.val){
				return false;
			}else{
				tmp1 = tmp1.next;
				tmp2 = tmp2.next;
			}
		}
		if(tmp1 == null && tmp2 == null) return true;
		return false;
	}
	
	/**
	 * Method II: using a stack to push the first half into it and pop to compare it with the second half
	 * 
	 * (1) Push the nodes from head to the midpoint to stack.
	 * (2) For the second half, pop a node from stack and compare with current node.
	 * (3) If no mismatch found, return true.
	 * 
	 * Time complexity of above method is O(n), but it requires O(n/2) extra space.
	 */
	public boolean isPalindromeStack(ListNode head){
		if(head == null || head.next == null) return true;
		Stack<Integer> stack = new Stack<Integer>();
		ListNode fast = head;
		ListNode slow = head;
		while(fast != null && fast.next != null){
			stack.push(slow.val);
			slow = slow.next;
			fast = fast.next.next;
		}
		/* fast_ptr would become NULL when there are even elements in list. And not NULL for odd elements. 
		 * We need to skip the middle node for odd case and store it somewhere so that we can restore the
		 * original list
		 */
		if(fast != null){
			slow = slow.next;
		}
		while(slow != null){
			int cur = stack.pop().intValue();
			if(cur != slow.val) return false;
			slow = slow.next;
		}
		return true;
	}
	
	/**
	 * METHOD 3 (Using Recursion)
	 * 
	 * Use two pointers left and right. Move right and left using recursion 
	 * and check for following in each recursive call.
	 * (1) Sub-list is palindrome.
	 * (2) Value at current left and right are matching.
	 * 
	 * If both above conditions are true then return true.
	 * 
	 * The idea is to use function call stack as container. Recursively traverse till 
	 * the end of list. When we return from last NULL, we will be at last node. 
	 * The last node to be compared with first node of list.
	 * 
	 * In order to access first node of list, we need list head to be available in the 
	 * last call of recursion. Hence we pass head also to the recursive function. 
	 * If they match, we need to compare (2, n-2) nodes. Again when recursion falls back 
	 * to (n-2)nd node, we need reference to 2nd node from head. 
	 * 
	 * We advance the head pointer in previous call, to refer to next node in the list.
	 * 
	 * However, the trick in identifying double pointer. 
	 * Passing single pointer is as good as pass-by-value, and we will pass the same 
	 * pointer again and again. We need to pass the address of head pointer for reflecting 
	 * the changes in parent recursive calls.
	 * 
	 * @complexity Time Complexity: O(n) 
	 * Auxiliary Space: O(n) if Function Call Stack size is considered, otherwise O(1).
	 */
	/*public boolean isPalindromeRecur(){
		//complex
	}
	*/
}
