package interviews;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Pinterest_找value最高的interval {
	/**
	 * 给如下的数据格式:<start_time, end_time, value> For example,
	 * 
	 * 1, 3, 100
	 * 2, 4, 200
	 * 5, 6, 300
	 * ...
	 * 
	 * 这些数据时间点可能有重合。在时间段2~3之间,value的和是100+200=300.
	 * 
	 * 找出数据中最⾼的value和 [consider end points]
	 */
	public static List<ValueInterval> getHighestValueIntervals(List<ValueInterval> data) {
		List<ValueInterval> res = new ArrayList<ValueInterval>();
		if (data == null || data.size() == 0) return res;
		int len = data.size();
		List<ValueEvent> events = new ArrayList<ValueEvent>();
		for (ValueInterval in: data) {
			events.add(new ValueEvent(in.start, 1, in.value));
			events.add(new ValueEvent(in.end, -1, in.value));
		}
		Collections.sort(events, new Comparator<ValueEvent>() {
			public int compare(ValueEvent e1, ValueEvent e2) {
				if (e1.time != e2.time) return e1.time - e2.time;
				else return e2.isStart - e1.isStart;
			}
		});

		int maxValue = 0;
		int curSum = 0;
		int curStart = 0;
		for (int i = 0; i < 2 * len; i++) {
			ValueEvent cur = events.get(i);
			if (cur.isStart == 1) {
				curSum += cur.value;
				if (curSum > maxValue) {
					maxValue = curSum;
					res.clear();
					curStart = cur.time;
				} 
				else if (curSum == maxValue) {
					curStart = cur.time;
				}
			} else {
				if (curSum == maxValue) {
					res.add(new ValueInterval(curStart, cur.time, maxValue));
				}
				curSum -= cur.value;
			}
		}
		return res;
	}
	
	public static void main(String[] args) {
		ValueInterval v1 = new ValueInterval(1, 3, 100);
		ValueInterval v2 = new ValueInterval(2, 4, 200);
		ValueInterval v3 = new ValueInterval(5, 6, 300);
		ValueInterval v4 = new ValueInterval(5, 7, 700);
		List<ValueInterval> list = new ArrayList<>();
		list.add(v1);
		list.add(v2);
		list.add(v3);
		list.add(v4);
		List<ValueInterval> res = getHighestValueIntervals(list);
		for (ValueInterval v : res) {
			System.out.println(v.start + "->" + v.end + " of value: " + v.value);
		}
		//System.out.println(res);
	}
}
class ValueInterval {
	int start;
	int end;
	int value;
	public ValueInterval(int s, int e, int v) {
		this.start = s;
		this.end = e;
		this.value = v;
	}
}

class ValueEvent {
	int time;
	int isStart; // 1->start, -1->end
	int value;
	public ValueEvent(int s, int i, int v) {
		this.time = s;
		this.isStart = i;
		this.value = v;
	}
}