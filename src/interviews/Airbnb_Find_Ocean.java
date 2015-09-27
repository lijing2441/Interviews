package interviews;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

public class Airbnb_Find_Ocean {
	/** Given: An array of strings where L indicates land and W indicates water, 
	 *  and a coordinate marking a starting point in the middle of the ocean.
	 *
	 * Q: Find and mark the ocean in the map by changing appropriate Ws to Os.
	 *   An ocean coordinate is defined to be the initial coordinate if a W, 
	 *   and any coordinate directly adjacent to any other ocean coordinate.
	 *
	 * void findOcean(String[] map, int row, int column);
	 *
	 * String[] map = new String[]{
	 *  "WWWLLLW",
	 *  "WWLLLWW",
	 *  "WLLLLWW"
	 * };
	 * printMap(map);
	 *
	 * STDOUT:
	 * WWWLLLW
	 * WWLLLWW
	 * WLLLLWW
	 *
	 * findOcean(map, 0, 1);
	 *
	 * printMap(map);
	 *
	 * STDOUT:
	 * OOOLLLW
	 * OOLLLWW
	 * OLLLLWW
	 */
	public static int[] x = {0, -1, 0, 1};
	public static int[] y = {1, 0, -1, 0};
	public static void findOcean(String[] map, int row, int col) {
		if (map[row].charAt(col) != 'W') return;
		
		int rowNum = map.length;
		int colNum = map[0].length();
		Queue<Integer> q = new LinkedList<Integer>();
		//Queue<Integer> cols = new LinkedList<Integer>();
		char[][] matrix = new char[rowNum][colNum];
		for (int i = 0; i < rowNum; i++) {
			char[] arr = map[i].toCharArray();
			for (int j = 0; j < colNum; j++) {
				matrix[i][j] = arr[j];
			}
		}
		Set<Integer> set = new HashSet<Integer>();
		matrix[row][col] = 'O';
		q.offer(row * colNum + col);
		//cols.offer(col);
		while (!q.isEmpty()) {
			int num = q.poll();
			int curRow = num / colNum;
			int curCol = num % colNum;
			for (int i = 0; i < 4; i++) {
				int nextRow = curRow + y[i];
				int nextCol = curCol + x[i];
				int code = nextRow * colNum + nextCol;
				if (set.contains(code)) continue;
				if (nextRow < rowNum && nextRow >= 0 && nextCol < colNum && nextCol >= 0 && matrix[nextRow][nextCol] == 'W') {
					matrix[nextRow][nextCol] = 'O';
					q.offer(nextRow * colNum + nextCol);
					set.add(code);
				}
			}
		}
		for (int i = 0; i < rowNum; i++) {
			char[] chars = new char[colNum];
			for (int j = 0; j < colNum; j++) {
				chars[j] = matrix[i][j];
			}
			map[i] = new String(chars);
 		}
	}
	
	public static void printMap(String[] map) {
		for (String s: map) {
			System.out.println(s);
		}
		System.out.println();
	}
	
	public static void main(String[] args) {
		String[] map = new String[]{"WWWLLLW",
				 					"WWLLLWW",
				 					"WLLLLWW"};
		
		printMap(map);
		findOcean(map, 0, 1);
		printMap(map);
	}
}
