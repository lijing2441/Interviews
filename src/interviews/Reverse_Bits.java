package interviews;

public class Reverse_Bits {
	/**
	 * Reverse bits of a given 32 bits unsigned integer.
	 * 
	 * For example, given input 43261596 (represented in binary as
	 * 00000010100101000001111010011100), return 964176192 (represented in
	 * binary as 00111001011110000010100101000000).
	 */
	// you need treat n as an unsigned value
    public int reverseBits(int n) {
        int res = 0;
        for(int i = 0; i < 32; i++) {
            res = (res << 1) | (n & 1);
            n >>= 1;
        }
        return res;
    }

	public int reverseBits2(int n) {
        int res = 0;
        for(int i = 0; i < 32; i++) {
            int tmp = n << i;
            tmp = tmp >>> 31 << i;
            res += tmp;
        }
        return res;
    }
}
