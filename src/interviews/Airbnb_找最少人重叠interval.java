package interviews;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Set;

public class Airbnb_找最少人重叠interval {
	
	public List<Interval> getMinimalOverlapIntervals(List<List<Interval>> schedules) {
		List<Interval> res = new ArrayList<Interval>();
		PriorityQueue<IntervalEvent> pq = new PriorityQueue<IntervalEvent>(new Comparator<IntervalEvent>() {
			public int compare(IntervalEvent i1, IntervalEvent i2) {
				if (i1.time != i2.time) return i1.time - i2.time;
				else return i1.isStart - i2.isStart;
			}
		});
		
		for (List<Interval> schedule : schedules) {
			for (Interval i : schedule) {
				pq.offer(new IntervalEvent(i.start, 1, i));
				pq.offer(new IntervalEvent(i.end, 0, i));
			}
		}
		Set<Interval> set = new HashSet<Interval>();
		int min = Integer.MAX_VALUE;
		while (!pq.isEmpty()) {
			IntervalEvent cur = pq.poll();
			if (cur.isStart == 1) {
				set.add(cur.interval);
			} else {
				set.remove(cur.interval);
				if (min > set.size()) {
					min = set.size();
					res.clear();
					res.addAll(set);
				}
			}
		}
		
		return res;
	}
	
	public static void main(String[] args) {
		List<Interval> list1 = new ArrayList<Interval>();
		list1.add(new Interval(0, 2));
		list1.add(new Interval(4, 6));
		list1.add(new Interval(8, 10));
		
		List<Interval> list2 = new ArrayList<Interval>();
		list2.add(new Interval(1, 5));
		
		List<Interval> list3 = new ArrayList<Interval>();
		list3.add(new Interval(4, 10));
		
		List<List<Interval>> list = new ArrayList<List<Interval>>();
		list.add(list1);
		list.add(list2);
		list.add(list3);
		
		List<Interval> res = new Airbnb_找最少人重叠interval().getMinimalOverlapIntervals(list);
		System.out.println(res.size());
		for (Interval i : res) {
			System.out.println(i.start + "->" + i.end);
		}
	}
}
class IntervalEvent {
	int time;
	Interval interval;
	int isStart;
	public IntervalEvent(int t, int i, Interval in) {
		this.time = t;
		this.isStart = i;
		this.interval = in;
	}
}