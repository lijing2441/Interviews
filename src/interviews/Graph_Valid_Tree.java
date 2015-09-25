package interviews;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

public class Graph_Valid_Tree {
	/**
	 * Given n nodes labeled from 0 to n - 1 and a list of undirected edges
	 * (each edge is a pair of nodes), write a function to check whether these
	 * edges make up a valid tree.
	 * 
	 * For example:
	 * 
	 * Given n = 5 and edges = [[0, 1], [0, 2], [0, 3], [1, 4]], return true.
	 * 
	 * Given n = 5 and edges = [[0, 1], [1, 2], [2, 3], [1, 3], [1, 4]], return
	 * false.
	 */
    // two things to check: cycle and connectivity
	// 可以用dfs判断联通和找环
	public boolean validTree(int n, int[][] edges) {
        int edgeNum = edges.length;
        boolean[] visited = new boolean[n];
        // pre-process the edge matrix
        Map<Integer, List<Integer>> graph = new HashMap<Integer, List<Integer>>();
        for(int i = 0; i < edgeNum; i++) {
            if(graph.containsKey(edges[i][0])) {
                graph.get(edges[i][0]).add(edges[i][1]);
            } else {
                List<Integer> l = new ArrayList<Integer>();
                l.add(edges[i][1]);
                graph.put(edges[i][0], l);
            }
            if(graph.containsKey(edges[i][1])) {
                graph.get(edges[i][1]).add(edges[i][0]);
            } else {
                List<Integer> l = new ArrayList<Integer>();
                l.add(edges[i][0]);
                graph.put(edges[i][1], l);
            }
        }
        // two things to check: cycle and connectivity
        // For every visited vertex ‘v’, if there is an adjacent ‘u’ already visited and is not parent of v
        // then a cycle exists in graph. 
        if(isCyclic(graph, visited, 0, -1)) {
            return false;
        }
        // connectivity
        for(int i = 0; i < n; i++) {
            if(!visited[i]) return false;
        }
        return true;
    }
    public boolean isCyclic(Map<Integer, List<Integer>> graph, boolean[] visited, int cur, int parent) {
        visited[cur] = true;
        List<Integer> ends = graph.get(cur);
        if(ends == null) return false;
        for(int end: ends) {
            if(!visited[end]) {
                if(isCyclic(graph, visited, end, cur)) {
                    return true;
                }
            }
            // check whether it's the parent node
            else if(end != parent) return true;
        }
        return false;
    }
}
