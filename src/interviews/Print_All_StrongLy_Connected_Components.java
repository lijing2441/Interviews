package interviews;

public class Print_All_StrongLy_Connected_Components {
	/**
	 * Print all Strongly Connected Components in a graph.
	 * 
	 * As far as I know, the best way to solve this would be with Tarjans
	 * algorithm for finding strongly connected components of a graph. Strongly
	 * connected components and cycles are synonymous (but not exactly the
	 * same). That is, print all cycles in a graph.
	 * 
	 * Some points to remember (Taken from link 1): 1.Two vertices, A and B, are
	 * strongly connected if there's a path from A to B and a path from B to A.
	 * 
	 * 2.The set of all vertices that are strongly connected to a given vertex
	 * forms a strongly connected component of the graph.
	 * 
	 * 3.Any strongly connected component with more than one vertex in it
	 * contains at least one cycle, except components with a self-loop. (Thanks
	 * for the help Jens Schauder, bcorso)
	 * 
	 * 4.We want to somehow collapse all the vertices in a cycle into a single
	 * node in a 'tree' (See links). Any future cycle involving vertices we've
	 * already visited gets folded into the same node. What we end up with is a
	 * tree where each node is a strongly connected component.
	 * 
	 * 5.To do this is to store two extra bits of information on each node. The
	 * number of steps the depth-first search takes to reach that node and the
	 * minimum number of steps the depth-first search takes to reach any node in
	 * that node's strongly connected component (from the nodes we've seen so
	 * far).
	 * 
	 * 6.As we perform a depth-first search on the main graph, we use the
	 * secondary data structure to help us test whether two nodes are "the same"
	 * (in the same strongly connected component, as it turns out) and add the
	 * current node to that secondary structure correctly.
	 * 
	 * => Algorithm The question you have isn't trivial to solve. Here's how
	 * Tarjans algorithm works-
	 * 
	 * 1.The first thing to know is that you have to do a DFS. I am assuming
	 * that a stack is used to implement it. The DFS has to cover all vertices
	 * in the graph.
	 * 
	 * 2.Each vertex v, has to be labeled with two values, the index and the
	 * lowval. The index is simply the order in which DFS visits the node. The
	 * lowval is the minimum of the v's index and the index of the vertex that
	 * is nearest to v in the DFS. This vertex is then pushed onto the stack.
	 * 
	 * 3.For each vertex accessible from v, recurse if it isn't already in the
	 * stack.
	 * 
	 * 4.For a vertex v, whose lowval == index, pop off all elements on the
	 * stack upto v itself and print them as one strongly connected component
	 * (cycle).
	 * 
	 * I am going to try and implement this algorithm. I'll post it here if I
	 * succeed and if you want it at that time.
	 * 
	 * A strongly connected component contains at least one cycle, but it might
	 * actually contain a huge number. For an extreme case consider a complete
	 * graph. It is one SCC but contains a number of cycles that is exponential
	 * in the number of nodes.
	 */

}
