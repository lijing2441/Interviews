	package interviews;

import java.util.Arrays;
import java.util.Map;
import java.util.HashMap;

public class Google_找最大等差数列 {
	/**
	 * 先排序，O(NlogN)
	 * 
	 * 从后往前，对于a[i],令 j=i+1~n-1
	 * 
	 * map[pair(a[i],a[j]-a[i])] = max(map[pair(a[j],a[j]-a[i])] , 1) + 1 
	 * 
	 * 其中pair(a,b)表示以a为首项，b为等差的等差数列的最长的长度
	 * 
	 * 从a[n-1]一直算到a[0],所有的pair(a[k],b)都算出来了，现在只要找出其中最大的即可。
	 */
	public int maximumSeries(int[] arr) {
		Arrays.sort(arr);
		Map<int[], Integer> map = new HashMap<int[], Integer>();
		int len = arr.length;
		for (int i = len - 1; i >= 0; --i) {
			for (int j = i + 1; j < len; j++) {
				int diff = arr[j] - arr[i];
				int[] previous = {arr[j], diff};
				int num = 1;
				if (map.containsKey(previous)) {
					num += map.get(previous);
				}
				int[] cur = {arr[i], diff};
				map.put(cur, num);
			}
		}
		int max = 1;
		for (int value : map.values()) {
			max = Math.max(value, max);
		}
		return max;
	}
	
}
