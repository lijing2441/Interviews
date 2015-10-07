package interviews;

public class Game_of_Life {
	/**
	 * Given a board with m by n cells, each cell has an initial state live (1)
	 * or dead (0). Each cell interacts with its eight neighbors (horizontal,
	 * vertical, diagonal) using the following four rules (taken from the above
	 * Wikipedia article):
	 * 
	 * Any live cell with fewer than two live neighbors dies, as if caused by under-population.
	 * 
	 * Any live cell with two or three live neighbors lives on to the next generation.
	 * 
	 * Any live cell with more than three live neighbors dies, as if by over-population.
	 * 
	 * Any dead cell with exactly three live neighbors becomes a live cell, as if by reproduction.
	 * 
	 * Write a function to compute the next state (after one update) of the
	 * board given its current state.
	 * 
	 * Follow up: in-place?
	 * 
	 */
	public void gameOfLife(int[][] board) {
		int m = board.length;
		if (m == 0)
			return;
		int n = board[0].length;
		if (n == 0)
			return;
		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				int countLive = 0;
				for (int p = Math.max(0, i - 1); p < Math.min(m, i + 2); p++) {
					for (int q = Math.max(0, j - 1); q < Math.min(n, j + 2); q++) {
						if (board[p][q] == 2 || board[p][q] == 1)
							countLive++;
					}
				}
				countLive -= board[i][j];
				if (board[i][j] == 0 && countLive == 3)
					board[i][j] = 3;
				else if (board[i][j] == 1 && (countLive < 2 || countLive > 3))
					board[i][j] = 2;
			}
		}
		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				board[i][j] %= 2;
			}
		}
	}
}
