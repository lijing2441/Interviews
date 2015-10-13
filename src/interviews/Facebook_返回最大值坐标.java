package interviews;

import java.util.ArrayList;
import java.util.List;

public class Facebook_返回最大值坐标 {
	/**
	 * 给一个int array，随机返回0到当前位置中最大值坐标，ex:
	 * [1,2,3,3,3,3,1,2]，在最后一个2的时候有4个3均为最大值，1/4概率返回其中一个3的index
	 * 
	 * Reservoir sampling: 遇到一样的用1/count的概率替换
	 */
	public int findMax(int[] arr) {
		int len = arr.length;
		int res = -1, max = Integer.MIN_VALUE;
		List<Integer> indice = new ArrayList<Integer>();
		int count = 0;
		for (int i = 0; i < len; i++) {
			if (arr[i] == max) {
				count++;
				int index = (int)(Math.random() * count);
				// if the random index is current one, 替换原来的res
				if (index == count - 1) res = i;
			} else if (arr[i] > max) {
				max = arr[i];
				res = i;
				count = 1;
				indice.clear();
				indice.add(i);
			}
		}
		return res;
	}

}
