package interviews;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

public class Uber_Topo_Sort {
	/**
	 * Given a > b, b > c, if pass in c > a, return false, if pass in a > c return true;
	 * 
	 * Corner case, 加上d > c，如果用链表，会改变父节点，而且space会增加。
	 * 
	 * 思路：topological sort
	 * 
	 *     給定N表示有N個點，M表示底下有M行關係式，然後一行一行讀取關係式：
	 *     
	 *     如果在第i行發現已經能明確將所有點排出順序(也就是只有一種答案)，輸出Sorted sequence 
	 *     determined after i relations: ...(順序)
	 *     
	 *     如果在第i行發現和之前的關係式有衝突(形成一個環，例如A>B>C>A)，則輸出Inconsistency 
	 *     found after i relations.
	 *     
	 *     如果讀取到最後關係都沒有衝突但也找不到唯一答案，則輸出Sorted sequence cannot be determined.
	 *     
	 * 想法：每次讀取關係式的時候就檢查是否有衝突(形成一個環)，如果沒有再去找topological sort，
	 *      topological sort這function裡面要判斷是否為唯一解，因此只能選一條路徑往下遞迴，
	 *      最後判斷答案優先順序為1.先看有沒有衝突2.看有沒有解，再依題目敘述輸出即可，詳細看底下code。
	 */
	public static void main(String[] args) {
		// input是“0 1”的字符串，中间空格，表示 >=
		String relation1 = "A>B";
		String relation2 = "A>C";
		List<String> relations = new ArrayList<String>();
		relations.add(relation1);
		relations.add(relation2);
		int n = 3;  // 需要sort 的 elements 个数
	    int m = 2;  // 关系式个数
	    int flag = 0;
	    boolean isLegal = false;
	    if (m == 0 || n == 0) System.out.println(isLegal);
	    
	    List<Character> sorted = new ArrayList<Character>();
	    boolean res = topoSort(relations, sorted, n);
	    
//	    Graph g = new Graph(n);
//	    for (int i = 0; i < relations.size(); i++) {
//	    	String s = relations.get(i);
//	    	char num1 = s.charAt(0);
//	    	char num2 = s.charAt(2);
//	    	g.addCurrentNumberOfVertex(num1, num2);
//	    	g.addArcNode(num1, num2); 
//	    	topologicalOrder(g, i + 1);
//	    if (printString.startsWith("*")) {
//            if (printString.length() - 1 == graph.getNumberOfVertex()) {   
//                flag = 2;   
//                break;  
//            }   
//            if (i == m - 1) {   
//                printString = "Sorted sequence cannot be determined.";   
//                flag = 1;   
//                break;   
//            }   
//        }   
//        if (printString.equals("Sorted sequence cannot be determined.")) {   
//            if (graph.hasLoop()) {   
//            printString = "Inconsistency found after " + (i + 1) + " relations.";  
//            flag = 1;   
//                break;   
//            } else if (i == m - 1) {   
//                flag = 1;   
//                break;   
//            }   
//        }   
//        if (printString.startsWith("Inconsistency")) {   
//            flag = 1;   
//            break;  
//        }   
//        recoverGraph(graph);
//    }
//	    }              
//	        int j; 
//	        if (i == m) {   
//	            j = i;   
//	        } else {   
//                j = i + 1;   
//                i++;   
//            }   
//            while (i < m) {
//                scanner.nextLine();   
//                i++;   
//            }   
//            if (flag == 1) {   
//                System.out.println(printString);   
//            } else if (flag == 2) {   
//                String ss = "Sorted sequence determined after " + j + " relations: " + printString.substring(1, printString.length());   
//	            System.out.println(ss + ".");   
//	        }   
//	    }   
	}   
	public static boolean topoSort(List<String> relations, List<Character> result, int numNode) {
		Map<Character, List<Character>> largerThanMap = new HashMap<Character, List<Character>>();
		// build the edge map first
		for (String s: relations) {
			char first = s.charAt(0);
			char rel = s.charAt(1);
			char second = s.charAt(2);
			if (rel == '<') {
				List<Character> l = null;
				if (largerThanMap.containsKey(second)) {
					
				}
			}
		}
		return false;
	}
	
