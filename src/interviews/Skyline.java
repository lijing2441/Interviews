package interviews;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;

public class Skyline {
	/**
	 * Input: Array of buildings 
	 * 		{ (1,11,5), (2,6,7), (3,13,9), (12,7,16),
	 * 		  (14,3,25), (19,18,22), (23,13,29), (24,4,28) } 
	 * Output: Skyline (an array of rectangular strips) 
	 *  	   A strip has x coordinate of left side and height:
	 * 		{ (1, 11), (3, 13), (9, 0), (12, 7), (16, 3), (19, 18), (22, 3), (25, 0) }
	 */
	
	// divide and conquer, O(nlogn)
	public List<int[]> getSkyline(int[][] buildings) {
        List<int[]> res = new LinkedList<int[]>();
        if(buildings == null || buildings.length == 0 || buildings[0].length != 3) return res;
        return recurSkyline(buildings, 0, buildings.length - 1);
    }
    public LinkedList<int[]> recurSkyline(int[][] buildings, int left, int right) {
        if(left < right) {
            int mid = left + (right - left) / 2;
            return merge(recurSkyline(buildings, left, mid), recurSkyline(buildings, mid + 1, right));
        } else {
            LinkedList<int[]> res = new LinkedList<int[]>();
            res.add(new int[] { buildings[left][0], buildings[left][2] });
            res.add(new int[] { buildings[left][1], 0 });
            return res;
        }
    }
    public LinkedList<int[]> merge(LinkedList<int[]> l1, LinkedList<int[]> l2) {
        LinkedList<int[]> res = new LinkedList<int[]>();
        int h1 = 0;
        int h2 = 0;
        while(l1.size() > 0 && l2.size() > 0) {
            int h = 0, x = 0;
            if(l1.getFirst()[0] < l2.getFirst()[0]) {
                x = l1.getFirst()[0];
                h1 = l1.getFirst()[1];
                h = Math.max(h1, h2);
                l1.removeFirst();
            } else if(l1.getFirst()[0] > l2.getFirst()[0]) {
                x = l2.getFirst()[0];
                h2 = l2.getFirst()[1];
                h = Math.max(h1, h2);
                l2.removeFirst();
            } else {
                x = l1.getFirst()[0];
                h1 = l1.getFirst()[1];
                h2 = l2.getFirst()[1];
                h = Math.max(h1, h2);
                l1.removeFirst();
                l2.removeFirst();
            }
            if(res.size() == 0 || h != res.getLast()[1]) {
                res.add(new int[] {x, h});
            }
        }
        res.addAll(l1);
        res.addAll(l2);
        return res;
    }
    
    // max-heap method
    /**
	 * Notice that "key points" are either the left or right edges of the
	 * buildings. Therefore, we first obtain both the edges of all the N
	 * buildings, and store the 2N edges in a sorted array. Maintain a max-heap
	 * of building heights while scanning through the edge array:
	 * 
	 * If the current edge is a left edge, then add the height of its associated
	 * building to the max-heap; If the edge is a right one, remove the
	 * associated height from the heap.
	 * 
	 * Then we take the top value of the heap (yi) as the maximum height at the
	 * current edge position (xi).
	 * 
	 * Now (xi, yi) is a potential key point. If yi is the same as the height of
	 * the last key point in the result list, it means that this key point is
	 * not a REAL key point, but rather a horizontal continuation of the last
	 * point, so it should be discarded; otherwise, we add (xi,yi) to the result
	 * list because it is a real key point. Repeat this process until all the
	 * edges are checked.
	 * 
	 * It takes O(NlogN) time to sort the edge array. For each of the 2N edges,
	 * it takes O(1) time to query the maximum height but O(logN) time to add or
	 * remove elements. Overall, this solution takes O(NlogN) time.
	 */
    public List<int[]> getSkylineHeap(int[][] buildings) {
        List<int[]> res = new ArrayList<int[]>();
        List<int[]> edges = new ArrayList<int[]>();
        for (int[] b : buildings) {
            edges.add(new int[] {b[0], -b[2]});
            edges.add(new int[] {b[1], b[2]});
        }
        // get the sorted x
        Collections.sort(edges, new Comparator<int[]>() {
            public int compare(int[] a, int[] b) {
                if (a[0] != b[0]) {
                    return a[0] - b[0];
                } else {
                    return a[1] - b[1];
                }
            }
        });
        // get the highest height for the current range
        PriorityQueue<Integer> pq = new PriorityQueue<Integer>(new Comparator<Integer>() {
            public int compare(Integer a, Integer b) {
                return b - a;
            }
        });
        pq.offer(0);
        int prev = 0;
        for (int[] edge : edges) {
            if (edge[1] < 0) {
                // left edge
                pq.offer(-edge[1]);
            } else {
                // right edge
                pq.remove(edge[1]);
            }
            int cur = pq.peek(); // current height
            if (cur != prev) {
                res.add(new int[] {edge[0], cur});
                prev = cur;
            }
        }
        return res;
        
    }

}
