package interviews;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

public class Pinterest_json_get_Diff {
	/**
	 * Get the different part between two maps, the map value can be either string or int[] array
	 * 
	 * 如果Value是String，若key相同，value不同就把不同的放在结果中，如果两个
	 * 
	 * 如果Value是int[]，若key相同，就把arr中不同的元素放在结果中
	 * 
	 * 结果以Map形式表现
	 * 
	 * Q for interviewer：对于int[], 我们可以求diff，但是如果是String，在key相同的情况下，是否应该两个都包含在结果里？
	 * 
	 * 本题中只保留json1中的那个。
	 * 
	 * 如果是都保留，则Value的type可以用0，1，2表示，0->String, 1->int[], 2->String[]
	 */
	public static Map<String, Value> findDiff(Map<String, Value> json1, Map<String, Value> json2) {
		Map<String, Value> json3 = new HashMap<String, Value>();

		for (Map.Entry<String, Value> entry : json1.entrySet()) {
			String key = (String) entry.getKey();
			if (json2.containsKey(key)) {
				Value v = (Value) entry.getValue();
				if (v.isString) { // string case, 不同就只保留json1中的词
					if (!json2.get(key).strValue.equals(v.strValue)) {
						json3.put(key, v);
					}
				} else { // array case
					int[] res = getDiffArr(v.arrValue, json2.get(key).arrValue);
					if (res.length > 0) {
						json3.put(key, new Value(false, res));
					}
				}
			} else {
				json3.put(key, (Value) entry.getValue());
			}
		}
		// put the other part which is different in json2
		for (Map.Entry<String, Value> entry : json2.entrySet()) {
			String key = (String) entry.getKey();
			if (!json1.containsKey(key)) {
				json3.put(key, (Value) entry.getValue());
			}
		}
		return json3;
	}

	public static int[] getDiffArr(int[] arr1, int[] arr2) {
		List<Integer> res = new ArrayList<Integer>();
		Map<Integer, Integer> map1 = new HashMap<Integer, Integer>();
		// 正数代表arr1
		for (int i : arr1) {
			if (map1.containsKey(i)) {
				map1.put(i, map1.get(i) + 1);
			} else {
				map1.put(i, 1);
			}
		}
		// 负数代表arr2
		for (int i : arr2) {
			if (map1.containsKey(i)) {
				if (map1.get(i) > 0) {
					map1.put(i, map1.get(i) - 1);
				} else {
					res.add(i);
				}
			} else {
				res.add(i);
			}
		}

		for (Map.Entry<Integer, Integer> entry : map1.entrySet()) {
			int key = (Integer) entry.getKey();
			int cnt = (Integer) entry.getValue();
			while (cnt > 0) {
				res.add(key);
				cnt--;
			}
		}
		int[] toReturn = new int[res.size()];
		for(int i = 0; i < res.size(); i++) {
			toReturn[i] = res.get(i);
		}
		return toReturn;
	}
	public static void main(String[] args) {
		/*
		 * json1 = {
    	 * "tree", [1,2],
    	 * "location", "post"
		   }

		   json2 = {
    		"tree", [1],
    		"location", "post",
    		"foo", [1,2]
		   }
		 */
		Map<String, Value> json1 = new HashMap<String, Value>();
		Map<String, Value> json2 = new HashMap<String, Value>();
		Value v1 = new Value(false, new int[] {1, 2});
		Value v2 = new Value(true, "post");
		Value v4 = new Value(true, "test");
		Value v3 = new Value(false, new int[] {1});
		json1.put("tree", v1);
		json1.put("location", v2);
		json2.put("tree", v3);
		json2.put("location", v4);
		json2.put("foo", v1);
		Map<String, Value> json3 = findDiff(json1, json2);
		for(Map.Entry<String, Value> entry: json3.entrySet()) {
			String key = entry.getKey();
			Value val = entry.getValue();
			if (val.isString) {
				System.out.println(key + " " + val.strValue);
			} else {
				int[] arr = val.arrValue;
				System.out.print(key + " ");
				for (int i = 0; i < arr.length; i++) {
					System.out.print(arr[i] + " ");
				}
				System.out.println();
			}
		}
	}
}
class Value {
	String strValue;
	int[] arrValue;
	boolean isString; // true-> string, false -> array

	public Value(boolean type, String strValue) {
		this.isString = type;
		this.strValue = strValue;
	}

	public Value(boolean type, int[] arrValue) {
		this.isString = type;
		this.arrValue = arrValue;
	}
}