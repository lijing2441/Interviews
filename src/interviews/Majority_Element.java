package interviews;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Given an array of size n, find the majority element. The majority element is
 * the element that appears more than ⌊ n/2 ⌋ times.
 * 
 * You may assume that the array is non-empty and the majority element always
 * exist in the array.
 * 
 * @logic 1 Hash table  Runtime: O(n), Space: O(n) 
 * 
 * 		  2 Sorting  Runtime: O(nlogn): the n/2th element is always the majority.
 * 
 *        3 Divide and conquer  Runtime: O(n log n) 
 *        		Divide the array into two halves, then find the majority element A and B.
 *        		i) If A == B, then it automatically becomes the global majority element. 
 *        		ii) If not, then both A and B are the candidates for the majority element, check between them. 
 *        
 *        4 Runtime: O(n) — Moore voting algorithm: We maintain a current candidate and a counter 
 *        initialized to 0. As we iterate the array, we look at the current element x: 
 *        		i) If the counter == 0, we set the current candidate to x and the counter to 1. 
 *        		ii) If the counter != 0, we increment or decrement counter based on whether x is the current
 *        candidate. After one pass, the current candidate is the majority element.
 *        
 *        5 Runtime: O(n) — Bit manipulation
 *        	We would need 32 iterations, each calculating the number of 1's for the ith bit of all n numbers. 
 *        Since a majority must exist, therefore, either count of 1's > count of 0's or vice versa (but can 
 *        never be equal). The majority number’s ith bit must be the one bit that has the greater count.
 *        
 */
public class Majority_Element {
	// Hash method
	public int majorityElement(int[] num) {
        if(num == null || num.length == 0) return -1;
        if(num.length <= 2) return num[0];
        
        HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
        for(int i = 0; i < num.length; i++){
            if(map.containsKey(num[i])){
                map.put(num[i], map.get(num[i]) + 1);
            }else{
                map.put(num[i], 1);
            }
            if(map.get(num[i]) > num.length/2) return num[i];
        }
        return -1;
    }
	//divide and conquer
	public int majorityElement_DC(int[] num) {
        if(num == null || num.length == 0) return -1;
        if(num.length <= 2) return num[0];
        int[] res = new int[1];
        helper(num, 0, num.length - 1, res);
        return res[0];
    }
    public void helper(int[] num, int left, int right, int[] res){
        if(left >= right){
            res[0] = num[right];
            return;
        }
        int mid = (left + right) / 2;
        int count = 0;
        //check whether num[mid] is majority element
        for(int i = 0; i < num.length; i++){
            if(num[i] == num[mid]) count++;
        }
        if(count > num.length/2){
            res[0] = num[mid];
            return;
        }
        helper(num, left, mid - 1, res);
        helper(num, mid + 1, right, res);
    }
    
    //Moore Voting
    public int majorityElement_Voting(int[] num) {
        int majority = -1;
        int count = 0;
        int n = num.length;
        for(int i = 0; i < n; i++){
            if(count == 0){
                majority = num[i];
                count++;
            }else if(count > 0){
                if(majority == num[i]) count++;
                else count--;
            } 
        }
        return majority;
    }
    
    /**
     * Problem II:
     * 
     * Follow up: Moore for n / 3
     */
    public List<Integer> majorityElementII(int[] nums) {
        List<Integer> res = new ArrayList<Integer>();
        if(nums == null || nums.length == 0) return res;
        int majorElement1 = 0, majorElement2 = 0, count1 = 0, count2 = 0;
        for(int i : nums) {
            if (i == majorElement1) count1++;
            else if (i == majorElement2) count2++;
            else if (count1 == 0) {
                majorElement1 = i;
                count1 = 1;
            } else if (count2 == 0) {
                majorElement2 = i;
                count2 = 1;
            } else {
                count1--;
                count2--;
            }
        }
        int count11 = 0;
        int count12 = 0;
        for(int i : nums) {
            if (i == majorElement1) count11++;
            else if (i == majorElement2) count12++;
        }
        if(count11 > nums.length / 3) res.add(majorElement1);
        if(count12 > nums.length / 3) res.add(majorElement2);
        return res;
    }
    
	public static void main(String[] args) {
		int a[] = { 4, 2, 4, 4, 5, 5, 4, 1, 7 };
		int major_index1 = 0, major_index2 = 1, count1 = 2, count2 = 2;
		for (int i = 2; i < a.length; i++) {
			if (a[major_index1] == a[i])
				count1 = count1 + 2;
			else if (a[major_index2] == a[i])
				count2 = count2 + 2;
			else {
				count1--;
				count2--;
			}
			if (count1 == 0) {
				major_index1 = i;
				for (; i < a.length; i++) {
					if (a[major_index1] == a[major_index2])
						i++;
					else
						break;
				}
				count1 = 2;
			}
			if (count2 == 0) {
				major_index2 = i;
				for (; i < a.length; i++) {
					if (a[major_index1] == a[major_index2])
						i++;
					else
						break;
				}
				count2 = 2;
			}
		}
		System.out.println(a[major_index1]);
		System.out.println(a[major_index2]);
	}
}
