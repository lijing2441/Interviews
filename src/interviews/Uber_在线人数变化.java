package interviews;

import java.util.Arrays;
import java.util.List;

public class Uber_在线人数变化 {
	/**
	 * 一道题，给定 <uid, login time, logout time> 格式的一系列pair，求在线人数随时间的变动。
	 * 
	 * 求一个论坛的在线人数，假设有一个论坛，其注册ID有两亿个，每个ID从登陆到退出会向一个日志文件中记下
	 * 登陆时间和退出时间，要求写一个算法统计一天中论坛的用户在线分布，取样粒度为秒。
	 * 
	 * 分析：
	 * 
	 * 先问login和logout时间是不是以秒为单位。再问这个function会不会被反复用？
	 * 
	 * 1）只统计一次。那么直接线性的扫一遍即可。
	 * 
	 * 2）统计多次，但是统计的Ti已经给出（离线问题），我们可以将时间点Ti排序，然后维护一个用户在线队列Q，
	 * 每次将Ti的指针和区间指针轮流移动，过程中Q的元素个数即是在线人数，O(n), O(n)
	 * 
	 * 3）统计多次，在线统计（一开始不知道Ti，即要统计时间点）。
	 * 我们观察到，时间点T时刻的在线人数，等于Si<=T的数目，减去Ei<=T的数目。换句话说，就是在T时间之前登陆的人数，
	 * 减去在T时间之前登出的人数，就得到了当时在线的人数。有了这个结论这道题目就很好解决了，Si，Ei皆已排好序，
	 * 
	 * 在原来的序列中二分查找即可，每次时间复杂度为logn。
	 * 
	 * 上述的分析已经比较完整，但细想还是有更深入的点，比如说这个log超级大，大到内存放不下等等，
	 * 这个时候其实更需要分而治之的思想，把log文件分块等等，甚至可以用B+树来维护动态的查找操作。
	 * 
	 * 如果能回答二分查找，你已经是一个优秀的面试者。如果能将log超级大的情况，还有log动态增长的情况一起解决了，Offer
	 */
	
	/**
	 * 一天总共有3600*24=86400秒。
	 * 定义一个int delta[86400]，每个整数对应这一秒的人数变化值，可能为正也可能为负。开始时将数组元素都初始化为0。
	 * 然后依次读入每个用户的登录时间和退出时间，将与登录时间对应的整数值加1，将与退出时间对应的整数值减1。
	 * 这样处理一遍后数组中存储了每秒中的人数变化情况。
	 * 定义另外一个长度为86400的 online_num[86400]，每个整数对应这一秒的论坛在线人数。
	 * 假设一天开始时论坛在线人数为0，则第1秒的人数online_num[0]=delta[0]。
	 * 							  第n+1秒的人数online_num[n]=online_num[n-1]+delta[n]。
	 * 这样我们就获得了一天中任意时间的在线人数。 
	 */
	// 有没有可能相同同户名，同时在线？如果可能，怎么算在线人数？算两次还是算一次？如果需要算unique，则需要set来check
	// 更进一步用interval tree, check "Interval Checking" "Segment Tree"
	public int[] getOnlinePersonNum(List<UserStat> log) {
		int[] delta = new int[86400];
		for (UserStat record: log) {
			delta[record.loginTime]++;
			delta[record.logoutTime]--;
		}
		int[] online_num = new int[86400];
		// assume 0 online user in the beginning of the day
		online_num[0] = delta[0];
		for (int i = 1; i < 86400; i++) {
			online_num[i] = online_num[i - 1] + delta[i];
		}
		return online_num;
	}
	// 某特定时间点的在线人数
	public int getOnlineNumForT(List<UserStat> log, int T) {
		int size = log.size();
		int[] starts = new int[size];
		int[] ends = new int[size];
		int i = 0;
		for (UserStat record: log) {
			starts[i] = record.loginTime;
			ends[i] = record.logoutTime;
			i++;
		}
		Arrays.sort(starts);
		Arrays.sort(ends);
		int onlineNum = 0;
		for (int j = 0; j < size; j++) {
			if (starts[j] <= T) onlineNum++;
			else break;
		}
		for (int j = 0; j < size; j++) {
			if (ends[j] <= T) onlineNum--;
		}
		return onlineNum;
	}
}

class UserStat {
	int uid;
	int loginTime;
	int logoutTime;
}