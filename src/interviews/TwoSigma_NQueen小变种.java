package interviews;

public class TwoSigma_NQueen小变种 {
	/**
	 * n－queen 小变种, 其实就是简单的给你一个数组，代表一个放好了queen的棋盘。 然后要返回受到威胁数最多的queen受到的威胁数。
	 * 
	 * 这里有两点要注意的 ，这题只考虑斜着的威胁，不用考虑横竖的...第二queen不能隔着一个或几个威胁。
	 * Example：给你数组［1，2，3，4，5］,就代表1行1列有个queen， 2行2列....5行5列都有一个queen。
	 * 这时候最大的威胁数是2. 因为隔着不能威胁，所以1只能威胁2， 不能威胁3. 但是2受到1，3的威胁。
	 */
	// 最大歇着威胁的可能数为4
	public static int getMaxThreatNum(int[] num) {
		for (int i = 0; i < num.length; i++) {
			if (i == 0) {
				
			}
		}
	}
	public static void main(String[] args) {
		int[] num = {1, 4, 3, 4, 1};
		System.out.println(getMaxThreatNum(num));
	}
}
