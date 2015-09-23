package interviews;

import java.util.Iterator;
import java.util.List;

public class Zigzag_Iterator {
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
	public boolean isFirst;
    public Iterator<Integer> ite1;
    public Iterator<Integer> ite2;
    public Zigzag_Iterator(List<Integer> v1, List<Integer> v2) {
        isFirst = true;
        ite1 = v1.iterator();
        ite2 = v2.iterator();
    }

    public int next() {
        if (isFirst) {
            isFirst = false;
            if (ite1.hasNext()) return ite1.next();
            else return ite2.next();
        } else {
            isFirst = true;
            if (ite2.hasNext()) return ite2.next();
            else return ite1.next();
        }
    }

    public boolean hasNext() {
        return ite1.hasNext() || ite2.hasNext();
    }
	
}
