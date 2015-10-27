package interviews;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

public class Airbnb_覆盖尽量多string {
	public List<String> findOptimalSolution(char[][] board, MyTrieNode root) {
		List<String> res = new ArrayList<String>();
		if (board == null || board.length == 0 || board[0].length == 0) {
			return res;
		}
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[0].length; j++) {
				if (board[i][j] != '#') {
					searchNext(board, i, j, res, root, root);
				}
			}
		}
		return res;
	}
	
	public void searchNext(char[][] board, int x, int y, List<String> res, MyTrieNode node, MyTrieNode root) {
		if (node.isEnd) {
			List<String> next = findOptimalSolution(board, root);
			next.add(node.s);
			if (getCharNumber(next) > getCharNumber(res)) {
				res.clear();
				res.addAll(next);
			}
		}
		if (x < 0 || x >= board.length || y < 0 || y >= board[0].length || !node.childMap.containsKey(board[x][y])) {
			return;
		}
		char tmp = board[x][y];
		board[x][y] = '#';
		int[] dx = {-1, -1, -1, 0, 1, 1, 1, 0};
		int[] dy = {-1, 0, 1, 1, 1, 0, -1, -1};
		for (int i = 0; i < 8; i++) {
			searchNext(board, x + dx[i], y + dy[i], res, node.childMap.get(tmp), root);
		}
		board[x][y] = tmp;
	}
	
	public int getCharNumber(List<String> l) {
		if (l == null || l.size() == 0) return 0;
		int res = 0;
		for (String s: l) {
			res += s.length();
		}
		return res;
	}
	
	public static void main(String[] args) {
		char[][] board1 = new char[3][3];
        {
            board1[0][0] = 's';
            board1[0][1] = 'a';
            board1[0][2] = 'c';

            board1[1][0] = 's';
            board1[1][1] = 't';
            board1[1][2] = 'a';

            board1[2][0] = 't';
            board1[2][1] = 's';
            board1[2][2] = 'x';
        }
        MyTrieNode root1 = new MyTrieNode();
        root1.add("sat");
        root1.add("cat");
        root1.add("s");
        root1.add("g");
        root1.add("st");
        root1.add("sa");
        List<String> res1 = new Airbnb_覆盖尽量多string().findOptimalSolution(board1, root1);
        
        for (String str : res1) {
            System.out.println(str);
        }
	}
}
class MyTrieNode {
	Map<Character, MyTrieNode> childMap;
	boolean isEnd;
	String s;
	public MyTrieNode() {
		childMap = new HashMap<>();
		isEnd = false;
		s = "";
	}
	public void add(String word) {
		if (word == null || word.length() == 0) return;
		MyTrieNode node = this;
		for (int i = 0; i < word.length(); i++) {
			if (!node.childMap.containsKey(word.charAt(i))) {
				node.childMap.put(word.charAt(i), new MyTrieNode());
			}
			node = node.childMap.get(word.charAt(i));
		}
		node.isEnd = true;
		node.s = word;
	}
}