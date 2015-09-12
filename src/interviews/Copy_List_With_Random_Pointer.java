package interviews;

import java.util.HashMap;

public class Copy_List_With_Random_Pointer {
	/**
	 * A linked list is given such that each node contains an additional random
	 * pointer which could point to any node in the list or null.
	 * 
	 * Return a deep copy of the list.
	 * 
	 * @analysis use HashMap to record the different pointers
	 * 			 be careful of the copying process
	 * 			 map: key - original node; value - copied node
	 */
	public RandomListNode copyRandomList(RandomListNode head) {
        HashMap<RandomListNode, RandomListNode> map = new HashMap<RandomListNode, RandomListNode>();
        return copyHelper(head, map);
    }
    public RandomListNode copyHelper(RandomListNode head, HashMap<RandomListNode, RandomListNode> map){
        if(head == null) return null;
        
        if(map.containsKey(head)) return map.get(head);
        
        RandomListNode cur = new RandomListNode(head.label);
        map.put(head, cur);
        cur.next = copyHelper(head.next, map);
        cur.random = copyHelper(head.random, map);
        return cur;
    }
    
    /**
     * @param head: The head of linked list with a random pointer.
     * @return: A new head of a deep copy of the list.
     */
    public RandomListNode copyRandomListNoMap(RandomListNode head) {
        if (head == null) return head;
        RandomListNode node = head;
        // copy the list
        while (node != null) {
            RandomListNode copy = new RandomListNode(node.label);
            copy.next = node.next;
            node.next = copy;
            node = copy.next;
        }
        // copy the random pointers
        node = head;
        while (node != null) {
            if (node.random != null) {
                node.next.random = node.random.next;
            }
            node = node.next.next;
        }
        // split the two lists
        node = head;
        RandomListNode newHead = node.next;
        while (node != null) {
            RandomListNode tmp = node.next;
            node.next = tmp.next;
            if (tmp.next != null) {
                tmp.next = tmp.next.next;
            }
            node = node.next;
        }
        return newHead;
    }
}

class RandomListNode {
	int label;
	RandomListNode next, random;

	RandomListNode(int x) {
		this.label = x;
	}
}