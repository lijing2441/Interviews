package interviews;

import java.util.Stack;
class Implement_Queue_Using_2_Stacks {
	/**
	 * Keep 2 stacks, let's call them inbox and outbox.
	 * 
	 * Queue: - Push the new element onto inbox
	 * 
	 * Dequeue: - If outbox is empty, refill it by popping each element from
	 * inbox and pushing it onto outbox - Pop and return the top element from
	 * outbox
	 * 
	 * Using this method, each element will be in each stack exactly once -
	 * meaning each element will be pushed twice and popped twice, giving
	 * amortized constant time operations.
	 */
	private Stack<Integer> inbox;
	private Stack<Integer> outbox;
	
	public  Implement_Queue_Using_2_Stacks(){
		inbox = new Stack<Integer>();  //newest elements
		outbox = new Stack<Integer>();  //oldest elements
	}
	//O(1) push
	public void enqueue(int x){
		inbox.push(x);
	}
	//O(n) or O(1) pop
	public int dequeue(){
		if(outbox.isEmpty()){
			while(!inbox.isEmpty()) 
				outbox.push(inbox.pop());
		}
		return outbox.pop();
	}
	//same to remove
	public int peek(){
		if(outbox.isEmpty()){
			while(!inbox.isEmpty()) 
				outbox.push(inbox.pop());
		}
		return outbox.peek();
	}
	public boolean isEmpty(){
		return inbox.isEmpty() && outbox.isEmpty();
	}
	public int size(){
		return inbox.size() + outbox.size();
	}
}
