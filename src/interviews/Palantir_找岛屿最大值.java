package interviews;

import java.util.LinkedList;
import java.util.Queue;

public class Palantir_找岛屿最大值 {
	/**
	 * 给一个2D的matrix，里面是数字。0代表这个点是水，其他数字代表陆地，相邻的陆地会形成一个岛屿。求所有岛屿的最大值。
	比如：
	1 2 0 0
	3 0 4 0
	0 1 0 1
	结果就是1 + 2 + 3 = 6.

	Follow up 1: 如果存在值为负数的点会有什么影响？并相应的修改之前的code。
	Follow up 2: 之前是用recursive的方法实现的，所以让用iterative的方法重新写一下。
	 */
	static int[] x = {0, 1, 0, -1};
	static int[] y = {1, 0, -1, 0};
	public static int getMaxIsland(int[][] matrix) {
		int m = matrix.length;
		int n = matrix[0].length;
		int max = 0;
		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				if (matrix[i][j] != 0) {
					int curSum = getNumFromIsland(matrix, i, j);
					if (curSum > max) {
						max = curSum;
					}
				}
			}
		}
		return max;
	}
	public static int getNumFromIsland(int[][] matrix, int i, int j) {
		int sum = 0;
		int m = matrix.length, n = matrix[0].length;
		Queue<Integer> q = new LinkedList<Integer>();
		q.offer(i * n + j);
		while (!q.isEmpty()) {
			int cur = q.poll();
			int curRow = cur / n;
			int curCol = cur % n;
			sum += matrix[curRow][curCol];
			matrix[curRow][curCol] = 0;
			for (int k = 0; k < 4; k++) {
				int nextRow = curRow + x[k];
				int nextCol = curCol + y[k];
				// check boundary
				if (nextRow < 0 || nextRow >= m || nextCol < 0 || nextCol >= n) continue;
				if (matrix[nextRow][nextCol] != 0) {
					q.offer(nextRow * n + nextCol);
				}
			}
		}
		return sum;
	}
	public static void main(String[] args) {
		int[][] matrix = {
				{1, 2, 0, 0},
				{3, 0, 4, 0},
				{0, 1, 0, 1},	
		};
		System.out.println(getMaxIsland(matrix));
		
	}
}
