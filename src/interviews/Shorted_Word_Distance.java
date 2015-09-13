package interviews;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

public class Shorted_Word_Distance {
	/**
	 * Given a list of words and two words word1 and word2, return the shortest
	 * distance between these two words in the list.
	 * 
	 * For example, Assume that words = ["practice", "makes", "perfect",
	 * "coding", "makes"].
	 * 
	 * Given word1 = “coding”, word2 = “practice”, return 3. 
	 * 
	 * Given word1 = "makes", word2 = "coding", return 1.
	 * 
	 * Note: You may assume that word1 does not equal to word2, and word1 and
	 * word2 are both in the list.
	 */
	public int shortestDistance(String[] words, String word1, String word2) {
		int dist = words.length - 1;
		int index1 = -1, index2 = -1;
		for (int i = 0; i < words.length; i++) {
			if (words[i].equals(word1)) {
				index1 = i;
				if (index2 != -1) {
					int cur = Math.abs(index1 - index2);
					if (cur < dist)
						dist = cur;
				}
			} else if (words[i].equals(word2)) {
				index2 = i;
				if (index1 != -1) {
					int cur = Math.abs(index1 - index2);
					if (cur < dist)
						dist = cur;
				}
			}
		}
		return dist;
	}
	/**
	 * II: now you are given the list of words and your method will be called
	 * repeatedly many times with different parameters. How would you optimize it?
	 * 
	 * Design a class.
	 * 
	 * @idea make a map, with key as string, and value as position in the array
	 */
public Map<String, List<Integer>> indexMap;
    
    public void WordDistance(String[] words) {
        indexMap = new HashMap<String, List<Integer>>();
        int n = words.length;
        for(int i = 0; i < n; i++) {
            if(indexMap.containsKey(words[i])) {
                indexMap.get(words[i]).add(i);
            } else {
                List<Integer> list = new ArrayList<Integer>();
                list.add(i);
                indexMap.put(words[i], list);
            }
        }
    }

    public int shortest(String word1, String word2) {
        List<Integer> l1 = indexMap.get(word1);
        List<Integer> l2 = indexMap.get(word2);
        int i = 0, j = 0, res = Integer.MAX_VALUE;
        while(i < l1.size() && j < l2.size()) {
            int index1 = l1.get(i), index2 = l2.get(j);
            if(index1 < index2) {
                res = Math.min(res, index2 - index1);
                i++;
            } else {
                res = Math.min(res, index1 - index2);
                j++;
            }
        }
        return res;
    }
    
    /**
     * III: The only difference is now word1 could be the same as word2.
     */
    public int shortestWordDistance(String[] words, String word1, String word2) {
        int dist = words.length - 1;
        if(word1.equals(word2)) {
            int last = -1;
            for(int i = 0; i < words.length; i++) {
                if(words[i].equals(word1)) {
                    if(last != -1) {
                        dist = Math.min(dist, i - last);
                    }
                    last = i;
                }
            }
            return dist;
        }
        int index1 = -1, index2 = -1;
        for(int i = 0; i < words.length; i++) {
            if(words[i].equals(word1)) {
                index1 = i;
                if(index2 != -1) {
                    int cur = Math.abs(index1 - index2);
                    if(cur < dist) dist = cur;
                }
            } else if(words[i].equals(word2)) {
                index2 = i;
                if(index1 != -1) {
                    int cur = Math.abs(index1 - index2);
                    if(cur < dist) dist = cur;
                }
            }
        }
        return dist;
    }
}
