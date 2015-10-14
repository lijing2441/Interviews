package interviews;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
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
	
	/**
	 * Number of islands II:
	 * 
	 * Given n = 3, m = 3, array of pair A = [(0,0),(0,1),(2,2),(2,1)].
	 * 
	 * return [1,1,2,2].
	 * 
	 * Note: 0 is represented as the sea, 1 is represented as the island. 
	 * If two 1 is adjacent, we consider them in the same island. 
	 * We only consider up/down/left/right adjacent.
	 * 
	 */
	int[] x = {0, 1, 0, -1};
	int[] y = {1, 0, -1, 0};
	// each island will have a father integer, who has itself as father in the father map
	public List<Integer> numIslands2(int n, int m, Point[] operators) {
	    if (operators == null || operators.length == 0) return new ArrayList<Integer>();

        Map<Integer, Integer> father = new HashMap<Integer, Integer>();
        List<Integer> res = new ArrayList<Integer>();
        int count = 0;
        for (Point p: operators) {
        	int cur = p.x * m + p.y;
        	int fCur = findFather(father, cur);
        	if (fCur == cur) count++;   // it is in the self union
        	for (int i = 0; i < 4; i++) {
        		int xx = p.x + x[i];
        		int yy = p.y + y[i];
        		if (xx < 0 || xx >= n || yy < 0 || yy >= m) {
        			continue;
        		} else {
        			int next = xx * m + yy;
        			if (father.containsKey(next)) {    // the neighbor has been converted to sea
        				int fNext = findFather(father, next);
        				if (fNext != fCur) { // we can union them now
        					count--;  // after union, the count of islands will reduce 1
        					father.put(fNext, fCur); // union
        				}
        			}
        		}
        	}
        	res.add(count);
        }
        return res;
    }
	public int findFather(Map<Integer, Integer> father, int cur) {
		if (!father.containsKey(cur)) {
			father.put(cur, cur);
			return cur;
		}
		int res = cur;
		int tmp = 0;
		// get the father of the current union
		while (res != father.get(res)) {
			res = father.get(res);
		}
		// set the middle ones' father to the final father
		while (cur != father.get(cur)) {
			tmp = father.get(cur);
			father.put(cur, res);
			cur = tmp;
		}
		return cur;
	}
	
}
