package interviews;

import java.util.ArrayList;

public class Permutation_Sequence {
	/**
	 * The set [1,2,3,…,n] contains a total of n! unique permutations. By listing and labeling all 
	 * of the permutations in order, We get the following sequence (ie, for n = 3): 
	 * "123", "132", "213", "231", "312", "321" 
	 * 
	 * Given n and k, return the kth permutation sequence.
	 * Note: Given n will be between 1 and 9 inclusive.
	 */
	/**
	 * First idea must be DFS to get all the permutations and output the kth one.
	 * 
	 * Second idea:
	 * say n = 4, you have {1, 2, 3, 4}
	 * 
	 * If you were to list out all the permutations you have
	 * 
	 * 1 + (permutations of 2, 3, 4)
	 * 2 + (permutations of 1, 3, 4)
	 * 3 + (permutations of 1, 2, 4)
	 * 4 + (permutations of 1, 2, 3)
	 * 
	 * 假设有n个元素，第K个permutation是 "a1, a2, ..., an"
	 * 那么a1是哪一个数字呢？
	 * 那么这里，我们把a1去掉，那么剩下的permutation为"a2, a3, .... .... an", 共计n-1个元素。 
	 * n-1个元素共有(n-1)!组排列，那么这里就可以知道
	 * 设变量
	 * K1 = K
	 * a1 = K1 / (n-1)!
	 * 同理，a2的值可以推导为
	 * a2 = K2 / (n-2)!
	 * K2 = K1 % (n-1)!
	 * .......
	 * a(n-1) = K(n-1) / 1!
	 * K(n-1) = K(n-2) % 2!
	 * 
	 * an = K(n-1)
	 */
	
	public String getKthPermutation(int n, int k) {
        if(n == 0 || k == 0) return "";
        ArrayList<Integer> checkList = new ArrayList<Integer>();
        // set factorial of n
        int mod = 1;
        for(int i = 1; i <= n; i++) {
            checkList.add(i);
            mod *= i;
        }
        // change k to be index
        k--;
        String res = "";
        for(int i = 0; i < n; i++) {
            mod = mod / (n - i);
            // find the right number(curIndex) of the current digit
            int curIndex = k / mod;
            // update k
            k = k % mod;
            res += Integer.toString(checkList.get(curIndex));
            checkList.remove(curIndex);
        }
        return res;
    }
}
