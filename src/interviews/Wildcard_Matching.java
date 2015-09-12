package interviews;

public class Wildcard_Matching {
	/**
	 * '?' Matches any single character. 
	 * '*' Matches any sequence of characters (including the empty sequence).
	 * 
	 * The difference is that: the * in this problem can match any sequence independently, 
	 * while the * in Regex Matching would only match duplicates, if any, of the character prior to it.
	 * 
	 * The matching should cover the entire input string (not partial).
	 * 
	 * Some examples: 
	 * isMatch("aa","a") → false 
	 * isMatch("aa","aa") → true
	 * isMatch("aaa","aa") → false 
	 * isMatch("aa", "*") → true 
	 * isMatch("aa", "a*") → true 
	 * isMatch("ab", "?*") → true 
	 * isMatch("aab", "c*a*b") → false
	 * isMatch("adcab", "*a*b") → true
	 * 
	 */
	/**
	 * In this problem, '*' has nothing to do with the preceding character
	 * use two pointers to proceed with the two strings, as long as match, move forward
	 */
	public boolean isMatch(String s, String p) {
        int si = 0, pi = 0, match = 0, starIdx = -1;
        int sLen = s.length(), pLen = p.length();
        while(si < sLen){
        	// easy case, make sure we have character left in p while s has remaining letters
        	// otherwise there cannot be a valid match
            if(pi < pLen && (s.charAt(si) == p.charAt(pi) || p.charAt(pi) == '?')){
                si++;
                pi++;
            // if we encounter a '*', note down its position for the future use
            // also advance p but not s to see if future letter in p can match current letter in s
            }else if(pi < pLen && p.charAt(pi) == '*'){
                starIdx = pi;
                match = si;
                pi++;
            }else if(starIdx != -1){
            	// since no match exists in p, we backtrack to the previous '*' 
            	// and count the current letter in s as a letter in the sequence match with '*'
                pi = starIdx + 1;
                match++;
                si = match;
            }else return false;
        }
        // at last, we need to make sure no letter left in p after exhaust s
        while(pi < pLen && p.charAt(pi) == '*') pi++;
        if(pi == pLen) return true;
        else return false;
    }
}
