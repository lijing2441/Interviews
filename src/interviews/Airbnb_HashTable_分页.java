package interviews;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Airbnb_HashTable_分页 {
	/**
	 * 给出一个list:每个元素是[host_id, list_id, score, city] 按score排好序,进⾏分页。
	 * 每个⻚面不能重复有重复的host-id.使⽤LinkedHashMap,如果有相同id则放在下页,保存⼀个最后能用的⻚面,
	 * 每当一个⻚面满了,更新哈希表
	 */
	public static ArrayList<ArrayList<String>> getPages(String[] source, int k) {
		int firstEmptyPage = 0;
		ArrayList<ArrayList<String>> pages = new ArrayList<ArrayList<String>>();
		// always put the empty page first and then add things into it
		pages.add(new ArrayList<String>());
		// host:id - updated 
		Map<Integer, Integer> map = new HashMap<Integer, Integer>(); // linked hashmap to store pages with same id
		for (String s : source) {
			int id = getId(s); // 找出来host id
			if (map.containsKey(id)) {
				int index = map.get(id) + 1; // 放下一个page
				if (index == pages.size())
					pages.add(new ArrayList<String>());
				pages.get(index).add(s);  // 放下一页
				map.put(id, index);
			} else {
				map.put(id, firstEmptyPage);
				pages.get(firstEmptyPage).add(s);
				if (pages.get(firstEmptyPage).size() == k) {
					firstEmptyPage = update(map, pages, firstEmptyPage); // 给下一页的页码
				}
			}
		}
		return pages;
	}
	// 一页满了之后，把这一页缓存到page，move the empty page pointer
	private static int update(Map<Integer, Integer> map, ArrayList<ArrayList<String>> pages, int i) {
		for (String s : pages.get(i)) {
			int id = getId(s);
			if (map.get(id) == i)
				map.remove(id);
		}
		if (i == pages.size()) pages.add(new ArrayList<String>());
		return i + 1;
	}

	private static int getId(String s) {
		int i = s.indexOf(',');
		return Integer.parseInt(s.substring(0, i));
	}
	
	public static void main(String[] args) {
        String[] source = new String[]{
                         "1,28,300.1,SanFrancisco",
                         "4,5,209.1,SanFrancisco",
                         "20,7,208.1,SanFrancisco",
                         "23,8,207.1,SanFrancisco",
                         "16,10,206.1,Oakland",
                         "1,16,205.1,SanFrancisco",
                         "6,29,204.1,SanFrancisco",
                         "7,20,203.1,SanFrancisco",
                         "8,21,202.1,SanFrancisco",
                         "2,18,201.1,SanFrancisco",
                         "2,30,200.1,SanFrancisco",
                         "15,27,109.1,Oakland",
                         "10,13,108.1,Oakland",
                         "11,26,107.1,Oakland",
                         "12,9,106.1,Oakland",
                         "13,1,105.1,Oakland",
                         "22,17,104.1,Oakland",
                         "1,2,103.1,Oakland",
                         "28,24,102.1,Oakland",
                         "18,14,11.1,SanJose",
                         "6,25,10.1,Oakland",
                         "19,15,9.1,SanJose",
                         "3,19,8.1,SanJose",
                         "3,11,7.1,Oakland",
                         "27,12,6.1,Oakland",
                         "1,3,5.1,Oakland",
                         "25,4,4.1,SanJose",
                         "5,6,3.1,SanJose",
                         "29,22,2.1,SanJose",
        };
        ArrayList<ArrayList<String>> ret = getPages(source, 12);
        int i = 0;
        for(ArrayList<String> list : ret) {
        	System.out.println(i++);
        	for(String str : list)
                System.out.println(str);
        }
    } 
}
