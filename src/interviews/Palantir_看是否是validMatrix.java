package interviews;

public class Palantir_看是否是validMatrix {
	/**
	 * 若任意一点0均可以连到所有0则valid
	 */
	public static boolean isValid(int[][] puzzle) {
		if (puzzle == null || puzzle.length == 0) {
			return false;
		}
		int m = puzzle.length, n = puzzle[0].length;
		boolean[][] marked = new boolean[m][n];
		helper(puzzle, 0, 0, marked);// dfs search all 0s connected to (0, 0)
		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				if (puzzle[i][j] == 0 && !marked[i][j]) {
					return false;
				}
			}
		}
		return true;

	}

	public static void helper(int[][] puzzle, int i, int j, boolean[][] marked) {
		if (!isValidIdx(puzzle, i, j)) {
			return;
		}
		if (marked[i][j]) {
			return;
		}
		if (puzzle[i][j] == 1) {
			return;
		} else {
			marked[i][j] = true;
			helper(puzzle, i - 1, j, marked);
			helper(puzzle, i + 1, j, marked);
			helper(puzzle, i, j - 1, marked);
			helper(puzzle, i, j + 1, marked);
		}
	}

	private static boolean isValidIdx(int[][] matrix, int i, int j) {
		int m = matrix.length, n = matrix[0].length;
		return i >= 0 && i < m && j >= 0 && j < n;
	}
}
