package interviews;

import java.util.List;

public class Snapchat_Bloomfilter {
	public boolean[] bitArray;
	public List<HashFunc> hashFuncs;
	public Snapchat_Bloomfilter(int capacity) {
		this.bitArray = new boolean[capacity];
	}
	public void add(int x) {
		for (HashFunc func: hashFuncs) {
			int key = func.getHash(x);
			key = key % bitArray.length;
			bitArray[key] = true;
		}
	}
	public boolean check(int x) {
		for (HashFunc func: hashFuncs) {
			int key = func.getHash(x);
			key = key % bitArray.length;
			if (!bitArray[key]) return false;
		}
		return true;
	}
	// 当占满某个百分比时候，怎么extend
	// 加一个新数组，同样大小，新加入的东西放到新数组中，在check的时候对两个数组进行hash，如果任意一个返回true，则存在
}
class HashFunc {
	public int getHash(int key) {
		return key - 1;
	}
}