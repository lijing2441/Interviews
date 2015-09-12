package interviews;

import java.util.PriorityQueue;

public class Online_Median {
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
	public static void addNewNumber(int randomNumber) {
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
	
	public static double getMedian() {
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
}



/**
 * Other two methods:
 * 
 * Method 1: Insertion Sort
 * 
 * If we can sort the data as it appears, we can easily locate median
 * element. Insertion Sort is one such online algorithm that sorts the data
 * appeared so far. At any instance of sorting, say after sorting i-th
 * element, the first i elements of array are sorted. The insertion sort
 * doesn’t depend on future data to sort data input till that point. In
 * other words, insertion sort considers data sorted so far while inserting
 * next element. This is the key part of insertion sort that makes it an
 * online algorithm.
 * 
 * However, insertion sort takes O(n2) time to sort n elements. Perhaps we
 * can use binary search on insertion sort to find location of next element
 * in O(log n) time. Yet, we can’t do data movement in O(log n) time. No
 * matter how efficient the implementation is, it takes polynomial time in
 * case of insertion sort.
 * 
 * Interested reader can try implementation of Method 1.
 * 
 * Method 2: Augmented self balanced binary search tree (AVL, RB, etc…)
 * 
 * At every node of BST, maintain number of elements in the subtree rooted
 * at that node. We can use a node as root of simple binary tree, whose left
 * child is self balancing BST with elements less than root and right child
 * is self balancing BST with elements greater than root. The root element
 * always holds effective median.
 * 
 * If left and right subtrees contain same number of elements, root node
 * holds average of left and right subtree root data. Otherwise, root
 * contains same data as the root of subtree which is having more elements.
 * After processing an incoming element, the left and right subtrees (BST)
 * are differed utmost by 1.
 * 
 * Self balancing BST is costly in managing balancing factor of BST.
 * However, they provide sorted data which we don’t need. We need median
 * only. The next method make use of Heaps to trace median.
 */
