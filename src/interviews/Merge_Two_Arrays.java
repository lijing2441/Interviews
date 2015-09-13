package interviews;

import java.util.ArrayList;
import java.util.Collections;

public class Merge_Two_Arrays {
	// O(n+m) time and space depends
	public static void merge(int A[], int m, int B[], int n) {
		int pointer_a = m - 1;
		int pointer_b = n - 1;
		int p = m + n - 1;

		while (pointer_a >= 0 && pointer_b >= 0) {
			if (A[pointer_a] > B[pointer_b]) {
				A[p--] = A[pointer_a--];
			} else {
				A[p--] = B[pointer_b--];
			}
		}
		if (pointer_b >= 0) {
			for (int i = 0; i <= pointer_b; i++) {
				A[i] = B[i];
			}
		}
	}
	
	// in the case that one of the list is very large
	public ArrayList<Integer> mergeSortedArray(ArrayList<Integer> A, ArrayList<Integer> B) {
        if (A == null || B == null) return A == null ? B : A;
        // always make the first one bigger than the second one
        if (A.size() < B.size()) {
            return mergeSortedArray(B, A);
        }
        for (int i : B) {
            A.add(i);
        }
        Collections.sort(A);
        return A;
    }
	
	// driver function
	public static void main(String[] args) {
		int[] a = { 0, 1, 2, 3, 0, 0, 0, 0 };
		int[] b = { 4, 5, 6, 7 };

		merge(a, 4, b, 4);
		for (int i = 0; i < 8; i++) {
			System.out.print(a[i] + " ");
		}
	}
}
