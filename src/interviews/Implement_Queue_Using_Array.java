package interviews;

import java.util.Arrays;

public class Implement_Queue_Using_Array {
	private int head;
	private int tail;
	int capacity;
	int[] queue;

	public Implement_Queue_Using_Array(int capacity) {
		this.capacity = capacity;
		queue = new int[capacity];
		head = -1;
		tail = -1;
	}
	
	public int size() {
		if (head == -1 && tail == -1) return 0;
		if (tail < head) {
			return tail - head + 1 + capacity;
		} else {
			return tail - head + 1;
		}
	}
	
	public boolean isEmpty() {
		return (head == -1 && tail == -1);
	}

	public void enQueue(int value) {
		if ((tail + 1) % capacity == head) {
			throw new IllegalStateException("Queue is full");
		} else if (isEmpty()) {
			head++;
			tail++;
			queue[tail] = value;
		} else {
			tail = (tail + 1) % capacity;
			queue[tail] = value;
		}
	}

	public int deQueue() {
		Integer value = null;
		if (isEmpty()) {
			throw new IllegalStateException("Queue is empty, cant dequeue");
		} else if (head == tail) { // empty the queue
			value = queue[head];
			head = -1;
			tail = -1;
		} else {
			value = queue[head];
			head = (head + 1) % capacity;
		}
		return value;

	}

	@Override
	public String toString() {
		return "Queue [head=" + head + ", tail=" + tail + ", size=" + capacity
				+ ", queue=" + Arrays.toString(queue) + "]";
	}
}
