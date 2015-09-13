package interviews;

import java.util.Iterator;
import java.util.List;

public class Flatten_2D_Vector {
	/**
	 * Implement an iterator to flatten a 2d vector.
	 * 
	 * For example, Given 2d vector =
	 * 
	 * [ [1,2], [3], [4,5,6] ] 
	 * 
	 * By calling next repeatedly until hasNext returns false, the order 
	 * of elements returned by next should be: [1,2,3,4,5,6].
	 */
	// Iterator way
	public Iterator<List<Integer>> listIte;
    public Iterator<Integer> ite;
    
    public Flatten_2D_Vector(List<List<Integer>> vec2d) {
        listIte = vec2d.iterator(); // initialize the ite in the hasNext()
    }

    public int next() {
        hasNext();
        return ite.next();
    }

    public boolean hasNext() {
        while((ite == null || !ite.hasNext()) && listIte.hasNext()) {
            ite = listIte.next().iterator();
        }
        return ite != null && ite.hasNext();
    }
    
    // Another math pointer way
    public List<List<Integer>> data;
    public int index1 = 0; // list index
    public int index2 = 0; // number index within list
    public int numLists;
    
    public void Vector2D2(List<List<Integer>> vec2d) {
        data = vec2d;
        numLists = vec2d.size();
        while(index1 < numLists && data.get(index1).size() == 0) {
            index1++;
        }
    }

    public int next2() {
        if(hasNext()) return data.get(index1).get(index2++);
        return -1;
    }

    public boolean hasNext2() {
        if(index1 >= numLists) return false;
        if(index2 < data.get(index1).size()) return true;
        while(++index1 < numLists) {
            if(data.get(index1).size() > 0) {
                index2 = 0;
                return true;
            }
        }
        return false;
    }
    
}
