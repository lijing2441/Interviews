package interviews;

import java.util.Map;
import java.util.HashMap;

public class SubMatrix_Sum_to_Zero {
	/**
	 * Given an integer matrix, find a submatrix where the sum of numbers is zero. 
	 * Your code should return the coordinate of the left-up and right-down number.
	 * Given matrix
	[
  		[1 ,5 ,7],
  		[3 ,7 ,-8],
  		[4 ,-8 ,9],
	]
		return [(1,1), (2,2)]
	 *
	 * 用同样subarray_to_0 + maxsubmatrix思想，先preprocess到sum[][],然后定下row的起始和终止点
	 * 
	 * 然后对col开始巡回，如果发现一样的值，说明从上一个相同值出现之后的所有数，相加得0
	 */
	public int[][] submatrixSum(int[][] matrix) {
        if (matrix.length == 0 || matrix[0].length == 0) return null;
        // edge cases，有一个元素直接为0
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                if (matrix[i][j] == 0) {
                    int[][] res = new int[2][2];
                    res[0][0] = i;
                    res[0][1] = j;
                    res[1][0] = i;
                    res[1][1] = j;
                    return res;
                }
            }    
        }
        // get the sum matrix
        int[][] sumMatrix = new int[matrix.length + 1][matrix[0].length + 1];
        preprocess(matrix, sumMatrix);
        // 轮询去找sum=0
        for (int rowStart = 0; rowStart < matrix.length; rowStart++) {
            for (int rowEnd = rowStart + 1; rowEnd <= matrix.length; rowEnd++) {
                Map<Integer, Integer> map = new HashMap<Integer, Integer>();
                for (int col = 0; col <= matrix[0].length; col++) {
                    // from 0 -> current col
                    int diff = sumMatrix[rowEnd][col] - sumMatrix[rowStart][col];
                    // 找到一个
                    if (map.containsKey(diff)) {
                        int[][] res = new int[2][2];
                        int colStart = map.get(diff);
                        res[0][0] = rowStart;
                        res[1][0] = rowEnd - 1;
                        res[0][1] = colStart;
                        res[1][1] = col - 1;
                        return res;
                    }
                    map.put(diff, col);
                }
            }
        }
        return null;
    }
    // make sum[i][j] => sum from (0,0) -> (i, j)
    public void preprocess(int[][] matrix, int[][] sum) {
        int m = matrix.length;
        int n = matrix[0].length;
        //sum[0][0] = 0;
        for (int i = 0; i < m; i++) {
            sum[i][0] = 0;
        }
        for (int i = 1; i < n; i++) {
            sum[0][i] = 0;
        }
        for(int i = 0; i < m; i++) {
            for(int j = 0; j < n; j++) {
                sum[i + 1][j + 1] = matrix[i][j] + sum[i + 1][j] + sum[i][j + 1] - sum[i][j];
            }
        }
    }
}
