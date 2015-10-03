package interviews;

public class Snapchat_Shift_Array {
	/**
	 * implement shift, get and set
	 */
	public int[] array;
	public int start;
	public Snapchat_Shift_Array(int[] arr) {
		this.array = arr;
		start = 0;
	}
	public int get(int index) {
		index -= start;
		if (index < 0) index += array.length;
		return array[index];
	}
	public void set(int index, int val) {
		index -= start;
		if (index < 0) index += array.length;
		array[index] = val;
	}
	public void shift(int shift) {
		start += shift;
		start %= array.length;
	}
	public static void main(String[] args) {
		int[] A = {1, 2, 3, 4};
		Snapchat_Shift_Array test = new Snapchat_Shift_Array(A);
		test.shift(1);
		System.out.println(test.get(0));
	}
}
