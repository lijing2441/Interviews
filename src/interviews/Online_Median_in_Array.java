package interviews;

import java.util.Comparator;
import java.util.PriorityQueue;

public class Online_Median_in_Array {
	public int[] medianII(int[] nums) {
        if (nums == null || nums.length == 0) return null;
        int len = nums.length;
        int[] res = new int[len];
        // make minHeap smaller or equal to maxHeap
        PriorityQueue<Integer> minHeap = new PriorityQueue<Integer>();
        PriorityQueue<Integer> maxHeap = new PriorityQueue<Integer>(new Comparator<Integer>() {
           public int compare(Integer a, Integer b) {
               return b - a;
           }
        });
        for (int i = 0; i < len; i++) {
            if (!minHeap.isEmpty() && minHeap.size() == maxHeap.size()) {
                // add element to maxHeap
                if (nums[i] > minHeap.peek()) {
                    maxHeap.offer(minHeap.poll());
                    minHeap.offer(nums[i]);
                } else {
                    maxHeap.offer(nums[i]);
                }
            } else {
                // add element to minHeap
                if (maxHeap.isEmpty()) {
                    maxHeap.offer(nums[i]);
                } else if (nums[i] < maxHeap.peek()) {
                    minHeap.offer(maxHeap.poll());
                    maxHeap.offer(nums[i]);
                } else {
                    minHeap.offer(nums[i]);
                }
            }
            res[i] = maxHeap.peek();
        }
        return res;
    }
}
