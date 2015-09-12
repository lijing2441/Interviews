package interviews;

import java.util.HashSet;

public class Remove_Dups_From_Unsorted_List {
	/**
	 * Method I: Two loops
	 * 
	 * Outer loop is used to pick the elements one by one and inner loop 
	 * compares the picked element with rest of the elements.
	 * 
	 * Time Complexity: O(n^2), space O(1)
	 */
	public void deleteDups(ListNode head){
		if(head == null || head.next == null) return;
		ListNode cur = head;
		while(cur != null){
			//remove all the future nodes with the same value
			ListNode pilot = cur;
			while(pilot.next != null){
				if(cur.val == pilot.next.val){
					pilot.next = pilot.next.next;
				}else{
					//continue with the remaining list, since the list is not sorted
					pilot = pilot.next;
				}
			}
			cur = cur.next;
		}
	}

	/**
	 * Method II: Hashing
	 * 
	 * We traverse the link list from head to end. For every newly encountered
	 * element, we check whether it is in the hash table: if yes, we remove it;
	 * otherwise we put it in the hash table.
	 * 
	 * Time Complexity: O(n) on average 
	 * (assuming that hash table access time is O(1) on average).
	 */
	public void deleteDupsHash(ListNode head){
		HashSet<Integer> set = new HashSet<Integer>();
		ListNode prev = null;
		ListNode cur = head;
		while(cur != null){
			if(set.contains(cur.val)){
				prev.next = cur.next;
			}else{
				set.add(cur.val);
				prev = cur;
			}
			cur = cur.next;
		}
	}
	
	/**
	 * Method III: Sorting (Note: it doesn't preserve the original order of the list)
	 * 
	 * In general, Merge Sort is the best suited sorting algorithm for sorting linked lists efficiently.
	 * 1)	Sort the elements using Merge Sort.
	 * 2)	Remove duplicates in linear time
	 * 
	 * Time Complexity: O(nLogn)
	 */
	public void deleteDupsSort(ListNode head){
		ListNode node = mergeSort(head);
		while(node.next != null){
			if(node.next.val == node.val){
				node.next = node.next.next;
			}else{
				node = node.next;
			}
		}
	}
	public ListNode mergeSort(ListNode head) {
		if(head == null || head.next == null) return head;
        ListNode fast = head;
        ListNode slow = head;
        ListNode preSlow = head;
        while(fast != null && fast.next != null){
            fast = fast.next.next;
            preSlow = slow;
            slow = slow.next;
        }
        preSlow.next = null;
        ListNode first = mergeSort(head);
        ListNode second = mergeSort(slow);
        return merge(first, second);
    }
    public ListNode merge(ListNode l1, ListNode l2){
        if(l1 == null) return l2;
        if(l2 == null) return l1;
        ListNode dummy = new ListNode(0);
        ListNode node = dummy;
        while(l1 != null && l2 != null){
            if(l1.val < l2.val){
                node.next = l1;
                l1 = l1.next;
            }else{
                node.next = l2;
                l2 = l2.next;
            }
            node = node.next;
        }
        if(l1 != null) node.next = l1;
        if(l2 != null) node.next = l2;
        return dummy.next;
    }
	
}
