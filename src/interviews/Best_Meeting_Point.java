package interviews;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Best_Meeting_Point {
	/**
	 * 找x和y的中位数，就是meeting point
	 */
	public int minTotalDistance(int[][] grid) {
        int m = grid.length;
        if (m == 0) return 0;
        int n = grid[0].length;
        if (n == 0) return 0;
        List<Integer> rows = new ArrayList<Integer>();
        List<Integer> cols = new ArrayList<Integer>();
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1) {
                    rows.add(i);
                    cols.add(j);
                }
            }
        }
        return getMin(rows) + getMin(cols);
    }
    public int getMin(List<Integer> list) {
        int count = 0;
        Collections.sort(list);
        int left = 0, right = list.size() - 1;
        while (left < right) {
            count += (list.get(right--) - list.get(left++));
        }
        return count;
    }
}
