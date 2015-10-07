package interviews;

import java.util.LinkedList;
import java.util.Queue;

public class Walls_and_Gates {
	/**
	 * You are given a m x n 2D grid initialized with these three possible values.

-1 - A wall or an obstacle.
0 - A gate.
INF - Infinity means an empty room. We use the value 231 - 1 = 2147483647 to represent INF as you may assume that the distance to a gate is less than 2147483647.
Fill each empty room with the distance to its nearest gate. If it is impossible to reach a gate, it should be filled with INF.

For example, given the 2D grid:
INF  -1  0  INF
INF INF INF  -1
INF  -1 INF  -1
  0  -1 INF INF
After running your function, the 2D grid should be:
  3  -1   0   1
  2   2   1  -1
  1  -1   2  -1
  0  -1   3   4
	 */
	
	public int[] x = {1, 0, -1, 0};
    public int[] y = {0, 1, 0, -1};
    public void wallsAndGates(int[][] rooms) {
        int m = rooms.length;
        if (m == 0) return;
        int n = rooms[0].length;
        if (n == 0) return;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (rooms[i][j] == 0) {
                    bfs(rooms, i, j);
                }
            }
        }
    }
    public void bfs(int[][] rooms, int row, int col) {
        int m = rooms.length;
        int n = rooms[0].length;
        Queue<Integer> recordQ = new LinkedList<Integer>();
        recordQ.offer(row * n + col);
        while (!recordQ.isEmpty()) {
            int curElem = recordQ.poll();
            int curRow = curElem / n;
            int curCol = curElem % n;
            for (int i = 0; i < 4; i++) {
                int newRow = curRow + y[i];
                int newCol = curCol + x[i];
                if (newRow >= 0 && newRow < m && newCol >= 0 && newCol < n && rooms[newRow][newCol] > rooms[curRow][curCol] + 1) {
                    rooms[newRow][newCol] = rooms[curRow][curCol] + 1;
                    recordQ.offer(newRow * n + newCol);
                }
            }
        }
    }
}
