package interviews;

import java.util.ArrayList;

/**
 * Given an integer n, return all distinct solutions to the n-queens puzzle.
 * 
 * Each solution contains a distinct board configuration of the n-queens'
 * placement, where 'Q' and '.' both indicate a queen and an empty space
 * respectively. 
 * 
 * For example, There exist two distinct solutions to the 4-queens
 * 
 * puzzle:
 * 
 * [ 
 * 	[".Q..", // Solution 1 
 * 	 "...Q", 
 *   "Q...", 
 *   "..Q."],
 * 
 * 	["..Q.", // Solution 2 
 * 	 "Q...", 
 *   "...Q", 
 *   ".Q.."] 
 * ]
 * @analysis backtracking
 * DFS遍历，考虑所有可能，colForRow中记录每一行棋子的column位置, try all columns for current row
 * 对角线的判断就是两点行差值和列差值是否相同。
 */
public class N_Queens {
	public ArrayList<String[]> solveNQueens(int n) {
		ArrayList<String[]> res = new ArrayList<String[]>();
		queenHelper(res, new int[n], 0, n);
		return res;
	}

	public void queenHelper(ArrayList<String[]> res, int[] columnForRow, int row, int limit) {
		//found one solution, add to the result
		if (row == limit) {
			String[] tmp = new String[limit];
			for (int i = 0; i < limit; i++) {
				StringBuilder sb = new StringBuilder();
				for (int j = 0; j < limit; j++) {
					if (j == columnForRow[i]) {
						sb.append('Q');
					} else {
						sb.append('.');
					}
				}
				tmp[i] = sb.toString();
			}
			res.add(tmp);
		} else {
			for (int i = 0; i < limit; i++) {
				columnForRow[row] = i;
				if (isSafe(columnForRow, row)) {
					queenHelper(res, columnForRow, row + 1, limit);
				}
			}
		}
	}

	public boolean isSafe(int[] columnForRow, int row) {
		for (int i = 0; i < row; i++) {
			// since we are comparing this row with the lower rows, we do not need to worry about row match
			if (columnForRow[i] == columnForRow[row] || Math.abs(columnForRow[i] - columnForRow[row]) == (row - i)) {
				return false;
			}
		}
		return true;
	}

	
	/**
	 * N-Queens II: 只需返回solution个数即可。
	 */
	public int totalNQueens(int n) {
		int[] res = { 0 };
		helper(res, new int[n], 0, n);
		return res[0];
	}

	public void helper(int[] res, int[] columnForRow, int row, int limit) {
		if (row == limit) {
			res[0]++;
		} else {
			for (int i = 0; i < limit; i++) {
				columnForRow[row] = i;
				if (isSafe(columnForRow, row)) {
					helper(res, columnForRow, row + 1, limit);
				}
			}
		}
	}

	public boolean isSafe2(int[] columnForRow, int row) {
		for (int i = 0; i < row; i++) {
			if (columnForRow[i] == columnForRow[row]
					|| Math.abs(columnForRow[i] - columnForRow[row]) == (row - i)) {
				return false;
			}
		}
		return true;
	}
}
