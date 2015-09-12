package interviews;

import java.util.ArrayList;
import java.util.List;

public class Summary_Ranges {
	/**
	 * Given a sorted integer array without duplicates, return the summary of
	 * its ranges.
	 * 
	 * For example, given [0,1,2,4,5,7], return ["0->2","4->5","7"].
	 */
	public List<String> summaryRanges(int[] nums) {
        if(nums.length == 0) return new ArrayList<String>();
        List<String> res = new ArrayList<String>();
        int start = nums[0];
        int end = nums[0];
        for(int i = 1; i < nums.length; i++) {
            int cur = nums[i];
            if(cur == nums[i - 1] + 1) {
                end = nums[i];
                continue;
            } else {
                res.add(getStr(start, end));
                start = nums[i];
                end = nums[i];
            }
        }
        res.add(getStr(start, end));
        return res;
    }
    
    public String getStr(int start, int end) {
        if(start == end) {
            return Integer.toString(start);
        }
        String s1 = Integer.toString(start);
        String s2 = Integer.toString(end);
        String res = s1 + "->" + s2;
        return res;
    }
}
