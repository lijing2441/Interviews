package interviews;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

public class Airbnb_Get_Free_Interval_of_All_People {
	/**
	 * Given a lot of person, who has a sorted schedule each (a list of intervals).
	 * Get the free interval for all people.
	 */
	public static List<Interval> schedule(List<List<Interval>> list, int start, int end){
		List<Interval> res = new ArrayList<Interval>();
		PriorityQueue<Interval> pq = new PriorityQueue<Interval>(new Comparator<Interval>() {
			public int compare(Interval i1, Interval i2) {
				if (i1.start != i2.start) return i1.start - i2.start;
				else return i1.end - i2.end;
			}
		});
		for (List<Interval> l : list) {
			for (Interval i : l) {
				pq.offer(i);
			}
		}
		int lastEndTime = start;
		while (!pq.isEmpty()) {
			Interval i = pq.poll();
			boolean isEnd = false;
			if (i.end >= end) {
				isEnd = true;
			}
			if (i.start > lastEndTime) {
				res.add(new Interval(lastEndTime, i.start));
				lastEndTime = i.end;
			} else {
				lastEndTime = Math.max(i.end, lastEndTime);
			}
			if (isEnd) break;
		}
		if (end > lastEndTime) {
			res.add(new Interval(lastEndTime, end));
		}
		return res;
	}
	
	public static void main(String[] args) {
		Interval i1 = new Interval(0, 2);
		Interval i2 = new Interval(4, 6);
		Interval i3 = new Interval(8, 10);
		Interval[] meetings1 = {i1 ,i2, i3};
		Interval i4 = new Interval(1, 3);
		Interval i5 = new Interval(8, 11);
		Interval[] meetings2 = {i4 ,i5};
		Interval i6 = new Interval(0, 1);
		Interval i7 = new Interval(5, 7);
		Interval[] meetings3 = {i6 ,i7};
		List<List<Interval>> list = new ArrayList<List<Interval>>();
		list.add(Arrays.asList(meetings1));
		list.add(Arrays.asList(meetings2));
		list.add(Arrays.asList(meetings3));
		List<Interval> res = schedule(list, 0, 12);
		for (Interval i : res) {
			System.out.println(i.start + "->" + i.end);
		}
		System.out.println();
	}
}
//class Person {
//	Interval[] meeings;
//	public Person(Interval[] m) {
//		this.meeings = m;
//	}
//}
//class IntervalWithIndex {
//	int index; // where it comes from
//	Interval interval;
//	public IntervalWithIndex(int i, Interval interval) {
//		this.index = i;
//		this.interval = interval;
//	}
//}