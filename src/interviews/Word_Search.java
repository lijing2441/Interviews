package interviews;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Word_Search {
	/**
	 * DFS: 从某一个元素出发，往上下左右深度搜索是否有相等于word的字符串。这里注意每次从一个元素出发时要重置访问标记
	 * （也就是说虽然单次搜索字符不能重复使用，但是每次从一个新的元素出发，字符还是重新可以用的）。
	 * 
	 * There are O(n*m) candidates for starting cell.
	 * 
	 * 空间上就是要用一个数组来记录访问情况，所以是O(m*n)。
	 */
    //global variable to mark the directions
    static int[] x = {1, 1, 1, 0, -1, -1, -1, 0};
    static int[] y = {1, 0, -1, -1, -1, 0, 1, 1};
    
    public static boolean findMatch(char[][] matrix, String pattern){
        boolean[][] used = new boolean[matrix.length][matrix[0].length];
        for(int i = 0; i < matrix.length; i++){
        	for(int j = 0; j < matrix[0].length; j++){
        		if(matrix[i][j] == pattern.charAt(0)){
        			if(findNeighbors(matrix, pattern, 0, i, j, used)){
        				System.out.println("Found");
        				return true;
        			}
        		}
        	}
        }
        System.out.println("cannot find it!");
        return false;
    }
    
    public static boolean findNeighbors(char[][] matrix, String pattern, int index, int row, int column, boolean[][] used){
    	if(index == pattern.length()) return true;
    	if(row >= 0 && row < matrix.length && column >= 0 && column < matrix[0].length && matrix[row][column] == pattern.charAt(index) && !used[row][column]){
    		used[row][column] = true;
    		for(int n = 0; n < 8; n++){
    			if(findNeighbors(matrix, pattern, index + 1, row + y[n], column + x[n], used)) return true;
    		}
    		// if the current position is not used until this step, restore it to the unused state
    		used[row][column] = false; 
    	}
    	return false;
    }
    /*
    if only need to use the four directions:
    public int[] x = {0, 0, 1, -1};
    public int[] y = {1, -1, 0, 0}; 
    
    public static boolean isSafe(char[][] matrix, int row, int column, String pattern, int index, boolean[][] used){
    	if(row >= 0 && row < matrix.length && column >= 0 && column < matrix[0].length && matrix[row][column] == pattern.charAt(index) && !used[row][column]){
    		used[row][column] = true;
    		return true;
    	}
    	return false;
    }
    */

    /**
     * Word search in leetcode
     * Version II: Given a grid of letters (and access to a dictionary function), 
     * create a function that will find all of the words in it. 	
     * A word is made of adjacent letters (up, down, left, right, diagonal) and cannot reuse the same block.
     * Find its runtime.
     * 
     */
    public ArrayList<String> findAllWords(char[][] matrix, HashSet<String> dict){
    	boolean[][] used = new boolean[matrix.length][matrix[0].length];
    	ArrayList<String> res = new ArrayList<String>();
    	for(String s: dict){
    		if(findMatch(matrix, s, used)){
    			res.add(s);
    		}
    	}
    	return res;
    }
    public boolean findMatch(char[][] matrix, String s, boolean[][] used){
    	for(int i = 0; i < matrix.length; i++){
        	for(int j = 0; j < matrix[0].length; j++){
        		if(matrix[i][j] == s.charAt(0)){
        			if(findNeighbors(matrix, s, 0, i, j, used)){
        				System.out.println("Found");
        				return true;
        			}
        		}
        	}
        }
        return false;
    }

	/**
	 * Word search II: Given a 2D board and a list of words from the dictionary,
	 * find all words in the board.
	 * 
	 * Each word must be constructed from letters of sequentially adjacent cell,
	 * where "adjacent" cells are those horizontally or vertically neighboring.
	 * The same letter cell may not be used more than once in a word.
	 * 
	 * @logic backtracking + Trie
	 */
    public class Solution {
        public int[] x = {0, 0, -1, 1};
        public int[] y = {1, -1, 0, 0};
        public Set<String> set = new HashSet<String>();
        
        public List<String> findWords(char[][] board, String[] words) {
            if(board.length == 0 || board[0].length == 0 || words.length == 0) return new ArrayList<String>();
            Trie trie = new Trie();
            for(int i = 0; i < words.length; i++) {
                trie.insert(words[i]);
            }
            int m = board.length;
            int n = board[0].length;
            boolean[][] used = new boolean[m][n];
            for(int i = 0; i < m; i++) {
                for(int j = 0; j < n; j++) {
                    findNeighbor(board, "", i, j, used, trie);
                }
            }
            return new ArrayList<String>(set);
        }
        public void findNeighbor(char[][] board, String word, int row, int col, boolean[][] used, Trie trie){
            if(row >= 0 && row < board.length && col >= 0 && col < board[0].length && !used[row][col]){
                word = word + board[row][col];
                if(!trie.startWith(word)) return;
                if(trie.search(word)) set.add(word);
                
                used[row][col] = true;
                for(int i = 0; i < 4; i++){
                    findNeighbor(board, word, row + x[i], col + y[i], used, trie);
                }
                used[row][col] = false;
            }
        }
    }
    // Trie implementation
    class TrieNode {
        public boolean isEnd;
        public TrieNode[] children;
        public TrieNode() {
            isEnd = false;
            children = new TrieNode[26];
        }
    }
    class Trie {
        private TrieNode root;
        public Trie(){
            root = new TrieNode();
        }
        
        public void insert(String word) {
            if(word == null || word.length() == 0) return;
            char[] arr = word.toCharArray();
            TrieNode tmp = root;
            for(int i = 0; i < arr.length; i++) {
                char c = arr[i];
                if(tmp.children[c - 'a'] == null) {
                    tmp.children[c - 'a'] = new TrieNode();
                }
                tmp = tmp.children[c - 'a'];
            }
            tmp.isEnd = true;
        }
        
        public boolean search(String word) {
            if(word == null || word.length() == 0) return false;
            char[] arr = word.toCharArray();
            TrieNode tmp = root;
            for(int i = 0; i < arr.length; i++) {
                char c = arr[i];
                if(tmp.children[c - 'a'] == null) {
                    return false;
                }
                tmp = tmp.children[c - 'a'];
            }
            if(tmp.isEnd) return true;
            else return false;
        }
        
        public boolean startWith(String word) {
            if(word == null || word.length() == 0) return false;
            char[] arr = word.toCharArray();
            TrieNode tmp = root;
            for(int i = 0; i < arr.length; i++) {
                char c = arr[i];
                if(tmp.children[c - 'a'] == null) {
                    return false;
                }
                tmp = tmp.children[c - 'a'];
            }
            return true;
        }
    }
    // test method
    public static void main(String[] args) {

        String pattern = "MICROSOFT";

        char[][] matrix = {
            {'A', 'C', 'P', 'R', 'C'},
            {'M', 'S', 'O', 'P', 'C'},
            {'I', 'O', 'V', 'N', 'I'},
            {'C', 'G', 'F', 'M', 'N'},
            {'Q', 'A', 'T', 'I', 'T'}
        };
        // printing matrix
        findMatch(matrix, pattern);                    
    }
}
