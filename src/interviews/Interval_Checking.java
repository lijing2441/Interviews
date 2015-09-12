package interviews;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Interval_Checking {
	/**
	 * Problem I: check if overlaps exist between a group of intervals
	 * 
	 * @logic starts and ends 分别存两个array，然后按大小遍历，对两个arrays给i，j两个指针
	 *        如果一个intervals的end和start连续弹出，则这个interval与其他intervals没有
	 *        overlap，否则有overlap
	 */
	public boolean checkOverlap(Interval[] intervals) {
		if (intervals.length < 2) return false;
		int n = intervals.length;
		
		//sort the intervals by their start positions
		Arrays.sort(intervals, new Comparator<Interval>() {
			public int compare(Interval v1, Interval v2) {
				return v1.start - v2.start;
			}
		});
		int[] starts = new int[n];
		int[] ends = new int[n];
		for(int i = 0; i < n; i++){
			starts[i] = intervals[i].start;
			ends[i] = intervals[i].end;
		}
		int startPtr = 0, endPtr = 0;
		while(startPtr < n && endPtr < n){
			// one point overlap is also counted
			if(starts[startPtr] <= ends[endPtr]){ 
				startPtr++;
			}else{
				endPtr++;
			}
			if(startPtr - endPtr > 1){
				return true;
			}
		}
		if(startPtr == n && endPtr == n) return false; // overlap doesn't exist
		return true; // overlap exists
		
		// if we need to output the overlaps, we can record the start and end:
		// overlapStart = starts[startPtr - 1]
		// the end point is the smallest end that is larger than the cur start point so far, minEnd
		// for(int i = startPtr - 1; i >= endPtr; i--){
		// 		if(ends[i] > start[startPtr - 1]){
		//			minEnd = Math.min(minEnd, ends[i]);
		// } }
	}

	/**
	 * Problem II: 找出 interval的overlap
	 */
	//if it's not allowed duplicates, use hashset
	public ArrayList<Interval> getOverlaps(List<Interval> intervals){
		ArrayList<Interval> res = new ArrayList<Interval>();
		if (intervals.size() < 2) return res;
		int n = intervals.size();
		
		//sort the intervals by their start positions
		Collections.sort(intervals, new Comparator<Interval>() {
			public int compare(Interval v1, Interval v2) {
				return v1.start - v2.start;
			}
		});
		for(int i = 0; i < n; i++){
			Interval i1 = intervals.get(i);
			for(int j = i + 1; j < n; j++){
				Interval i2 = intervals.get(j);
				if(i1.end >= i2.start){
					res.add(new Interval(i2.start, Math.min(i1.end, i2.end)));
				}
			}
		}
		return res;
	}

	/**
	 * Problem III: 
	 * 
	 * version I: 给一个interval list, including a large amount of intervals，
	 * 对任意一个点，给出有几个intervals在该点处overlap
	 * 
	 * version II: 找出overlap最多的interval;
	 * 
	 * @logic 用 interval tree
	 */
	
	
	
	
	
	/**
	 * Problem IV: Merge Intervals
	 */
	public List<Interval> merge(List<Interval> intervals) {
		if (intervals.size() < 2)
			return intervals;
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
				res.add(new Interval(start, end));
				start = interval.start;
				end = interval.end;
			}
		}
		res.add(new Interval(start, end));
		return res;
	}
}

class Interval {
	int start;
	int end;

	Interval() {
		start = 0;
		end = 0;
	}
	Interval(int s, int e) {
		start = s;
		end = e;
	}
}
