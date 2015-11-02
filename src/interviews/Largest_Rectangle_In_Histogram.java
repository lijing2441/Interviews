package interviews;

import java.util.Stack;

public class Largest_Rectangle_In_Histogram {
	/**
	 * Given n non-negative integers representing the histogram's bar height
	 * where the width of each bar is 1, find the area of largest rectangle 
	 * in the histogram.
	 * 
	 * @thought 
	 * Use two stacks to keep track of the heights and corresponding indices
	 * one pass: O(n) (each element is pushed and popped only once); Stack: O(n) space
	 * 
	 * 		As long as the current histogram has a higher height than the last one
	 * 			-> push it to stack; 
	 * 
	 * 		Otherwise pop to get the largest rectangle so far. 
	 * 			-> Pop until the stack top has a lower height than the current one, 
	 * 			-> Push the current height to the stack, and the last popped indices to 
	 * 	  		   the indices stack, since this should be the longest area we can get 
	 * 			   with the current height.
	 */
	public int largestRectangleArea(int[] height) {
        int n = height.length;
        if(n == 0) return 0;
        // we use one stack to store the heights, one to store the index corresponding the height 
        Stack<Integer> stack = new Stack<Integer>();
        Stack<Integer> index = new Stack<Integer>();
        int maxHist = 0;
        for(int i = 0; i < n; i++){
            if(stack.isEmpty() || stack.peek() <= height[i]){
                stack.push(height[i]);
                index.push(i);
            }else{
            	// initialize to a number
                int j = 0;
                // pop until the one that is smaller than the current height
                // thus, the elements in stack are always sorted increasingly.
                while(!stack.isEmpty() && stack.peek() > height[i]){
                    j = index.pop();
                    int cur = (i - j) * stack.pop();
                    if(cur > maxHist) maxHist = cur;
                }
                // since we get this height[i] with at least j - i elements
                stack.push(height[i]);
                index.push(j);
            }
        }
        // the elements in stack is increasingly sorted, so as popped, the height should reduce
        // thus we just need to multiply the smaller height, that is, the newly popped one
        while(!stack.isEmpty()){
            int cur = (n - index.pop()) * stack.pop();
            if(cur > maxHist) maxHist = cur;
        }
        return maxHist;
    }
	
	// another way
	public int largestRectangleArea2(int[] height) {
        if (height == null || height.length == 0) return 0;
        Stack<Integer> stack = new Stack<Integer>();
        Stack<Integer> index = new Stack<Integer>();
        int max = 0;
        for (int i = 0; i <= height.length; i++) {
            if (i < height.length && (stack.isEmpty() || height[i] >= stack.peek())) {
                stack.push(height[i]);
                index.push(i);
            } else {
                int k = 0;
                while (!stack.isEmpty() && (i == height.length || height[i] < stack.peek())) {
                    k = index.pop();
                    int cur = (i - k) * stack.pop();
                    if (cur > max) max = cur;
                }
                if (i < height.length) {
                    stack.push(height[i]);
                    index.push(k);
                }
            }
        }
        return max;
    }
}
