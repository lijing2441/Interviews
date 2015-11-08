package interviews;

import java.util.Arrays;

public class H_Index {
	/**
	 * Given an array of citations (each citation is a non-negative integer) of
	 * a researcher, write a function to compute the researcher's h-index.
	 * 
	 * According to the definition of h-index on Wikipedia:
	 * "A scientist has index h if h of his/her N papers have at least h citations each, 
	 * and the other N − h papers have no more than h citations each."
	 * 
	 * For example, given citations = [3, 0, 6, 1, 5], which means the
	 * researcher has 5 papers in total and each of them had received 3, 0, 6,
	 * 1, 5 citations respectively. Since the researcher has 3 papers with at
	 * least 3 citations each and the remaining two with no more than 3
	 * citations each, his h-index is 3.
	 * 
	 * Note: If there are several possible values for h, the maximum one is
	 * taken as the h-index.
	 */
	// sort method
	public int hIndex(int[] citations) {
        int len = citations.length;
        if(len == 0) return 0;
        
        Arrays.sort(citations);
        for(int i = len; i > 0; i--) {
            int below = len - i;
            if(citations[below] < i) {
                continue;
            } else return i;
        }
        return 0;
    }
	// O(n) method
	public int hIndexN(int[] citations) {
        int len = citations.length;
        if (len == 0) return 0;
        // count the number of papers with their citations
        int[] count = new int[len + 1];
        for (int c : citations) {
            if (c > len) count[len]++;
            else count[c]++;
        }
        int total = 0; // number of papers so far
        for (int i = len; i >= 0; i--) {
            total += count[i];
            if (total >= i) return i;
        }
        return 0;
    }
	// Problem II: the array is sorted
	public int hIndexII_1(int[] citations) {
        int len = citations.length;
        if(len == 0) return 0;
        
        int left = 0, right = len - 1;
        while (left <= right) {
            int mid = (left + right) / 2;
            int cit = citations[mid];
            int limit = len - mid;
            if (cit >= limit) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return len - right - 1;
    }
	public int hIndexII_2(int[] citations) {
        int len = citations.length;
        if(len == 0) return 0;
        
        int left = 0, right = len - 1;
        while (left <= right) {
            int mid = (left + right) / 2;
            if (citations[mid] >= len - mid) {
                if (mid == 0 || citations[mid - 1] <= len - mid) return len - mid;
                else right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return 0;
    }
	
}
