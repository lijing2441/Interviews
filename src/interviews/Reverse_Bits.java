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

    public static int reverseBits1(int n) {
        StringBuilder sb = new StringBuilder();
        while(n > 0) {
            int digit = n % 2;
            n /= 2;
            sb.insert(0, Integer.toString(digit));
        }
        // padding zeroes
        while(sb.length() < 32) {
            sb.insert(0, '0');
        }
        String str = sb.reverse().toString();
        
        int res = 0;
        for(int i = 31; i >= 0; i--) {
            char c = str.charAt(i);
            if(c == '1') {
                res += Math.pow(2, 31 - i);
            }
        }
        return res;
    }
	
	public static int reverseBits2(int n) {
        String str = String.format("%32s", Integer.toBinaryString(n)).replace(' ', '0');
        str = new StringBuilder(str).reverse().toString();
        int res = Integer.parseUnsignedInt(str, 2);
        return res;
    }
	
	public int reverseBits3(int n) {
        int res = 0;
        for(int i = 0; i < 32; i++) {
            int tmp = n << i;
            tmp = tmp >>> 31 << i;
            res += tmp;
        }
        return res;
    }
}
