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
	
	/**
	 * Problem II: We need to get the free intervals between two sets of schedules, represented as intervals
	 * 			   The schedules are already sorted increasing sequence of pairs of numbers.
	 */
	
	public List<Interval> getFreeIntervals(List<Interval> schedule1, List<Interval> schedule2){
		ArrayList<Interval> res = new ArrayList<Interval>();
		if(schedule1.size() == 0 || schedule2.size() == 0) return res;
		
		//pointer of the two schedules
		int i = 0, j = 0;
		//merge them into the same list according to their start time, O(n)
		ArrayList<Interval> intervals = new ArrayList<Interval>();
		while(i < schedule1.size() && j < schedule2.size()){
			if(schedule1.get(i).start <= schedule2.get(j).start){
				intervals.add(schedule1.get(i));
				i++;
			}else{
				intervals.add(schedule2.get(j));
				j++;
			}
		}
		if(i < schedule1.size()){
			for(; i < schedule1.size(); i++){
				intervals.add(schedule1.get(i));
			}
		}
		if(j < schedule1.size()){
			for(; j < schedule2.size(); j++){
				intervals.add(schedule2.get(j));
			}
		}
		
		// merge all together, O(n)
		int start = intervals.get(0).start;
		int end = intervals.get(0).end;
		ArrayList<Interval> merged = new ArrayList<Interval>();
		for (Interval interval : intervals) {
			if (end >= interval.start) {
				end = Math.max(end, interval.end);
			} else {
				merged.add(new Interval(start, end));
				start = interval.start;
				end = interval.end;
			}
		}
		merged.add(new Interval(start, end));
		
		//get the holes, O(n)
		for(int k = 0; k < merged.size() - 1; k++){
			res.add(new Interval(merged.get(k).end, merged.get(k + 1).start));
		}
		return res;
		
		/*
		int start = 0;
		int end = 0;
		//pointer of the two schedules
		int i = 0, j = 0;
		
		while(i < schedule1.size() && j < schedule2.size()){
			if(schedule1.get(i).end < schedule2.get(j).end){
				if(i == schedule1.size() - 1 || schedule1.get(i + 1).start <= schedule2.get(j).end){
					i++;
					continue;
				}else{
					res.add(new Interval(schedule1.get(i).end, schedule))
				}
			}
		}
		*/
	}
	
}



// or you can do it this way
class IntervalComparator implements Comparator<Interval> {
	public int compare(Interval i1, Interval i2) {
		return i1.start - i2.start;
	}
}