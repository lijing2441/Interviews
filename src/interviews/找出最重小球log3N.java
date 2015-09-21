package interviews;

public class 找出最重小球log3N {
	public static int findHeavy(int[] balls_array) {
		// return index of the heavier ball
		return getIndex(balls_array, 0, balls_array.length - 1);
	}

	public static int getIndex(int[] array, int start, int end) {
		// the function to get index
		// Time complexity: O(log3(N)). From 1point 3acres bbs
		if (start == end) {
			return start;
		}
		double len = (double) (end - start + 1) / 3;
		int size = (int) Math.ceil(len);
		int oneThird = start + size;
		int twoThird = start + size + size;
		// suppose the time complexity of weighing is O(1)
		if (getWeight(array, start, oneThird - 1) == getWeight(array, oneThird,
				twoThird - 1)) {
			return getIndex(array, twoThird, end);
		} else if (getWeight(array, start, oneThird - 1) > getWeight(array,
				oneThird, twoThird - 1)) {
			return getIndex(array, start, oneThird - 1);
		} else {
			return getIndex(array, oneThird, twoThird - 1);
		}
	}

	public static int getWeight(int[] array, int start, int end) {
		// a function return the total weight
		int totalWeight = 0;
		for (int i = start; i <= end; i++) {
			totalWeight += array[i];
		}
		return totalWeight;
	}

	public static void main(String[] args) {
		int[] array = { 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2, 1, 1, 1,
				1, 1, 1 };
		System.out.println(findHeavy(array));
	}
}
