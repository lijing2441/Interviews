package interviews;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Facebook_输出多叉树每层node数量 {
	// bfs
	public static void printNodeNumInEachLevel(MultiwayTreeNode root) {
		if (root == null) System.out.println("Empty Tree");
		Queue<MultiwayTreeNode> q = new LinkedList<MultiwayTreeNode>();
		q.offer(root);
		//int level = 0;
		while (!q.isEmpty()) {
			int size = q.size();
			for (int i = 0; i < size; i++) {
				MultiwayTreeNode node = q.poll();
				System.out.print(node.val + " ");
				if (!node.children.isEmpty()) {
					for (MultiwayTreeNode child: node.children) {
						q.offer(child);
					}
				}
			}
			System.out.println();
		}
	}
	public static void main(String[] args) {
		MultiwayTreeNode root = new MultiwayTreeNode(1);
		MultiwayTreeNode c1 = new MultiwayTreeNode(2);
		MultiwayTreeNode c2 = new MultiwayTreeNode(3);
		MultiwayTreeNode c3 = new MultiwayTreeNode(4);
		root.children.add(c1);
		root.children.add(c2);
		root.children.add(c3);
		MultiwayTreeNode c4 = new MultiwayTreeNode(5);
		MultiwayTreeNode c5 = new MultiwayTreeNode(6);
		MultiwayTreeNode c6 = new MultiwayTreeNode(7);
		MultiwayTreeNode c7 = new MultiwayTreeNode(8);
		MultiwayTreeNode c8 = new MultiwayTreeNode(9);
		MultiwayTreeNode c9 = new MultiwayTreeNode(10);
		c1.children.add(c4);
		c1.children.add(c5);
		c1.children.add(c6);
		c2.children.add(c7);
		c3.children.add(c8);
		c3.children.add(c9);
		printNodeNumInEachLevel(root);
	}
}
class MultiwayTreeNode {
	int val;
	List<MultiwayTreeNode> children;
	public MultiwayTreeNode(int v) {
		this.val = v;
		this.children = new ArrayList<MultiwayTreeNode>();
	}
}