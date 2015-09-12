package interviews;

import java.util.ArrayList;
import java.util.List;

public class Insert_Interval {
	/**
	 * Given a set of non-overlapping intervals, insert a new interval into the
	 * intervals (merge if necessary).
	 * 
	 * You may assume that the intervals were initially sorted according to
	 * their start times.
	 * 
	 * Example 1: Given intervals [1,3],[6,9], insert and merge [2,5] in as [1,5],[6,9].
	 * 
	 * Example 2: Given [1,2],[3,5],[6,7],[8,10],[12,16], insert and merge [4,9]
	 * in as [1,2],[3,10],[12,16].
	 * 
	 * This is because the new interval [4,9] overlaps with [3,5],[6,7],[8,10].
	 */
	public List<Interval> insert(List<Interval> intervals, Interval newInterval) {
        List<Interval> res = new ArrayList<Interval>();
        if(intervals == null || intervals.size() == 0){
            res.add(newInterval);
            return res;
        }
        int len = intervals.size();
        int i = 0;
        Interval merge = newInterval;
        for(i = 0; i < len; i++){
        	// for the intervals end before the new interval start, we add them to res
            if(intervals.get(i).end < merge.start) res.add(intervals.get(i));
            // for the ones start after the interval to insert end, break
            else if(intervals.get(i).start > merge.end) break;
            else{
            	// get the new start and end according to the positions
                merge.start = Math.min(intervals.get(i).start, merge.start);
                merge.end = Math.max(intervals.get(i).end, merge.end);
            }
        }
        res.add(merge);
        for(; i < len; i++){
            res.add(intervals.get(i));
        }
        return res;
    }
}
