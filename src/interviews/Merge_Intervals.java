package interviews;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Merge_Intervals {
	/**
	 * Problem IV: Merge Intervals Given a collection of intervals, merge all
	 * overlapping intervals.
	 * 
	 * For example, Given [1,3],[2,6],[8,10],[15,18], 
	 * 				return [1,6],[8,10],[15,18].
	 * 
	 * O(nlogn) in sorting
	 */
	public List<Interval> merge(List<Interval> intervals) {
		if (intervals.size() < 2)
			return intervals;
		
		//modified version of merge sort, O(nlogn)
		Collections.sort(intervals, new Comparator<Interval>() {
			public int compare(Interval v1, Interval v2) {
				return v1.start - v2.start;
			}
		});
		
		List<Interval> res = new ArrayList<Interval>();
		
		int start = intervals.get(0).start;
		int end = intervals.get(0).end;
		
		for (Interval interval : intervals) {
			if (end >= interval.start) {
				end = Math.max(end, interval.end);
			} else {
				// add the current interval we have found
				res.add(new Interval(start, end));
				// we have known the current interval has a start later than the original end
				// we can just start with this interval's start and end
				start = interval.start;
				end = interval.end;
			}
		}
		res.add(new Interval(start, end));
		return res;
	}
}