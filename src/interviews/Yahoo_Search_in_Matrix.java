package interviews;

public class Yahoo_Search_in_Matrix {
	/**
	 * Find specific number in which rows are increasing order and cols are in decreasing order.
	 */
	public static boolean searchInMatrix(int[][] matrix, int target) {
		int m = matrix.length;
		int n = matrix[0].length;
//		int top = 0, bottom = m - 1;
//		while (top <= bottom) {
//    		System.out.println("top: " + top + ", bottom: " + bottom);
//            int midRow = (top + bottom) / 2;
//            if (matrix[midRow][0] <= target && matrix[midRow][n - 1] >= target) {
//            	int left = 0, right = n - 1;
//            	while (left <= right) {
//            		System.out.println("left: " + left + ", right: " + right);
//            		
//            		int midCol = (left + right) / 2;
//            		if (target == matrix[midRow][midCol]) {
//            			System.out.println("[" + midRow + ", " + midCol + "]");
//            			return true;
//            		} else if (target > matrix[midRow][midCol]) {
//            			left = midCol + 1;
//            		} else {
//            			right = midCol - 1;
//            		}
//            	}
//            	
//            } else if (matrix[midRow][0] > target) {
//            	top = midRow + 1;
//            } else if (matrix[midRow][n - 1] < target){
//            	bottom = midRow - 1;
//            } else {
//            	return false;
//            }
//        }
//		return false;
		// diagonal binary search
		if (target < matrix[0][0] || target > matrix[m - 1][n - 1]) return false;
        int row = 0, col = 0;
        while (row < m && col < n) {
            if (target == matrix[row][col]) {
                return true;
            } else if (target < matrix[row][col]) {
                row++;
            } else {
                col++;
            }
        }
        return false;
	}
	public static void main(String[] args) {
		int[][] matrix = {
				{10, 20, 30, 40, 50},
				{9, 11, 20, 32, 45},
				{8, 10, 11, 13, 16}
		};
		int num1 = 38;
		int num2 = 11;
		System.out.println(searchInMatrix(matrix, num1));
		System.out.println(searchInMatrix(matrix, num2));
	}
}
