package interviews;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

public class Medalia_Frequent_CoOccurrence {
	/**
	 * 背景就是假设相信类似于Yelp的那种评论，每个评论里面有好几句话，每句话里面有n个关键词，例如：“The food was delicious,
	 * I had a great time”就被parse成(0,0,food),(0,0,delicious),(0,1,great),(0,1,time)
	 * 这些token。然后这道题还定义了一个类叫做CoOccurrence,就是定义为在同一个评论的同一句话中不同的关键词按照字母序组成的pair，
	 * 所以例子中的CoOccurrence就是(delicious, food)和(great, time) 
	 * 
	 * 现在给你一群unsorted, iterable tokens和一个整数N，然后让你输出在这些tokens里面出现频率最高的N个CoOccurrence。 
	 * 
	 * 举例：
	 * (0,0,food), 
	 * (0,0,delicious), 
	 * (0,1,great), 
	 * (0,1,time)， 
	 * (1,0,food)
	 * (1,0,delicious) 
	 * (1,0,time) 
	 * (1,1, great) 
	 * 
	 * output就是(delicious, food)
	 * 这题我用一个HashMap<Integer, HashMap<Integer, ArrayList<String>>>的数据结构
	 * 来分类保存所有的可以拼成CoOccurrence的candidates，然后再用一个set来check哪些CoOccurrence
	 * 出现过，再出现的时候count就加1，然后排序一下就OK了。
	 */
	// key: 评论编号， value：对应句子的map
	// 内部map: key: 评论中第几句话， value:出现过的词
	public static List<String> frequentCoOccurrence(List<Token> comments, int N) {
		List<String> res = new ArrayList<String>();
		if (comments == null || comments.size() == 0) return res;
		Map<Integer, Map<Integer, List<String>>> map = new HashMap<Integer, Map<Integer, List<String>>>();
		// add them to map
		for (Token comment: comments) {
			int commentID = comment.commentID;
			Map<Integer, List<String>> curMap;
			if (map.containsKey(commentID)) {
				curMap = map.get(commentID);
			} else {
				curMap = new HashMap<Integer, List<String>>();
				map.put(commentID, curMap);
			}
			int sentenceID = comment.sentenceID;
			List<String> list;
			if (curMap.containsKey(sentenceID)) {
				list = curMap.get(sentenceID);
			} else {
				list = new ArrayList<String>();
				curMap.put(sentenceID, list);
			}
			list.add(comment.word);
		}
		// traverse to get the co-occurrence
		Map<StringPair, Integer> pairCounts = new HashMap<StringPair, Integer>();
		for (Map<Integer, List<String>> curMap : map.values()) {
			for (List<String> list : curMap.values()) {
				if (list.size() < 2) continue;
				else {
					for (int i = 0; i < list.size(); i++) {
						for (int j = i + 1; j < list.size(); j++) {
							
						}
					}
				}
			}
		}
		
		return res;
	}
}
class Token {
	int commentID;
	int sentenceID;
	String word;
}
class StringPair {
	public String first;
	public String second;
	public StringPair(String f, String s) {
		this.first = f;
		this.second = s;
	}
	public boolean equals(Object o) {
		if (!(o instanceof StringPair)) return false;
		StringPair p = (StringPair) o;
		return p.first.equals(this.first) && p.second.equals(this.second);
	}
	public int hashCode() {
		return (first == null ? 0 : first.hashCode()) ^ (second == null ? 0 : second.hashCode());
	}
}