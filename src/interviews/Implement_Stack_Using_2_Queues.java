package interviews;

import java.util.LinkedList;
import java.util.Queue;

public class Implement_Stack_Using_2_Queues {
	/**
	 * Given two queues with their standard operations (enqueue, dequeue,
	 * isempty, size), implement a stack with its standard operations (pop,
	 * push, isempty, size).
	 * 
	 * There should be TWO versions of the solution.
	 * 
	 * Version A: The stack should be efficient when pushing an item. 
	 * Version B: The stack should be efficient when popping an item.
	 * 
	 * @solution
	 * 
	 *           Version A:
	 *           push: -> enqueue in queue1 
	 *           pop: 
	 *           -> while size of queue1 is bigger than 1, pipe dequeued items from queue1 into queue2 
	 *           -> dequeue and return the last item of queue1, then switch the names of queue1
	 *           	and queue2.
	 */
	public Queue<Integer> queue1;
	public Queue<Integer> queue2;
	
	public Implement_Stack_Using_2_Queues(){
		queue1 = new LinkedList<Integer>();
		queue2 = new LinkedList<Integer>();
	}
	
	/**
	 * version A: efficient push, inefficient pop
	 */
	public void pushA(int x){
		queue1.add(x);
	}
	public int popA(){
		while(queue1.size() > 1){
			queue2.add(queue1.poll());
		}
		int toReturn = -1;
		if(queue1.size() > 0) toReturn = queue1.poll();
		// add it back
		while(!queue2.isEmpty()) queue1.add(queue2.remove());
		return toReturn;
	}
	
	/**
	 * version B: efficient pop, inefficient push
	 * 
	 * push: -> enqueue in queue2 
	 *       -> enqueue all items of queue1 in queue2, then switch the names of queue1 and queue2 
	 * pop: -> dequeue from queue1
	 *  	   
	 * after each push and each pop, all items are in queue1, while queue2 is empty.
	 */
	public int popB(int x){
		return queue1.remove();
	}
	public void pushB(int data){
		if (queue1.peek() == null) {
            queue1.add(data);
        } else {
            while (!queue1.isEmpty()) {
                queue2.add(queue1.remove());
            }
            queue1.add(data);
            while (!queue2.isEmpty()) {
                queue1.add(queue2.remove());
            }
        }
	}
}

class MyStack {
	private int maxSize;
	private long[] stackArray;
	private int top;

	public MyStack(int s) {
		maxSize = s;
		stackArray = new long[maxSize];
		top = -1;
	}

	public void push(long j) {
		stackArray[++top] = j;
	}

	public long pop() {
		return stackArray[top--];
	}

	public long peek() {
		return stackArray[top];
	}

	public boolean isEmpty() {
		return (top == -1);
	}
	
	public boolean isFull() {
		return (top == maxSize - 1);
	}
}
