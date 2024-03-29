package interviews;

public class First_Bad_Version {
	/**
	 * You are a product manager and currently leading a team to develop a new
	 * product. Unfortunately, the latest version of your product fails the
	 * quality check. Since each version is developed based on the previous
	 * version, all the versions after a bad version are also bad.
	 * 
	 * Suppose you have n versions [1, 2, ..., n] and you want to find out the
	 * first bad one, which causes all the following ones to be bad.
	 * 
	 * You are given an API bool isBadVersion(version) which will return whether
	 * version is bad. Implement a function to find the first bad version. You
	 * should minimize the number of calls to the API.
	 */
	// logn => binary search
	public int firstBadVersion(int n) {
        if (n < 0) return -1;
        int left = 0, right = n;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (isBadVersion(mid)) {
                if (mid == 0 || !isBadVersion(mid - 1)) return mid;
                else {
                    right = mid - 1;
                }
            } else {
                left = mid + 1;
            }
        }
        return -1;
    }
	
	boolean isBadVersion(int version) {
		return false;
	}
}
