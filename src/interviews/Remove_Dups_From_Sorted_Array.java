package interviews;

public class Remove_Dups_From_Sorted_Array {
	public int removeDuplicates(int[] A) {
		if (A == null || A.length < 2)
			return A == null ? 0 : A.length;
		int cur = 0;
		for (int i = 1; i < A.length; i++) {
			if (A[cur] != A[i])
				A[++cur] = A[i];
		}
		return cur + 1;
	}
	// Problem II: if duplicates are allowed at most twice
	public int removeDuplicatesII(int[] A) {
        if(A.length <= 2) return A.length;
        //int curPos = 2;
        int len = 2;
        for(int i = 2; i < A.length; i++){
            if(A[i] != A[len - 2]){
                A[len++] = A[i];
            }
        }
        return len;
    }
}
