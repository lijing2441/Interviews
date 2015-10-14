package interviews;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Snapchat_Bipartite {
	// check whether a graph is bipartite using bfs
	private int numberOfVertices;
	private Queue<Integer> queue;

	public static final int NO_COLOR = 0;
	public static final int RED = 1;
	public static final int BLUE = 2;

	public Snapchat_Bipartite(int numberOfVertices) {
		this.numberOfVertices = numberOfVertices;
		queue = new LinkedList<Integer>();
	}

	public boolean isBipartite(int adjacencyMatrix[][], int source) {
		int[] colored = new int[numberOfVertices + 1];
		// 先都initialize to 0
		for (int vertex = 1; vertex <= numberOfVertices; vertex++) {
			colored[vertex] = NO_COLOR;
		}
		// 把source定为red
		colored[source] = RED;
		queue.add(source);

		int element, neighbour;
		while (!queue.isEmpty()) {
			element = queue.remove();
			neighbour = 1;
			while (neighbour <= numberOfVertices) {
				// 如果已经被标为相同颜色，说明不可能形成bipartite
				if (adjacencyMatrix[element][neighbour] == 1 && colored[element] == colored[neighbour]) {
					return false;
				}
				// Color下一层为另一个颜色
				if (adjacencyMatrix[element][neighbour] == 1 && colored[neighbour] == NO_COLOR) {
					colored[neighbour] = (colored[element] == RED) ? BLUE : RED;
					queue.add(neighbour);
				}
				neighbour++;
			}
		}
		return true;
	}

	public static void main(String[] arg) {
		int number_of_nodes, source;
		Scanner scanner = null;
		try {
			System.out.println("Enter the number of nodes in the graph");
			scanner = new Scanner(System.in);
			number_of_nodes = scanner.nextInt();

			int adjacency_matrix[][] = new int[number_of_nodes + 1][number_of_nodes + 1];
			System.out.println("Enter the adjacency matrix");
			for (int i = 1; i <= number_of_nodes; i++) {
				for (int j = 1; j <= number_of_nodes; j++) {
					adjacency_matrix[i][j] = scanner.nextInt();
				}
			}

			for (int i = 1; i <= number_of_nodes; i++) {
				for (int j = 1; j <= number_of_nodes; j++) {
					if (adjacency_matrix[i][j] == 1
							&& adjacency_matrix[j][i] == 0) {
						adjacency_matrix[j][i] = 1;
					}
				}
			}

			System.out.println("Enter the source for the graph");
			source = scanner.nextInt();

			Snapchat_Bipartite bipartiteBfs = new Snapchat_Bipartite(
					number_of_nodes);
			if (bipartiteBfs.isBipartite(adjacency_matrix, source)) {
				System.out.println("The given graph is bipartite");
			} else {
				System.out.println("The given graph is not bipartite");
			}
		} catch (Exception ex) {
			System.out.println("Wrong Input format");
		}
		scanner.close();
	}
}
