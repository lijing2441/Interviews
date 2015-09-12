package interviews;

public class Minimum_in_Rotated_Sorted_Array {
	/**
	 * Suppose a sorted array is rotated at some pivot unknown to you
	 * beforehand.
	 * 
	 * (i.e., 0 1 2 4 5 6 7 might become 4 5 6 7 0 1 2).
	 * 
	 * Find the minimum element.
	 * 
	 * You may assume no duplicate exists in the array.
	 */
	public int findMin(int[] num) {
        if(num.length == 0 || num == null) return -1;
        //if(num.length < 2) return num
        int l = 0;
        int r = num.length - 1;
        if(num[l] < num[r]) return num[0];
        
        while(l < r - 1){
            int mid = (l + r) / 2;
            if(num[l] < num[mid]){
                l = mid;
            }else{
                r = mid;
            }
        }
        return Math.min(num[l], num[r]);
    }
	
	/**
	 * Problem II: 
	 * Duplicates allowed
	 */
	public int findMinWithDup(int[] num) {
        if(num == null || num.length == 0) return -1;
        if(num.length == 1) return num[0];
        int l = 0;
        int r = num.length - 1;
        
        while(l < r - 1){
            if(num[l] < num[r]) return num[l];
            int mid = (l + r) / 2;
            if(num[l] < num[mid]){
                l = mid; 
            }else if(num[l] > num[mid]){
                r = mid;
            }else{
                if(num[l] == num[r]){
                    l++;
                    r--;
                }else{
                    l = mid + 1;
                }
            }
        }
        if(num[l] <= num[r]){
            return num[l];
        }else{
            return num[r];
        }
    }
}
