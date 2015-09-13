package interviews;

public class Paint_Fence {
	/**
	 * There is a fence with n posts, each post can be painted with one of the k
	 * colors.
	 * 
	 * You have to paint all the posts such that no more than two adjacent fence
	 * posts have the same color.
	 * 
	 * Return the total number of ways you can paint the fence.
	 * 
	 * Note: n and k are non-negative integers.
	 */
	public int numWays(int n, int k) {
        if(n <= 1 || k == 0) return n * k;
        int sameColor = k;
        int differentColor = k * (k - 1);
        for (int i = 2; i < n; i++) {
            int tmp = differentColor;
            differentColor = (sameColor + differentColor) * (k - 1);
            sameColor = tmp;
        }
        return sameColor + differentColor;
    }
}
