package interviews;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;

public class Uber_Find_Path_In_Matrix {
	/**
	 * Find the shortest path in the matrix from one grid to another if possible
	 * 
	 * -1 => obstacle
	 * 0 => good to go
	 */
	public static int[] x = {0, -1, 0, 1};
	public static int[] y = {1, 0, -1, 0};
	public static List<PathStep> res = new ArrayList<PathStep>();
	public static boolean shortestPathInMatrix(int[][] matrix, Point A, Point B) {
		int rowA = A.y;
		int colA = A.x;
		int rowB = B.y;
		int colB = B.x;
		PathStep orig = new PathStep(rowA, colA, null);
		Set<Integer> check = new HashSet<Integer>();
		// id
		check.add(rowA * 100 + colA);
		Queue<PathStep> q = new LinkedList<PathStep>();
		q.offer(orig);
		while (!q.isEmpty()) {
			PathStep curStep = q.poll();
			if (curStep.row == rowB && curStep.col == colB) {
				res = getPath(curStep);
				return true;
			}
			for (int i = 0; i < 4; i++) {
				int curRow = curStep.row + y[i];
				int curCol = curStep.col + x[i];
				if (check.contains(curRow * 100 + curCol)) continue; // added before
				if (curRow >= 0 && curRow < matrix.length && curCol >= 0 && curCol < matrix[0].length && matrix[curRow][curCol] == 0) {
					PathStep next = new PathStep(curRow, curCol, curStep);
					q.offer(next);
					check.add(curRow * 100 + curCol);
				}
			}
		}
		return false;
	}
	
	public static List<PathStep> getPath(PathStep dest) {
		List<PathStep> res = new ArrayList<PathStep>();
		PathStep cur = dest;
		while (cur != null) {
			res.add(0, cur);
			cur = cur.prev;
		}
		return res;
	}
	public static void main(String[] args) {
		Point A = new Point(1, 3);
		Point B = new Point(3, 1);
		// 2 => dest
		int[][] matrix = {
				{0, 0, 0,-1,0},
				{0,-1, 0, 0,0},
				{0,-1,-1,-1,0},
				{0, 0,-1, 0,0},
				{0, 0,-1, 0,0},
				{0, 0, 0, 0,0}
		};
		if(shortestPathInMatrix(matrix, A, B)) {
			System.out.println(res.size());
			for (PathStep p: res) {
				System.out.println(p.toString());
			}
		}
		else {
			System.out.println("No Path found");
		}
	}
}
class PathStep {
	int row, col;
	PathStep prev;

	public PathStep(int i, int j, PathStep prev) {
		this.row = i;
		this.col = j;
		this.prev = prev;
	}

	public String toString() {
		return "x:" + row + ", y:" + col;
	}
}