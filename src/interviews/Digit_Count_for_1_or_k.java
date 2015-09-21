package interviews;

public class Digit_Count_for_1_or_k {
	/**
	 * Given an integer n, count the total number of digit 1 appearing in all
	 * non-negative integers less than or equal to n.
	 * 
	 * For example: Given n = 13, Return 6, because digit 1 occurred in the
	 * following numbers: 1, 10, 11, 12, 13.
	 * 
	 * @logic Let's start by counting the ones for every 10 numbers...
	 *        0, 1, 2, 3 ... 9 (1)
	 *        10, 11, 12, 13 ... 19 (1) + 10
	 *        20, 21, 22, 23 ... 29 (1)
	 *        ...
	 *        90, 91, 92, 93 ... 99 (1)
	 *        100, 101, 102, 103 ... 109 (10 + 1)
	 *        110, 111, 112, 113 ... 119 (10 + 1) + 10
	 *        120, 121, 122, 123 ... 129 (10 + 1)
	 *        ...
	 *        190, 191, 192, 193 ... 199 (10 + 1)
	 * 
	 * 
	 * 1) If we don't look at those special rows (start with 10, 110 etc), we know that 
	 *    there's a one at ones' place in every 10 numbers, there are 10 ones at tens' place
	 *    in every 100 numbers, and 100 ones at hundreds' place in every 1000 numbers, so 
	 *    on and so forth.
	 * 
	 *    Ok, let's start with ones' place and count how many ones at this place, set k = 1, as 
	 *    mentioned above, there's a one at ones' place in every 10 numbers, so how many 10 
	 *    numbers do we have? The answer is (n / k) / 10.
	 *    
	 *    Now let's count the ones in tens' place, set k = 10, as mentioned above, there 
	 *    are 10 ones at tens' place in every 100 numbers, so how many 100 numbers do we have?
	 *    The answer is (n / k) / 10, and the number of ones at ten's place is (n / k) / 10 * k.
	 * 
	 *    Let r = n / k, now we have a formula to count the ones at k's place: r / 10 * k
	 * 
	 * 2) So far, everything looks good, but we need to fix those special rows, how?
	 * 
	 *    We can use the mod. Take 10, 11, and 12 for example, if n is 10, we get (n / 1) / 10 * 1 = 1 
	 *    ones at ones's place, perfect, but for tens' place, we get (n / 10) / 10 * 10 = 0, that's not 
	 *    right, there should be a one at tens' place! Calm down, from 10 to 19, we always have a one 
	 *    at tens's place, let m = n % k, the number of ones at this special place is m + 1, so let's 
	 *    fix the formula to be: r / 10 * k + (r % 10 == 1 ? m + 1 : 0)
	 * 
	 * 3) Wait, how about 20, 21 and 22?
	 * 
	 *    Let's say 20, use the above formula we get 0 ones at tens' place, but it should be 10. 
	 *    How to fix it? We know that once the digit is larger than 2, we should add 10 more ones 
	 *    to the tens' place, a clever way to fix is to add 8 to r, so our final formula is:
	 *    (r + 8) / 10 * k + (r % 10 == 1 ? m + 1 : 0)
	 */
	public int countDigitOne(int n) {
		int ones = 0;
		for (long digit = 1; digit <= n; digit *= 10) {
			long a = n / digit, b = n % digit;
			// 第一个part处理主位+个别须进位，第二part处理10，100，1000
			ones += (a + 8) / 10 * digit + (a % 10 == 1 ? b + 1 : 0);
		}
		return ones;
	}
	
	/**
	 * Count the number of k's between 0 and n. k can be 0 - 9.
	 * 
	 * if n=12, k=1 in [0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12], 
	 * we have FIVE 1's (1, 10, 11, 12)
	 */
	// brute-force
	public int digitCounts(int k, int n) {
        int count = 0;
        char kChar = (char)(k + '0');
        for (int i = 0; i <= n; i++) {
            String curNum = Integer.toString(i);
            for (int j = 0; j < curNum.length(); j++) {
                if (curNum.charAt(j) == kChar) count++;
            }
        }
        return count;
    }
	/**
	 * Math:
	 * 
	 * 当某一位的数字小于i时，那么该位出现i的次数为：更高位数字x当前位数
	 * 当某一位的数字等于i时，那么该位出现i的次数为：更高位数字x当前位数+低位数字+1
	 * 当某一位的数字大于i时，那么该位出现i的次数为：(更高位数字+1)x当前位数
	 * 
	 * 假设一个5位数N=abcde，我们现在来考虑百位上出现2的次数，即，从0到abcde的数中，
	 * 有多少个数的百位上是2。分析完它，就可以用同样的方法去计算个位，十位，千位， 万位等各个位上出现2的次数。
	 * 
	 * 当百位c为0时，比如说12013，0到12013中哪些数的百位会出现2？我们从小的数起， 
	 * 200~299, 1200~1299, 2200~2299, … , 11200~11299, 也就是固定低3位为200~299，
	 * 然后高位依次从0到11，共12个。再往下12200~12299 已经大于12013，因此不再往下。所以，
	 * 当百位为0时，百位出现2的次数只由更高位决定， 等于更高位数字(12)x当前位数(100)=1200个。
	 * 
	 * 当百位c为1时，比如说12113。分析同上，并且和上面的情况一模一样。 最大也只能到11200~11299，
	 * 所以百位出现2的次数也是1200个。
	 * 
	 * 上面两步综合起来，可以得到以下结论：
	 * 
	 * 当某一位的数字小于2时，那么该位出现2的次数为：更高位数字x当前位数
	 * 当百位c为2时，比如说12213。那么，我们还是有200~299, 1200~1299, 2200~2299, … , 
	 * 11200~11299这1200个数，他们的百位为2。但同时，还有一部分12200~12213， 共14个(低位数字+1)。
	 * 所以，当百位数字为2时， 百位出现2的次数既受高位影响也受低位影响，结论如下：
	 * 
	 * 当某一位的数字等于2时，那么该位出现2的次数为：更高位数字x当前位数+低位数字+1
	 * 当百位c大于2时，比如说12313，那么固定低3位为200~299，高位依次可以从0到12， 
	 * 这一次就把12200~12299也包含了，同时也没低位什么事情。因此出现2的次数是： 
	 * (更高位数字+1)x当前位数。结论如下：
	 * 
	 * 当某一位的数字大于2时，那么该位出现2的次数为：(更高位数字+1)x当前位数
	 */
	public int digitCountsMath(int k, int n) {
		int count = 0;
		int base = 1;
	    while (n / base >= 1) {
	    	int curBit = n % (base*10) / base;
	    	int higher = n / (base*10);
	    	int lower = n % base;
	    	if (curBit < k) {
	    		count += higher * base;
	    	} else if (curBit == k) {
	    		count += higher * base + lower + 1;
	    	} else {
	    		count += (higher + 1) * base;
	    	}
	    	base *= 10;
		}
		return count;
	}
}
