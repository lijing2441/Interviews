package interviews;

public class Hedvig_Cycle_detection思想O_n找重复元素 {
	/**
	 * 有n+1个1到n的数字，找出重复的字符。可能有多个字符重复,找出其中任意一个就可以。
	 * 
	 * 每个字符可能重复2遍以上。 read only，O(1) space.
	 * 
	 * sort，不行，因为是read only; hash，不行，因为O(1) space; 用linked list find
	 * cycle的方式，也不行，可以找到有重复，可是不知道怎么找具体重复的数值。
	 */
	// 可用pigeon hole principle
	// 如果把数组看成一个映射，i -> f(i) = a[i]， 那么这个问题可以转换成更抽象的模型
	// 其实只要选取不孤立的那个点当作起点就可以检测环, 选最后一个
	// nums 为 n + 1 大小，从最后一个数开始
	public static void findOne(int[] nums) {
		int slow = nums.length - 2;
		int fast = nums.length - 2;
		while (true) {
			slow = nums[slow];
			fast = nums[nums[fast]];
			if (slow == fast) break;
		}
		fast = nums.length - 2;
		while (true) {
			slow = nums[slow];
			fast = nums[fast];
			if (slow == fast) break;
		}
		System.out.println(fast);
	}
	
	public static void main(String[] args) {
		int[] nums = {1, 5, 3, 3, 2, 0};
		findOne(nums);
	}
}
