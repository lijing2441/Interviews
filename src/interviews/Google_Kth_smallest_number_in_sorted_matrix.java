package interviews;

import java.util.Comparator;
import java.util.PriorityQueue;

public class Google_Kth_smallest_number_in_sorted_matrix {
	/**
	 * 1) Build a min heap of elements from first row. A heap entry also stores 
	 * 	  row number and column number.
	 * 
	 * 2) Do following k times.
	 * 	…a) Get minimum element (or root) from min heap.
	 * 	…b) Find row number and column number of the minimum element.
	 * 	…c) Replace root with the next element from same column and min-heapify the root.
	 * 
	 * 3) Return the last extracted root.
	 * 
	 * Complexity:
	 * 1) Build a min heap which takes O(n) time
	 * 2) Heapify k times which takes O(kLogn) time.
	 * 
	 * overall time complexity is O(n + kLogn) time.
	 */
	public int kthSmallest(int[][] matrix, int k) {
        int m = matrix.length;
        if (m == 0) return -1;
        int n = matrix[0].length;
        if (n == 0) return -1;
        if (k > m * n) return -1;
        PriorityQueue<NumWithRowCol> pq = new PriorityQueue<NumWithRowCol>(n, new Comparator<NumWithRowCol>() {
            public int compare(NumWithRowCol n1, NumWithRowCol n2) {
                return n1.val - n2.val;
            }
        });
        for (int i = 0; i < n; i++) {
            pq.offer(new NumWithRowCol(matrix[0][i], 0, i));
        }
        for (int i = 0; i < k; i++) {
            NumWithRowCol cur = pq.poll();
            if (i == k - 1) return cur.val;
            else {
                int row = cur.row;
                int col = cur.col;
                int val = (row < m - 1 ? matrix[row + 1][col] : Integer.MAX_VALUE);
                pq.offer(new NumWithRowCol(val, row + 1, col));
            }
        }
        return -1;
    }
}
class NumWithRowCol {
    int val;
    int row;
    int col;
    public NumWithRowCol(int v, int r, int c) {
        this.val = v;
        this.row = r;
        this.col = c;
    }
}
