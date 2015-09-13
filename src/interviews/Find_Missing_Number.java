package interviews;

public class Find_Missing_Number {
	/**
	 * Given an array contains N numbers of 0 .. N, find which number doesn't
	 * exist in the array.
	 * 
	 * Have you met this question in a real interview? Yes Example Given N = 3
	 * and the array [0, 1, 3], return 2.
	 * 
	 * Challenge Do it in-place with O(1) extra memory and O(n) time.
	 */
	/**
	 * @param nums
	 *            : an array of integers
	 * @return: an integer
	 */
	public int findMissing(int[] nums) {
		// write your code here
		int i = 0;
		int res = nums.length;
		for (int num : nums) {
			res ^= i;
			res ^= num;
			i++;
		}
		return res;
	}
	
	// or use math
	public int missingNumber(int[] nums) {
        int len = nums.length;
        int res = len * (len + 1) / 2;
        for(int i : nums) {
            res -= i;
        }
        return res;
    }
}
