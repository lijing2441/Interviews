package interviews;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class ZigzagIterator {
	/**
	 * Given two 1d vectors, implement an iterator to return their elements
	 * alternately.
	 * 
	 * For example, given two 1d vectors:
	 * 
	 * v1 = [1, 2] 
	 * v2 = [3, 4, 5, 6] 
	 * 
	 * By calling next repeatedly until hasNext returns false, the order of 
	 * elements returned by next should be: [1, 3, 2, 4, 5, 6].
	 * 
	 * Follow up: What if you are given k 1d vectors? How well can your code be
	 * extended to such cases?
	 */
//	public boolean isFirst;
//    public Iterator<Integer> ite1;
//    public Iterator<Integer> ite2;
//    public Zigzag_Iterator(List<Integer> v1, List<Integer> v2) {
//        isFirst = true;
//        ite1 = v1.iterator();
//        ite2 = v2.iterator();
//    }
//
//    public int next() {
//        if (isFirst) {
//            isFirst = false;
//            if (ite1.hasNext()) return ite1.next();
//            else return ite2.next();
//        } else {
//            isFirst = true;
//            if (ite2.hasNext()) return ite2.next();
//            else return ite1.next();
//        }
//    }
//
//    public boolean hasNext() {
//        return ite1.hasNext() || ite2.hasNext();
//    }
	
    // Queue 做法
	public Queue<Iterator<Integer>> q;
    public ZigzagIterator(List<Integer> v1, List<Integer> v2) {
        q = new LinkedList<>();
        q.offer(v1.iterator());
        q.offer(v2.iterator());
    }

    public int next() {
        if (!hasNext()) return -1;
        else {
            Iterator<Integer> cur = q.poll();
            int toReturn = cur.next();
            if (cur.hasNext()) q.offer(cur);
            return toReturn;
        }
    }

    public boolean hasNext() {
        while (!q.isEmpty()) {
            if (q.peek().hasNext()) return true;
            else q.poll();
        }
        return false;
    }
    
    public static void main(String[] args) {
    	List<Integer> l1 = new ArrayList<Integer>();
    	l1.add(1);
    	l1.add(2);
    	List<Integer> l2 = new ArrayList<Integer>();
    	l2.add(3);
    	l2.add(4);
    	l2.add(5);
    	l2.add(6);
    	ZigzagIterator it = new ZigzagIterator(l1, l2);
    	while (it.hasNext()) {
    		System.out.println(it.next());
    	}
    }
    
}
