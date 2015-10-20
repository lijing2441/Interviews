package interviews;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

public class Clone_Graph {
	// O(n), O(n), BFS
	// we need to prevent create duplicates
	public UndirectedGraphNode cloneGraph(UndirectedGraphNode node) {
		if (node == null) return null;
		// use the value as key 
		// use the node as value
		HashMap<Integer, UndirectedGraphNode> map = new HashMap<Integer, UndirectedGraphNode>();
		Queue<UndirectedGraphNode> q = new LinkedList<UndirectedGraphNode>();
		q.add(node);
		UndirectedGraphNode copy = new UndirectedGraphNode(node.label);
		map.put(copy.label, copy);
		while (!q.isEmpty()) {
			UndirectedGraphNode curNode = q.poll();
			UndirectedGraphNode curCopy = map.get(curNode.label);
			Iterator<UndirectedGraphNode> it = curNode.neighbors.iterator();
			while (it.hasNext()) {
				UndirectedGraphNode tmp = it.next();
				if (!map.containsKey(tmp.label)) {
					UndirectedGraphNode newNode = new UndirectedGraphNode(tmp.label);
					map.put(tmp.label, newNode);
					curCopy.neighbors.add(newNode);
					q.add(tmp);  // 之前没有被加入Queue中，放进去
				} else {
					curCopy.neighbors.add(map.get(tmp.label));
				}
			}
		}
		return copy;
	}
	
	// DFS 方法
	public UndirectedGraphNode cloneGraphDFS(UndirectedGraphNode node) {
        if(node==null) return null;
        Map<UndirectedGraphNode, UndirectedGraphNode>  map = new HashMap<>();
        clone(node, map);
        return map.get(node);
    }

    private void clone(UndirectedGraphNode node, Map<UndirectedGraphNode, UndirectedGraphNode> map) {
        if(map.containsKey(node)) return;
        map.put(node, new UndirectedGraphNode(node.label));
        for(UndirectedGraphNode t : node.neighbors) {
            clone(t, map);
            map.get(node).neighbors.add(map.get(t));
        }
    }
}
class UndirectedGraphNode {
    int label;
    ArrayList<UndirectedGraphNode> neighbors;
    UndirectedGraphNode(int x) { label = x; neighbors = new ArrayList<UndirectedGraphNode>(); }
}
