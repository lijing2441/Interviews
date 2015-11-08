package interviews;

import java.util.HashSet;
import java.util.Set;

/**
 * Except the backtracking brute force method used here, we can: -> randomly
 * assign numbers to the blank cells -> calculate the number of errors ->
 * shuffle the inserted numbers around the grid until the # of mistakes is
 * reduced to 0.
 */

public class Sudoku {
	public boolean isValidSudokuAnother(char[][] board) {
		// check every line
		for (int i = 0; i < 9; i++) {
			if (!check(board, i, i, 0, 8)) {
				return false;
			}
		}

		// check every col
		for (int j = 0; j < 9; j++) {
			if (!check(board, 0, 8, j, j)) {
				return false;
			}
		}

		// check every 3*3 block
		for (int i = 0; i < 9; i = i + 3) {
			for (int j = 0; j < 9; j = j + 3) {
				if (!check(board, i, i + 2, j, j + 2)) {
					return false;
				}
			}
		}

		return true;
	}

	public boolean check(char[][] board, int rows, int rowe, int cols, int cole) {
		Set<Integer> already = new HashSet<Integer>();

		for (int i = rows; i <= rowe; i++) {
			for (int j = cols; j <= cole; j++) {
				if (board[i][j] == '.') {
					continue;
				}
				int tmp = board[i][j] - '0';

				if (tmp == 0 || already.contains(tmp)) {
					return false;
				}
				already.add(tmp);
			}
		}
		return true;
	}

	/**
	 * Problem II: Sodoku solver
	 * 
	 * Write a program to solve a Sudoku puzzle by filling the empty cells.
	 * 
	 * Empty cells are indicated by the character '.'.
	 * 
	 * You may assume that there will be only one unique solution.
	 * 
	 * 这种类型的游戏一般回溯法来解决，设置某个空格时，如果该空格无论设置什么数字都无法达到合法状态，那么回溯重新设置上一个空格
	 * 
	 * backtracking的递归函数，怎么能没有返回值呢？！因为要判断递归的方案正确与否，所以这里的递归一定是有返回值的
	 * （除非是combination那种没有正确错误概念的backtracking）
	 * 
	 * 可以考虑“先放置，再判断”的方案。比如这里，首先判断当前位置是否为空，如果为空，那么放置一个元素，检查它是否正确。
	 * 如果正确，就继续进行下面的递归。当函数返回错误之后,将刚刚的数值变为空，再进行下一次尝试即可。
	 * 
	 * 所有的方案（k从1到9）完毕之后，应该返回错误，这个是不应该被忽略的。
	 */
	public void solveSudoku(char[][] board) {
		if (board.length != 9 || board[0].length != 9)
			return;
		helper(board, 0, 0);
	}

	public boolean helper(char[][] board, int i, int j) {
		if (j >= 9) {
			return helper(board, i + 1, 0);
		}
		if (i >= 9) {
			return true;
		}
		if (board[i][j] == '.') {
			for (int l = 1; l <= 9; l++) {
				char c = (char) ('0' + l);
				if (isValid(board, i, j, c)) { // 先判断可放，再放
					board[i][j] = c;
					if (helper(board, i, j + 1))
						return true;
					board[i][j] = '.';
				}
			}
		} else {
			return helper(board, i, j + 1);
		}
		return false;
	}

	public boolean isValid(char[][] board, int row, int col, char c) {
		for (int i = 0; i < 9; i++) {
			if (i != row && board[i][col] == c)
				return false;
		}
		for (int i = 0; i < 9; i++) {
			if (i != col && board[row][i] == c)
				return false;
		}
		for (int i = row / 3 * 3; i < (row / 3 * 3 + 3); i++) {
			for (int j = col / 3 * 3; j < (col / 3 * 3 + 3); j++) {
				if ((i != row || j != col) && c == board[i][j])
					return false;
			}
		}
		return true;
	}

	// valid sudoku 数学方法
	/**
	 * Problem I: Determine if a Sudoku is valid.
	 * 
	 * 在检测行是否合法时，i 表示矩阵的行，j 表示矩阵的列； 检测列是否合法时，i 表示矩阵的列，j 表示矩阵的行； 检测九宫格是否合法时，i
	 * 表示九宫格的标号，j 表示九宫格里的每个元素 （只是我们需要根据i、j定位相应的元素到原来的矩阵：第 i 个九宫格里面的第 j 个元素在原矩阵的
	 * 第 3*(i/3) + j/3 行，第 3*(i%3) + j%3）列，“/” 表示整数除法）
	 */
	public boolean isValidSudoku(char[][] board) {
		if (board.length != 9 || board[0].length != 9)
			return false;
		for (int i = 0; i < 9; i++) {
			// we just use the 1-9 indices
			boolean[] row = new boolean[10];
			boolean[] col = new boolean[10];
			boolean[] box = new boolean[10];
			for (int j = 0; j < 9; j++) {
				if (!isValid(row, board[i][j])
						|| !isValid(col, board[j][i])
						|| !isValid(box, board[3 * (i / 3) + (j / 3)][3
								* (i % 3) + (j % 3)])) {
					return false;
				}
			}
		}
		return true;
	}

	public boolean isValid(boolean[] num, char c) {
		// it's not occupied, valid
		if (c == '.')
			return true;
		// it's not valid
		if (c - '0' > 9)
			return false;
		// it's has been occupied
		if (num[c - '0'])
			return false;
		else {
			// occupy it
			num[c - '0'] = true;
			return true;
		}
	}

	/**
	 * Another method: much simpler to understand
	 */
	public boolean isValidSudoku2(char[][] board) {
		if (board.length != 9 || board[0].length != 9)
			return false;

		for (int i = 0; i < 9; i++) {
			int[] row = new int[9];
			int[] col = new int[9];
			int[] plate = new int[9];

			for (int j = 0; j < 9; j++) {
				if (board[i][j] != '.') {
					int num = board[i][j] - '1';
					if (row[num] > 0) {
						return false;
					} else {
						row[num] = 1;
					}
				}
				if (board[j][i] != '.') {
					int num = board[j][i] - '1';
					if (col[num] > 0) {
						return false;
					} else {
						col[num] = 1;
					}
				}
				if (board[i / 3 * 3 + j / 3][i % 3 * 3 + j % 3] != '.') {
					int num = board[i / 3 * 3 + j / 3][i % 3 * 3 + j % 3] - '1';
					if (plate[num] > 0) {
						return false;
					} else {
						plate[num] = 1;
					}
				}
			}
		}
		return true;
	}
}
