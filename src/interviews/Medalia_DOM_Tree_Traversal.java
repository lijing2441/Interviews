package interviews;

import java.io.File;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.PriorityQueue;
import java.util.Queue;

import org.w3c.dom.*;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

public class Medalia_DOM_Tree_Traversal {
	/**
	 * 这道题就是说给你一个DOM树，这个树里面有两种节点，一种节点叫Element，这种节点里面有三种元素：
	=> tag,比如<html>...</html>中的<html>就是tag；
	=> id, 比如<div id="foo">中的foo就是id: 
	=> a list of children，可以是任何子节点； 	
	
	还有一种节点叫Content，比如<body>some content</body>里面的some content就是Content节点
	
	然后让你写个程序来represent这个DOM树，输出的每一行表示DOM树的每一层。每一行都要有tag,id（如果存在）
	和content（如果存在），另外还有个条件就是给你一个white list,如果这个DOM树里面的元素是在white 
	list里面，那个就ignore DOM树里面的这个元素。

	举例：
	<html>
     	<body id="content"> 
           This
           <div id="wrapper1">
              is a
                 <div id="container1">
                       <div id="container2">
                               funny
                       </div>
                 </div> 
                 <div id="container2"> 
                        enjoyable
                 </div>
                 little
           </div>
           good example
           <div id="wrapper4">
           </div>
           <a id="link">
                   Add a link
           </a> 
     	</body>
	</html>

	whitelist = {"wrapper4","a little"}
	所以output就是：
	html
	body content This good example
	div wrapper1 a link Add a link
	 */
	// 思路：创建一个List<String>,每层是一个string，每深入一层，加一个string
	// html为第一层，body第二层，div第三层。。。
	
	// first condition: 有Element节点和Content节点
	public static List<String> treeTraverse(ElementNode root, List<String> whiteList) {
		List<String> res = new ArrayList<String>();
		if (root == null) return res;
		//int depth = -1;
		Queue<DOMNode> q = new LinkedList<DOMNode>();
		Queue<Integer> depth = new LinkedList<Integer>();
		Map<Integer, String> map = new HashMap<Integer, String>(); // depth map
		q.offer(root);
		depth.offer(0);
		while (!q.isEmpty()) {
			DOMNode node = q.poll();
			int curDepth = depth.poll();
			if (node instanceof ElementNode) {
				ElementNode eNode = (ElementNode) node;
				// if whiteList, ignore
				if (whiteList.contains(eNode.id) || whiteList.contains(eNode.tag)) {
					continue;
				}
				String curStr = "";
				if (map.containsKey(curDepth)) {
					curStr = map.get(curDepth);
				}
				//String curStr = res.get(curDepth);
				if (curStr.length() > 0) curStr += " "; 
				curStr += eNode.tag;
				if (eNode.id != null) curStr += (" " + eNode.id);
				map.put(curDepth, curStr);
				// add the children
				if (eNode.children != null) {
					for (DOMNode next: eNode.children) {
						q.offer(next);
						depth.offer(curDepth + 1);
					}
				}
			} else {
				ContentNode cNode = (ContentNode) node;
				// if whiteList, ignore
				/***** "a" "little" might be in different node */
				/***** need to process to connect them before move to the next level */
				if (whiteList.contains(cNode.content)) continue;
				// check whether each of them are true
				boolean ignore = false;
				for (String s : whiteList) {
					if (cNode.content.indexOf(s) != -1) {
						ignore = true;
						break;
					}
				}
				if (ignore) continue;
				String curStr = "";
				if (map.containsKey(curDepth)) {
					curStr = map.get(curDepth);
				}
				if (curStr.length() > 0) curStr += " "; 
				curStr += cNode.content;
				//if (eNode.id != null) curStr += (" " + eNode.id);
				map.put(curDepth, curStr);
			}
		}
		// 去掉任何whitelist里面的东西
//		for (String s: res) {
//			for (String str: whiteList) {
//				if (s.indexOf(str) != -1) {
//					s.replaceAll(str, "");
//				}
//			}
//		}
		PriorityQueue<Map.Entry<Integer, String>> pq = new PriorityQueue<Map.Entry<Integer, String>>(new Comparator<Map.Entry<Integer, String>>() {
            public int compare(Map.Entry<Integer, String> entry1, Map.Entry<Integer, String> entry2) {
                return entry1.getKey() - entry2.getKey();
            }
        });
		for (Map.Entry<Integer, String> entry: map.entrySet()) {
			pq.add(entry);
		}
		for (int i = 0; i < map.size(); i++) {
			res.add(pq.poll().getValue());
		}
		return res;
	}
	
	// driver
	public static void main(String[] args) {
		Medalia_DOM_Tree_Traversal test = new Medalia_DOM_Tree_Traversal();
		ElementNode root = new ElementNode("html");
		List<DOMNode> rootChildren = new ArrayList<DOMNode>();
		root.children = rootChildren;
		ElementNode body = new ElementNode("body", "content");
		root.children.add(body);
		List<DOMNode> bodyChildren = new ArrayList<DOMNode>();
		body.children = bodyChildren;
		
		ContentNode thisInfo = new ContentNode("This");
		root.children.add(thisInfo);
		
		ElementNode wrapper1 = new ElementNode("div", "wrapper1");
		
		body.children.add(wrapper1);
		List<String> white = new ArrayList<String>();
		white.add("wrapper4");
		white.add("a little");
		List<String> res = treeTraverse(root, white);
		for (String s: res) {
			System.out.println(s + " ");
		}
	}
	
