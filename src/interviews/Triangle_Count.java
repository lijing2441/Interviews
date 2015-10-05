package interviews;

import java.util.Arrays;

public class Triangle_Count {
	/**
	 * Given array S = [3,4,6,7], return 3. They are:

	[3,4,6]
	[3,6,7]
	[4,6,7]
	
	Given array S = [4,4,4,4], return 4. They are:

	[4(1),4(2),4(3)]
	[4(1),4(2),4(4)]
	[4(1),4(3),4(4)]
	[4(2),4(3),4(4)]
	 * @param S
	 * @return
	 */
	public int triangleCount(int[] S) {
        if (S == null) return 0;
        int len = S.length;
        if (len < 3) return 0;
        Arrays.sort(S);
        int res = 0;
        for (int end = len - 1; end > 0; end--) {
            int start = 0, mid = end - 1;
            while (start < mid) {
                int first = S[start];
                int second = S[mid];
                int third = S[end];
                if (first + second <= third) start++;
                else {
                    res += (mid - start);
                    mid--;
                }
            }
        }
        return res;
    }
}
