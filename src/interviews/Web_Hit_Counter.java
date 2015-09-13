package interviews;

import java.util.Date;
import java.util.LinkedList;
import java.util.Queue;

public class Web_Hit_Counter {
	/**
	 * Use Queue! or N=900 array
	 * 
	 * First thought:
	 * 
	 * offer numbers of visitors in each minutes
	 * 
	 * Second thought:
	 * 
	 * offer each visitor information into queue and see whether the time of
	 * visitor in the tail exceed 15 minutes
	 */
	// first using queue
	private int N = 900; // 15 minutes
	private int count = 0;
	private Queue<Hit> q = new LinkedList<Hit>();
	public int getCount() {
		return count;
	}
	// need to be called every second
	public void move(Hit[] hitsThisSecond) {
		int curTime = getCurrentTime();
		for (Hit h: hitsThisSecond) {
			q.offer(h);
			count++;
		}
		while (q.peek().timestamp <= curTime - N) {
			q.poll();
			count--;
		}
	}
	
	public int getCurrentTime() {
		return 0;
	}
	
	// second data structure: use a 900 size array
	// in this case, we need to restore the relative array position
	int[] arr = new int[900];
	int curPosition = 0; // the pointer of the array
	public void move(int hitsThisSecond) {
		count += hitsThisSecond;
		arr[curPosition] = hitsThisSecond;
		count -= arr[curPosition - 1];
		curPosition++;
	}
	// or we can iterate this array to collect the sum
}

class Hit {
	int timestamp;
	User u;
	float ip;
}
class User {
	String name;
	Date createdAt;
}