package interviews;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

public class Airbnb_找空闲时间 {
	public List<Segment> schedule(List<List<Segment>> list, int start, int end) {
        List<Segment> res = new ArrayList<>();

        PriorityQueue<Segment> heap = new PriorityQueue<>(new Comparator<Segment>() {
            @Override
            public int compare(Segment s1, Segment s2) {
                if (s1.start != s2.start) {
                    return s1.start - s2.start;
                }
                return s1.end - s2.end;
            }
        });

        for (List<Segment> person : list) {
            for (Segment seg : person) {
                heap.offer(seg);
            }
        }

        int lastEndTime = start;
        while (!heap.isEmpty()) {
            Segment seg = heap.poll();
            boolean isLast = false;

            if (seg.end >= end) {
                isLast = true;
            }

            if (seg.start > lastEndTime) {
                res.add(new Segment(lastEndTime, seg.start));
                lastEndTime = seg.end;
            } else {
                lastEndTime = Math.max(lastEndTime, seg.end);
            }

            if (isLast) {
                break;
            }
        }

        if (end > lastEndTime) {
            res.add(new Segment(lastEndTime, end));
        }

        return res;
    }

    public static void main(String[] args) {
        List<List<Segment>> input = new ArrayList<>();
        List<Segment> l1 = new ArrayList<>();
        List<Segment> l2 = new ArrayList<>();
        List<Segment> l3 = new ArrayList<>();
        input.add(l1);
        input.add(l2);
        input.add(l3);

        l1.add(new Segment(0, 50));
        l1.add(new Segment(100, 120));

        l2.add(new Segment(70, 90));

        l3.add(new Segment(60, 80));
        l3.add(new Segment(110, 130));

        List<Segment> res = new Airbnb_找空闲时间().schedule(input, 55, 150);
        for (Segment seg : res) {
            System.out.println(seg.start + " -> " + seg.end);
        }
    }
    
 // Follow up 把所有人的meeting放到一起就和meeting room ii 一样了
    /*
        class Event {
            int time;
            int isStart;
            Interval interval;

            public Event(int time, int isStart, Interval interval) {
                this.time = time;
                this.isStart = isStart;
                this.interval = interval;
            }
        }

        public int minMeetingRooms(Interval[] intervals) {
            if (intervals == null || intervals.length == 0) {
                return 0;
            }

            PriorityQueue<Event> heap = new PriorityQueue<>(intervals.length * 2, new Comparator<Event>() {
                @Override
                public int compare(Event e1, Event e2) {
                    if (e1.time != e2.time) {
                        return e1.time - e2.time;
                    }

                    return e1.isStart - e2.isStart;
                }
            });

            for (Interval interval : intervals) {
                heap.offer(new Event(interval.start, 1, interval));
                heap.offer(new Event(interval.end, 0, interval));
            }

            Set<Interval> set = new HashSet<>();
            int res = 0;
            while (!heap.isEmpty()) {
                Event e = heap.poll();

                if (e.isStart == 1) {
                    set.add(e.interval);
                    res = Math.max(res, set.size());
                } else {
                    set.remove(e.interval);
                }
            }

            return res;
        }
        */
}
class Segment {
    int start;
    int end;

    public Segment(int start, int end) {
        this.start = start;
        this.end = end;
    }
}