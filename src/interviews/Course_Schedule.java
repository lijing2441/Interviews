package interviews;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;
import java.util.HashMap;;

public class Course_Schedule {
	/**
	 * There are a total of n courses you have to take, labeled from 0 to n - 1.
	 * 
	 * Some courses may have prerequisites, for example to take course 0 you
	 * have to first take course 1, which is expressed as a pair: [0,1]
	 * 
	 * Given the total number of courses and a list of prerequisite pairs, is it
	 * possible for you to finish all courses?
	 * 
	 * For example:
	 * 
	 * 2, [[1,0]] There are a total of 2 courses to take. To take course 1 you
	 * should have finished course 0. So it is possible.
	 * 
	 * 2, [[1,0],[0,1]] There are a total of 2 courses to take. To take course 1
	 * you should have finished course 0, and to take course 0 you should also
	 * have finished course 1. So it is impossible.
	 */
	// BFS， Topological Sort
	public boolean canFinishBFS(int numCourses, int[][] prerequisites) {
        if(numCourses < 2) return true;
        if(prerequisites == null || prerequisites.length == 0 || prerequisites[0].length == 0) return true;
        int numEdges = prerequisites.length;
        Queue<Integer> q = new LinkedList<Integer>();
        int[] degree = new int[numCourses];
        int count = 0;
        HashMap<Integer, ArrayList<Integer>> edgeMap = new HashMap<Integer, ArrayList<Integer>>();
        for(int i = 0; i < numEdges; i++) {
            int start = prerequisites[i][1];
            int end = prerequisites[i][0];
            degree[end]++;
            ArrayList<Integer> curList = null;
            if(edgeMap.containsKey(start)) {
                curList = edgeMap.get(start);
            } else {
                curList = new ArrayList<Integer>();
            }
            curList.add(end);
            edgeMap.put(start, curList);
        }
        for(int i = 0; i < numCourses; i++) {
            if(degree[i] == 0) {
                q.offer(i);
                count++;
            }
        }
        while(!q.isEmpty()) {
            int curNode = q.poll();
            ArrayList<Integer> curList = edgeMap.get(curNode);
            if(curList == null) continue;
            for(int end : curList) {
                degree[end]--;
                if(degree[end] == 0) {
                    q.offer(end);
                    count++;
                }
            }
        }
        if(count == numCourses) return true;
        else return false;
    }
	
	// DFS, Topological sort, O(|E| + |V|)
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public boolean canFinishDFS(int numCourses, int[][] prerequisites) {
        if(numCourses < 2) return true;
        if(prerequisites == null || prerequisites.length == 0 || prerequisites[0].length == 0) return true;
        ArrayList[] edgeList = new ArrayList[numCourses];
        for(int i = 0; i < numCourses; i++) {
            edgeList[i] = new ArrayList();
        }   
        for(int i = 0; i < prerequisites.length; i++) {
            edgeList[prerequisites[i][0]].add(prerequisites[i][1]);
        }
        
        boolean[] visited = new boolean[numCourses];
        for(int i = 0; i < numCourses; i++) {
            if(!dfs(edgeList, visited, i)) {
                return false;
            }
        }
        return true;
    }
    @SuppressWarnings("rawtypes")
	public boolean dfs(ArrayList[] edgeList, boolean[] visited, int start) {
        if(visited[start]) {
            return false;
        } else {
            visited[start] = true;
        }
        
        for(int i = 0; i < edgeList[start].size(); i++) {
            if(!dfs(edgeList, visited, (int)edgeList[start].get(i))) {
                return false;
            }
        }
        visited[start] = false;
        return true;
    }
    
    /**
     * Course Schedule II: return the order of the schedule
     */
    public int[] findOrderWithResult(int numCourses, int[][] prerequisites) {
        // build the edge list
    	List<List<Integer>> edgeList = new ArrayList<List<Integer>>();
        int[] degree = new int[numCourses];
        for(int i = 0; i < numCourses; i++) {
            edgeList.add(new ArrayList<Integer>());
        }
        for(int i = 0; i < prerequisites.length; i++) {
            int start = prerequisites[i][1], end = prerequisites[i][0];
            edgeList.get(start).add(end);
            degree[end]++;
        }
        List<Integer> res = new ArrayList<Integer>();
        // build the set of edges with no incoming edges 
        Queue<Integer> noIncome = new LinkedList<Integer>();
        for(int i = 0; i < numCourses; i++) {
            if(degree[i] == 0) {
                noIncome.offer(i);
            }
        }
        while(!noIncome.isEmpty()) {
            int n = noIncome.poll();
            res.add(n);
            List<Integer> curList = edgeList.get(n);
            for(int i : curList) {
                degree[i]--;
                if(degree[i] == 0) noIncome.offer(i);
            }
        }
        // check whether all the nodes has been added to the res
        if(res.size() != numCourses) return new int[0];
        int[] resArr = new int[numCourses];
        for(int i = 0; i < numCourses; i++) {
            resArr[i] = res.get(i);
        }   
        return resArr;
    }
    
    
    // Recursive way
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        List<List<Integer>> adj = new ArrayList<>(numCourses);
        for (int i = 0; i < numCourses; i++) adj.add(i, new ArrayList<>());
        for (int i = 0; i < prerequisites.length; i++) adj.get(prerequisites[i][1]).add(prerequisites[i][0]);
        boolean[] visited = new boolean[numCourses];
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < numCourses; i++) {
            if (!topologicalSort(adj, i, stack, visited, new boolean[numCourses])) return new int[0];
        }
        int i = 0;
        int[] result = new int[numCourses];
        while (!stack.isEmpty()) {
            result[i++] = stack.pop();
        }
        return result;
    }
    
    private boolean topologicalSort(List<List<Integer>> adj, int v, Stack<Integer> stack, boolean[] visited, boolean[] isLoop) {
        if (visited[v]) return true;
        if (isLoop[v]) return false;
        isLoop[v] = true;
        for (Integer u : adj.get(v)) {
            if (!topologicalSort(adj, u, stack, visited, isLoop)) return false;
        }
        visited[v] = true;
        stack.push(v);
        return true;
    }
}