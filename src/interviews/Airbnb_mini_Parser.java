package interviews;

import java.util.ArrayList;
import java.util.List;

public class Airbnb_mini_Parser {
	/**
	 * 实现一个mini parser, 输入是以下格式的string:"324" or "[123,456,[788,799,833],[[]],10,[]]" 
	 * 要求输出:324 or [123,456,[788,799,833],[[]],10,[]] 
	 * 
	 * 也就是将字符串转换成对应的格式的数据. 输入一个数组的字符串,
	 * 要返回一个数组, 里面每一个元素是要么一个整数, 要么是一个数组，
	 * 注意数组可以多层嵌套，output要么是integer, 要么是array
	 */
	public static EntityInArray miniParser(String input) {
		int len = input.length();
		if (len > 0) {
			if (input.charAt(0) == '[') {
				EntityInArray entity = new EntityInArray(false);
				String elem = input.substring(1, len - 1);
				List<String> elements = split(elem);
				for (String s: elements) {
					EntityInArray curElem = miniParser(s);
					entity.list.add(curElem);
				}
				return entity;
			} else {
				int num = Integer.parseInt(input);
				EntityInArray entity = new EntityInArray(true);
				entity.num = num;
				return entity;
			}
		}
		return new EntityInArray(false);
	}
	
	public static List<String> split(String input) {
		List<String> res = new ArrayList<String>();
		int start = 0, end = 0;
		while (end < input.length()) {
			if (input.charAt(end) == '[') {
				int open = 1;
				int close = 0;
				while (end < input.length() && open != close) {
					end++;
					if (input.charAt(end) == '[') open++;
					else if (input.charAt(end) == ']') close++;
				}
				String cur = input.substring(start, end + 1);
				res.add(cur);
				start = end + 2;
				end = start;
			} else if (input.charAt(end) == ',') {
				if (start != end) {
					res.add(input.substring(start, end));
				}
				start = end + 1;
				end = start;
			} else end++;
		}
		return res;
	}
	
	public static void main(String[] args) {
		String str1 = "324";
		System.out.println(miniParser(str1).toString());
		String str2 = "[123,456,[788,799,833],[[]],10,[]]";
		System.out.println(miniParser(str2).toString());
	}
}
class EntityInArray {
	int num;
	List<EntityInArray> list;
	boolean isNum;
	public EntityInArray(boolean isNum) {
		this.isNum = isNum;
		if (!isNum) {
			list = new ArrayList<EntityInArray>();
		}
	}
	public String toString() {
		if (isNum) return String.valueOf(num);
		else {
			String res = "[";
			for (EntityInArray elem : list) {
				res += (elem.toString() + ",");
			}
			res = res.substring(0, res.length() - 1);
			res += "]";
			return res;
		}
	}
}