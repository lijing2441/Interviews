package interviews;

public class Pinterest_rand7 {
	/**
	 * 有产⽣生5的随机数,怎么⽣生成7的? 
	 * 12345
	 * 67123
	 * 45671
	 * 23456
	 * 71234
	 */
	public static int rand7() {
		int s = 25;
		while (s > 21) {
			s = 0;
			for (int i = 0; i < 5; i++) {
				s += rand5();
			}
		}
		int res = s % 7;
		if (res > 0) return res;
		else return 7;
	}
	
	// rand5 => return 1-5 at random
	public static int rand5() {
		int res = (int)(5 * Math.random()) + 1;
		System.out.println("This rand5 generates: " + res);
		return res;
	}
	
	public static void main(String[] args) {
		for (int i = 0; i < 10; i++) {
			System.out.println(rand7());
		}
	}
}
