package interviews;

import java.util.Arrays;

public class Facebook_Find_Alibaba {
	/**
	 * Ali baba是一个怪兽，他可能在[0, 1, ..., numCaves - 1]出现，他每隔一天就要换一个地方，但每次只能左移或者右移一格
	 * 
	 * 然后给你一个strategy数组，数组里面是猜测每天ali baba的位置，如果这里面有一个猜中了，这个strategy就是正确的
	 * 
	 * 让你写一个判断函数 alibaba(int numCaves, int[] strategy)来判断这个strategy是不是稳赢的策略
	 * 
	 * 比如：如果只有三个房间那么如果打开房间的sequence是{1，1} 那么一定会找到小偷。因为如果小偷在中间那么第一天就会被找到，
	 * 如果小偷在两边那么第二天一定回来到中间也会被找到。房间数为n，sequence长度为k
	 * 
	 * 跟着我开始brute force假设小偷在某个房间然后dfs所有路径，大概是O（n*n^k）。考官说好，如果考虑cut branch
	 * 呢？跟着我就说可以拿一个n*k的matrix跟着根据sequence来cut branch，reduce到O（n*n*k）。
	 * 
	 * 他说有没有可能同时从所有房间开始呢？我说可以跟着直接在那个n*k matrix上做一个类似dp的东西。
	 * 跟着reduce 到 O（n*k）。他说有没有可能把space reduce呢？我说可以我只要O（n）的space.
	 * 跟着他就让我再写一个叫nextRow的function来实现O(n) space。 
	 * 
	 * CanSurvive[k][n] : 第k天，第n个房间小偷是否可以survive
	 * 
	 * CanSurvive[i][j] = (CanSurvive[i-1][j-1] or CanSurvive[i-1][j+1]) && sequence[i] != j
	 */
	public static boolean canCatchTheft(int n, int strategy[]) {
        int k = strategy.length;
        // nextDay[i] means theft can survive in spot i or not on this day
        boolean nextDay[] = new boolean[n]; 
        boolean canSurvive, pre, a, b;
        // init the first day
        Arrays.fill(nextDay, true); 
        nextDay[strategy[0]] = false;
        for (int i = 1; i < k; ++i) { 
            canSurvive = false; 
            pre = false;
            for (int j = 0; j < n; ++j) { 
                a = j == 0 ? false : pre; // nextDay[i - 1]已被改为当前一轮值，所以要存下来
                b = j == n - 1 ? false : nextDay[j + 1];
                pre = nextDay[j]; // store current day for the next round
                // nextDay[i][j] = (next[i - 1][j - 1] || next[i - 1][j + 1]) && strategy[i] != j
                nextDay[j] = ((a || b) && strategy[i] != j);
                if(nextDay[j] == true) canSurvive = true; 
            }
            if (!canSurvive) return true;
        }
        return false;
    }
	
	
	public static void main(String[] args) {
		int numCaves = 3;
		int[] s = {1, 1};
		System.out.println(canCatchTheft(numCaves, s));
	}
}
