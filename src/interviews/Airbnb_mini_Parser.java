package interviews;

public class Airbnb_mini_Parser {
	/**
	 * 实现一个mini parser, 输入是以下格式的string:"324" or"[123,456,[788,799,833],[[]],10,[]]" 
	 * 要求输出:324 or [123,456,[788,799,833],[[]],10,[]] 
	 * 
	 * 也就是将字符串转换成对应的格式的数据. 输入一个数组的字符串,
	 * 要返回一个数组, 里面每一个元素是要么一个整数, 要么是一个数组，
	 * 注意数组可以多层嵌套，output要么是integer, 要么是array
	 */
	public EntityInArray miniParser(String input) {
		int len = input.length();
		for (int i = 0; i < len; i++) {
			
		}
	}

}
class EntityInArray {
	int num;
	EntityInArray[] array;
	boolean isNum;
}