package interviews;

public class Regular_Expression_Matching {
	/**
	 * '.' Matches any single character. 
	 * '*' Matches zero or more of the preceding element.
	 * 
	 * The matching should cover the entire input string (not partial).
	 * 
	 * recursive method O(n) time, O(n) space (if stack counted) backtracking
	 */
	public boolean isMatch2(String s, String p) {
		return isMatchHelper(s, p, s.length() - 1, p.length() - 1);
	}

	private boolean isMatchHelper(String s, String p, int endA, int endB) {
		if (endA < 0 && endB < 0)
			return true;
		if (endB < 0 && endA >= 0)
			return false;
		if (endA < 0) {
			if (endB > 0 && p.charAt(endB) == '*')
				return isMatchHelper(s, p, endA, endB - 2);
			return false;
		}
		// if p points to '.' or same letter exists, easy case, advance both pointers
		if (p.charAt(endB) == '.' || p.charAt(endB) == s.charAt(endA))
			return isMatchHelper(s, p, endA - 1, endB - 1);

		// if p points to '*', backtrack to see the last letters
		if (p.charAt(endB) == '*') {
			// match the previous char
			if (isMatchHelper(s, p, endA, endB - 2) == true)
				return true;
			// process the preceding
			/**
			 *  Follow-up: if * can only match its preceding char no more than twice =>
			 *  for (int i = endA; i >= Math.max(endA - 2, 0); i--) 
			 */
			for (int i = endA; i >= 0; i--) {
				// if p.charAt(endB - 1) == ‘.’，可以跟所有match
				// if endB - 1与之前的一个mismatch，false
				if (p.charAt(endB - 1) != '.' && s.charAt(i) != p.charAt(endB - 1))
					return false;
				// endB - 1 == '.' or s.charAt(i) == p.charAt(endB - 1)
				// as long as we can match the remaining, they are matched
				if (isMatchHelper(s, p, i - 1, endB - 2) == true)
					return true;
			}
		}
		return false;
	}

	/**
	 * Method 2: DP. Using 2D-matrix to memorize the match pattern.
	 */

	public boolean isMatch(String s, String p) {
		if (s == null && p == null)
			return true;
		if (s.length() == 0 && p.length() == 0)
			return true;

		boolean[][] res = new boolean[s.length() + 1][p.length() + 1];
		res[0][0] = true;

		// initialize
		for (int i = 1; i <= s.length(); i++) {
			res[i][0] = false;
		}
		for (int j = 1; j <= p.length(); j++) {
			if (p.charAt(j - 1) == '*' && j - 1 > 0) {
				res[0][j] = res[0][j - 2];
			} else {
				res[0][j] = false;
			}
		}

		// the other parts
		for (int i = 1; i <= s.length(); i++) {
			for (int j = 1; j <= p.length(); j++) {
				if (s.charAt(i - 1) == p.charAt(j - 1) || p.charAt(j - 1) == '.') {
					res[i][j] = res[i - 1][j - 1];
				} else if (p.charAt(j - 1) == '*' && j > 1) {
					if (s.charAt(i - 1) == p.charAt(j - 2) || p.charAt(j - 2) == '.') {
						res[i][j] = res[i - 1][j] || res[i][j - 2] || res[i][j - 1];
						// matrix[i-1][j]:abb vs ab*: depends on ab vs ab*
						// matrix[i][j-2] a vs ab* depends on a vs a
						// matrix[i][j-1] ab vs ab*: depends on ab vs ab
					} else {
						res[i][j] = res[i][j - 2];
					}
				} else {
					res[i][j] = false;
				}
			}
		}
		return res[s.length()][p.length()];
	}
}
