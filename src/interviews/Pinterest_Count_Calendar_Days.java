package interviews;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Pinterest_Count_Calendar_Days {
	/**
	 * Count calendar days.
	 * 
	 * Input: list of ranges [start,end], a range represent days i.e. [1,4]
	 * means days from 1 to day 4.
	 * 
	 * Output: Total days included in the list of ranges.
	 * 
	 * 1) Merge overlapping ranges and 
	 * 2) Count/Sum the days of the resulting non-overlapping ranges.
	 */
	public static int countCalendarDays(List<Interval> intervals) {
		Collections.sort(intervals, new Comparator<Interval>() {
			public int compare(Interval i1, Interval i2) {
				if (i1.start != i2.start) {
					return i1.start - i2.start;
				} else {
					return i1.end - i2.end;
				}
			}
		});
		List<Interval> merged = new ArrayList<Interval>();
		int start = intervals.get(0).start, end = intervals.get(0).end;
		for (int i = 1; i <= intervals.size(); i++) {
			if (i < intervals.size() && intervals.get(i).start <= end) {
				start = Math.min(start, intervals.get(i).start);
				end = Math.max(end, intervals.get(i).end);
			} else {
				merged.add(new Interval(start, end));
				if (i < intervals.size()) {
					start = intervals.get(i).start;
					end = intervals.get(i).end;
				}
			}
		}
		int days = 0;
		for (Interval i: merged) {
			days += (i.end - i.start + 1);
		}
		return days;
	}
	public static void main(String[] args) {
		Interval i1 = new Interval(1, 4);
		Interval i2 = new Interval(2, 6);
		Interval i3 = new Interval(3, 4);
//		Interval i4 = new Interval(10, 12);
//		Interval i5 = new Interval(11, 17);
//		Interval i6 = new Interval(12, 15);
		List<Interval> res = new ArrayList<Interval>();
		res.add(i1);
		res.add(i2);
		res.add(i3);
//		res.add(i4);
//		res.add(i5);
//		res.add(i6);
		System.out.println(countCalendarDays(res));
	}
}