	public static String topologicalOrder(Graph graph, int m) {   
	    int index = graph.judge0InDegree();   
	    String print = "";   
	    while (index >= 0) {   
	        print += graph.deleteNode(index);
	        graph.deleteSide(index);
	        index = graph.judge0InDegree();
	    }   
	    if (print.length() < graph.getCurrentNumberOfVertex()) {   
	    	String s = null;   
	        if (index == -2) {   
	            s = "Sorted sequence cannot be determined.";   
	        } else if (index == -1) {   
	            s = "Inconsistency found after " + m + " relations.";   
	        }   
	        return s;   
	    } else {   
	        String s = "*";
	        return s + print;   
        }   
    }   
	  
	public static void recoverGraph(Graph graph) {   
	    ArrayList< Vertex> al = graph.getVertexes();   
	    for (Vertex v : al) {   
            if (v.isVisited()) {   
                v.setVisited(false);   
                v.setExisted(true);   
                LinkedList< Vertex> lk = v.getArcNode();   
                if (lk != null) {   
                    int in = (lk.getFirst()).getInDegree();   
                    for (Vertex vv : lk) {   
                        graph.getVertexes().get(vv.getData() - 'A').addInDegree();   
                    }   
                }   
            }   
        }   
    }
	
	private class Vertex {   
		  
	    private boolean existed;   
	    private boolean visited;   
	    private int inDegree;   
	    private char data;   
	    private LinkedList< Vertex> arcNode;   
	  
	    public boolean isExisted() {   
	        return existed;   
	    }   
	  
	    public void setExisted(boolean existed) {   
	        this.existed = existed;   
	    }   
	  
	    public boolean isVisited() {   
	        return visited;   
	    }   
	  
	    public void setVisited(boolean visited) {   
	        this.visited = visited;   
	    }   
	  
	    public Vertex(int inDegree, char data, LinkedList<Vertex> arcNode, boolean visited, boolean existed) {   
	        this.inDegree = inDegree;   
	        this.data = data;   
	        this.arcNode = arcNode;   
	        this.visited = visited;   
	        this.existed = existed;   
	    }   
	  
	    public LinkedList<Vertex> getArcNode() {   
	        return arcNode;   
	    }   
	  
	    @SuppressWarnings("unused")
		public void setArcNode(LinkedList<Vertex> arcNode) {   
	        this.arcNode = arcNode;   
	    }   
	  
	    public char getData() {   
	        return data;   
	    }   
	  
	    @SuppressWarnings("unused")
		public void setData(char data) {   
	        this.data = data;   
	    }   
	  
	    public int getInDegree() {   
	        return inDegree;   
	    }   
	  
	    @SuppressWarnings("unused")
		public void setInDegree(int inDegree) {   
	        this.inDegree = inDegree;   
	    }   
	  
	    @SuppressWarnings("unused")
		public void addInDegree() {   
	        this.inDegree++;   
	    }   
	  
	    @SuppressWarnings("unused")
		public void reduceInDegree() {   
	        this.inDegree--;   
	    }   
	}   
	  
	class Graph {   
	  
	    private ArrayList<Vertex> vertexes;   
	    private int numberOfVertex;   
	    private int currentNumberOfVertex;   
	  
	    public Graph(int n) {   
	        this.numberOfVertex = n;   
	        this.vertexes = new ArrayList<Vertex>(n);   
	        this.currentNumberOfVertex = 0;   
	        init();   
	    }   
	  
	    public int getCurrentNumberOfVertex() {   
	        return currentNumberOfVertex;   
	    }   
	  
	    public void addCurrentNumberOfVertex(char a, char b) {   
	        if (!this.vertexes.get(a - 'A').isExisted()) {   
	            this.currentNumberOfVertex++;   
	            this.vertexes.get(a - 'A').setExisted(true);   
	        }   
	        if (!this.vertexes.get(b - 'A').isExisted()) {   
	            this.currentNumberOfVertex++;   
	            this.vertexes.get(b - 'A').setExisted(true);   
	        }   
	    }   
	  
