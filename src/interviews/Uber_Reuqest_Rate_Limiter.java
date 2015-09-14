package interviews;

import java.util.Date;

public class Uber_Reuqest_Rate_Limiter {
	/**
	 * Develop an API Rate-limit Throttling Client
	 * 
	 * 要求写一个api， 请求第三方api， 如果一秒内的请求太多， 自己的api就直接忽略掉。
	 */
	
	public boolean make_request(Request req) {
		if ()
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
class Request {
	Date requestTime;
}