package interviews;

import java.util.*;
import java.util.HashMap;

public class Alien_Dictionary_Topological_Sort {
	/**
	 * Given a list of sorted words, output the order of the letters used to sort them. 
	 * 
	 * @analysis Form a graph (DAG) and do a topological sort: from in-degree = 0 node, one by one sort.
	 * 
	 * The idea is to create a graph of characters and then find topological sorting of 
	 * the created graph.
	 * 
	 * 1) Create a graph g with number of vertices equal to the size of alphabet in the 
	 * given alien language. For example, if the alphabet size is 5, then there can be 5 
	 * characters in words. Initially there are no edges in graph.
	 * 
	 * 2) Do following for every pair of adjacent words in given sorted array.
	 * 		a) Let the current pair of words be word1 and word2. One by one compare 
	 * 		   characters of both words and find the first mismatching characters.
	 * 		b) Create an edge in g from mismatching character of word1 to that of word2.
	 * 
	 * 3) Print topological sorting of the above created graph.
	 */
	public String alienOrder(String[] words) {
        int len = words.length;
        if(len == 0) return "";
        // build the graph first, use set to avoid dups
        Map<Character, Set<Character>> graph = new HashMap<Character, Set<Character>>();
        for(int i = 0; i < len; i++) {
            for(int j = 0; j < words[i].length(); j++) {
                if(!graph.containsKey(words[i].charAt(j))) {
                    graph.put(words[i].charAt(j), new HashSet<Character>());
                }
            }
            if(i > 0) getOrder(words[i - 1], words[i], graph);
        }
        return topoSort(graph);
    }
    // topological sort
    public String topoSort(Map<Character, Set<Character>> graph) {
        StringBuilder sb = new StringBuilder();
        Map<Character, Integer> indegree = new HashMap<Character, Integer>();
        Queue<Character> noIncome = new LinkedList<Character>();
        // get the indegree
        for(char c: graph.keySet()) {
            for(char end: graph.get(c)) {
                int num = 1;
                if(indegree.containsKey(end)) num += indegree.get(end);
                indegree.put(end, num);
            }
        }
        for(char c: graph.keySet()) {
            if(!indegree.containsKey(c)) {
                noIncome.offer(c);
            }
        }
        while(!noIncome.isEmpty()) {
            char curChar = noIncome.poll();
            sb.append(curChar);
            Set<Character> set = graph.get(curChar);
            graph.remove(curChar);
            if(set == null) continue;
            for(char c : set) {
                indegree.put(c, indegree.get(c) - 1);
                if(indegree.get(c) == 0) noIncome.offer(c);
            }
        }
        for(int i: indegree.values()) {
            if(i != 0) return "";
        }
        return sb.toString();
    }
    public void getOrder(String word1, String word2, Map<Character, Set<Character>> graph) {
        for(int i = 0; i < Math.min(word1.length(), word2.length()); i++) {
            char c1 = word1.charAt(i), c2 = word2.charAt(i);
            if(c1 != c2) {
                if(!graph.get(c1).contains(c2)) {
                    graph.get(c1).add(c2);
                }
                break;
            }
        }
    }
	
	
	
	
	public ArrayList<Character> topoSortDictOrder(String[] dict, int alphasize){
		DGraph g = new DGraph(alphasize);
		// Process all adjacent pairs of words and create a graph
	    for (int i = 0; i < dict.length - 1; i++){
	    	// Take the current two words and find the first mismatching character
	    	// add edge from the first to the second, that is, dict order
	    	String word1 = dict[i];
	    	String word2 = dict[i + 1];
	    	for(int j = 0; j < Math.min(word1.length(), word2.length()); j++){
	    		if(word1.charAt(j) != word2.charAt(j)){
	    			g.addEdge(new Node(word1.charAt(j)), new Node(word2.charAt(j)));
	    			break;
	    		}
	    	}
	    }
	    ArrayList<Node> order = g.topologicalSort();
	    ArrayList<Character> res = new ArrayList<Character>();
	    for(int i = 0; i < order.size(); i++){
	    	res.add(order.get(i).data);
	    }
	    return res;
	}
}

class DGraph{
	int vertices; // number of vertices
	Set<Node> nodes = new HashSet<Node>();
	Set<_Edge> edges = new HashSet<_Edge>();
	
	//constructor
	DGraph(int v){
		vertices = v;
	}
	public void addVertex(Node node){
	    this.nodes.add(node);
	}
	public void addEdge(Node x, Node y){
		_Edge eg = new _Edge(x, y);
		x.outEdges.add(eg);
		y.inEdges.add(eg);
		
	    this.edges.add(eg);
	}
	
	/**
	 * Topological Sort!
	 * 
	 * L ← Empty list that will contain the sorted elements 
	 * S ← Set of all nodes with no incoming edges 
	 * while S is non-empty do 
	 * 		remove a node n from S 
	 * 		add n to tail of L 
	 * 		for each node m with an edge e from n to m do 
	 * 			remove edge e from the graph 
	 * 			if m has no other incoming edges then 
	 * 				insert m into S 
	 * if graph has edges then 
	 * 		return error (graph has at least one cycle) 
	 * else
	 * 		return L (a topologically sorted order)
	 */
	@SuppressWarnings("rawtypes")
	public ArrayList<Node> topologicalSort() {
		//L <- Empty list that will contain the sorted elements
	    ArrayList<Node> L = new ArrayList<Node>();
	    //S <- Set of all nodes with no incoming edges
	    HashSet<Node> S = new HashSet<Node>();
	    for(Node n : nodes){
	    	if(n.inEdges.size() == 0) S.add(n);
	    }
	    while(!S.isEmpty()){
	    	// remove node n from S
	    	Node n = S.iterator().next();
	    	S.remove(n);
	    	
	    	// insert n into L
	    	L.add(n);
	    	
	    	// for each node m with an edge e from n to m, we remove the edge
	    	// if the indegree == 0 after removing the edge, add m to S
	    	Iterator it = n.outEdges.iterator();
	    	while(it.hasNext()){
	    		_Edge e = (_Edge) it.next();
	    		Node m = e.to;
	    		it.remove(); // remove edge from n
	    		m.inEdges.remove(e);
	    		// if the in-degree == 0, add to S
	    		if(m.inEdges.isEmpty()){
	    			S.add(m);
	    		}
	    	}
	    }
	    //check if all edges are removed, otherwise cycle exists
	    boolean isCycle = false;
	    for(Node n: this.nodes){
	    	if(!n.inEdges.isEmpty()){
	    		isCycle = true;
	    		break;
	    	}
	    }
	    // if cycle exists, no valid topological sort exists
	    if(isCycle) return new ArrayList<Node>();
	    // give L 
	    else return L;
	}
}
class Node{
	char data;
	HashSet<_Edge> inEdges = new HashSet<_Edge>();
	HashSet<_Edge> outEdges = new HashSet<_Edge>();
    public Node (char data) {
        this.data = data;
    }
}

class _Edge{
	Node from;
	Node to;
	public _Edge(Node from, Node to) {
		this.from = from;
		this.to = to;
	}
}
