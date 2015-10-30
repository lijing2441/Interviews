package interviews;

import java.util.Map;
import java.util.HashMap;

public class Add_Delete_RandomDelete_in_O1 {
	/**
	 * A hashtable H and an array A. The hashtable keys are the elements in the
	 * data structure, and the values are their positions in the array.
	 * 
	 * insert(value): append the value to array and let i be it's index in A.
	 * Set H[value]=i.
	 * 
	 * remove(value): We are going to replace the cell that contains value in A
	 * with the last element in A.
	 * 
	 * contains(value): return H.contains(value)
	 * 
	 * getRandomElement(): let r=random(current size of A). return A[r].
	 */
	public Map<Integer, Integer> map;
	public int[] arr;
	public int index;
	public int capacity;
	public Add_Delete_RandomDelete_in_O1(int capacity) {
		this.map = new HashMap<Integer, Integer>();
		this.arr = new int[capacity];
		this.index = 0;
		this.capacity = capacity;
	}
	
	// 如果已经存在不需要再输入
	public void insert(int num) {
		if (!map.containsKey(num)) {
			if (index < capacity) {
				map.put(num, index);
				arr[index++] = num;
			} else {
				// resize or throw exception
				try {
					throw new Exception("the data structure is full");
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
	public void remove(int num) {
		if (!map.containsKey(num)) {
			return; // nothing to remove
		}
		if (index == 0) {
			try {
				throw new Exception("the data structure is empty");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		// else, move the last element to the pos
		int lastNum = arr[--index];
		int toReplace = map.get(num);
		map.remove(num);
		map.put(lastNum, toReplace);
		arr[toReplace] = lastNum;
		arr[index] = 0; // set the last position to 0
	}
	public boolean contains(int num) {
		return map.containsKey(num);
	}
	public int getRandomElement() {
		int rand = ((int)(Math.random() * (index))) + 1;
		return arr[rand];
	}
	public int removeRandomElement() {
		int res = getRandomElement();
		this.remove(res);
		return res;
	}
	
	public static void main(String[] args) {
		Add_Delete_RandomDelete_in_O1 cur = new Add_Delete_RandomDelete_in_O1(10);
		cur.insert(1);
		cur.insert(2);
		cur.insert(3);
		cur.insert(4);
		System.out.println(cur.map.size());
		int ran = cur.removeRandomElement();
		System.out.println(ran);
		System.out.println(cur.map.size());
		for (Map.Entry<Integer, Integer> entry: cur.map.entrySet()) {
			System.out.println(entry.getKey() + " at index: " + entry.getValue());
		}
		for (int i = 0; i < cur.arr.length; i++) {
			System.out.println("index " + i + ": has Element: " + cur.arr[i]);
		}
	}
}
