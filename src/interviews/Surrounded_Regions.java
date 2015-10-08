package interviews;

import java.util.LinkedList;
import java.util.Queue;

public class Surrounded_Regions {
/**
 * Given a 2D board containing 'X' and 'O', capture all regions surrounded by 'X'.
 * A region is captured by flipping all 'O's into 'X's in that surrounded region.
 * 
 * For example,
 * X X X X
 * X O O X
 * X X O X 
 * X O X X
 * 
 * After running your function, the board should be:
 * X X X X
 * X X X X
 * X X X X
 * X O X X
 */
/**
 * 这个题目用到的方法是Flood fill算法，就是从一个点出发对周围区域进行目标颜色的填充。
 * 背后的思想就是把一个矩阵看成一个图的结构，每个点看成结点，而边则是他上下左右的相邻点，然后进行一次广度或者深度优先搜索。
 * 
 * 首先根据题目要求，边缘上的'O'是不需要填充的，所以我们的办法是对上下左右边缘做Flood fill算法，
 * 把所有边缘上的'O'都替换成另一个字符，比如'#'。接下来我们知道除去被我们换成'#'的那些顶点，
 * 剩下的所有'O'都应该被替换成'X'，而'#'那些最终应该是还原成'O'，如此我们可以做最后一次遍历，
 * 然后做相应的字符替换就可以了。
 * 
 * 复杂度分析，我们先对边缘做Flood fill算法，因为只有是'O'才会进行，而且会被替换成'#'，
 * 所以每个结点改变次数不会超过一次，因而是O(m*n)的复杂度，最后一次遍历同样是O(m*n)，
 * 所以总的时间复杂度是O(m*n)。
 * 
 * 空间上就是递归栈（深度优先搜索）或者是队列（广度优先搜索）的空间，同时存在的空间占用不会超过O(m+n)
 * （以广度优先搜索为例，每次队列中的结点虽然会往四个方向拓展，但是事实上这些结点会有很多重复，
 * 假设从中点出发，可以想象最大的扩展不会超过一个菱形，也就是n/2*2+m/2*2=m+n，
 * 空间复杂度是O(m+n)）
 */
	public void solve(char[][] board) {
        if(board == null || board.length <= 2 || board[0].length <= 2) return;
        //check the first and the last row, one by one
        for(int i = 0; i < board[0].length; i++){
            fill(board, 0, i);
            fill(board, board.length - 1, i);
        }
        //check the first and the last column
        for(int i = 0; i < board.length; i++){
            fill(board, i, 0);
            fill(board, i, board[0].length - 1);
        }
        
        //final traversal to set the board to the final goal
        for(int i = 0; i < board.length; i++){
            for(int j = 0; j < board[0].length; j++){
                if(board[i][j] == 'O') board[i][j] = 'X';
                else if(board[i][j] == '#') board[i][j] = 'O';
            }
        }
    }
	
	// bfs to flood fill each grid on the board boundary
    public void fill(char[][] board, int i, int j){
        //if the current grid is not 'O', no need to fill, return
    	if(board[i][j] != 'O') return;
        //set the 'O' to '#' to differentiate it with the original 'O'
        board[i][j] = '#';
        // BFS to check four directions
        Queue<Integer> rows = new LinkedList<Integer>();
        Queue<Integer> cols = new LinkedList<Integer>();
        //BFS start point
        rows.offer(i);
        cols.offer(j);
        // BFS to check four directions
        while(!rows.isEmpty()){
            int row = rows.poll();
            int col = cols.poll();
            // each check, check boundary first and then check if the current char is 'O'
            if(row > 0 && board[row - 1][col] == 'O'){
                board[row - 1][col] = '#';
                rows.offer(row-1);
                cols.offer(col);
            }
            if(col > 0 && board[row][col - 1] == 'O'){
                board[row][col - 1] = '#';
                rows.offer(row);
                cols.offer(col-1);
            }
            if(row < board.length - 1 && board[row + 1][col] == 'O'){
                board[row + 1][col] = '#';
                rows.offer(row+1);
                cols.offer(col);
            }
            if(col < board[0].length - 1 && board[row][col + 1] == 'O'){
                board[row][col + 1] = '#';
                rows.offer(row);
                cols.offer(col+1);
            }
        }
    }
}
