package interviews;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

public class Uber_Reuqest_Rate_Limiter {
	/**
	 * Develop an API Rate-limit Throttling Client
	 * 
	 * 要求写一个api， 请求第三方api， 如果一秒内的请求太多， 自己的api就直接忽略掉。
	 * 
	 * Example, at most 5 requests/second
	 */
	// final one, circular array, 节省空间
	int mPtr = 0;
	int[] mRequestArray = new int[5];
	public boolean acquireCircularArray() {
		int currentTime = getCurrentSecond();
		if(currentTime - mRequestArray[mPtr] > 1) {
			mRequestArray[mPtr] = currentTime;
			mPtr = (mPtr + 1) % 5;
			return true;
		} else {
			return false;
		}
	}
	
	// how to limit 5/s for any second, such as 0.9s -> 1.9s
	int preFifthSecond; // 往前数5个的时间
	List<Integer> mRequestList = new ArrayList<Integer>();
	
	public boolean make_request_Queue() {
		int currentSecond = getCurrentSecond();
		preFifthSecond = mRequestList.get(mRequestList.size()-5);
		if (currentSecond - preFifthSecond > 1) {
			mRequestList.add(currentSecond); 
			return true;
		} else {
			return false;
		}
	}
	
	// one-bucket 算法
	int mPreSecond;
	int mCountPointer;
	public boolean make_request_one_bucket() {
		int nowSecond = getCurrentSecond();
		if (nowSecond != mPreSecond) {
			mCountPointer = 0;
			mPreSecond = nowSecond;
		}
		if (mCountPointer >= 5) {
			return false;
		} else {
			mCountPointer++;
			return true;
		}
	}
	// 第三种，如果必须用Database
	Database database = new Database();
	public boolean acquireDB() {
		int now = getCurrentSecond();
		Integer count = database.db.get(now);
		if (count != null && count >= 5) {
			return false;
		} else {
			// database.increase(s,1);
			// database.expire(s, 1);
			return true;
		}
	}
	
	// 第二种，用一个counter array去做
	int[] mCounter; // 大小为一天的秒数：86400
	public boolean acquireStillNaive() {
		int now = getCurrentSecond();
		if (mCounter[now] >= 5) return false;
		else {
			mCounter[now]++;
			return true;
		}
	}
	
	// 超过0.2s间隔才让执行
	int mLastTime;
	public boolean acquireNaive() {
		int now = getCurrentSecond();
		if (now - mLastTime >= 0.2) {
			mLastTime = now;
			return true;
		} else {
			return false;
		}
	}
	
	/// return the current second helper
	public int getCurrentSecond() {
		return -1;
	}
	/**
	 * Python code:
	 * 
	def __init__(self):
        self.client = GoogleMapsClient()
        self.time = time.time()
        self.request_times = 0

    def make_request(self):
        if (time.time() - self.time) > 1:
            self.time = time.time()
            self.request_times = 0
        else:
            self.request_times += 1
        if self.request_times>=10: 
            time.sleep(1)      		# 不接受request

        return self.client.make_request()
	 */
}
class Database {
	Map<Integer, Integer> db = new HashMap<Integer, Integer>();
}