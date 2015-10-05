package socket_Client;

import java.io.OutputStream;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
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
