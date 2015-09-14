package interviews;

import java.util.ArrayList;

public class Google_Binary_Watch {
	/**
	 * 上边是小时，下边是分钟，最左边最significant，最右边为1。给你数字n，return所有可能的时间，以string为表达形式
	 */
	public ArrayList<String> getAllTime(int n) {
		ArrayList<String> res = new ArrayList<String>();
		if (n >= 9)
			return res;
		for (int i = 0; i <= n && i <= 4; i++) {
			if (n - i > 6)
				continue;
			// n-i: number of one for minute, 6-n+i: number of zero for minute
			ArrayList<Integer> mins = new ArrayList<Integer>();
			getTime(n - i, 6 - n + i, 0, 60, mins);
			// i: number of one for hour, 4-i: number of zero for hour
			ArrayList<Integer> hours = new ArrayList<Integer>();
			getTime(i, 4 - i, 0, 12, hours);
			for (int hour : hours) {
				for (int min : mins) {
					String tmp = ((hour < 10 ? "0" : "") + hour
							+ (min < 10 ? "0" : "") + min);
					res.add(tmp);
					System.out.println(tmp);
				}
			}
		}
		return res;
	}

	void getTime(int oneNB, int zeroNB, int currTime, int maxVal,
			ArrayList<Integer> times) {
		if (zeroNB == 0 && oneNB == 0 && currTime <= maxVal)
			times.add(currTime);
		if (oneNB > 0) {
			currTime = currTime << 1;
			currTime++;
			getTime(oneNB - 1, zeroNB, currTime, maxVal, times);
			currTime = currTime >> 1;
		}
		if (zeroNB > 0) {
			currTime = currTime << 1;
			getTime(oneNB, zeroNB - 1, currTime, maxVal, times);
			currTime = currTime >> 1;
		}
	}
}
