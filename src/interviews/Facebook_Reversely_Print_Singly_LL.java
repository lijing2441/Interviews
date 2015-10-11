package interviews;

public class Facebook_Reversely_Print_Singly_LL {
	/**
	 * 反向输出单向链表，不能改变list结构
	 * 
	 * 若链表很长会stack overflow
	 */
	void printReverse(ListNode node) {
	    if(node.next != null) { // we recurse every time unless we're on the last one
	        printReverse(node.next);  // this says "do this to the next node first"
	    }
	    System.out.println(node.val); // we'll print out our node now
	}
	// 怎么改善空间 => 牺牲时间
	// O(n^2)
	static void printReverse_n2(ListNode node) {
		ListNode tail = null;
		ListNode cur = node;
		while (node != tail) {
			cur = node;
			while (cur.next != tail) {
				cur = cur.next;
			}
			System.out.print(cur.val);
			tail = cur;
		}
	}
	public static void main(String[] args) {
		ListNode n1 = new ListNode(1);
		ListNode n2 = new ListNode(2);
		ListNode n3 = new ListNode(3);
		ListNode n4 = new ListNode(4);
		ListNode n5 = new ListNode(5);
		n1.next = n2;
		n2.next = n3;
		n3.next = n4;
		n4.next = n5;
		printReverse_n2(n1);
		ListNode n = n1;
		while(n != null) {
			System.out.print(n.val + " ");
			n = n.next;
		}
	}
}
