package interviews;

import java.util.Arrays;
import java.util.Map;
import java.util.HashMap;

public class Facebook_按照自定义字母顺序排序 {
	/**
	 * 输入是两个字符串（第一个字符串是自定义的字母顺序比如zafdbeg，第二个字符是任意输入的字符串）
	 * 
	 * 输出是按照第一个字符串的规则排好序的字符串。
	 */
	public String outputAsOrder(String order, String input) {
		Map<Character, Integer> map = new HashMap<>();
		int[] arr = new int[input.length()];
		StringBuffer output = new StringBuffer();
		// 给每个order中的char assign一个数字做顺序
		for (int i = 0; i < order.length() - 1; i++) {
			map.put(order.charAt(i), i);
		}
		// 对每个input中的char找到对应的数字
		for (int i = 0; i < input.length(); i++) {
			arr[i] = map.get(input.charAt(i));
		}
		// 对数字进行排列
		Arrays.sort(arr);
		// 按照数字找回原来的char
		for (int i = 0; i < arr.length; i++) {
			output.append(order.charAt(i));
		}
		return output.toString();
	}

}
