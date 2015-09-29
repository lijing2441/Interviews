package interviews;

import java.util.Stack;

public class Verify_Preorder_Sequence_In_BST {
	// iterative is much faster
	public boolean verifyPreorder(int[] preorder) {
        if(preorder.length < 2) return true;
        int min = Integer.MIN_VALUE;
        Stack<Integer> stack = new Stack<Integer>();
        for(int i : preorder) {
            if(i < min) return false;
            while(!stack.isEmpty() && i > stack.peek()) {
                min = stack.pop();
            }
            stack.push(i);
        }
        return true;
    }
	
	// 或者abuse the array
	public boolean verifyPreorderArr(int[] preorder) {
        if(preorder.length < 2) return true;
        int min = Integer.MIN_VALUE, minIndex = -1;
        for(int i : preorder) {
            if(i < min) return false;
            while(minIndex >= 0 && i > preorder[minIndex]) {
                min = preorder[minIndex];
                minIndex--;
            }
            preorder[++minIndex] = i;
        }
        return true;
    }
	
	// recursive
	public boolean verifyPreorderRecur(int[] preorder) {
        if(preorder.length < 2) return true;
        return helper(preorder, 0, preorder.length - 1);
    }
    public boolean helper(int[] preorder, int start, int end) {
        if(start >= end) return true;
        int rootVal = preorder[start];
        int rightStart = -1;
        for(int i = start + 1; i <= end; i++) {
            if(rightStart == -1 && preorder[i] > rootVal) rightStart = i;
            if(rightStart != -1 && preorder[i] < rootVal) return false;
        }
        if(rightStart == -1) {
            return helper(preorder, start + 1, end);
        } else {
            return helper(preorder, start, rightStart - 1) && helper(preorder, rightStart, end);
        }
    }
}
