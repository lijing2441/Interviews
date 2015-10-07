package interviews;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Deque;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

public class Sliding_Window_Maximum {
	/**
	 * Given an array nums, there is a sliding window of size k which is moving from the very 
	 * left of the array to the very right. You can only see the k numbers in the window. 
	 * Each time the sliding window moves right by one position.
	 * 
	 * For example,
	 * Given nums = [1,3,-1,-3,5,3,6,7], and k = 3.
	 * 
	 * Window position                Max
	 * ---------------               -----
	 * [1  3  -1] -3  5  3  6  7       3
	 *  1 [3  -1  -3] 5  3  6  7       3
	 *  1  3 [-1  -3  5] 3  6  7       5
	 *  1  3  -1 [-3  5  3] 6  7       5
	 *  1  3  -1  -3 [5  3  6] 7       6
	 *  1  3  -1  -3  5 [3  6  7]      7
	 * 
	 * Therefore, return the max sliding window as [3,3,5,5,6,7].
	 * 
	 * Note: You may assume k is always valid, ie: 1 ≤ k ≤ input array's size for non-empty array.
	 * 
	 * @logic: just use PriorityQueue, create a integer comparator to sort the integers from big to small.
	 */
	public int[] maxSlidingWindow(int[] nums, int k) {
        int len = nums.length;
        if(len == 0) return new int[0];
        
        //int max = Integer.MIN_VALUE;
        int[] res = new int[len - k + 1];
        Queue<Integer> pq = new PriorityQueue<Integer>(new Comparator<Integer>() {
            @Override
            public int compare(Integer i1, Integer i2){
                return Integer.compare(i2, i1);
            }
        });
        
        for(int i = 0; i < k; i++) {
            pq.offer(nums[i]);
        }
        res[0] = pq.peek();
        for(int i = k; i < len; i++) {
            pq.remove(nums[i - k]);
            pq.offer(nums[i]);
            res[i - k + 1] = pq.peek();
        }
        return res;
    }
	
	// O(n)做法，用Deque
	public ArrayList<Integer> maxSlidingWindowDeque(int[] nums, int k) {
        int len = nums.length;
        if (len == 0) return new ArrayList<Integer>();
        Deque<Integer> deque = new LinkedList<Integer>(); // k-size deque
        ArrayList<Integer> res = new ArrayList<Integer>();
        for(int i = 0; i < len; i++) {
            int num = nums[i];
            // 保持deque为一个单调递减的单调queue，这样first位置永远是最大的
            while (!deque.isEmpty() && deque.peekLast() < num) {
                deque.pollLast();
            }
            deque.offerLast(num);
            // 如果到了k+1个 in array，要开始看是否要poll前面的
            if (i >= k && deque.peekFirst() == nums[i - k]) {
                deque.pollFirst();
            }
            // 到k个，开始可以加入result
            if (i >= k - 1) {
                res.add(deque.peekFirst());
            }
        }
        return res;
    }
}
