package interviews;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

public class Subarray_Sum_Closest {
	/**
	 * Given an integer array, find a subarray with sum closest to zero. Return
	 * the indexes of the first number and last number.
	 */
	// 暴搜，O(n^2)
	// sort sum, O(nlogn)
	public ArrayList<Integer> subarraySumClosest(int[] nums) {
        //ArrayList<Integer> res = new ArrayList<Integer>();
        if (nums == null || nums.length == 0) return new ArrayList<Integer>();
        int len = nums.length;
        SumWithIndex[] sums = new SumWithIndex[len + 1];
        sums[0] = new SumWithIndex(0, 0);
        for (int i = 1; i <= len; i++) {
            sums[i] = new SumWithIndex(sums[i - 1].num + nums[i - 1], i);
        }
        Arrays.sort(sums, new Comparator<SumWithIndex>() {
            public int compare(SumWithIndex n1, SumWithIndex n2) {
                return n1.num - n2.num;
            }
        });
        int diff = Integer.MAX_VALUE;
        ArrayList<Integer> list = new ArrayList<Integer>();
        for (int i = 1; i <= len; i++) {
            if (diff >= (sums[i].num - sums[i - 1].num)) {
                diff = (sums[i].num - sums[i - 1].num);
                list.clear();
                int index1 = sums[i].index;
                int index2 = sums[i - 1].index;
                if (index1 < index2) {
                    list.add(index1);
                    list.add(index2 - 1);
                } else {
                    list.add(index2);
                    list.add(index1 - 1);
                }
            }
        }
        return list;
    }
}
class SumWithIndex {
    int num;
    int index;
    public SumWithIndex(int n, int i) {
        this.num = n;
        this.index = i;
    }
}