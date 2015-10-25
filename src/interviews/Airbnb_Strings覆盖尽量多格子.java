package interviews;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;


public class Airbnb_Strings覆盖尽量多格子 {
	public static void main(String[] args) {
        char[][] board = new char[4][4];
        {
            board[0][0] = 'd';
            board[0][1] = 'g';
            board[0][2] = 'h';
            board[0][3] = 'i';

            board[1][0] = 'k';
            board[1][1] = 'l';
            board[1][2] = 'p';
            board[1][3] = 's';

            board[2][0] = 'y';
            board[2][1] = 'e';
            board[2][2] = 'u';
            board[2][3] = 't';

            board[3][0] = 'e';
            board[3][1] = 'o';
            board[3][2] = 'r';
            board[3][3] = 'n';
        }
        
        MyTrieNode root = new MyTrieNode();
        // case 1
        /**
         root.add("turn");
         root.add("leo");
         root.add("super");
         **/

        // case 2
        root.add("is");
        root.add("our");
        root.add("key");
        // root.add("dgh");

        root.add("leo");
        root.add("gps");
        root.add("turn");


        List<String> res = new Airbnb_Strings覆盖尽量多格子().findOptimalSolution(board, root);

        for (String str : res) {
            System.out.println(str);
        }
	}
	
	public List<String> findOptimalSolution(char[][] board, MyTrieNode root) {
        List<String> res = new ArrayList<>();
        if (board == null || board.length == 0 || board[0].length == 0) {
            return res;
        }

        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (board[i][j] != 'X') {
                    searchNext(board, i, j, root, res, root);
                }
            }
        }

        return res;
    }

    public void searchNext(char[][] board, int x, int y, MyTrieNode node, List<String> res, MyTrieNode root) {
        if (node.isString) {
            List<String> next = findOptimalSolution(board, root);

            List<String> tmp = new ArrayList<>();
            tmp.add(node.s);
            tmp.addAll(next);
            if (getCharacterCnt(tmp) > getCharacterCnt(res)) {
                res.clear();
                res.addAll(tmp);
            }
        }

        if (x < 0 || x >= board.length || y < 0 || y >= board[0].length || !node.map.containsKey(board[x][y])) {
            return;
        }

        int[] dx = {-1, -1, -1, 0, 0, 1, 1, 1};
        int[] dy = {-1, 0, 1, -1, 1, -1, 0, 1};

        char tmp = board[x][y];
        board[x][y] = 'X';
        for (int i = 0; i < 8; i++) {
            searchNext(board, x + dx[i], y + dy[i], node.map.get(tmp), res, root);
        }
        board[x][y] = tmp;
    }

    public int getCharacterCnt(List<String> list) {
        int res = 0;

        for (String str : list) {
            res += str.length();
        }

        return res;
    }
	
}
class MyTrieNode {
    Map<Character, MyTrieNode> map;
    boolean isString;
    String s;

    public MyTrieNode() {
        map = new HashMap<>();
        isString = false;
        s = "";
    }

    public void add(String str) {
        MyTrieNode node = this;

        for (int i = 0; i < str.length(); i++) {
            char ch = str.charAt(i);

            if (!node.map.containsKey(ch)) {
                node.map.put(ch, new MyTrieNode());
            }

            node = node.map.get(ch);
        }

        node.isString = true;
        node.s = str;
    }
}