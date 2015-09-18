package interviews;

public class Scramble_String {
	/**
	 * Given a string s1, we may represent it as a binary tree by partitioning it to two non-empty substrings recursively.
	 * Below is one possible representation of s1 = "great":

 	   	great
   		/    \
  	   gr    eat
 	  / \    / \
	 g   r  e   at
           	    / \
          	   a   t
     * To scramble the string, we may choose any non-leaf node and swap its two children.
     * For example, if we choose the node "gr" and swap its two children, it produces a scrambled string "rgeat".

    	rgeat
   		/    \
  	  rg    eat
     / \    /  \
    r   g  e   at
               / \
              a   t
     * We say that "rgeat" is a scrambled string of "great".
     * Similarly, if we continue to swap the children of nodes "eat" and "at", it produces a scrambled string "rgtae".
     * 
	 */
	/**
	 * O(n^4) method DP
	 * four iteration: start1, start2, curLen, the site we cut the current two strings
	 * 
	 * 就是s1和s2是scramble的话，那么必然存在一个在s1上的长度l1，将s1分成s11和s12两段，同样有s21和s22。
	 * 那么要么s11和s21是scramble的并且s12和s22是scramble的；
	 * 要么s11和s22是scramble的并且s12和s21是scramble的。
	 * 
	 * 三维DP, table[i][j][k] = 1 means we could get a scramble until now
	 * 							from i in s1, j in s2, length = k scramble is valid
	 * 	      table[i][j][k] = 2 means we could not
	 * 		  table[i][j][k] = 0 means we have not check this case
	 */ 
	//since we need to use this table for the entire process, we make it global here
    public int[][][] table;
    public boolean isScramble(String s1, String s2) {
        int n = s1.length();
        table = new int[n][n][n+1];
        return scrambleHelper(s1, s2, 0, 0, n);
    }
    public boolean scrambleHelper(String s1, String s2, int start1, int start2, int curLen){
        if(s1.equals(s2)){
            table[start1][start2][curLen] = 1;
            return true;
        }
        if(s1.length() == 1){
            return s1.charAt(0) == s2.charAt(0);
        }
        // we have checked the current cases
        if(table[start1][start2][curLen] != 0){
            if(table[start1][start2][curLen] == 1){
                return true;
            }else{
                return false;
            }
        }
        // table[start1][start2][curLen] = 0
        // we have not checked this position, so we proceed to check it
        for(int i = 1; i < curLen; i++){
        	// case 1: do not scramble the part (0, i)
            boolean result = scrambleHelper(s1.substring(0, i), s2.substring(0, i), start1, start2, i) && scrambleHelper(s1.substring(i, curLen), s2.substring(i, curLen), start1+i, start2+i, curLen - i);
            // case2: scramble the two parts (0, i) and (len - 1, len)
            // as long as we get one valid case, it's valid, so we |= it
            result |= scrambleHelper(s1.substring(0, i), s2.substring(curLen - i, curLen), start1, start2 + curLen - i, i) && scrambleHelper(s1.substring(i, curLen), s2.substring(0, curLen - i), start1+i, start2, curLen - i);
            // if result == true, then the current scramble is valid
            if(result) {
                table[start1][start2][curLen] = 1;
                return true;
            }
        }
        // try all the case, it's not valid, set it to 2
        table[start1][start2][curLen] = 2;
        return false;
    }
}