package interviews;

import java.util.ArrayList;
import java.util.List;

public class Missing_Range {
	/**
	 * Given a sorted integer array where the range of elements are [0, 99]
	 * inclusive, return its missing ranges. 
	 * For example, given [0, 1, 3, 50, 75], 
	 * return [“2”, “4->49”, “51->74”, “76->99”]
	 * 
	 * [分析] 一遍线性扫描即可。 [注意事项]
	 * 1）针对一些特殊情况，询问面试官，比如说如果array是个空的，或者array包含区间内的所有元素，相应的返回值是什么
	 * 2）可以给出一些有意思的test case，另外就是不需要限制给出的范围是[0, 99]，用start和end表示就行。
	 * 在面试的时候可以先提一下，写出[0, 99]的代码，然后在稍作修改，变成start和end的版本。
	 */
	public List<String> findMissingRanges(int[] vals, int start, int end) {
		List<String> ranges = new ArrayList<String>();
		int prev = start - 1;
		for (int i = 0; i <= vals.length; i++) {
			// index of the end
			int cur = (i == vals.length) ? end + 1 : vals[i];
			if (cur - prev >= 2) {
				ranges.add(getRange(prev + 1, cur - 1));
			}
			prev = cur;
		}
		return ranges;
	}
	// get a string for the range, inclusive
	private String getRange(int from, int to) {
        return (from==to) ? String.valueOf(from) : from + "->" + to;
    }
}
