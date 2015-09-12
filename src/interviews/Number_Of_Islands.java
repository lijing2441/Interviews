package interviews;

import java.util.Stack;

public class Number_Of_Islands {
	/**
	 * Given a 2d grid map of '1's (land) and '0's (water), count the number of
	 * islands. An island is surrounded by water and is formed by connecting
	 * adjacent lands horizontally or vertically. You may assume all four edges
	 * of the grid are all surrounded by water.
	 */
	
	// Running time for DFS/BFS is V + E (Nodes number and edge number)
	// Recursive way
	public int numIslandsRecurse(char[][] grid) {
        if(grid == null || grid.length == 0 || grid[0].length == 0) return 0;
        int count = 0;
        for(int i = 0; i < grid.length; i++) {
            for(int j = 0; j < grid[i].length; j++) {
                if(grid[i][j] == '1') {
                    sink(grid, i, j);
                    count++;
                }
            }
        }
        return count;
    }
    
    public void sink(char[][] grid, int i, int j) {
        grid[i][j] = '0';
        if(i + 1 < grid.length && grid[i + 1][j] == '1') {
            sink(grid, i + 1, j);
        }
        if(j + 1 < grid[i].length && grid[i][j + 1] == '1') {
            sink(grid, i, j + 1);
        }
        if(i > 0 && grid[i - 1][j] == '1') {
            sink(grid, i - 1, j);
        }
        if(j > 0 && grid[i][j - 1] == '1') {
            sink(grid, i, j - 1);
        }
    }
	
	// Iterative way
	public int numIslands(char[][] grid) {
        if(grid == null || grid.length == 0 || grid[0].length == 0) return 0;
        int count = 0;
        Stack<Integer> rows = new Stack<Integer>();
        Stack<Integer> cols = new Stack<Integer>();
        
        for(int i = 0; i < grid.length; i++) {
            for(int j = 0; j < grid[i].length; j++) {
                if(grid[i][j] == '1') {
                    rows.push(i);
                    cols.push(j);
                    while(!rows.isEmpty()) {
                        int x = rows.pop();
                        int y = cols.pop();
                        grid[x][y] = '0';
                        if(x + 1 < grid.length && grid[x + 1][y] == '1') {
                            rows.push(x + 1);
                            cols.push(y);
                        }
                        if(y + 1 < grid[x].length && grid[x][y + 1] == '1') {
                            rows.push(x);
                            cols.push(y + 1);
                        }
                        if(x > 0 && grid[x - 1][y] == '1') {
                            rows.push(x - 1);
                            cols.push(y);
                        }
                        if(y > 0 && grid[x][y - 1] == '1') {
                            rows.push(x);
                            cols.push(y - 1);
                        }
                    }
                    count++;
                }
            }
        }
        return count;
    }
}
