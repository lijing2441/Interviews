package interviews;

public class Jump_Game {
	/**
	 * Given an array of non-negative integers, you are initially positioned at
	 * the first index of the array.
	 * 
	 * Each element in the array represents your maximum jump length at that
	 * position. Determine if you are able to reach the last index.
	 * 
	 * @idea use greedy
	 */
	public boolean canJump(int[] A) {
        int n = A.length;
        int canReach = 0;
        for(int i = 0; i < n; i++){
            if(canReach < i) return false;
            if(canReach >= n - 1) return true;
            canReach = Math.max(canReach, i + A[i]);
        }
        return false;
    }
	
	/**
	 * Jump Game II: Your goal is to reach the last index in the minimum number of jumps.
	 * 
	 * For example: Given array A = [2,3,1,1,4]
	 * 
	 * The minimum number of jumps to reach the last index is 2. (Jump 1 step
	 * from index 0 to 1, then 3 steps to the last index.)
	 */
	public int jump(int[] A) {
		// the count of the steps
		int count = 0;
		// the maximum position of the last step can reach
		int last = 0;
		// the maximum position of the next step can reach
		int next = 0;
		// if i == A.length - 1, we arrive at the last step and do not need to proceed
		// Moreover, i cannot be larger than the last step we can reach
		for (int i = 0; i < A.length - 1 && i <= last; i++) {
			next = Math.max(next, A[i] + i);
			// arrive the last step, then count+1 and set the last step to the next
			if (i == last) {
				count++;
				last = next;
			}
		}
		return last >= A.length - 1 ? count : -1;
	}
}
