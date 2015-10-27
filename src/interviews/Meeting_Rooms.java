package interviews;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

public class Meeting_Rooms {
	/**
	 * Given an array of meeting time intervals consisting of start and end
	 * times [[s1,e1],[s2,e2],...] (si < ei), determine if a person could attend
	 * all meetings.
	 * 
	 * For example, Given [[0, 30],[5, 10],[15, 20]], return false.
	 */
	public boolean canAttendMeetings(Interval[] intervals) {
		int len = intervals.length;
		if (len <= 1)
			return true;
		Arrays.sort(intervals, new Comparator<Interval>() {
			public int compare(Interval i1, Interval i2) {
				if (i1.start != i2.start) {
					return i1.start > i2.start ? 1 : -1;
				} else {
					if (i1.end != i2.end) {
						return i1.end > i2.end ? 1 : -1;
					}
				}
				return 0;
			}
		});
		int lastEnd = Integer.MIN_VALUE;
		for (int i = 0; i < len; i++) {
			if (lastEnd > intervals[i].start)
				return false;
			lastEnd = intervals[i].end;
		}
		return true;
	}

	/**
	 * Given an array of meeting time intervals consisting of start and end
	 * times [[s1,e1],[s2,e2],...] (si < ei), find the minimum number of
	 * conference rooms required.
	 * 
	 * For example, Given [[0, 30],[5, 10],[15, 20]], return 2.
	 * 
	 * @param intervals
	 * @return
	 */
	public int minMeetingRooms(Interval[] intervals) {
		int len = intervals.length;
		if (len <= 1)
			return len;
		int[] starts = new int[len];
		int[] ends = new int[len];
		for (int i = 0; i < len; i++) {
			starts[i] = intervals[i].start;
			ends[i] = intervals[i].end;
		}
		Arrays.sort(starts);
		Arrays.sort(ends);
		int numRooms = 0, available = 0, s = 0, e = 0;
		while (s < len) {
			if (starts[s] < ends[e]) {
				if (available > 0)
					available--;
				else
					numRooms++;
				s++;
			} else {
				available++;
				e++;
			}
		}
		return numRooms;
	}
	
	// priority queue 做法
	public int minMeetingRoomsIII(Interval[] intervals) {
        int len = intervals.length;
        if(len <= 1) return len;
        Arrays.sort(intervals, new Comparator<Interval>() {
           public int compare(Interval i1, Interval i2) {
               if (i1.start != i2.start) {
                   return i1.start - i2.start; 
               } else {
                   return i1.end - i2.end;
               }
           }
        });
        PriorityQueue<Integer> pq = new PriorityQueue<Integer>();
        pq.offer(intervals[0].end);
        for (int i = 1; i < len; i++) {
            int start = intervals[i].start;
            int end = pq.peek();
            if (start >= end) {
                pq.poll();
            }
            pq.offer(intervals[i].end);
        }
        return pq.size();
    }
}
