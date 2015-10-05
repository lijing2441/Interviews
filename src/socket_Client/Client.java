package socket_Client;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.Socket;

public class Client {
	public static void main(String[] args) throws Exception {
		Socket s = new Socket("localhost", 7777);
		//System.out.println("Connection established");
		InputStream obj = s.getInputStream();
		BufferedReader br = new BufferedReader(new InputStreamReader(obj));
		
		String str;
		while ((str = br.readLine()) != null) {
			System.out.println("From Server:=" + str);
		}
		br.close();
		s.close();
	}
}
