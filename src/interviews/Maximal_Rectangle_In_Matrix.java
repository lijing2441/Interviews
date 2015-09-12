package interviews;

import java.util.Stack;
/**
 * Given a 2D binary matrix filled with 0's and 1's, find the 
 * largest rectangle containing all ones and return its area.
 */
public class Maximal_Rectangle_In_Matrix {
	//using stacks to keep track of the index
	//based on largest rectangle in histogram, starting from each row and extend downwards
	//Complexity: O(n^2) and O(n) space 
	
	//The complexity cannot be better than O(n^2). 
	//To see this, consider a rectangle that contains all 1's. 
	//You definitely have to check each and every square to find the maximum rectangle.
	
	public int maximalRectangle(char[][] matrix) {
        int rowNum = matrix.length;
        if(rowNum == 0) return 0;
        int colNum = matrix[0].length;
        if(colNum == 0) return 0;
        // use the last column to avoid the need to clear the stacks
        int[] heightForCols = new int[colNum + 1];
        int max = 0;
        for(int i = 0; i < rowNum; i++){
            Stack<Integer> stack = new Stack<Integer>();
            Stack<Integer> index = new Stack<Integer>();
            // the last loop to empty the stack, or we need to loop through to clear the stack
            // in order to find the area with length (colNum - index.pop()) * stack.pop()
            for(int j = 0; j <= colNum; j++){  
            	//only difference with the histogram
                if(j < colNum && matrix[i][j] == '1') heightForCols[j] += 1;
                else heightForCols[j] = 0;
                
                if(stack.isEmpty() || heightForCols[j] >= stack.peek()){
                    stack.push(heightForCols[j]);
                    index.push(j);
                }else{
                    int k = 0;
                    while(!stack.isEmpty() && heightForCols[j] < stack.peek()){
                        k = index.pop();
                        int cur = stack.pop() * (j - k);
                        if(cur > max) max = cur;
                    }
                    stack.push(heightForCols[j]);
                    index.push(k);
                }
            }
        }
        return max;
    }
}
