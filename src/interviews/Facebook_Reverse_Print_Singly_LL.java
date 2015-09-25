package interviews;

public class Facebook_Reverse_Print_Singly_LL {
	/**
	 * 反向输出单向链表，不能改变list结构
	 */
	void printReverse(ListNode node) {
	    if(node.next != null) { // we recurse every time unless we're on the last one
	        printReverse(node.next);  // this says "do this to the next node first"
	    }
	    System.out.println(node.val); // we'll print out our node now
	}
	// 怎么改善空间 => 牺牲时间
	// O(n^2)
	
}
