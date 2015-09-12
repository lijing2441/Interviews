package interviews;

public class Count_Primes {
	/**
	 * Count the number of prime numbers less than a non-negative number, n.
	 * 
	 * @logic Sieve of Eratosthenes uses an extra O(n) memory and its runtime complexity is O(n log log n).
	 * 
	 * 1. We start off with a table of n numbers. Let's look at the first number, 
	 * 2. We know all multiples of 2 must not be primes, so we mark them off as non-primes. 
	 *    Then we look at the next number, 3. Similarly, all multiples of 3 such as 3 × 2 = 6, 3 × 3 = 9, ... 
	 *    must not be primes, so we mark them off as well. Now we look at the next number, 4, 
	 *    which was already marked off. What does this tell you? Should you mark off all multiples of 4 as well?
	 *    
	 *    4 is not a prime because it is divisible by 2, which means all multiples of 4 must also be 
	 *    divisible by 2 and were already marked off. So we can skip 4 immediately and go to the next number, 5. 
	 *    Now, all multiples of 5 such as 5 × 2 = 10, 5 × 3 = 15, 5 × 4 = 20, 5 × 5 = 25, ... 
	 *    can be marked off. There is a slight optimization here, we do not need to start from 5 × 2 = 10. 
	 *    Where should we start marking off?
	 * 3. In fact, we can mark off multiples of 5 starting at 5 × 5 = 25, because 5 × 2 = 10 
	 *    was already marked off by multiple of 2, similarly 5 × 3 = 15 was already marked off by multiple of 3. 
	 *    Therefore, if the current number is p, we can always mark off multiples of p starting at p2, then in 
	 *    increments of p: p2 + p, p2 + 2p, ... Now what should be the terminating loop condition?
	 */
	public int countPrimes(int n) {
        boolean[] isPrime = new boolean[n];
        for(int i = 2; i < n; i++) {
            isPrime[i] = true;
        }
        for(int i = 2; i * i < n; i++) {
        	// the previously removed ones should be the ones that have been tested through a smaller number
        	// thus no need to test it again, since it must be already non-prime
            if(!isPrime[i]) continue;
            // remove the multiply ones
            for(int j = i * i; j < n; j += i) {
                isPrime[j] = false;
            }
        }
        int count = 0;
        for(int i = 2; i < n; i++) {
            if(isPrime[i]) count++;
        }

        return count;
    }
	
	// normal O(n^1.5) solution
	/**
	 * 2 × 6 = 12
	 * 3 × 4 = 12
	 * 4 × 3 = 12
	 * 6 × 2 = 12
	 * 
	 * As you can see, calculations of 4 × 3 and 6 × 2 are not necessary. 
	 * Therefore, we only need to consider factors up to √n because, if n is 
	 * divisible by some number p, then n = p × q and since p ≤ q, we could derive that p ≤ √n.
	 */
	
	public int countPrimesEasy(int n) {
        //int[] dp = new int[n];
        int count = 0;
        for(int i = 1; i < n; i++) {
            if(isPrime(i)) count++;
        }
        return count;
    }
    public boolean isPrime(int n) {
        for(int i = 2; i * i <= n; i++) {
            if(n % i == 0) return false;
        }
        return true;
    }
}
