package interviews;

import java.util.ArrayList;
import java.util.List;

public class Snapchat_对角打印 {
	/**
	 * 从东北往西南打印矩阵：遍历第一行和最后一列，打印的坐标为(i + 1 , j - 1)
	* for example:
	* input:
	* 1 2 3 4
	* 5 6 7 8
	* 
	* output:
	* 1
	* 2 5. 
	* 3 6
	* 4 7
	* 8
	 */
	public static List<List<Integer>> getMatrixDiagonally(int[][] matrix) {
		int m = matrix.length, n = matrix[0].length;
		List<List<Integer>> res = new ArrayList<List<Integer>>();
		for (int i = 0; i < m + n - 1; i++) {
			List<Integer> cur = new ArrayList<Integer>();
			for (int col = Math.min(i, n - 1); col >= Math.max(0, i - m + 1); col--) {
                cur.add(matrix[i - col][col]);
            }
			res.add(cur);
		}
		return res;
	}
	public static void main(String[] args) {
		int[][] matrix = {
				{1, 2, 3, 4},
				{5, 6, 7, 8}
		};
		List<List<Integer>> res = getMatrixDiagonally(matrix);
		for (List<Integer> l : res) {
			System.out.println(l);
		}
	}
}
