package interviews;

import java.util.ArrayList;
import java.util.List;

public class Print_Numbers_by_Recursive {
	/**
	 * Print numbers from 1 to the largest number with N digits by recursion.
	 * 
	 * Given N = 1, return [1,2,3,4,5,6,7,8,9].
	 * 
	 * Given N = 2, return [1,2,3,4,5,6,7,8,9,10,11,12,...,99].
	 */
	// request: O(n) recursions
	public List<Integer> numbersByRecursion(int n) {
        List<Integer> res = new ArrayList<Integer>();
        if (n > 0) {
            add(n, res);
        }
        return res;
    }
    public int add(int n, List<Integer> res) {
        if (n == 0) {
            return 1;
        }
        // this will return the digit we are dealing with now
        int base = add(n - 1, res);
        int size = res.size(); // this should be the size of each round of addition
        for (int i = 1; i <= 9; i++) {
            int curBase = i * base;  // this should be 100, 200, 300, ...
            res.add(curBase); // add the 100, 200, ...
            for (int j = 0; j < size; j++) {
                res.add(curBase + res.get(j)); // add the 101, 102,...
            }
        }
        return base * 10;
    }
}
