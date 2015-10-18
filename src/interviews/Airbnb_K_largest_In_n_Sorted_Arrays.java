package interviews;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

public class Airbnb_K_largest_In_n_Sorted_Arrays {
	/**
	 * 1. if brute force => search for largest head each time O(n), 重复k次 => O(n*k)
	 * 2. heap? n-size的heap，每次最大的入堆，要标每个入堆的下标，然后还要存一个index array来标明每个array走到哪一步了
	 * 		O(n + klogn) 建初始堆 => nlogn
	 */
	public static int findKLargestNum(List<List<Integer>> lists, int k) {
		if (lists == null || lists.size() == 0) return -1;
		int len = lists.size();
		int[] indice = new int[len];
		PriorityQueue<NumWithIndex> pq = new PriorityQueue<NumWithIndex>(len, new Comparator<NumWithIndex>() {
			// build a max heap
			public int compare(NumWithIndex i1, NumWithIndex i2) {
				return (i2.val - i1.val);
			}
		});
		// build a n-size heap
		for(int i = 0; i < len; i++) {
			List<Integer> curList = lists.get(i);
			// if empty list is possible, need to check on it here
			int num = curList.get(curList.size() - 1);
			pq.offer(new NumWithIndex(num, i));
			// mark position in each array
			indice[i] = curList.size() - 1;
		}
		// poll each and offer the new ones
		int res = 0;
		for (int i = 0; i < k && !pq.isEmpty(); i++) {
			NumWithIndex curElem = pq.poll();
			// 找到第k个，return即可
			if (i == k - 1) {
				res = curElem.val;
				return res;
			}
			// otherwise, 找下一个入堆
			int arrayIndex = curElem.arrayIndex;
			int curPos = indice[arrayIndex];
			if (curPos > 0) {
				int num = lists.get(arrayIndex).get(curPos - 1);
				indice[arrayIndex] = curPos - 1;
				pq.offer(new NumWithIndex(num, arrayIndex));
			}
		}
		// not enough elements
		return -1;
	}
	// 在n个有序列中找第k大数字
	public static void main(String[] args) {
		List<Integer> l1 = new ArrayList<Integer>();
		l1.add(1);
		l1.add(2);
		l1.add(3);
		l1.add(10);
		
		List<Integer> l2 = new ArrayList<Integer>();
		l2.add(4);
		l2.add(7);
		l2.add(8);
		l2.add(9);
		
		List<Integer> l3 = new ArrayList<Integer>();
		l3.add(5);
		l3.add(6);
		List<List<Integer>> lists = new ArrayList<List<Integer>>();
		lists.add(l1);
		lists.add(l2);
		lists.add(l3);
		System.out.println(findKLargestNum(lists, 3) + ", which should be 8.");
	}
}
class NumWithIndex {
	int val;
	int arrayIndex;
	public NumWithIndex(int v, int index) {
		this.val = v;
		this.arrayIndex = index;
	}
}