	    public int getNumberOfVertex() {   
	        return numberOfVertex;   
	    }   
	  
	    @SuppressWarnings("unused")
		public void setNumberOfVertex(int numberOfVertex) {   
	        this.numberOfVertex = numberOfVertex;   
	    }   
	  
	    public ArrayList<Vertex> getVertexes() {   
	        return vertexes;   
	    }   
	  
	    @SuppressWarnings("unused")
		public void setVertexes(ArrayList<Vertex> vertexes) {   
	        this.vertexes = vertexes;   
	    }   
	  
	    void init() {   
	        for (int i = 0; i < this.numberOfVertex; i++) {   
	            char c = (char) ('A' + i);   
	            Vertex v = new Vertex(0, c, null, false, false);   
	            vertexes.add(v);   
	        }   
	    }   
	  
	    public void addArcNode(char end, char head) {   
	        boolean isAdd = true;   
	        LinkedList< Vertex> lk = this.vertexes.get(end - 'A').getArcNode();   
	        if (lk != null) {   
	            for (Vertex v : lk) {   
	                if (v.getData() == head) {   
	                    isAdd = false;   
	                }   
	            }   
	        }   
	  
	        if (isAdd) {   
	            this.vertexes.get(head - 'A').addInDegree();
	            Vertex v = new Vertex(this.vertexes.get(head - 'A').getInDegree(),   
	                    head, null, false, true);  
	            LinkedList<Vertex> firstArcNode = this.vertexes.get(end - 'A').getArcNode();   
	            if (firstArcNode == null) {   
	                firstArcNode = new LinkedList<Vertex>();   
	            }   
	            firstArcNode.addLast(v);   
	            this.vertexes.get(end - 'A').setArcNode(firstArcNode);   
	        }   
	    }   
	  
	    public int judge0InDegree() {   
	        int flag = 0;   
	        int index = 0;   
	        for (int i = 0; i < this.numberOfVertex; i++) {   
	            if (this.vertexes.get(i).isExisted() == true  
	                    && this.vertexes.get(i).isVisited() == false  
	                    && this.vertexes.get(i).getInDegree() == 0) {   
	                flag++;   
	                index = i;   
	            }   
	        }   
	        switch (flag) {   
	            case 0:   
	                return -1;
	            case 1:   
	                return index;
	            default:   
	                return -2;
	        }   
	    }   
	  
	    public int getNext0InDegree() {   
	        boolean hasVertex = false;
	        int i;   
	        for (i = 0; i < this.numberOfVertex; i++) {   
	            if (this.vertexes.get(i).isExisted() == true) {   
	                hasVertex = true;   
	                if (this.vertexes.get(i).isVisited() == false  
	                        && this.vertexes.get(i).getInDegree() == 0) {   
	                    return i;  
	                }   
	            }   
	        }   
	        if (hasVertex && this.numberOfVertex == i) {   
	            return -1;  
	        } else {   
	            return -2;
	        }   
	    }   
	  
	    public boolean hasLoop() {   
	        int ind;   
	        int i = this.getCurrentNumberOfVertex();   
	        while (i > 0) {   
	            ind = this.getNext0InDegree();   
	            if (ind == -1) {   
	                return true;   
	            }   
	            if (ind == -2) {   
	                return false;   
	            }   
	            this.deleteNode(ind);   
	            this.deleteSide(ind);   
	            i--;   
	        }   
	        return false;   
	    }   
	  
	    public void deleteSide(int index) {   
	        LinkedList< Vertex> lk = this.vertexes.get(index).getArcNode();   
	        if (lk != null) {   
	            for (Vertex arcV : lk) {   
	                this.vertexes.get(arcV.getData() - 'A').reduceInDegree();   
	            }   
	        }   
	    }   
	  
	    public char deleteNode(int index) {   
	        char c = 0;   
	        this.vertexes.get(index).setExisted(false); 
	        this.vertexes.get(index).setVisited(true);
	        c = this.vertexes.get(index).getData();   
	        return c;   
	    }   
	}
}
