package interviews;

import java.util.HashSet;
import java.util.Set;

public class TwoSigma_FriendCycle {
	/**
	 * 1. friend circle.
给你一个string[] friends。反应每个人的对应关系。然后返回一个有几个friend circle。
例子：
	 输入： ynyy
           nyyn
           yyyn. 
           ynny
n表示不是朋友，y表示是朋友。
这个图表示1跟2 不是朋友，跟3.4是朋友。2跟1,4不是朋友,跟3是朋友。3跟1，2是朋友，跟4不是朋友；4跟1是朋友，跟2.3不是朋友。

其实这个string［］可以理解成一个char 的二维数组，表示各个人的关系。 左上到右下的对角线一定全是y。 然后以这条线为轴，的两个元素相同。
即2是3的朋友，3 也是2的朋友。

然后怎么算是一个circle， 朋友的朋友也算一个circle， 假如1 和2 是朋友， 2 和4是朋友， 4和7是朋友，那么1，2，3，7是一个circle。
单独一个人也可以是一个朋友。

例子返回1， 因为全部人都是一个circle 里的。
	 */
	public static int numFriendCycle(String[] friends) {
		 if (friends == null || friends.length == 0) {
			 return 0;
	     }
		 int n = friends.length;
		 int[] circle = new int[n];
	     for (int i = 0; i < n; i++) {
	    	 circle[i] = i; // 有几行就是几个circle
	     }
	        
		for (int i = 0; i < n; i++) {
			int minIdInCycle = circle[i];
			for (int j = i + 1; j < n; j++) {
				if (friends[i].charAt(j) == 'Y' || friends[j].charAt(i) == 'Y') {
					if (circle[j] < minIdInCycle) {
						minIdInCycle = circle[j];
					}
				}
			}
			for (int j = i; j < n; j++) {
				if (friends[i].charAt(j) == 'Y' || friends[j].charAt(i) == 'Y') {
					circle[j] = minIdInCycle;
				}
			}
		}

		Set<Integer> circleId = new HashSet<Integer>();
		for (int cid : circle) {
			circleId.add(cid);
		}
		return circleId.size();
	}
	public static void main(String[] args) {
		String[] friends = {"YNYY", "NYYN", "YYYN", "YNNY"};
		System.out.println(numFriendCycle(friends));
	}
}
