package interviews;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class Route_Between_Two_Nodes_In_Graph {
	public Set<DirectedGraphNode> visited = new HashSet<DirectedGraphNode>();
    public boolean hasRoute(ArrayList<DirectedGraphNode> graph, DirectedGraphNode s, DirectedGraphNode t) {
        if (s == t) return true;
        ArrayList<DirectedGraphNode> neighbors = s.neighbors;
        visited.add(s);
        for (DirectedGraphNode node : neighbors) {
            if (node.label == t.label) return true;
            if (visited.contains(node)) continue;
            if (hasRoute(graph, node, t)) {
                return true;
            }
        }
        return false;
    }
}
class DirectedGraphNode {
	int label;
    ArrayList<DirectedGraphNode> neighbors;
    DirectedGraphNode(int x) {
    	label = x;
        neighbors = new ArrayList<DirectedGraphNode>();
    }
};