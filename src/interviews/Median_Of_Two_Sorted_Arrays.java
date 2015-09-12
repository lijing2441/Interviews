package interviews;

import java.util.Arrays;

public class Median_Of_Two_Sorted_Arrays {

	// Binary search method, O(logn), O(1)
	/**
	 * Given a sorted array A of length m, we can split it into two part: {
	 * A[0], A[1], ... , A[i - 1] }; { A[i], A[i + 1], ... , A[m - 1] } All
	 * elements in right part is greater than elements in left part. The left
	 * part has "i" elements, and right part has "m - i" elements. There are
	 * "m + 1" kinds of splits. (i = 0 ~ m) When i = 0, the left part has "0"
	 * elements, right part has "m" elements. When i = m, the left part has "m"
	 * elements, right part has "0" elements.
	 * 
	 * For array B, we can split it by the same way:
	 * { B[0], B[1], ... , B[j - 1] }; { B[j], B[j + 1], ... , B[n - 1] }
	 * The left part has "j" elements, and right part has "n - j" elements.
	 * Put A's left part and B's left part into one set. (Let's name this set
	 * "LeftPart")
	 * Put A's right part and B's right part into one set. (Let's name this set
	 * "RightPart")
	 */
	public double findMedianSortedArraysMIT(int A[], int B[]) {
		int n = A.length;
		int m = B.length;
		// make sure len(A) <= len(B).
		if (n > m)
			return findMedianSortedArrays(B, A);

		// now, do binary search
		int k = (n + m - 1) / 2;
		int l = 0, r = Math.min(k, n); // r is n, NOT n-1, this is important!!
		while (l < r) {
			int midA = (l + r) / 2;
			int midB = k - midA;
			if (midB > k || A[midA] < B[midB])
				l = midA + 1;
			else
				r = midA;
		}
		// after binary search, we almost get the median because it must be
		// between these 4 numbers: A[l-1], A[l], B[k-l], and B[k-l+1]

		// if (n+m) is odd, the median is the larger one between A[l-1] and
		// B[k-l]. and there are some corner cases we need to take care of.
		// remember the k - l >= 0!!
		int a = Math.max(l > 0 ? A[l - 1] : Integer.MIN_VALUE, k - l >= 0 ? B[k - l] : Integer.MIN_VALUE);
		if (((n + m) & 1) == 1)
			return (double) a;

		// if (n+m) is even, the median can be calculated by
		// median = (max(A[l-1], B[k-l]) + min(A[l], B[k-l+1]) / 2.0
		// also, there are some corner cases to take care of.
		int b = Math.min(l < n ? A[l] : Integer.MAX_VALUE, k - l + 1 < m ? B[k - l + 1] : Integer.MAX_VALUE);
		return (a + b) / 2.0;
	}

	/**
	 * just sort
	 */
	public double findMedianSortedArrays(int A[], int B[]) {
		int m = A.length;
		int n = B.length;
		int[] array = new int[m + n];
		for (int i = 0; i < m + n; i++) {
			if (i < m)
				array[i] = A[i];
			else
				array[i] = B[i - m];
		}

		Arrays.sort(array);
		if (array.length % 2 == 0) {
			int index1 = (m + n - 1) / 2;
			int index2 = index1 + 1;
			return (double) ((double) array[index1] + (double) array[index2]) / 2;
		} else {
			return (double) array[(m + n - 1) / 2];
		}
	}
	
	
	/**
	 * Another Binary search method
	 */
	public double findMedianSortedArraysHard(int A[], int B[]) {
		int m = A.length;
		int n = B.length;

		if ((m + n) % 2 != 0) // odd number of total elements
			return (double) findKth(A, B, (m + n) / 2, 0, m - 1, 0, n - 1);
		else { // even number of total elements
			return ((double) findKth(A, B, (m + n) / 2, 0, m - 1, 0, n - 1) + (double) findKth(
					A, B, (m + n) / 2 - 1, 0, m - 1, 0, n - 1)) * 0.5;
		}
	}

	public static int findKth(int A[], int B[], int k, int ista_A, int iend_A,
			int ista_B, int iend_B) {

		int nA = iend_A - ista_A + 1;
		int nB = iend_B - ista_B + 1;

		// Special cases
		if (nA == 0)
			return B[ista_B + k];
		if (nB == 0)
			return A[ista_A + k];
		if (k == 0)
			return A[ista_A] < B[ista_B] ? A[ista_A] : B[ista_B];

		// Reduce search ranges in A and B
		int imid_A = nA * k / (nA + nB);
		int imid_B = k - imid_A - 1;

		// Add offset so that imid_A and imid_B index directly into A and B,
		// respectively
		imid_A += ista_A;
		imid_B += ista_B;

		if (A[imid_A] > B[imid_B]) {
			k -= imid_B - ista_B + 1;
			iend_A = imid_A;
			ista_B = imid_B + 1;
		} else {
			k -= imid_A - ista_A + 1;
			iend_B = imid_B;
			ista_A = imid_A + 1;
		}

		return findKth(A, B, k, ista_A, iend_A, ista_B, iend_B);

	}

}
