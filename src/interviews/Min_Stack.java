package interviews;

import java.util.Stack;

public class Min_Stack {
	//keep track of two stacks
	public Stack<Integer> stack;
    public Stack<Integer> minStack;
    public Min_Stack(){
        stack = new Stack<Integer>();
        minStack = new Stack<Integer>();
    }
    
    public void push(int x) {
        stack.push(x);
        if(minStack.isEmpty() || x <= minStack.peek()){
            minStack.push(x);
        }
    }

    public void pop() {
        if(stack.isEmpty()) return;
        int x = stack.pop();
        if(x == minStack.peek()) minStack.pop();
    }

    public int top() {
        return stack.peek();
    }

    public int getMin() {
        return minStack.peek();
    }
}
