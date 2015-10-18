package interviews;

public class Implement_Stack_By_LinkedList {
	StackNode first;
	StackNode last;
	
	public Implement_Stack_By_LinkedList() {
		first.next = last;
	}
	public void push(int data) {
		if (first == null) {
			first = new StackNode(data);
		} else {
			last.next = new StackNode(data);
			last = last.next;
		}
	}
	public int pop() {
		if (first == null) return -1;
		else {
			int item = last.data;
			StackNode cur = first;
			while (cur.next.next != null) cur = cur.next;
			last = cur;
			last.next = null;
			return item;
		}
	}
	
	public int peek() {
		if (first == null) return -1;
		int item = last.data;
		return item;
	}
	
	private class StackNode {
		int data;
		StackNode next;
		private StackNode(int data) {
			this.data = data;
		}
	}
}
