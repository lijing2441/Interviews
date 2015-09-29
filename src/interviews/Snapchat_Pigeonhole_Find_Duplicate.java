package interviews;

public class Snapchat_Pigeonhole_Find_Duplicate {
	/**
	 * Given an array nums containing n + 1 integers where each integer is
	 * between 1 and n (inclusive), prove that at least one duplicate element
	 * must exist. Assume that there is only one duplicate number, find the
	 * duplicate one.
	 * 
	 * Note: 
	 * - You must not modify the array (assume the array is read only). 
	 * - You must use only constant extra space. 
	 * - Your runtime complexity should be less than O(n^2).
	 * 
	 * 分析：
	 * It's a binary search solution. The search space is numbers between 1 to n. 
	 * Each time I select a number mid (which is the one in the middle).
	 *  
	 * Let's say n=10 and I select mid=5. Then I count all the numbers in the array 
	 * which are less than equal mid. If the there are more than 5 numbers that are 
	 * less than 5, then by Pigeonhole Principle, one of them has occurred more than once. 
	 * 
	 * So I shrink the search space from [1 10] to [1 5]. Otherwise the duplicate 
	 * number is in the second half so for the next step the search space would be [6 10].
	 */
	public int findDuplicate(int[] nums) {
        int l = 0;
        int r = nums.length - 1;
        while (l < r) {
            int mid = l + (r - l) / 2;
            int count = 0;
            for (int i : nums) {
                if (i <= mid) {
                    count += 1;
                }
            }
            if (count <= mid) {
                l = mid + 1;
            } else {
                r = mid;
            }
        }
        return l;
    }
	
}
