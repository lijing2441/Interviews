 package interviews;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

public class Word_Ladder {
	/**
	 * 
	 * Given two words (start and end), and a dictionary, find the length of
	 * shortest transformation sequence from start to end, such that:
	 * 
	 * Only one letter can be changed at a time Each intermediate word must
	 * exist in the dictionary For example,
	 * 
	 * Given: start = "hit" end = "cog" dict = ["hot","dot","dog","lot","log"]
	 * As one shortest transformation is "hit" -> "hot" -> "dot" -> "dog" ->
	 * "cog", return its length 5.
	 * 
	 * Note: Return 0 if there is no such transformation sequence. All words
	 * have the same length. All words contain only lowercase alphabetic
	 * characters.
	 *
	 * 
	 * word ladder I only needs to return the length between start and end, BFS is enough.
	 */
	public int ladderLength(String start, String end, Set<String> dict) {
		if (dict == null)
			return 0;
		Queue<String> wordQ = new LinkedList<String>();
		Queue<Integer> distQ = new LinkedList<Integer>();

		wordQ.add(start);
		distQ.add(1);
		while (!wordQ.isEmpty()) {
			String curWord = wordQ.poll();
			Integer curDist = distQ.poll();
			if (curWord.equals(end))
				return curDist;

			for (int i = 0; i < curWord.length(); i++) {
				char[] ch = curWord.toCharArray();
				for (char c = 'a'; c <= 'z'; c++) {
					ch[i] = c;
					String newWord = new String(ch);
					if (dict.contains(newWord)) {
						wordQ.add(newWord);
						distQ.add(curDist + 1);
						// remember to remove the word, since the 
						// others use it later can not be the shortest
						dict.remove(newWord);
					}
				}
			}
		}
		return 0;
	}

	/**
	 * Word Ladder II: return all shortest transformation sequence(s) from start
	 * to end
	 * 
	 * 思路1：自定义一个Node类，里面保存当前节点的所有前驱，然后再用DFS去从end到start递推获得所有路径。
	 * 
	 * 思路2：使用Set<List<String>>。虽然结果需要的形式为List<List<String>>，可以先用Set保存各个路径来达到去重的目的，
	 * 然后调用容器类的addAll方法即可。
	 * 
	 * 和上一题比较不同的是，当前str为一个字典词，且也在map里时，需要进一步判断：如果这条路径的长度和当前所保存的路径长度相等，
	 * 那么也应该把当前路径加入到路径集合中。但是这里不能把这个str再次加入到搜索队列，因为这个str已经出现在map中了，说明之前肯定已经搜索到了，
	 * 在第一次搜索到的时候已经加入到了搜索队列中。如果再次加入搜索队列，则会导致重复搜索而超时。
	 * 
	 */
	 public ArrayList<ArrayList<String>> findLadders(String start, String end, HashSet<String> dict) {
		 ArrayList<ArrayList<String>> res = new ArrayList<ArrayList<String>>();
		 HashMap<String, NodeWithP> map = new HashMap<String, NodeWithP>();
		 Queue<String> q = new LinkedList<String>();
		 q.offer(start);
		 dict.add(end);
		 while(!q.isEmpty()){
			 String curWord = q.poll();
			 if(curWord.equals(end)){
				 getPath(map.get(end), map, new ArrayList<String>(), res);
				 return res;
			 }
			 for(int i = 0; i < curWord.length(); i++){
				 char[] ch = curWord.toCharArray();
				 for(char c = 'a'; c <= 'z'; c++){
					 ch[i] = c;
					 String changed = new String(ch);
					 if(dict.contains(changed)){
						 if(!map.containsKey(changed)){
							 NodeWithP node = map.get(curWord);
							 NodeWithP newNode = new NodeWithP(node.dist + 1, changed);
							 newNode.prev.add(node);
							 map.put(changed, newNode);
							 q.offer(changed);
						 }else{
							 NodeWithP node = map.get(curWord);
							 NodeWithP newNode = map.get(changed);
							 if(node.dist + 1 == newNode.dist){
								 newNode.prev.add(node);
							 }
						 }
					 }
				 }
			 }
		 }
		 return res;
	 }
	 public void getPath(NodeWithP node, HashMap<String, NodeWithP> map, ArrayList<String> cur, ArrayList<ArrayList<String>> res){
		 if(node == null){
			 res.add(cur);
			 return;
		 }
		 cur.add(0, node.str);
		 if(!node.prev.isEmpty()){
			 for(NodeWithP prevNode: node.prev){
				 ArrayList<String> next = new ArrayList<String>(cur);
				 getPath(prevNode, map, next, res);
			 }
		 }else{
			 getPath(null, map, cur, res);
		 }
	 }
}
class NodeWithP{
	public int dist;
	public String str;
	public LinkedList<NodeWithP> prev = new LinkedList<NodeWithP>();
	public NodeWithP(int d, String str){
		dist = d;
		this.str = str;
	}
}
