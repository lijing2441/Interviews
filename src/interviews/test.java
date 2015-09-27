package interviews;

import java.util.ArrayList;
import java.util.List;

public class test {
	public static String alphabet = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
	public static int BASE = 62;
	public static String encode(int dbID) {
		List<Integer> list = new ArrayList<Integer>();
		while (dbID > 0) {
			int remain = dbID % BASE;
			list.add(remain);
			dbID /= BASE;
		}
		StringBuilder sb = new StringBuilder();
		for (int i = list.size() - 1; i >= 0; i--) {
			sb.append(alphabet.charAt(list.get(i)));
		}
		return sb.toString();
	}
	public static int decode(String url) {
		//List<Integer> list = new ArrayList<Integer>();
		int j = url.length() - 1;
		int res = 0;
		for (int i = 0; i < url.length(); i++) {
			int index = alphabet.indexOf(url.charAt(i));
			res += (Math.pow(BASE, j) * index);
			j--;
		}
		return res;
	}
	
	public static void main(String[] args) {
		int dbID = 2423557;
		String res = encode(dbID);
		System.out.println(res);
		int num = decode(res);
		System.out.println(num);
	}
}