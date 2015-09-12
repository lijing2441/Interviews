package interviews;

public class Trapping_Rain_Water {
	/**
	 * Given n non-negative integers representing an elevation map where the
	 * width of each bar is 1, compute how much water it is able to trap after
	 * raining.
	 * 
	 * For example, Given [0,1,0,2,1,0,1,3,2,1,2,1], return 6.
	 * 
	 * [analysis]
	 * We use two pointers to record the left and right boundaries. Keep track of the water stored and blocks.
	 * Increase the level one by one.
	 * 一层一层向上算，每一层算上block和water，最后减去block即可
	 */
	public int trap(int[] A) {
        if(A.length < 2) return 0;
        int l = 0;
        int r = A.length - 1;
        int water = 0;
        int block = 0;
        int curLevel = 0;
        while(l <= r){
        	// if the level is lower than the current level
        	// we can store water and increase the level
            if(Math.min(A[l], A[r]) > curLevel){
                water += (Math.min(A[l], A[r]) - curLevel) * (r - l + 1);
                curLevel = Math.min(A[l], A[r]);
            }
            if(A[l] < A[r]){
                block += A[l++];
            }else{
                block += A[r--];
            }
        }
        return water - block;
    }
}
