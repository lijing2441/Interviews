package interviews;

public class Segment_Tree_Interval {
	/**
	 * Build:
	 * Given start=0, end=3. The segment tree will be:

               [0,  3]
             /        \
      [0,  1]           [2, 3]
      /     \           /     \
   [0, 0]  [1, 1]     [2, 2]  [3, 3]
Given start=1, end=6. The segment tree will be:

               [1,  6]
             /        \
      [1,  3]           [4,  6]
      /     \           /     \
   [1, 2]  [3,3]     [4, 5]   [6,6]
   /    \           /     \
[1,1]   [2,2]     [4,4]   [5,5]
	 * 
	 */
	/**
     *@param start, end: Denote an segment / interval
     *@return: The root of Segment Tree
     */
    public SegmentTreeNode build(int start, int end) {
        if (start > end) return null;
        int mid = (start + end) / 2;
        SegmentTreeNode root = new SegmentTreeNode(start, end);
        if (start == end) return root;
        root.left = build(start, mid);
        root.right = build(mid + 1, end);
        return root;
    }
    
    // Query
    /**
     * For array [1, 4, 2, 3], the corresponding Segment Tree is:

                  [0, 3, max=4]
                 /             \
          [0,1,max=4]        [2,3,max=3]
          /         \        /         \
   [0,0,max=1] [1,1,max=4] [2,2,max=2], [3,3,max=3]

		query(root, 1, 1), return 4

		query(root, 1, 2), return 4

		query(root, 2, 3), return 3

		query(root, 0, 2), return 4
     */
    public int query(SegmentTreeNode root, int start, int end) {
        if (root == null || start > end) return Integer.MIN_VALUE;
        if (root.start == start && root.end == end) {
            return root.max;
        }
        int mid = (root.start + root.end) / 2;
        int leftMax = Integer.MIN_VALUE, rightMax = Integer.MIN_VALUE;
        if (start <= mid) {
            if (mid < end) {
                leftMax = query(root.left, start, mid);
            } else {
                leftMax = query(root.left, start, end);
            }
        }
        if (mid < end) {
            if (start <= mid) {
                rightMax = query(root.right, mid + 1, end);
            } else {
                rightMax = query(root.right, start, end);
            }
        }
        return Math.max(leftMax, rightMax);
    }
}
class SegmentTreeNode {
	public int start, end, max;
	public SegmentTreeNode left, right;
	public SegmentTreeNode(int start, int end) {
		this.start = start;
		this.end = end;
		this.left = this.right = null;
	}
	public SegmentTreeNode(int start, int end, int max) {
		this.start = start;
		this.end = end;
		this.max = max;
		this.left = this.right = null;
	}
}