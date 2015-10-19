package interviews;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

public class Facebook_稀疏vector相乘 {
	/**
	 * 假设A=[0,0,1,2,0,0,4]那么化简后就是[(2,1), (3,2), (6,4)], (2,1) 2是在A中的index, 1是A[2] 
	 * 
	 * 假设B化简后是[(3,3),(5,1)] 要找到A[1] -> (3,2) B[0] -> (3,3) 
	 * 
	 * 然后 sum += 2*3这样,
	 * 
	 * 所以用two pointer 
	 * 
	 * follow up就是通过binary search找到(3,2)然后再找(5,1)的时候我就只search A[2:]这部分
	 */
	public int vectorMultiply(Map<Integer, Integer> v1, Map<Integer, Integer> v2) {
		int res = 0;
		for (int key : v1.keySet()) {
			if (v2.containsKey(key)) {
				res += v1.get(key) * v2.get(key);
			}
		}
		return res;
	}
	
	// 预处理每个vector => Map<Index, number>
	public Map<Integer, Integer> preprocess(int[] arr) {
		//List<int[]> res = new ArrayList<int[]>();
		Map<Integer, Integer> map = new HashMap<Integer, Integer>();
		for (int i = 0; i < arr.length; i++) {
			if (arr[i] != 0) {
				map.put(i, arr[i]);
			}
		}
		return map;
	}
	
	// follow up
	public List<int[]> preprocessToList(int[] arr) {
		List<int[]> res = new ArrayList<int[]>();
		//Map<Integer, Integer> map = new HashMap<Integer, Integer>();
		for (int i = 0; i < arr.length; i++) {
			if (arr[i] != 0) {
				int[] cur = {i, arr[i]};
				res.add(cur);
			}
		}
		return res;
	}
	public int vectorTimes(List<int[]> v1, List<int[]> v2) {
		// 规定v1 size小于 v2
		if (v1.size() > v2.size()) {
			return vectorTimes(v2, v1);
		}
		int sum = 0;
		int left = 0, right = v2.size() - 1;
		for (int[] pair1 : v1) {
			int index = pair1[0];
			// find the pos in v2 using binary search
			while (left < right) {
				int mid = (left + right) / 2;
				if (v2.get(mid)[0] == index) {
					sum += pair1[1] * v2.get(mid)[1];
					left = mid + 1;
					break;
				} else if (v2.get(mid)[0] < index){
					left = mid + 1;
				} else {
					right = mid - 1;
				}
			}
		}
		return sum;
	}
}
