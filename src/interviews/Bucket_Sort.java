package interviews;

import java.util.LinkedList;

/**
 * 
 * @author tina
 * 
 *         Given 100 threads, each with content and priority (0-7), arrange them
 *         in a way that they can be obtained in the set priority
 * 
 */
public class Bucket_Sort {
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public LinkedList[] bucketSortThread(Thread[] threads){
		LinkedList[] res = new LinkedList[8];
		for(Thread th: threads){
			int index = th.getPriority();
			LinkedList<Thread> curBucket = null;
			if(res[index] != null){
				curBucket = res[index];
			}else{
				curBucket = new LinkedList();
			}
			curBucket.add(th);
			res[index] = curBucket;
		}
		return res;
	}
}
