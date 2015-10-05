package interviews;

import java.io.*;
import java.net.*;

public class Airbnb_写Socket_Client {
	// send request to a car
	// 可以发送一个加速度设置来让车加速，可以从车那边get一个速度
	// 怎样才可以用minimal的通信数量来达到requested speed
	public static void main(String[] args) throws Exception {
		Socket s = new Socket("localhost", 7777);
		// input from server, get speed
		InputStream obj = s.getInputStream();
		BufferedReader br = new BufferedReader(new InputStreamReader(obj));
		
		// output to stream, send acceleration
		PrintStream ps = new PrintStream(s.getOutputStream());
		ps.println();
		
		// 从server那边读speed
		String str; 
		while ((str = br.readLine()) != null) {
			System.out.println("From Server:=" + str);
		}
		br.close();
		s.close();
	}
}
// server 方向的代码
class Server {
	public static void main(String[] args) throws Exception {
		ServerSocket ss = new ServerSocket(7777);
		Socket s = ss.accept();
		System.out.println("Connection established");
		
		OutputStream obj = s.getOutputStream();
		PrintStream ps = new PrintStream(obj);
		String str = "Hello Client";
		ps.println(str);
		ps.println("Bye"); // server 那边print
		ps.close();
		ss.close();
		s.close();
	}
}