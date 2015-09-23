package interviews;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Iterator_Peeking {
	/**
	 * 写一个PeekIterator，包装一个普通的Iterator，要实现peek()方法，返回当前iterator指向的元素，但是不能移动它。
	 * 除此之外也要实现has_next()和next()方法。
	 * 
	 * PeekIterator可以用普通iterator的get_next()来获得，但是要记录下来，
	 * 下一次调用get_next的时候直接返回上一次peek的即可。
	 */
	public Iterator<Integer> ite;
	public List<Integer> peek; // 大小为1的list来存peek
	public Iterator_Peeking(List<Integer> list) {
		peek = new ArrayList<Integer>();
		ite = list.iterator();
	}
	public int peek() {
        if(peek.isEmpty()) {
            int cur = ite.next();
            peek.add(cur);
            return cur;
        } else {
            return peek.get(0);
        }
    }
    
    public boolean hasNext() {
        return ite.hasNext() || !peek.isEmpty();
    }
    
    int get_next() {
        if(peek.isEmpty()) {
            return ite.next();
        } else {
            int ret = peek.remove(0);
            return ret;
        }
    }
}
// leetcode problem
class PeekingIterator implements Iterator<Integer> {
    
    public List<Integer> peek;
    public Iterator<Integer> ite;
	public PeekingIterator(Iterator<Integer> iterator) {
	    // initialize any member here.
	    peek = new ArrayList<Integer>();
	    ite = iterator;
	}

    // Returns the next element in the iteration without advancing the iterator.
	public Integer peek() {
        if (peek.isEmpty()) {
            Integer next = ite.next();
            peek.add(next);
            return next;
        } else {
            return peek.get(0);
        }
	}

	// hasNext() and next() should behave the same as in the Iterator interface.
	// Override them if needed.
	@Override
	public Integer next() {
	    if (peek.isEmpty()) {
	        return ite.next();
	    } else {
	        int val = peek.remove(0);
	        return val;
	    }
	}

	@Override
	public boolean hasNext() {
	    return ite.hasNext() || (!peek.isEmpty());
	}
}
