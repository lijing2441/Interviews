package interviews;

public class Flip_Bits {
	/**
	 * Example Given n = 31 (11111), m = 14 (01110), return 2.
	 * 
	 * Note Both n and m are 32-bit integers.
	 */
	public static int bitSwapRequired(int a, int b) {
        // write your code here
        int count = 0;
        for(int i = 0; i < 32; i++) {
            if(((a >> i) & 1) != ((b >> i) & 1)) count++;
        }
        return count;
    }
}
