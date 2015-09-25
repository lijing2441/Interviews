package interviews;

import java.util.Scanner;

public class Medalia_OA_ENNG1 {
	static String format(DOM dom, String[] whiteList) {
        // Your code here
        List<String> res = new ArrayList<String>();
        if (dom == null || dom.root == null) return "";
        Element root = dom.root;
        Queue<Element> q = new LinkedList<Element>();
        Queue<Integer> depthQ = new LinkedList<Integer>();
        Map<Integer, String> map = new HashMap<Integer, String>();
        q.offer(root);
        depthQ.offer(0);
        while (!q.isEmpty()) {
            Element node = q.poll();
            int nodeDepth = depthQ.poll();
            boolean ignore = false;
                
            // parse the content
            String content = "";
            if (node.children != null) {
                for (Node child : eNode.children) {
                    if (child instanceof Content) {
                        Content cChild = (Content) child;
                        content += (cChild.content + " ");
                    }
                }
            }
            // check the whitelist
            for (String white: whiteList) {
                    if (node.tag.contains(white)) {
                        ignore = true;
                        break;
                    }
                    if (node.id != null && node.id.contains(white)) {
                        ignore = true;
                        break;
                    }
                    if (content.contains(white)) {
                        ignore = true;
                        break;
                    }
            }
            if (ignore) continue;
            else {
                    String curLevel = "";
                    if (map.containsKey(nodeDepth)) {
                        curLevel = map.get(nodeDepth);
                    }
                    if (curLevel.length() > 0) curLevel += " ";
                    curLevel += node.tag;
                    if (node.id != null) curLevel += (" " + node.id);
                    if (content.length() > 0) curLevel += (" " + content);
                    map.put(nodeDepth, curLevel);
                    if (node.children != null) {
                        for (Node next : node.children) {
                            if (next instanceof Element) {
                                Element nextE = (Element) next;
                                q.offer(nextE);
                                depthQ.offer(nodeDepth + 1);
                            }
                        }
                    }
            }
        }
        PriorityQueue<Map.Entry<Integer, String>> pq = new PriorityQueue<Map.Entry<Integer, String>>(new Comparator<Map.Entry<Integer, String>>() {
           public int compare(Map.Entry<Integer, String> entry1, Map.Entry<Integer, String> entry2) {
               return entry1.getKey() - entry2.getKey();
           }
        });
        for (Map.Entry<Integer, String> entry: map.entrySet()) {
            pq.offer(entry);
        }
        for (int i = 0; i < map.size(); i++) {
            res.add(pq.poll().getValue());
        }
        String toReturn = "";
        for (String levelStr : res) {
            toReturn += (toReturn + "\n");
        }
        return toReturn;
    }
	
	
	
	static List<CoOccurrence> findFrequentCoOccurrences(int n, Iterable<Token> tokens) {
        List<CoOccurrence> res = new ArrayList<CoOccurrence>();
        if (comments == null || tokens. == 0) return res;
		
        
        
		Map<Integer, Map<Integer, List<String>>> map = new HashMap<Integer, Map<Integer, List<String>>>();
		// add them to map
		for (Token comment: tokens) {
			int commentID = comment.commentId;
			Map<Integer, List<String>> curMap;
			if (map.containsKey(commentID)) {
				curMap = map.get(commentID);
			} else {
				curMap = new HashMap<Integer, List<String>>();
				map.put(commentID, curMap);
			}
			int sentenceID = comment.sentenceIndex;
			List<String> list;
			if (curMap.containsKey(sentenceID)) {
				list = curMap.get(sentenceID);
			} else {
				list = new ArrayList<String>();
				curMap.put(sentenceID, list);
			}
			list.add(comment.word);
		}
        Set<CoOccurrence> set = new HashSet<CoOccurrence>();
        for (Map<Integer, List<String>> curMap: map.values()) {
            for (List<String> list: curMap.values()) {
                if (list.size() < 2) continue;
                else {
                    for (int i = 0; i < list.contains(); i++) {
                        for (int j = i + 1; j < list.size(); j++) {
                            String str1 = list.get(i);
                            String str2 = list.get(j);
                            String curWord1;
                            String curWord2;
                            int isFirstSmaller = str1.compareTo(str2);
                            if (isFirstSmaller <= 0) {
                                curWord1 = str1;
                                curWord2 = str2;
                            } else {
                                curWord1 = str2;
                                curWord2 = str1;
                            }
                            CoOccurrence curPair;
                            for (CoOccurrence o : set) {
                                if (o.word1.equals(curWord1) && o.word2.equals(curWord2)) {
                                    curPair = o;
                                    o.count++;
                                }
                            }
                            if (curPair == null) {
                                curPair = new CoOccurrence(curWord1, curWord2, 1);
                                set.add(curPair);
                            }
                        }
                    }
                }
            }
        }
        for (CoOccurrence cur: set) {
            res.add(cur);
        }
        Collections.sort(res);
        List<CoOccurrence> toReturn = new ArrayList<CoOccurrence>();
        for (int i = res.size() - 1; i >= 0; i--) {
            if (n > 0) {
                toReturn.add(res.get(i));
                n--;
            }
        }
        return toReturn;
    }
}
