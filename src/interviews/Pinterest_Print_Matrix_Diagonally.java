package interviews;

public class Pinterest_Print_Matrix_Diagonally {
	/**
	 * 
	 * abcd
	 * efgh
	 * ijkl
	 * mnop
	 * ----
	 * 打印：
	 * afkp bgl ch d ejo in m
	 * 
	 * abc 
	 * edf 
	 * ---- 
	 * 打印
	 * ad bf c e
	 */
	
	public void print(char[][] matrix) {
		int n = matrix.length;
		int m = matrix[0].length;
		// print the top part
		for (int col = 0; col < m; col++) {
			int i = 0, j = col;
			while (i < n && j < m) {
				System.out.print(matrix[i++][j++]);
			}
			System.out.println();
		}
		// print the bottom part
		for (int row = 1; row < n; row++) {
			int i = row, j = 0;
			while (i < n && j < m) {
				System.out.print(matrix[i++][j++]);
			}
			System.out.println();
		}
	}
	
	public static void main(String[] args) {
		char[][] matrix = {
				{'a','b','c','d'},
				{'e','f','g','h'},
				{'i','j','k','l'},
				{'m','n','o','p'}
		};
		new Pinterest_Print_Matrix_Diagonally().print(matrix);
	}
}
