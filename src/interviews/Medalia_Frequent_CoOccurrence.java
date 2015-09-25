package interviews;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.PriorityQueue;
import java.util.Set;

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
	// 如果给的list，问会不会有重复token，有的话需要先去重复
	public static List<StringPair> frequentCoOccurrence(Set<Token> comments, int N) {
		List<StringPair> res = new ArrayList<StringPair>();
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
//		for (Map.Entry<Integer, Map<Integer, List<String>>> entry: map.entrySet()) {
//			int key = entry.getKey();
//			Map<Integer, List<String>> cur = entry.getValue();
//			for (Map.Entry<Integer, List<String>> e: cur.entrySet()) {
//				int key2 = e.getKey();
//				List<String> list = e.getValue();
//				System.out.print(key + " " + key2 + " ");
//				for (String str: list) {
//					System.out.print(str + " ");
//				}
//				System.out.println();
//			}
//			
//		}
		// traverse to get the co-occurrence
		Map<StringPair, Integer> countMap = new HashMap<StringPair, Integer>();
		
		for (Map<Integer, List<String>> curMap : map.values()) {
			for (List<String> list : curMap.values()) {
				if (list.size() < 2) continue;
				else {
					for (int i = 0; i < list.size(); i++) {
						for (int j = i + 1; j < list.size(); j++) {
							String str1 = list.get(i);
							String str2 = list.get(j);
							int isFirstSmaller = str1.compareTo(str2);
							StringPair curPair;
							if (isFirstSmaller <= 0) {
								curPair = new StringPair(str1, str2);
							} else {
								curPair = new StringPair(str2, str1);
							}
							int curCount = 1;
							if (countMap.containsKey(curPair)) {
								curCount += countMap.get(curPair);
							}
							countMap.put(curPair, curCount);
						}
					}
				}
			}
		}
		PriorityQueue<Map.Entry<StringPair, Integer>> pq = new PriorityQueue<Map.Entry<StringPair, Integer>>(new Comparator<Map.Entry<StringPair, Integer>>() {
            public int compare(Map.Entry<StringPair, Integer> entry1, Map.Entry<StringPair, Integer> entry2) {
                return entry2.getValue() - entry1.getValue();
            }
        });
		for (Map.Entry<StringPair, Integer> entry: countMap.entrySet()) {
			pq.add(entry);
		}
		for (int i = 0; i < countMap.size(); i++) {
			Map.Entry<StringPair, Integer> entry = pq.poll();
			res.add(entry.getKey());
//			System.out.println(entry.getKey().description() + entry.getValue());
		}
		
		return res;
	}
	
	public static void main(String[] args) {
		Token t1 = new Token(0, 0, "food");
		Token t2 = new Token(0, 0, "delicious");
		Token t3 = new Token(0, 1, "great");
		Token t4 = new Token(0, 1, "time");
		Token t5 = new Token(1, 0, "food");
		Token t6 = new Token(1, 0, "delicious");
		Token t7 = new Token(1, 0, "time");
		Token t8 = new Token(1, 1, "great");
		Set<Token> set = new HashSet<Token>();
		set.add(t1);
		set.add(t2);
		set.add(t3);
		set.add(t4);
		set.add(t5);
		set.add(t6);
		set.add(t7);
		set.add(t8);
		List<StringPair> res = frequentCoOccurrence(set,1);
		for (StringPair p: res) {
			System.out.println(p.description());
		}
	}
}
class Token {
	int commentID;
	int sentenceID;
	String word;
	public Token(int n1, int n2, String w) {
		this.commentID = n1;
		this.sentenceID = n2;
		this.word = w;
	}
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
	public String description() {
		return first + " " + second;
	}
}