package interviews;

public class 加1或乘2找到n的最少步数 {
	/**
	 * 给你一个正整数n, 从1开始做运算,运算有两种: +1 和 x2. 问怎么得到从1到n的运算最少步数
	 */
	public int getMinSteps(int n) {
        int[] res = new int[n + 1];
        res[1] = 0;
        res[2] = 1;

        for (int i = 3; i <= n; i++) {
            if (i % 2 == 0) {
                res[i] =  res[i / 2] + 1;
            } else {
                res[i] = res[i - 1] + 1;
            }
        }
        return res[n];
    }
}
