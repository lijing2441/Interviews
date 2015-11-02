package interviews;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.PriorityQueue;

public class Sliding_Window_Median {
	public ArrayList<Integer> medianSlidingWindow(int[] nums, int k) {
		ArrayList<Integer> ans = new ArrayList<Integer>();
		if (nums.length == 0)
			return ans;
		int median = nums[0];
		// 两个堆
		PriorityQueue<Integer> minheap = new PriorityQueue<Integer>();
		PriorityQueue<Integer> maxheap = new PriorityQueue<Integer>(new Comparator<Integer>() {
			public int compare(Integer i1, Integer i2) {
				return i2 - i1;
			}
		});
		
		for (int i = 0; i < nums.length; i++) {
			if (i != 0) {
				if (nums[i] > median) {
					minheap.add(nums[i]);
				} else {
					maxheap.add(nums[i]);
				}
			}

			if (i >= k) {
				if (median == nums[i - k]) {
					if (maxheap.size() > 0) {
						median = maxheap.poll();
					} else if (minheap.size() > 0) {
						median = minheap.poll();
					} 

				} else if (median < nums[i - k]) {
					minheap.remove(nums[i - k]);
				} else {
					maxheap.remove(nums[i - k]);
				}
			}

			while (maxheap.size() > minheap.size()) {
				minheap.add(median);
				median = maxheap.poll();
			}
			while (minheap.size() > maxheap.size() + 1) {
				maxheap.add(median);
				median = minheap.poll();
			}

			if (i + 1 >= k) {
				ans.add(median);
			}
		}
		return ans;
	}

	public ArrayList<Integer> getmedian(int[] nums, int k) {
		ArrayList<Integer> ans = new ArrayList<Integer>();
		for (int i = 0; i < nums.length; i++) {
			ArrayList<Integer> temp = new ArrayList<Integer>();
			for (int j = i; j < i + k && j < nums.length; j++)
				temp.add(nums[j]);
			Collections.sort(temp);
			Collections.reverse(temp);
			ans.add(temp.get(temp.size() / 2));
		}
		return ans;
	}
}
