package interviews;

public class Matrix_Region_Sum {
	/**
	 * Pre-process to get the matrix region sum in O(1) time
	 * 
	 * the program will be called multiple times with different rectangular
	 * regions which makes me thing that I’m expected to do some kind of
	 * caching.
	 * 
	 * 相对位置：
	 * A	D
	 * 
	 * C	B
	 */
	public int matrixRegionSum(int[][] matrix, Point A, Point B) {
		if (matrix == null || matrix.length == 0 || matrix[0].length == 0) return 0;
		preprocess(matrix);
		int OA = 0, OC = 0, OD = 0;
		if (A.x == 0 || A.y == 0) {
			OA = 0;
		} else {
			OA = matrix[A.y - 1][A.x - 1];
		}
		if (A.x == 0) {
			OC = 0;
		} else {
			OC = matrix[B.y][A.x - 1];
		}
		if (A.y == 0) {
			OD = 0;
		} else {
			OD = matrix[A.y - 1][B.x];
		}
		int OB = matrix[B.y][B.x];
		return OB - OC - OD + OA;
	}
	
	public void preprocess(int[][] matrix) {
		for(int i = 1; i < matrix.length; i++) {
			matrix[i][0] += matrix[i - 1][0];
		}
		for (int j = 1; j < matrix[0].length; j++) {
			matrix[0][j] += matrix[0][j - 1];
		}
		for (int i = 1; i < matrix.length; i++) {
			for (int j = 1; j < matrix[0].length; j++) {
				matrix[i][j] = matrix[i - 1][j] + matrix[i][j - 1] - matrix[i - 1][j - 1];
			}
		}
	}
}
