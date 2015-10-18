package interviews;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

public class Airbnb_Get_Free_Interval_of_All_People {
	/**
	 * Given a lot of person, who has a sorted schedule each (a list of intervals).
	 * Get the free interval for all people.
	 */
	
	public static List<Interval> getFreeIntervals(List<Person> schedules){
		List<Interval> res = new ArrayList<Interval>();
		if(schedules == null || schedules.size() == 0) return res;
		int personNum = schedules.size();
		// Add them into a heap of size m - number of persons in the schedules
		int[] indice = new int[personNum]; // indicate which interval we are throw into the heap
		PriorityQueue<IntervalWithIndex> pq = new PriorityQueue<IntervalWithIndex>(personNum, new Comparator<IntervalWithIndex>() {
			public int compare(IntervalWithIndex i1, IntervalWithIndex i2) {
				return i1.interval.start - i2.interval.start;
			}
		});
		// build a minimal heap with the first element
		// if the list is Empty, we can ignore it
		for (int i = 0; i < personNum; i++) {
			Interval[] schedule = schedules.get(i).meeings;
			if (schedule != null && schedule.length != 0) {
				Interval first = schedule[0];
				IntervalWithIndex cur = new IntervalWithIndex(i, first);
				pq.offer(cur);
			}
		}
		// merge them into the same list according to their start time, O(n)
		List<Interval> merged = new ArrayList<Interval>();
		int start = 0, end = 0;
		while (!pq.isEmpty()) {
			IntervalWithIndex cur = pq.poll();
			int curStart = cur.interval.start;
			int curEnd = cur.interval.end;
			if (curStart <= end) {
				start = Math.min(start, curStart);
				end = Math.max(end, curEnd);
			} else {
				merged.add(new Interval(start, end));
				start = curStart;
				end = curEnd;
			}
			// see if there is another one in the same schedule
			int personIndex = cur.index;
			int curIndex = indice[personIndex];
			if (curIndex < schedules.get(personIndex).meeings.length - 1) {
				indice[personIndex]++;
				IntervalWithIndex next = new IntervalWithIndex(personIndex, schedules.get(personIndex).meeings[indice[personIndex]]);
				pq.offer(next);
			}
		}
		if (start < end) {
			merged.add(new Interval(start, end));
		}
		
		//get the holes, O(n)
		for(int k = 0; k < merged.size() - 1; k++){
			res.add(new Interval(merged.get(k).end, merged.get(k + 1).start));
		}
		return res;
	}
	
	public static void main(String[] args) {
		Interval i1 = new Interval(0, 2);
		Interval i2 = new Interval(4, 6);
		Interval i3 = new Interval(8, 10);
		Interval[] meetings1 = {i1 ,i2, i3};
		Person p1 = new Person(meetings1);
		Interval i4 = new Interval(1, 3);
		Interval i5 = new Interval(8, 11);
		Interval[] meetings2 = {i4 ,i5};
		Person p2 = new Person(meetings2);
		Interval i6 = new Interval(0, 1);
		Interval i7 = new Interval(5, 7);
		Interval[] meetings3 = {i6 ,i7};
		Person p3 = new Person(meetings3);
		List<Person> persons = new ArrayList<Person>();
		persons.add(p1);
		persons.add(p2);
		persons.add(p3);
		List<Interval> res = getFreeIntervals(persons);
		for (Interval i : res) {
			System.out.println(i.start + "->" + i.end);
		}
	}
}
class Person {
	Interval[] meeings;
	public Person(Interval[] m) {
		this.meeings = m;
	}
}
class IntervalWithIndex {
	int index;
	Interval interval;
	public IntervalWithIndex(int i, Interval interval) {
		this.index = i;
		this.interval = interval;
	}
}