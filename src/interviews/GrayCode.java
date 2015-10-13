package interviews;

import java.util.*;

public class GrayCode {
	/**
	 * The gray code is a binary numeral system where two successive values differ in only one bit.
	 * Given a non-negative integer n representing the total number of bits in the code, 
	 * print the sequence of gray code. A gray code sequence must begin with 0.
	 * For example, given n = 2, return [0,1,3,2]. Its gray code sequence is:
	 * 00 - 0
	 * 01 - 1
	 * 11 - 3
	 * 10 - 2
	 * 
	 * @complexity depends on the size of the solution, O(2^n)
	 * 算法复杂度是O(2+2^2+...+2^n-1)=O(2^n)，所以是指数量级的，因为是结果数量无法避免。空间复杂度则是结果的大小，也是O(2^n)。
   	**/
	public List<Integer> grayCode(int n) {
		List<Integer> list = new LinkedList<Integer>();
		list.add(0);

		for (int i = 0; i < n; i++) {
			int nextDigit = 1 << i;
			for (int j = list.size() - 1; j >= 0; j--) {
				list.add(list.get(j) + nextDigit);
			}
		}
		return list;
	}
	// 根据公式N^(N>>1), Facebook要求O(1)
	public ArrayList<Integer> grayCodeBitInt(int n) {
        ArrayList<Integer> res = new ArrayList<Integer>();
        for (int i = 0; i < Math.pow(2, n); i++) {
            int grayCode = i ^ (i>>1);
            res.add(grayCode);
        }
        return res;
    }
	
	// 返回string
	public static void grayCodeBit(int bitNum) {
		for (int i = 0; i < (int)Math.pow(2, bitNum); i++) {
			int grayCode = (i>>1) ^ i;
			System.out.println(num2Binary(grayCode, bitNum));
		}
	}
	public static String num2Binary(int num, int bitNum) {
		String res = "";
		for (int i = bitNum - 1; i >= 0; i--) {
			res += ((num >> i) & 1);
		}
		return res;
	}
	
	public static void main(String[] args) {
		int num = 3;
		grayCodeBit(num);
	}
}
