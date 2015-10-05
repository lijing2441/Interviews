package interviews;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Facebook_找overlap最多的interval {
	/**
	 * Given a sequence of intervals, return the max overlapping interval
	 */
	public static List<Interval> getMaxOverlapInterval(List<Interval> intervals) {
		int len = intervals.size();
		int[] starts = new int[len];
		int[] ends = new int[len];
		int[] intervalPoints = new int[len * 2]; // used to find the maximal interval finally
		int ptr = 0, ptr2 = 0;
		for (Interval i: intervals) {
			starts[ptr] = i.start;
			ends[ptr] = i.end;
			intervalPoints[ptr2++] = i.start;
			intervalPoints[ptr2++] = i.end;
			ptr++;
		}
		Arrays.sort(starts);
		Arrays.sort(ends);
		Arrays.sort(intervalPoints);
		int sPtr = 0, ePtr = 0, count = 0, maxCount = 0;
		// use differentialArray to node the difference between all possible positions
		int[] differentialArray = new int[len * 2];
		int counter = 0; // mark as counter in differentialArray
		while (sPtr < len || ePtr < len) {
			if (sPtr < len && ePtr < len) {
                if (starts[sPtr] < ends[ePtr]) {
                	count++;
                	differentialArray[counter++] = count;
                	if (maxCount < count) {
                        maxCount = count;
                    }
                    sPtr++;
                } else {
                	count--;
                	differentialArray[counter++] = count;
                	ePtr++;
                }
            } else if (sPtr < len) {
            	count++;
            	differentialArray[counter++] = count;
            	if (maxCount < count) {
                    maxCount = count;
                }
                sPtr++;
            } else {
            	count--;
            	differentialArray[counter++] = count;
            	ePtr++;
            }
		}
		System.out.println("Maximal overlap number should be : " + maxCount);
		List<Interval> res = new ArrayList<Interval>();
		for (int i = 0; i < len * 2 - 1; i++) {
			if (differentialArray[i] == maxCount) {
				// in case of continuous intervals
				if (res.size() != 0 && res.get(res.size() - 1).end == intervalPoints[i]) {
					Interval cur = res.get(res.size() - 1);
					cur.end = intervalPoints[i + 1];
					res.remove(res.size() - 1);
					res.add(cur);
				} else {
					Interval newInterval = new Interval(intervalPoints[i], intervalPoints[i + 1]);
					res.add(newInterval);
				}
			}
		}
		return res;
	}
	public static void main(String[] args) {
		// 5, 11, 6, 18, 2, 5, 3, 12
		Interval i1 = new Interval(5, 11);
		Interval i2 = new Interval(6, 18);
		Interval i3 = new Interval(2, 5);
		Interval i4 = new Interval(3, 12);
		List<Interval> list = new ArrayList<Interval>();
		list.add(i1);
		list.add(i2);
		list.add(i3);
		list.add(i4);
		List<Interval> results = getMaxOverlapInterval(list);
		for (Interval res : results) System.out.println("[" + res.start + ", " + res.end + "] ");
	}
}
