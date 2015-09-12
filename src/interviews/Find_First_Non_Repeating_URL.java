package interviews;

import java.util.HashMap;

public class Find_First_Non_Repeating_URL {
	/**
	 * Find the first unique URL in a given large list of URLs. 
	 * 
	 * Expecting O(n) and O(n), one pass algorithm.
	 * @analysis using a hashmap, for each url, we insert it into the map as key, the value is
	 * int array of size 2: arr[0] = times, arr[1] = first appearing index
	 */
	public String firstUniqueURL(String[] urls){
		HashMap<String, int[]> map = new HashMap<String, int[]>();
		//traverse the list to get the count
		for(int i = 0; i < urls.length; i++){
			int[] arr = null;
			if(map.containsKey(urls[i])){
				arr = map.get(urls[i]);
				arr[0]++;
				map.put(urls[i], arr);
			}else{
				arr = new int[2];
				arr[0] = 1;
				arr[1] = i;
			}
			map.put(urls[i], arr);
		}
		// here we just need to traverse all the unique urls
		int index = Integer.MAX_VALUE;
		for(int i = 0; i < map.keySet().size(); i++){
			if(map.get(i)[0] == 1 && index > map.get(i)[1]){
				index = map.get(i)[1];
			}
		}
		return urls[index];
	}
}
