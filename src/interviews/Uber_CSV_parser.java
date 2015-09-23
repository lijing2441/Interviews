package interviews;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Uber_CSV_parser {
	@SuppressWarnings("unused")
	public static void main(String[] args) {
		Uber_CSV_parser obj = new Uber_CSV_parser();
		List<String[]> res = obj.run();
	}

	public List<String[]> run() {
		String csvFile = "/Users/mkyong/Downloads/GeoIPCountryWhois.csv";
		BufferedReader br = null;
		String line = "";
		String cvsSpliter = ",";
		List<String[]> res = new ArrayList<String[]>();
		try {
			br = new BufferedReader(new FileReader(csvFile));
			while ((line = br.readLine()) != null) {

				// use comma as separator
				String[] parsed = line.split(cvsSpliter);
				res.add(parsed);
//				System.out.println("Country [code= " + country[4] + " , name=" + country[5] + "]");

			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		System.out.println("Done");
		return res;
	}
}
