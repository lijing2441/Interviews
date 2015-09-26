package interviews;

import java.util.Arrays;

public class Implement_ArrayList_using_Array {
	/**
	 * implement an arraylist using array
	 */
	public Integer[] mArray;
	public int size = 0;
	public int INITIAL_SIZE = 10;
	public Implement_ArrayList_using_Array() {
		mArray = new Integer[INITIAL_SIZE];
	}
	public int get(int index) {
		if (index < size) {
			return mArray[index];
		} else {
			throw new ArrayIndexOutOfBoundsException();
		}
	}
	public void add(int val) {
		if (mArray.length - size <= 5) { // self-defined to resizing if remaining spaces smaller than 5
			increaseSize();
		}
		mArray[size++] = val;
	}
	public int remove(int index) {
		if (index < size) {
			int val = mArray[index];
			mArray[index] = null;
			// move the remaining part
			int tmp = index;
			while (tmp < size) {
				mArray[tmp] = mArray[tmp + 1];
				mArray[tmp + 1] = null;
				tmp++;
			}
			size--;
			return val;
		} else {
			throw new ArrayIndexOutOfBoundsException();
		}
	}
	public void increaseSize() {
		mArray = Arrays.copyOf(mArray, mArray.length * 2);
		
	}
}
