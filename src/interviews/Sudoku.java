package interviews;
/**
 * Except the backtracking brute force method used here, we can: 
 * -> randomly assign numbers to the blank cells
 * -> calculate the number of errors
 * -> shuffle the inserted numbers around the grid until the # of mistakes is reduced to 0.
 */

public class Sudoku {
	/**
	 * Problem I: Determine if a Sudoku is valid.
	 * 
	 * 在检测行是否合法时，i 表示矩阵的行，j 表示矩阵的列； 
	 * 检测列是否合法时，i 表示矩阵的列，j 表示矩阵的行； 
	 * 检测九宫格是否合法时，i 表示九宫格的标号，j 表示九宫格里的每个元素
	 * （只是我们需要根据i、j定位相应的元素到原来的矩阵：第 i 个九宫格里面的第 j 个元素在原矩阵的
	 * 第 3*(i/3) + j/3 行，第 3*(i%3) + j%3）列，“/” 表示整数除法）
	 */
	public boolean isValidSudoku(char[][] board){
		if(board.length != 9 || board[0].length != 9) return false;
		for(int i = 0; i < 9; i++){
			// we just use the 1-9 indices
			boolean[] row = new boolean[10];
			boolean[] col = new boolean[10];
			boolean[] box = new boolean[10];
			for(int j = 0; j < 9; j++){
				if(!isValid(row, board[i][j]) || !isValid(col, board[j][i]) || !isValid(box, board[3 * (i/3) + (j/3)][3 *(i%3) + (j%3)])){
					return false;
				}
			}
		}
		return true;
	}
	public boolean isValid(boolean[] num, char c){
		// it's not occupied, valid
		if(c == '.') return true;
		// it's not valid
		if(c-'0' > 9) return false;
		// it's has been occupied
		if(num[c - '0']) return false;
		else{
			// occupy it
			num[c-'0'] = true;
			return true;
		}
	}
	
	/**
	 * Another method: much simpler to understand
	 */
	public boolean isValidSudoku2(char[][] board) {
        if(board.length != 9 || board[0].length != 9) return false;
        
        for(int i = 0; i < 9; i++){
            int[] row = new int[9];
            int[] col = new int[9];
            int[] plate = new int[9];
        
            for(int j = 0; j < 9; j++){
                if(board[i][j] != '.'){
                    int num = board[i][j] - '1';
                    if(row[num] > 0){
                        return false;
                    }else{
                        row[num] = 1;
                    }
                }
                if(board[j][i] != '.'){
                    int num = board[j][i] - '1';
                    if(col[num] > 0){
                        return false;
                    }else{
                        col[num] = 1;
                    }
                }
                if(board[i/3*3 + j/3][i%3*3 + j%3] != '.'){
                    int num = board[i/3*3 + j/3][i%3*3 + j%3] - '1';
                    if(plate[num] > 0){
                        return false;
                    }else{
                        plate[num] = 1;
                    }
                }
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
	 * 如果正确，就继续进行下面的递归（也就是第117行 isValid&&solveSudoku的作用）。当函数返回错误之后，
	 * 将刚刚的数值变为空，再进行下一次尝试即可。
	 * 
	 * 所有的方案（k从1到9）完毕之后，应该返回错误，这个是不应该被忽略的。
	 * 
	 * 最后一点需要注意的是，当i,j循环完毕之后，第36行应该返回true。这里实际上是最终/最底层的一次循环，
	 * 表明已经解出了sudoku，返回true！切记切记，最终情况！
	 */
	public void solveSudoku(char[][] board) {
        if(board == null || board.length != 9 || board[0].length != 9) return;
        helper(board, 0, 0);
    }
    private boolean helper(char[][] board, int i, int j){
    	// start from 0 column of next row
        if(j >= 9) return helper(board, i + 1, 0); 
        // all rows have been checked and filled
        if(i == 9) return true; 
        // if the current pos is '.', we can start fill
        if(board[i][j] == '.'){
        	// check each possible candidate
            for(int k = 1; k <= 9; k++){
                board[i][j] = (char) (k + '0');
                // if set this boar[i][j] = k + '0' is valid
                // we proceed the check
                if(isValid(board, i, j)){
                    return helper(board, i, j+1);
                }
            }
            // remember to reset the current position back to '.' 
            // to let the other check reuse this position 
            board[i][j] = '.';
        }else{
        	// it's filled, check next column
            return helper(board, i, j+1);
        }
        return false;
    }
    private boolean isValid(char[][] board, int row, int col){
    	// check row
        for(int k = 0; k < 9; k++){
            if(k != row && board[row][col] == board[k][col]) return false;
        }
        // check col
        for(int k = 0; k < 9; k++){
            if(k != col && board[row][col] == board[row][k]) return false;
        }
        // check plate
        for(int i = (row/3 * 3); i < (row/3 *3 + 3); i++){
            for(int j = col/3 * 3; j < col/3 * 3 + 3; j++){
                if((i != row || j != col) && board[row][col] == board[i][j]) return false;
            }
        }
        return true;
    }
	
}
