package interviews;

import java.util.ArrayList;

public class Palindrome_Partition {
	/**
	 * Given a string s, partition s such that every substring of the partition is a palindrome.
	 * Return all possible palindrome partitioning of s.
	 * 
	 * For example, given s = "aab",
	 * Return
	 * [
	 *   ["aa","b"],
	 *   ["a","a","b"]
	 * ]
	 * 
	 */
	//DP solution, save time by using more space for memorization
	//O(n^2) time
	//would cause generic array creation error
	@SuppressWarnings("unchecked")
	public ArrayList<ArrayList<String>> partitionDP(String s){
		ArrayList<ArrayList<String>> res = new ArrayList<ArrayList<String>>();
        
		if(s == null || s.length() == 0) return res;
        
		int n = s.length();
        
		boolean[][] map = new boolean[n][n];
        initiate(s, map);
        
        ArrayList<ArrayList<String>>[] palindromes = new ArrayList[n+1];
        
        palindromes[n] = new ArrayList<ArrayList<String>>();
        palindromes[n].add(new ArrayList<String>());
        
        for(int i = n - 1; i >= 0; i--){
        	palindromes[i] = new ArrayList<ArrayList<String>>();
        	
        	for(int j = i; j < n; j++){
        		if(map[i][j]){
        			ArrayList<ArrayList<String>> previous = palindromes[j + 1];
        			
        			String newString = s.substring(i, j + 1);
        			
        			for(ArrayList<String> list: previous){
        				ArrayList<String> newList = new ArrayList<String>(list);
        				newList.add(newString);
        				palindromes[i].add(newList);
        			}
        		}
        	}
        }
        return palindromes[0];
        
	}
	
	//DFS solution
	//O(n!) time and O(n^2) space, Recursive solution
	public ArrayList<ArrayList<String>> partition(String s) {
        ArrayList<ArrayList<String>> res = new ArrayList<ArrayList<String>>();
        if(s == null || s.length() == 0) return res;
        int len = s.length();
        //map[i][j] means whether s[i~j] can be a palindrome
        boolean[][] map = new boolean[len][len];
        //record where we should cut the string
        boolean[] isCut = new boolean[len];
        initiate(s, map);
        helper(s, 0, isCut, map, res);
        return res;
    }
	//initiate the map. O(n^2)
    public void initiate(String s, boolean[][] map){
        int len = s.length();
        for(int i = 0; i < len; i++) map[i][i] = true;
        for(int k = 1; k < len; k++){
            for(int i = 0; i+k < len; i++){
                if(s.charAt(i) == s.charAt(i+k)){
                    map[i][i+k] = (i + 1 <= i + k -1)? map[i+1][i+k-1]:true;    
                }
            }
        }
    }
    //O(n!) time
    public void helper(String s, int start, boolean[] isCut, boolean[][] map, ArrayList<ArrayList<String>> res){
        if(start == s.length()) addToRes(s, isCut, res);
        else{
            for(int i = start; i < s.length(); i++){
                if(map[start][i]){
                    isCut[i] = true;
                    helper(s, i + 1, isCut, map, res);
                    isCut[i] = false;
                }
            }
        }
    }
    //O(n) time to add the cur list to the result
    public void addToRes(String s, boolean[] isCut, ArrayList<ArrayList<String>> res){
        ArrayList<String> cur = new ArrayList<String>();
        int start = 0;
        for(int i = 0; i < s.length(); i++){
            if(isCut[i]){
                cur.add(s.substring(start, i+1));
                start = i+1;
            }
        }
        res.add(cur);
    }
    
    
    /**
     * Palindrome partition II: needs only return the minimal 
     * number of cut in order to generate a list of palindromes
     * 
     * DP idea: O(n^2) time and O(n^2) space
     * 
     * map[i][j] = true means s[i~j] can be a palindrome
     * 
     * min[i][n] = min(min[i][j] + min[j+1][n]) i <= j < n
     * 二维DP写代码维护比较麻烦，转化成1维DP，从右往左扫，每找到一个回文就算一次DP最小值。
     * min[i] 是区间[i, n]之间最小的cut数. 
     * min[i] = min {min[i], min[j+1] + 1}  if map[i][j] == true
     */
    public int minCut(String s) {
        if(s == null || s.length() == 0) return 0;
        int n = s.length();
        //map[i][j] means whether s[i~j] can be a palindrome
        boolean[][] map = new boolean[n][n];
        //initiate the map
        initiateDP(s, map);
        int[] min = new int[n + 1];
        for(int i = n - 1; i >= 0; i--){
            min[i] = Integer.MAX_VALUE;
            for(int j = i; j < n; j++){
                if(map[i][j]) min[i] = Math.min(min[i], 1 + min[j + 1]);    
            }
        }
        return min[0] - 1;
    }
    public void initiateDP(String s, boolean[][] map){
        int n = s.length();
        for(int i = 0; i < n; i++) map[i][i] = true;
        for(int i = n - 1; i >= 0; i--){
            for(int j = i + 1; j < n; j++){
                if(s.charAt(i) == s.charAt(j) && (j - i < 2 || map[i + 1][j - 1])){
                    map[i][j] = true;
                }
            }
        }
    }
    
    /**
     * Improved, only one pass 
     */
    public int minCutDP2(String s) {
        if(s == null || s.length() < 2) return 0;
        int n = s.length();
        boolean[][] map = new boolean[n][n];
        for(int i = 0; i < n; i++) map[i][i] = true;
        int[] min = new int[n + 1];
        for(int i = n - 1; i >= 0; i--){
            min[i] = Integer.MAX_VALUE;
            for(int j = i; j < n; j++){
                if(s.charAt(i) == s.charAt(j) && (j - i < 2 || map[i + 1][j - 1])){
                    map[i][j] = true;
                    min[i] = Math.min(min[i], min[j + 1] + 1);
                }
            }
        }
        // we add 1 at the end, reduce it since we do not cut at the end
        return min[0] - 1;
    }
}
