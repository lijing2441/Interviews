package interviews;

import java.util.ArrayList;
import java.util.List;

public class Airbnb_K_largest_In_n_Sorted_Arrays {
	public static int findKLargestNum(List<List<Integer>> lists, int k) {
		if (lists == null || lists.size() == 0) return -1;
		
	}
	// 在n个有序列中找第k大数字
	public static void main(String[] args) {
		List<Integer> l1 = new ArrayList<Integer>();
		l1.add(1);
		l1.add(2);
		l1.add(3);
		l1.add(10);
		
		List<Integer> l2 = new ArrayList<Integer>();
		l1.add(4);
		l1.add(7);
		l1.add(8);
		l1.add(9);
		
		List<Integer> l3 = new ArrayList<Integer>();
		l1.add(5);
		l1.add(6);
		List<List<Integer>> lists = new ArrayList<List<Integer>>();
		lists.add(l1);
		lists.add(l2);
		lists.add(l3);
		System.out.println(findKLargestNum(lists, 3) + ", which should be 8.");
	}
}
