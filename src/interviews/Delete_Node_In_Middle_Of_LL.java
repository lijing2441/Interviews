package interviews;

public class Delete_Node_In_Middle_Of_LL {
	// delete the node 
	public void deleteNode(ListNode node) {
		// 把后面的都挪前面来
        if(node == null) return;
        ListNode pre = null;
        while(node.next != null) {
            node.val = node.next.val;
            pre = node;
            node = node.next;
        }
        if(pre != null) pre.next = null;
    }
}
