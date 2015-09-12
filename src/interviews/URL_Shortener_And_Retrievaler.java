package interviews;

import java.util.*;

public class URL_Shortener_And_Retrievaler {
	/**
	 * Provide a system design for real world URL shortener (solution must scale
	 * to millions of users). Back up your assumptions with real numbers.
	 *
	 * Tiny url is a URL service that users enter a long URL and then the service 
	 * return a shorter and unique url such as "http://tiny.me/5ie0V2". 
	 * The highlight part can be any string with 6 letters containing [0-9, a-z, A-Z]. 
	 * That is, 62^6 ~= 56.8 billions unique strings.
	 * 
	 * @analysis While shortening the URL:
	 * 
	 *           Store the Long URL in a DB table which has an auto-generated
	 *           identity column (increments by 1 each record)
	 * 
	 *           Next write an algorithm to convert the integer ID of this
	 *           database record in to a base 62 number and then represent the
	 *           number in base62: where a-z are mapped to 0-25 A-Z are mapped
	 *           to 26-51 and 0-9 are mapped to 52-61
	 * 
	 *           essentially we want to say that the shortened URL would contain
	 *           a-z; A-Z and/or 0-9 characters only
	 *
	 */
	// Check if URL exists in Table; if so fetch that id from DB and process; 
	// if not then insert URL in DB and process

	static String APPLICABLE_CHARS = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
	static int BASE = 62; // 26 upper case + 26 lower case + 10 numbers
	
	// convert an integer id to a short url
	public String getShortURLFromID(int dbID) {
		
		List<Integer> digits = new ArrayList<Integer>();
		while (dbID > 0) {
			int remainder = dbID % BASE;
			digits.add(remainder);
			dbID = dbID / BASE;
		}
		// convert the numbers to the code
		StringBuilder shortCode = new StringBuilder(digits.size());
		for (int i = digits.size() - 1; i >= 0; i--) {  // we count from the end to the begin
			shortCode.append(APPLICABLE_CHARS.charAt(digits.get(i)));
		}

		return shortCode.toString();
	}

	/**
	 * While retrieving the complete URL from Short URL; we need to decode the
	 * shortURL to get the DB ID out of it.
	 * 
	 * Example: if shortened URL was kkDN
	 * 
	 * k maps to 10 in our APPLICABLE_CHARS String D maps to 29 and N maps to 39
	 * 
	 * then DB ID = 10 X 62^3 + 10 X 62^2 + 29 X 62^1 + 39 X 62^0 = 2383280 +
	 * 38440 + 1798 + 39 = 2423557
	 */
	//change from a 62-base number to decimal
	public static int getDbIdFromShortURL(String shortURL) {
		int dbID = 0;
		for (int i = 0, j = shortURL.length() - 1; i < shortURL.length() && j > -1; i++, j--) {
			char letter = shortURL.charAt(i);
			int digit = APPLICABLE_CHARS.indexOf(letter);
			dbID += digit * Math.pow(BASE, j);
		}
		return dbID;
	}
}
