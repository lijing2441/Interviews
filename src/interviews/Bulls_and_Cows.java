package interviews;

import java.util.Map;
import java.util.HashMap;

public class Bulls_and_Cows {
	/**
	 * ou are playing the following Bulls and Cows game with your friend: You
	 * write a 4-digit secret number and ask your friend to guess it, each time
	 * your friend guesses a number, you give a hint, the hint tells your friend
	 * how many digits are in the correct positions (called "bulls") and how
	 * many digits are in the wrong positions (called "cows"), your friend will
	 * use those hints to find out the secret number.
	 * 
	 * For example:
	 * 
	 * Secret number: 1807 
	 * Friend's guess: 7810 
	 * Hint: 1 bull and 3 cows. 
	 * 
	 * Write a function to return a hint according to the secret number and
	 * friend's guess, use A to indicate the bulls and B to indicate the cows,
	 * in the above example, your function should return 1A3B.
	 * 
	 * You may assume that the secret number and your friend's guess only
	 * contain digits, and their lengths are always equal.
	 */
	
	public String getHint(String secret, String guess) {
        //if (secret == null && guess == null) return true;
        if (secret == null || guess == null) return "";
        int len = secret.length();
        if (len != guess.length()) return "";
        char[] sArr = secret.toCharArray();
        char[] gArr = guess.toCharArray();
        int bull = 0, cow = 0;
        Map<Character, Integer> map = new HashMap<Character, Integer>();
        for (int i = 0; i < len; i++) {
            if (sArr[i] == gArr[i]) {
                bull++;
            } else {
                int num = 1;
                if (map.containsKey(sArr[i])) num += map.get(sArr[i]);
                map.put(sArr[i], num);
            }
            
        }
        for (int i = 0; i < len; i++) {
            if (sArr[i] == gArr[i]) {
                continue;
            } else if (map.containsKey(gArr[i])) {
                cow++;
                map.put(gArr[i], map.get(gArr[i]) - 1);
                if (map.get(gArr[i]) == 0) map.remove(gArr[i]);
            }
        }
        return String.valueOf(bull) + "A" + String.valueOf(cow) + "B";
    }
	// one pass
	public String getHintGreat(String secret, String guess) {
        int len = secret.length();
        char[] sArr = secret.toCharArray();
        char[] gArr = guess.toCharArray();
        int bull = 0, cow = 0;
        int[] count = new int[10];
        for (int i = 0; i < len; i++) {
            if (sArr[i] == gArr[i]) {
                bull++;
            } else {
                int s = (int)(sArr[i] - '0');
                int g = (int)(gArr[i] - '0');
                // s如果之前在g中出现过，则为一个cow
                // g如果之前在s中出现过，则为一个cow
                if (count[s] < 0) cow++;
                if (count[g] > 0) cow++;
                // 对s中的char++，g中的char--
                count[s]++;
                count[g]--;
            }
            
        }
        return String.valueOf(bull) + "A" + String.valueOf(cow) + "B";
    }
}
