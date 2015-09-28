package interviews;

import java.util.List;

public class Detect_Cycle_In_a_Graph {
	/**
	 * Given a directed graph, check whether the graph contains a cycle or not.
	 * Your function should return true if the given graph contains at least one
	 * cycle, else return false. 
	 * 
	 * @logic DFS can be used here. DFS for a connected graph produces a tree. 
	 * 		  
	 * 		  There is a cycle in a graph only if there is a back edge present in the graph. 
	 * 		  A back edge is an edge that is from a node to itself (selfloop) or one of its 
	 * 		  ancestor in the tree produced by DFS.
	 * 
	 * 		  To detect a back edge, we can keep track of vertices currently in recursion 
	 * 		  stack of function for DFS traversal. If we reach a vertex that is already in 
	 * 		  the recursion stack, then there is a cycle in the tree. The edge that connects 
	 * 		  current vertex to the vertex in the recursion stack is back edge.
	 * 
	 */
	public boolean isCyclic(Graph g){
		int n = g.vertices.size();
		
		boolean[] visited = new boolean[n];
		boolean[] recStack = new boolean[n];
		
		for(Vertex i: g.vertices){
			if(isCyclic(i, visited, recStack)) return true;
		}
		return false;
	}
	// check each node i
	public boolean isCyclic(Vertex cur, boolean[] visited, boolean[] recStack){
		if(!visited[cur.number]){
			visited[cur.number] = true;
			// 入 recursion stack
			recStack[cur.number] = true;
			
			for(Vertex i: cur.adjacent){
				// if this neighbor has not been visited and its recursion return false
				// then we have completed all the check for this current node and did not detect a cycle
				if(!visited[i.number] && !isCyclic(i, visited, recStack)){
					return false;
					// if we either visited this neighbor or a cycle is detected.
					// if this neighbor is still in the recursion stack, it has a cycle with the current node
				}else if(recStack[i.number]){
					return true;
				}
			}
		}
		// 出 recursion stack
		recStack[cur.number] = false;
		return false;
	}
}
class Vertex{
	// Node 编号
	public int number;
	public int val;
	public State state;
	public Vertex[] adjacent;
}
enum State{
	Unvisited, Visited, Visiting;
}
class Edge{
	public Vertex[] adjacent;
}
class Graph{
	public List<Vertex> vertices;
	public List<Edge> edges;
	public Vertex root;
}
