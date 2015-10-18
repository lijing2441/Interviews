package interviews;

public class Uber_Find_Neighbor_Counts_In_Matrix {
	/**
	 * 假设给你一个2D array由'\'和'/'构成。让你求这些slashes把整个区域分成了几个区间.
	假设输入是
	/\/
	\/\
	那么需要返回6。
	----------
	A/F\B/C
	D\F/E\C
	-----------
	其中字母代表的是区域的标号。
	如果各位没看懂的话我再举个例子。input是这个
	/\\
	/\/
	/\\

	返回还是6。因为整个可以分成这样：
	----------
	B/\C\D
	/CC\C\
	C/\CC/
	/EE\/F
	E/\E\F
	/GG\E\
	 * 
	 */
	private final static byte LEFT = 0X1;
	private final static byte RIGHT = 0X2;

	public static int num(char[][] slashes) {
		int n = slashes.length;
		int m = slashes[0].length;
		byte[][] infos = new byte[n][m];
		int result = 0;
		for (int i = 0; i < n; ++i) {
			for (int j = 0; j < m; ++j) {
				if ((infos[i][j] & LEFT) == 0) {
					dfs(infos, slashes, i, j, LEFT, n, m);
					result++;
				}
				if ((infos[i][j] & RIGHT) == 0) {
					dfs(infos, slashes, i, j, RIGHT, n, m);
					result++;
				}
			}
		}
		return result;
	}

	private static void dfs(byte[][] infos, char[][] slashes, int x, int y,
			byte pos, int n, int m) {
		infos[x][y] |= pos;
		if (slashes[x][y] == '/') {
			if (pos == LEFT) {
				if (x - 1 >= 0 && (infos[x - 1][y] & RIGHT) == 0) {
					dfs(infos, slashes, x - 1, y, RIGHT, n, m);
				}
				if (y - 1 >= 0) {
					if (slashes[x][y - 1] == '/') {
						if ((infos[x][y - 1] & RIGHT) == 0)
							dfs(infos, slashes, x, y - 1, RIGHT, n, m);
					} else {
						if ((infos[x][y - 1] & LEFT) == 0)
							dfs(infos, slashes, x, y - 1, LEFT, n, m);
					}
				}
			} else {
				if (x + 1 < n && (infos[x + 1][y] & LEFT) == 0) {
					dfs(infos, slashes, x + 1, y, LEFT, n, m);
				}
				if (y + 1 < m) {
					if (slashes[x][y + 1] == '/') {
						if ((infos[x][y + 1] & LEFT) == 0)
							dfs(infos, slashes, x, y + 1, LEFT, n, m);
					} else {
						if ((infos[x][y + 1] & RIGHT) == 0)
							dfs(infos, slashes, x, y + 1, RIGHT, n, m);
					}
				}
			}
		} else {
			if (pos == LEFT) {
				if (x - 1 >= 0 && (infos[x - 1][y] & RIGHT) == 0) {
					dfs(infos, slashes, x - 1, y, RIGHT, n, m);
				}
				if (y + 1 < m) {
					if (slashes[x][y + 1] == '/') {
						if ((infos[x][y + 1] & LEFT) == 0)
							dfs(infos, slashes, x, y + 1, LEFT, n, m);
					} else {
						if ((infos[x][y + 1] & RIGHT) == 0)
							dfs(infos, slashes, x, y + 1, RIGHT, n, m);
					}
				}
			} else {
				if (x + 1 < n && (infos[x + 1][y] & LEFT) == 0) {
					dfs(infos, slashes, x + 1, y, LEFT, n, m);
				}
				if (y - 1 >= 0) {
					if (slashes[x][y - 1] == '/') {
						if ((infos[x][y - 1] & RIGHT) == 0)
							dfs(infos, slashes, x, y - 1, RIGHT, n, m);
					} else {
						if ((infos[x][y - 1] & LEFT) == 0)
							dfs(infos, slashes, x, y - 1, LEFT, n, m);
					}
				}
			}
		}
	}
	public static void main(String[] args) {
		char[][] e1 = {
				{'/', '\\', '/'},
	            {'\\', '/', '\\'},
	    	};
		char[][] e2 = {
	            {'/', '\\', '\\'},
	            {'/', '\\', '/'},
	            {'/', '\\', '\\'},
		};
		char[][] e3 = {
	            {'/', '\\', '/'},
	            {'\\', '\\', '\\'},
		};
		System.out.println(num(e1));
		System.out.println(num(e2));
		System.out.println(num(e3));
	}
}
