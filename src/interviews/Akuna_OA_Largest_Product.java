package interviews;

public class Akuna_OA_Largest_Product {
	/**
	 * Given an int array of positive integers, find the pair of number that owns the largest
	 * product, the left number must be smaller than the right one.
	 * 
	 * @logic 对于每个词，找右边的最大值，和他相乘;
	 */
	public static int maxPairProduct(int[] arr) {
		// store an array
		int maxProduct = 1;
		int len = arr.length;
		int[] rightMax = new int[len];
		rightMax[len - 1] = 1;
		for (int i = len - 2; i >= 0; i--) {
			rightMax[i] = Math.max(arr[i + 1], rightMax[i + 1]);
		}
		for (int i = 0; i < len; i++) {
			if (rightMax[i] >= arr[i]) {
				maxProduct = Math.max(maxProduct, rightMax[i] * arr[i]);
			}
		}
		return maxProduct;
	}
	
	public static void main(String[] args) {
		int[] arr = {3, 4, 7, 5, 10};
		System.out.println(maxPairProduct(arr) + ", which should be 70.");
	}
}
