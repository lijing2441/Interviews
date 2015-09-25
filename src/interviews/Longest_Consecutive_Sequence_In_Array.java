package interviews;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class Longest_Consecutive_Sequence_In_Array {
	/**
	 * Given an unsorted array of integers, find the length of the longest
	 * consecutive elements sequence.
	 * 
	 * For example, Given [100, 4, 200, 1, 3, 2], The longest consecutive
	 * elements sequence is [1, 2, 3, 4]. Return its length: 4.
	 * 
	 * Your algorithm should run in O(n) complexity.
	 * 
	 * @logic Use a hashmap to record the corresponding top and bottom
	 * Since it requires O(n) solution, normal sort won't be work here. Hash probably is the best choice.
	 * 3 Steps:
	 * 1. create the hashmap to hold <num, index>
	 * 2. scan the num vector from left to right, for each num
	 *       i. check whether num -1 is in the map  (loop)
	 *       ii. check whether num+1 is in the map  (loop)
     * 3. track the sequence length during scanning.
	 * 
	 */
	
	public int longestConsecutive(int[] num) {
        if(num.length < 2) return num.length;
        HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
        int longestSoFar = 1;
        for(int i : num){
            if(map.containsKey(i)) continue;
            int top = i;
            int bottom = i;
            // find the smallest number along the increasing sequence
            if(map.containsKey(i-1)){
                bottom = map.get(i-1);
            }
            // find the largest
            if(map.containsKey(i+1)){
                top = map.get(i+1);
            }
            // if the sequence need to be returned
            // we can record it here with its top and bottom
            longestSoFar = Math.max(longestSoFar, top - bottom + 1);
            map.put(i, i);
            map.put(bottom, top);
            map.put(top, bottom);
        }
        return longestSoFar;
    }
	
	/**
	 * 
	 * Since it requires O(n) solution, normal sort won't be work here. Hash probably is the best choice.
	 * 3 Steps:
	 * 1. create the hashset to hold <num, index>
	 * 2. For each element e in array.
	 *   2.1 if e in hashset:
	 *       -> find e-1, e+1 in hashset until cannot go further anymore
	 *       -> remove these elements from hashset
	 *   2.2 if the sequence is longer all previous sequences ，update max length
	 */
	public static int longestConsecutiveSet(int[] num) {
		// if array is empty, return 0
		if (num.length == 0) {
			return 0;
		}
		Set<Integer> set = new HashSet<Integer>();
		int max = 1;
	 
		for (int e : num)
			set.add(e);
	 
		for (int e : num) {
			int left = e - 1;
			int right = e + 1;
			int count = 1;
	 
			while (set.contains(left)) {
				count++;
				set.remove(left);
				left--;
			}
	 
			while (set.contains(right)) {
				count++;
				set.remove(right);
				right++;
			}
	 
			max = Math.max(count, max);
		}
	 
		return max;
	}
}
