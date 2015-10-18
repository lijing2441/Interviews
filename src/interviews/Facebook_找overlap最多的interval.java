package interviews;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.HashMap;
import java.util.Set;

public class Facebook_找overlap最多的interval {
	/**
	 * Given a sequence of intervals, return the max overlapping interval
	 */
	public static List<Interval> getMaxOverlapInterval(List<Interval> intervals) {
		int len = intervals.size();
		int[] starts = new int[len];
		int[] ends = new int[len];
		int[] intervalPoints = new int[len * 2]; // used to find the maximal interval finally
		int ptr = 0, ptr2 = 0;
		for (Interval i: intervals) {
			starts[ptr] = i.start;
			ends[ptr] = i.end;
			intervalPoints[ptr2++] = i.start;
			intervalPoints[ptr2++] = i.end;
			ptr++;
		}
		Arrays.sort(starts);
		Arrays.sort(ends);
		Arrays.sort(intervalPoints);
		int sPtr = 0, ePtr = 0, count = 0, maxCount = 0;
		// use differentialArray to node the difference between all possible positions
		int[] differentialArray = new int[len * 2];
		int counter = 0; // mark as counter in differentialArray
		while (sPtr < len && ePtr < len) {
//			if (sPtr < len && ePtr < len) {
                if (starts[sPtr] < ends[ePtr]) {
                	count++;
                	differentialArray[counter++] = count;
                	if (maxCount < count) {
                        maxCount = count;
                    }
                    sPtr++;
                } else {
                	count--;
                	differentialArray[counter++] = count;
                	ePtr++;
                }
//            } 
//			else if (sPtr < len) {
//            	count++;
//            	differentialArray[counter++] = count;
//            	if (maxCount < count) {
//                    maxCount = count;
//                }
//                sPtr++;
//            } 
//			else {
//            	count--;
//            	differentialArray[counter++] = count;
//            	ePtr++;
//            }
		}
		System.out.println("Maximal overlap number should be : " + maxCount);
		List<Interval> res = new ArrayList<Interval>();
		for (int i = 0; i < len * 2 - 1; i++) {
			if (differentialArray[i] == maxCount) {
				// in case of continuous intervals
				if (res.size() != 0 && res.get(res.size() - 1).end == intervalPoints[i]) {
					Interval cur = res.get(res.size() - 1);
					cur.end = intervalPoints[i + 1];
//					res.remove(res.size() - 1);
//					res.add(cur);
				} else {
					Interval newInterval = new Interval(intervalPoints[i], intervalPoints[i + 1]);
					res.add(newInterval);
				}
			}
		}
		return res;
	}
	public static void main(String[] args) {
		// 5, 11, 6, 18, 2, 5, 3, 12
		Interval i1 = new Interval(5, 11);
		Interval i2 = new Interval(6, 18);
		Interval i3 = new Interval(2, 5);
		Interval i4 = new Interval(3, 12);
		List<Interval> list = new ArrayList<Interval>();
		list.add(i1);
		list.add(i2);
		list.add(i3);
		list.add(i4);
		List<Interval> results = getMaxOverlapInterval(list);
		for (Interval res : results) System.out.println("[" + res.start + ", " + res.end + "] ");
	}
	
	
	// 线段树
	
}
class Solution {
	class SegmentTreeNode {
	    int start;
	    int end;
	    int cnt;
	    SegmentTreeNode left, right;
	    public SegmentTreeNode(int sta, int end) {
	            this.start = sta;
	            this.end = end;
	            left = null;
	            right = null;
	    }
	}
	private SegmentTreeNode buildST(int sta, int end) {
        if (sta > end) {
                return null;
        }
        if (sta == end) {
                return new SegmentTreeNode(sta, end);
        }
        int mid = (sta+end)/2;
        SegmentTreeNode midNode = new SegmentTreeNode(sta, end);
        midNode.left = buildST(sta, mid);
        midNode.right = buildST(mid+1, end);
        return midNode;
	}

	SegmentTreeNode root = null;
	int maxNum = 0;

	private void updateST(SegmentTreeNode root, int sta, int end, HashMap<Integer, Integer> hash) {
        if (root.start == sta && root.end == end && sta == end) {
                root.cnt++;
                if (root.cnt > maxNum) {
                        maxNum = root.cnt;
                }
                hash.put(root.start, root.cnt);
                return;
        }
        int mid = (root.start + root.end) / 2;
        if (end <= mid) {
                updateST(root.left, sta, end, hash);
        }
        else if (sta > mid) {
                updateST(root.right, sta, end, hash);
        }
        else {
                updateST(root.left, sta, mid, hash);
                updateST(root.right, mid+1, end, hash);
        }
        //root.cnt = root.left.cnt+root.right.cnt;
	}
	public List<Integer> getmax(Interval[] num) {
        HashMap<Integer, Integer> hash = new HashMap<>();
        int min = num[0].start, max = num[0].end;
        for (int i = 1; i < num.length; i++) {
                if (num[i].start < min) {
                        min = num[i].start;
                }
                if (num[i].end > max) {
                        max = num[i].end;
                }
        }
        root = buildST(min, max);
        for (int i = 0; i < num.length; i++) {
                updateST(root, num[i].start, num[i].end, hash);
        }
        Set<Integer> key = hash.keySet();
        List<Integer> res = new LinkedList<>();
        for (int x: key) {
                if (hash.get(x) == maxNum) {
                        res.add(x);
                }
        }
        return res;
	}
}