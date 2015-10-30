package interviews;

import java.util.ArrayList;

public class Spiral_Matrix {
	/**
	 * Given a matrix of m x n elements (m rows, n columns), return all elements of the matrix in spiral order.
	 * For example.
	 * Given the following matrix:
	 * [
	 * 	[ 1, 2, 3 ],
	 * 	[ 4, 5, 6 ],
	 * 	[ 7, 8, 9 ]
	 * ]
	 * You should return [1,2,3,6,9,8,7,4,5].
	 */
	public ArrayList<Integer> spiralOrder(int[][] matrix) {
        ArrayList<Integer> res = new ArrayList<Integer>();
        int m = matrix.length;
        if(m == 0) return res;
        int n = matrix[0].length;
        if(n == 0) return res;
        int top = 0;
        int bottom = m - 1;
        int left = 0;
        int right = n - 1;
        while(left <= right && top <= bottom){
            for(int i = left; i <= right; i++){
                res.add(matrix[top][i]);
            }
            top++;
            for(int i = top; i <= bottom; i++){
                res.add(matrix[i][right]);
            }
            right--;
            if(top <= bottom){
                for(int i = right; i >= left; i--){
                    res.add(matrix[bottom][i]);
                }
                bottom--;
            }
            if(left <= right){
                for(int i = bottom; i >= top; i--){
                    res.add(matrix[i][left]);
                }
                left++;
            }
        }
        return res;
    }
	/**
	 * Problem II: Given an integer n, generate a square matrix filled with elements from 1 to n2 in spiral order.
	 * For example, given n = 3,
	 * You should return the following matrix:
	 * [
	 * 	[ 1, 2, 3 ],
	 *  [ 8, 9, 4 ],
	 *  [ 7, 6, 5 ]
	 * ]
	 */
	public int[][] generateMatrix(int n) {
        int[][] res = new int[n][n];
        if(n < 1) return res;
        if(n == 1){
            res[0][0] = 1;
            return res;
        }
        int left = 0; 
        int right = n-1;
        int top = 0;
        int bottom = n-1;
        int curLevel = 0;
        int curNum = 1;
        while(left <= right && top <= bottom && curLevel < n){
            for(int i = left; i <= right; i++){
                res[top][i] = curNum++;
            }
            top++;
            for(int i = top; i <= bottom; i++){
                res[i][right] = curNum++;
            }
            right--;
            if(top <= bottom && curLevel < n){
                for(int i = right; i >= left; i--){
                    res[bottom][i] = curNum++;
                }
                bottom--;
            }
            if(left <= right && curLevel < n){
                for(int i = bottom; i >= top; i--){
                    res[i][left] = curNum++;
                }
                left++;
            }
        }
        return res;
    }
}