package interviews;

import java.util.HashSet;
import java.util.Set;

public class Pinterest_board_jump_game {
	/**
	 * You’re given a board game, which is a row of squares, each labeled with
	 * an integer. This can be represented by a list, e.g. [1, 3, 2, 0, 5, 2, 8,
	 * 4, 1] Given a start position on the board, you “win” by landing on a
	 * zero, where you move by jumping from square to square either left or
	 * right the number of spaces specified on the square you’re currently on.
	 * Your task is to implement the function: def can_win(board, pos): returns
	 * True if you can win the board from that starting pos, False otherwise
	 */
	public static boolean can_win(int[] board, int startPos) {
		Set<Integer> usedPos = new HashSet<Integer>();
		return helper(board, startPos, usedPos);
	}
	public static boolean helper(int[] arr, int cur, Set<Integer> used) {
		if (cur >= 0 && cur < arr.length && !used.contains(cur)) {
			if (arr[cur] == 0) return true;
			used.add(arr[cur]);
			if (helper(arr, cur + arr[cur], used)) return true;
			else if (helper(arr, cur - arr[cur], used)) return true;
			used.remove(arr[cur]);
		}
		return false;
	}
	public static void main(String[] args) {
		int[] arr = {1, 3, 2, 0, 5, 2, 8, 4, 1};
		int start = 5;
		System.out.println(can_win(arr, start));
	}
}
