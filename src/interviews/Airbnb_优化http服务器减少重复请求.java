package interviews;
//import static spark.Spark;

public class Airbnb_优化http服务器减少重复请求 {
	/**
	 * Java Spark web framework: request.GET, request.POST，用gson, RESTful, spark
	 * 
	 * hint: 用cache存结果，有的就不发送请求了
	 * 
	 * 发送一个旅客id获取到他之前去过的历史房子的ids，然后对ids来取title，问怎么在这个过程中减少服务器请求数量
	 * ex: 用一个id查询找到三个房子id，这样总共本来需要4个request，怎么减少？
	 * - 如果三个id中有两个是一样的，就只需要再查两次，这样就只需要总共三次。
	 * - 能不能换成两个请求？第一个请求id，得到id list；第二个请求把id list全都送过去，在服务器做查询
	 * - 能不能换成一个请求？第一个请求把用户id发过去，然后服务器把所有东西都搞好，再传回来，但是这样就不flexible
	 * 					 服务器要改!
	 */
	
}
