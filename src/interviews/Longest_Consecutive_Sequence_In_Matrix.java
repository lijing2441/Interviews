package interviews;

import java.util.ArrayList;

public class Longest_Consecutive_Sequence_In_Matrix {
	static ArrayList<ArrayList<Integer>> res;
	static boolean[][] visited;
	static int ROW, COL;
	static int max;

	public static void find(int[][] arr) {
		res = new ArrayList<ArrayList<Integer>>();
		ROW = arr.length;
		if (ROW == 0)
			return;
		COL = arr[0].length;
		visited = new boolean[ROW][COL];
		max = 0;
		for (int row = 0; row < ROW; row++)
			for (int col = 0; col < COL; col++)
				if (!visited[row][col])
					DFS(arr, row, col, new ArrayList<Integer>());

		for (ArrayList<Integer> ls : res)
			System.out.println(ls);
	}

	public static void DFS(int[][] arr, int row, int col, ArrayList<Integer> ans) {
		visited[row][col] = true;
		ans.add(arr[row][col]);
		if (ans.size() >= max) {
			if (ans.size() > max)
				res.clear();
			res.add(new ArrayList<Integer>(ans));
			max = ans.size();
		}
		int cur = arr[row][col];
		if (row > 0 && cur - arr[row - 1][col] == -1)
			DFS(arr, row - 1, col, ans);

		if (col > 0 && cur - arr[row][col - 1] == -1)
			DFS(arr, row, col - 1, ans);

		if (row < ROW - 1 && cur - arr[row + 1][col] == -1)
			DFS(arr, row + 1, col, ans);

		if (col < COL - 1 && cur - arr[row][col + 1] == -1)
			DFS(arr, row, col + 1, ans);

		ans.remove(ans.size() - 1);
	}

	public static void main(String[] args) {
		int[][] arr = { { 2, 3, 4, 5 }, 
						{ 4, 5, 10, 11 }, 
						{ 20, 6, 9, 12 },
						{ 6, 7, 8, 40 } };

		find(arr);
		System.out.println();
		System.out.print(findGridLIS(arr));
	}
	
	/**
	 * DP Method:
	 * 1. create a new 2d array (let say named temp[][]) of same dimension as original. 
	 * the element dp[i][j] contains the length of the LIS starting at this point. 
	 * 2. first fill this dp[][] array with 1's. 
	 * 3. now, start filling this dp[][] array. let say given array's dimension is MxN. 
	 * 
	 * time complexity is: O(M.N),因为我们每个点只fill一次
	 */
	public static int[] x = {-1, 1, 0, 0};
	public static int[] y = {0, 0, -1, 1};
	
	public static int findGridLIS(int A[][]) {
		int len = A.length;
		int dp[][] = new int[len][len];
		int max = 0;
		// initialize memorizing array with all ones
		for (int i = 0; i < len; i++) {
			for (int j = 0; j < len; j++) {
				int tempmax = fillDP(A, dp, i, j);
				if (tempmax > max)
					max = tempmax;
			}
		}
		return max;

	}
	
	public static int fillDP(int[][] A, int[][] dp, int i, int j) {		
		// we calculate each element of dp array only once. 
		if (dp[i][j] != 0)	return dp[i][j];
		dp[i][j] = 1;
		
		for(int n = 0; n < 4; n++){
			if(i + y[n] < 0 || i + y[n] >= A.length || j + x[n] < 0 || j + x[n] >= A[0].length) continue;
			if(A[i + y[n]][j + x[n]] == A[i][j] - 1){
				int localMax = fillDP(A, dp, i + y[n], j + x[n]) + 1;
				if(localMax > dp[i][j]) dp[i][j] = localMax;
			}
		}
		return dp[i][j];
		
		/*
		 * 以下是increasing，但不一定consecutive
		int left = 0, right = 0, topLeft = 0, topRight = 0, bottom = 0, top = 0, bottomLeft = 0, bottomRight = 0;
		
		if (j - 1 >= 0 && A[i][j] = A[i][j - 1])
			left = fillDP(A, dp, i, j - 1);

		if (j + 1 < len && A[i][j] < A[i][j + 1])
			right = fillDP(A, dp, i, j + 1);

		if (i + 1 < len && A[i][j] < A[i + 1][j])
			bottom = fillDP(A, dp, i + 1, j);

		if (i - 1 >= 0 && A[i][j] < A[i - 1][j])
			top = fillDP(A, dp, i - 1, j);

		if (i + 1 < len && j - 1 >= 0 && A[i][j] < A[i + 1][j - 1])
			bottomLeft = fillDP(A, dp, i + 1, j - 1);

		if (i + 1 > len && j + 1 > len && A[i][j] < A[i + 1][j + 1])
			bottomRight = fillDP(A, dp, i + 1, j + 1);

		if (i - 1 >= 0 && j - 1 >= 0 && A[i][j] < A[i - 1][j - 1])
			topLeft = fillDP(A, dp, i - 1, j - 1);

		if (i - 1 >= 0 && j + 1 < len && A[i][j] < A[i - 1][j + 1])
			topRight = fillDP(A, dp, i - 1, j + 1);

		dp[i][j] = Math.max(
				Math.max(Math.max(top, bottom), Math.max(right, left)),
				Math.max(Math.max(bottomRight, bottomLeft),
						Math.max(topLeft, topRight))) + 1;
		return dp[i][j];
		 */
	}
}
