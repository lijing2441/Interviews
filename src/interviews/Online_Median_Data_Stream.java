package interviews;

import java.util.Comparator;
import java.util.PriorityQueue;

public class Online_Median_Data_Stream {
	/**
	 * Given that integers are read from a data stream. Find median of elements
	 * read so for in efficient way.
	 * 
	 * @question for interviewer: all numbers counted or only part?
	 * 			 how many times you want the median?
	 * 			 how many number are there approximately? (build the heaps)
	 * 
	 * @analysis Step 1: Add next item to one of the heaps
	 * 
	 *           if next item is smaller than maxHeap root add it to maxHeap,
	 *           else add it to minHeap
	 * 
	 *           Step 2: Balance the heaps (after this step heaps will be either
	 *           balanced or one of them will contain 1 more item)
	 * 
	 *           if number of elements in one of the heaps is greater than the
	 *           other by more than 1, remove the root element from the one
	 *           containing more elements and add to the other one
	 *           
	 *           Then at any given time you can calculate median like this:
	 *           If the heaps contain equal elements; 
	 *           	median = (root of maxHeap + root of minHeap)/2
	 *           Else
	 *           	median = root of the heap with more elements
	 * @complexity Time Complexity: If we omit the way how stream was read, 
	 * 			 complexity of median finding is O(N log N), as we need to read the 
	 * 			 stream, and due to heap insertions/deletions.
	 */
	//maxHeap stores the smaller half, while minHeap stores the larger half
	private static PriorityQueue<Integer> maxHeap, minHeap;
	
	// we keep the minHeap size <= maxHeap size
	public static void addNewNumberI(int randomNumber) {
		if (maxHeap.size() == minHeap.size()) {
			// after this, the maxHeap should have one more element than the minHeap
			if ((minHeap.peek() != null) && randomNumber > minHeap.peek()) {
				maxHeap.offer(minHeap.poll());
				minHeap.offer(randomNumber);
			} else {
				maxHeap.offer(randomNumber);
			}
		} else {
			// after this, the two heaps should have same size
			if (randomNumber < maxHeap.peek()) {
				minHeap.offer(maxHeap.poll());
				maxHeap.offer(randomNumber);
			} else {
				minHeap.offer(randomNumber);
			}
		}
	}
	
	public static double getMedianI() {
		// if both heaps are empty, return 
		if(maxHeap.isEmpty() && minHeap.isEmpty()) return Double.MIN_VALUE;
		// if one of the heap is empty, return the other
		if (maxHeap.isEmpty()) {
			return minHeap.peek();
		} else if (minHeap.isEmpty()) {
			return maxHeap.peek();
		}
		// if the two heaps own the same size of elements
		if (maxHeap.size() == minHeap.size()) {
			return (minHeap.peek() + maxHeap.peek()) / 2;
		} else if (maxHeap.size() > minHeap.size()) {
			return maxHeap.peek();
		} else {
			return minHeap.peek();
		}
	}
	
	/**
     * @param nums: A list of integers.
     * @return: the median of numbers
     */
    private PriorityQueue<Integer> minHeap2 = new PriorityQueue<Integer>(); // store large half
    private PriorityQueue<Integer> maxHeap2 = new PriorityQueue<Integer>(new Comparator<Integer>(){
            public int compare(Integer num1, Integer num2) {
                return num2 - num1;
            }
        }); // store small half
    public int[] medianII(int[] nums) {
        if (nums == null || nums.length == 0) return null;
        int len = nums.length;
        int[] res = new int[len];
        res[0] = nums[0];
        maxHeap2.offer(nums[0]);
        for (int i = 1; i < len; i++) {
            addNewNumber(nums[i]);
            res[i] = getMedian();
        }
        return res;
    }
    // maxHeap size >= minHeap size
    public void addNewNumber(int num) {
        if (maxHeap2.size() == minHeap2.size()) { // maxHeap入堆
            if (maxHeap2.peek() != null && minHeap2.peek() < num) {
                maxHeap2.offer(minHeap2.poll()); // make the larger num to be top
                minHeap2.offer(num);
            } else {
                maxHeap2.offer(num);
            }
        } else {
            if (maxHeap2.peek() > num) {
                minHeap2.offer(maxHeap2.poll());
                maxHeap2.offer(num);
            } else {
                minHeap2.offer(num);
            }
        }
    }
    
    // get the smaller one always
    public int getMedian() {
        if (minHeap2.isEmpty() && maxHeap2.isEmpty()) return Integer.MIN_VALUE;
        if (minHeap2.isEmpty()) {
            return maxHeap2.peek();
        }
        if (maxHeap2.isEmpty()) {
            return minHeap2.peek();
        } 
        if (minHeap2.size() == maxHeap2.size()) {
            return Math.min(maxHeap2.peek(), minHeap2.peek());
        } else if (maxHeap2.size() > minHeap2.size()) {
            return maxHeap2.peek();
        } else {
            return minHeap2.peek();
        }
    }
    
    // if I want to get the n/10 th element? 1/10 max heap + 9/10 min heap!
    public int get1of10thMedian() {
        if (minHeap2.isEmpty() && maxHeap2.isEmpty()) return Integer.MIN_VALUE;
        if (minHeap2.isEmpty()) {
            return maxHeap2.peek();
        }
        if (maxHeap2.isEmpty()) {
            return minHeap2.peek();
        } 
        if (minHeap2.size() == 9 * maxHeap2.size()) {
            return Math.min(maxHeap2.peek(), minHeap2.peek());
        } else if (maxHeap2.size() > 9 * minHeap2.size()) {
            return maxHeap2.peek();
        } else {
            return minHeap2.peek();
        }
    }
}