package interviews;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;

public class Find_the_Connected_Component_In_the_Undirected_Graph {
	/**
     * @param nodes a array of Undirected graph node
     * @return a connected set of a Undirected graph
     */
	// O(n) => 对每个节点只操作一次
    public List<List<Integer>> connectedSet(ArrayList<UndirectedGraphNode> nodes) {
        List<List<Integer>> res = new ArrayList<List<Integer>>();
        //int res = 0;
        Set<UndirectedGraphNode> visited = new HashSet<UndirectedGraphNode>();
        for(UndirectedGraphNode node: nodes) {
            if(!visited.contains(node)) {
                List<Integer> cur = addAllNeighbors(node, visited, nodes);
                Collections.sort(cur);
                res.add(cur);
            }
        }
        return res;
    }
    // bfs 去加所有的neighbor
    public List<Integer> addAllNeighbors(UndirectedGraphNode node, Set<UndirectedGraphNode> set, ArrayList<UndirectedGraphNode> nodes) {
        List<Integer> res = new ArrayList<Integer>();
        //res.add(node);
        Queue<UndirectedGraphNode> q = new LinkedList<UndirectedGraphNode>();
        q.offer(node);
        while(!q.isEmpty()) {
            UndirectedGraphNode cur = q.poll();
            if(set.add(cur)) {
                res.add(cur.label);
                for(UndirectedGraphNode neighbor : cur.neighbors) {
                    q.offer(neighbor);
                }
            }
        }
        return res;
    }
}