	public ArrayList<ArrayList<String>> levelOrder(ElementNode root, List<String> whiteList) {
		ArrayList<ArrayList<String>> res = new ArrayList<ArrayList<String>>();
		//corner case
		if (null == root) return res;
		ArrayList<DOMNode> cur = new ArrayList<DOMNode>();
		cur.add(root);
		List<DOMNode> banned = new ArrayList<DOMNode>();
		while (!cur.isEmpty() && cur.get(0) != null) {
			ArrayList<String> list = new ArrayList<String>();
			ArrayList<DOMNode> next = new ArrayList<DOMNode>();
			// get the next level nodes
			for (DOMNode node : cur) {
				if (node instanceof ElementNode) {
					ElementNode eNode = (ElementNode) node;
					// if whiteList, ignore
					if (whiteList.contains(eNode.id) || whiteList.contains(eNode.tag)) {
						continue;
					}
					
					//String curStr = res.get(curDepth);
					//if (curStr.length() > 0) curStr += " "; 
					list.add(eNode.tag);
					if (eNode.id != null) list.add(eNode.id);
//					map.put(curDepth, curStr);
					// add the children
					if (eNode.children != null) {
						for (DOMNode child: eNode.children) {
							next.add(child);
//							depth.offer(curDepth + 1);
						}
					}
				} else {
					ContentNode cNode = (ContentNode) node;
					// if whiteList, ignore
					/***** something wrong here, "a" "little" might be in different node */
					/***** need to process to connect them before move to the next level */
					if (whiteList.contains(cNode.content)) continue;
					// check whether each of them are true
					boolean ignore = false;
					for (String s : whiteList) {
						if (cNode.content.indexOf(s) != -1) {
							ignore = true;
							break;
						}
					}
					if (ignore) continue; 
					list.add(cNode.content);
				}
			}
			String level = "";
			for (String s : list) {
				level += s + " ";
			}
			for (String s : whiteList) {
				if (level.contains(s)) {
					// content node has something wrong
					for (int i = 0; i < level.length(); i++) {
						if (level.substring(i, i + s.length()).equals(s)) {
							for (DOMNode node: cur) {
								if (node instanceof ContentNode) {
									if (s.contains(((ContentNode) node).content)) {
										cur.remove(node);
										banned.add(node);
									}
								}
							}
						}
					}
				}
			}
			res.add(list); // if reversely, res.add(0, list), or before return, add Collections.reverse(res)
			// replace the cur node list with next level node list
			cur = next;
		}
		return res;
	}

	
	
	// 给的如果是file的话，需要parse
	// Strings to process the file 如果一直是开括号，就加深度，闭括号就减深度
	public List<List<String>> domTreeTraversal(List<String> input) {
		List<List<String>> res = new ArrayList<List<String>>();
		if (input == null || input.size() == 0) return res;
		int depth = -1;
		for (String line: input) {
			line = line.trim(); // no leading and trailing spaces
			if (line.charAt(0) == '<') {
				if(line.charAt(1) != '/') { // 开始
					depth++; // 新的一层
					List<String> newLevel;
					if (res.size() <= depth) {
						newLevel = new ArrayList<String>();
						res.add(newLevel);
					} else {
						newLevel = res.get(depth);
					}
					// add the tag and id
					int j = 0;
					while (j < line.length() && line.charAt(j) != ' ') j++;
					newLevel.add(line.substring(1, j));
					while (j < line.length() && line.charAt(j) != '"') j++;
					int k = j + 1;
					while (k < line.length() && line.charAt(k) != '"') k++;
					if (k > j + 1) newLevel.add(line.substring(j + 1, k));
				} else { // 结束
 					depth--; // 回到上一层
				}
			} else {
				// 在本层加入东西
				List<String> cur = res.get(depth);
				cur.add(line);
			}
		}
		return res;
	}
	
	// 用DOM java内置处理api
	public void xmlDOMTraversal() {
		try{
			String file = "TestData\\test.xml";
			DocumentBuilderFactory dbf =DocumentBuilderFactory.newInstance();
			DocumentBuilder docBuilder = dbf.newDocumentBuilder();
			Document doc = docBuilder.parse(new File(file));
		
			NodeList nodes = doc.getElementsByTagName("person");
			System.out.println("总共有"+nodes.getLength()+"个人。");
		
			for(int i=0;i<nodes.getLength();i++) {
				org.w3c.dom.Node node = nodes.item(i);
				NodeList childNodes = node.getChildNodes();
				System.out.println("person有"+childNodes.getLength()+"个节点。");
			
				for(int j=0;j<childNodes.getLength();j++) {
					org.w3c.dom.Node childNode = childNodes.item(j);
					if(childNode.getNodeType() == org.w3c.dom.Node.ELEMENT_NODE && childNode.getNodeName().equals("name"))
						System.out.println("名字："+childNode.getFirstChild().getNodeValue());
					if(childNode.getNodeType() == org.w3c.dom.Node.ELEMENT_NODE && childNode.getNodeName().equals("age"))
						System.out.println("年龄："+childNode.getFirstChild().getNodeValue());
				}			
				System.out.println();
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	/**
	 * getNodeName()：获取节点的名字，返回值为String类型。
	 * 
	 * getNodeValue()：获取节点的值，如果节点类型为TEXT_NODE，则返回文本值，返回值为String类型。
	 */
	
}
abstract class DOMNode {
	
}
class ElementNode extends DOMNode{
	String tag;
	String id;
	List<DOMNode> children;
	
	public ElementNode(String t) {
		this.tag = t;
		//this.id = "";
		//this.children = new ArrayList<DOMNode>();
	}
	public ElementNode(String t, String i) {
		this.tag = t;
		this.id = i;
	}
}
class ContentNode extends DOMNode{
	String content;
	List<DOMNode> children;
	public ContentNode (String c) {
		this.content = c;
	}
}
