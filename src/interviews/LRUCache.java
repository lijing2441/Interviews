package interviews;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * In gmail, while composing an email, upon adding a contact, related contacts are displayed. 
 * how would you implement that feature?
 * - write an algorithm for it.
 * - What data structure would you use to store the weight? 
 * 		I use the relative position in the list
 * - In what format would you persist this data? 
 * 		I use the combination of HashMap and DLL to store the data, key is the name and value is the 
 * node storing related information.
 *
 * 为了能够快速删除最久没有访问的数据项和插入最新的数据项，我们双向链表连接Cache中的数据项，
 * 并且保证链表维持数据项从最近访问到最旧访问的顺序。每次数据项被查询到时，都将此数据项移动到链表头部（O(1)的时间复杂度）。
 * 这样，在进行过多次查找操作后，最近被使用过的内容就向链表的头移动，而没有被使用的内容就向链表的后面移动。
 *
 * 当需要替换时，链表最后的位置就是最近最少被使用的数据项，我们只需要将最新的数据项放在链表头部，当Cache满时，淘汰链表最后的位置.
 * Key idea: 双向链表插入、删除很快，可以灵活的调整相互间的次序，时间复杂度为O(1)。
 * 
 * 查找一个链表中元素的时间复杂度是O(n)，每次命中的时候，我们就需要花费O(n)的时间来进行查找，如果不添加其他的数据结构，
 * 这个就是我们能实现的最高效率了。目前看来，整个算法的瓶颈就是在查找这里了，怎么样才能提高查找的效率呢？HashMap!
 * 
 * Search: 
 * 		1) search the value through the key in HashMap, if exists, return, otherwise return null; 
 * 		2) delete the node and insert it in the head
 * Set(insert + update): 
 * 		1) connect the new node to map 
 * 		2) if cache is full, delete tail node and the corresponding record in map
 * 		3) insert the node in the head
 * Delete: delete the node from the list and the record from the map
 * 
 */

public class LRUCache {

	// Doubly LL method:
	private int capacity;
	private int size;
	private HashMap<Integer, DoublyLinkedListNode> map = new HashMap<Integer, DoublyLinkedListNode>();
	private DoublyLinkedListNode head;
	private DoublyLinkedListNode tail;

	public LRUCache(int capacity) {
		this.capacity = capacity;
		this.size = 0;
	}

	public int get(int key) {
		if (map.containsKey(key)) {
			DoublyLinkedListNode latest = map.get(key);
			removeNode(latest);
			setHead(latest);
			return latest.val;
		} else {
			return -1;
		}
	}

	public void removeNode(DoublyLinkedListNode node) {
		DoublyLinkedListNode pre = node.pre;
		DoublyLinkedListNode post = node.post;

		if (pre != null) {
			pre.post = post;
		} else {
			head = post;
		}
		if (post != null) {
			post.pre = pre;
		} else {
			tail = pre;
		}
	}

	public void setHead(DoublyLinkedListNode node) {
		node.post = head;
		node.pre = null;

		if (head != null) {
			head.pre = node;
		}
		head = node;
		if (tail == null) {
			tail = node;
		}
	}

	public void set(int key, int value) {
		if (map.containsKey(key)) {
			DoublyLinkedListNode latest = map.get(key);
			latest.val = value;
			removeNode(latest);
			setHead(latest);
		} else {
			DoublyLinkedListNode newNode = new DoublyLinkedListNode(key, value);
			if (size < capacity) {
				size++;
			} else {
				map.remove(tail.key);
				tail = tail.pre;
				if (tail != null) {
					tail.post = null;
				}
			}
			map.put(key, newNode);
			setHead(newNode);
		}
	}

	// test code
	public static void main(String[] args) {
		LRUCache lru = new LRUCache(2);
		System.out.println(lru.get(2));
		lru.set(1, 10);
		lru.set(2, 20);
		System.out.println(lru.get(1));
		lru.set(3, 30);
		System.out.println(lru.get(2));
	}
	/**
	 * Distribute the cache: divide up the cache, such that each machine holds a different part of cache.
	 * Assign the queries based on the formula hash(query) % N. Then machine i only needs to apply this 
	 * formula to know that machine j should store the results for this query.
	 * 
	 * So, when a new query comes in to machine i, this machine would apply the formula and call out to machine j.
	 * Machine j would then return the value from its cache. Machine j should update its cache and return
	 * the value to machine i.
	 *
	 */
}

class DoublyLinkedListNode {
	public int val;
	public int key;
	public DoublyLinkedListNode pre;
	public DoublyLinkedListNode post;

	public DoublyLinkedListNode(int key, int value) {
		this.key = key;
		this.val = value;
	}
}

