package interviews;

public class Set_Matrix_Zeros {
	/**
	 * Given a m x n matrix, if an element is 0, set its entire row and column to 0. Do it in place.
	 */
	// basic method, using a auxiliary matrix O(mn)
	public void setZeroesI(int[][] matrix) {
        int m = matrix.length;
        if(m == 0) return;
        int n = matrix[0].length;
        if(n == 0) return;
        
        boolean[][] map = new boolean[m][n];
        for(int i = 0; i < m; i++){
            for(int j = 0; j < n; j++){
                if(matrix[i][j] == 0) map[i][j] = true;
            }
        }
        for(int i = 0; i < m; i++){
            for(int j = 0; j < n; j++){
                if(map[i][j]){
                    for(int k = 0; k < m; k++){
                        matrix[k][j] = 0;
                    }
                    for(int l = 0; l < n; l++){
                        matrix[i][l] = 0;
                    }
                }
            }
        }
    }
	
	// using two vectors to memorize, O(m + n)
	public void setZeroesII(int[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;
        
        boolean[] rows = new boolean[m];
        boolean[] cols = new boolean[n];
        
        for(int i = 0; i < m; i++){
            for(int j = 0; j < n; j++){
                if(matrix[i][j] == 0){
                    rows[i] = cols[j] = true;
                }
            }
        }
        for(int i = 0; i < m; i++){
            for(int j = 0; j < n; j++){
                if(rows[i] == true || cols[j] == true){
                    matrix[i][j] = 0;
                }
            }
        }
    }
	// using constant space: O(1)
	// use the first column and first row as the helper space
	public void setZeroes(int[][] matrix) {
        int m = matrix.length;
        if(m == 0) return;
        int n = matrix[0].length;
        if(n == 0) return;
        
        boolean firstRow = false;
        boolean firstColumn = false;
        //find if the first column and first row contain zero first
        for(int i = 0; i < m; i++){
            if(matrix[i][0] == 0){
                firstColumn = true;
                break;
            }
        }
        for(int j = 0; j < n; j++){
            if(matrix[0][j] == 0) {
                firstRow = true;
                break;
            }
        }
        // mark the zero positions in the first column and first row
        for(int i = 1; i < m; i++){
            for(int j = 1; j < n; j++){
                if(matrix[i][j] == 0){
                    matrix[i][0] = 0;
                    matrix[0][j] = 0;
                }
            }
        }
        for(int i = 1; i < m; i++){
            for(int j = 1; j < n; j++){
                if(matrix[i][0] == 0 || matrix[0][j] == 0){
                    matrix[i][j] = 0;
                }
            }
        }
        // set the first column and first row to zero finally if applicable
        if(firstColumn == true){
            for(int i = 0; i < m; i++){
                matrix[i][0] = 0;
            }
        }
        if(firstRow == true){
            for(int i = 0; i < n; i++){
                matrix[0][i] = 0;
            }
        }
    }
}
