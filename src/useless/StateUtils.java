package useless;

import java.util.*;

/**
 * 
 * The following Java code is responsible for creating an HTML "SELECT" list of
 * U.S. states, allowing a user to specify his or her state. This might be used,
 * for instance, on a credit card transaction screen. Please rewrite this code
 * to be "better". Submit your replacement code, and please also submit a few
 * brief comments explaining why you think your code is better than the sample.
 *
 * (For brevity, this sample works for only 5 states. The real version would
 * need to work for all 50 states. But it is fine if your rewrite shows only the
 * 5 states here.)
 * 
 * @author Tina
 *
 */
public class StateUtils {
	/**
	 * I would use enum to store full name and abbr. of states, and possibly
	 * some methods, but not just list them every time like in the given code,
	 * which is not good for extensibility. With the enum, if someone wants to
	 * add new state into the list, he/she can just modify the enum, but not
	 * modify each method.
	 * 
	 * @author Tina
	 */
	public enum State {
		ALABAMA("Alabama", "AL"), ALASKA("Alaska", "AK"), ARIZONA("Arizona",
				"AZ"), ARKANSAS("Arkansas", "AR"), CALIFORNIA("California",
				"CA");
		// more states here

		private final String fullName;
		private final String abbr;

		private State(String fullName, String abbr) {
			this.fullName = fullName;
			this.abbr = abbr;
		}

		// maps are good for storing paired data and the find operation is easy
		private static HashMap<String, String> fullToAbbr = new HashMap<String, String>();
		private static HashMap<String, String> abbrToFull = new HashMap<String, String>();
		// insert the paired data into the maps
		static {
			for (final State state : State.values()) {
				fullToAbbr.put(state.fullName, state.abbr);
				abbrToFull.put(state.abbr, state.fullName);
			}
		}

		// getters
		private String getFullName() {
			return fullName;
		}

		private String getAbbr() {
			return abbr;
		}

		/**
		 * For the methods parseSelectedState(String s) and
		 * displayStateFullName(String abbr), they are just getting abbr from
		 * fullName and getting fullName from abbr, respectively. So we can
		 * implement their work here in enum.
		 */
		private static String getAbbrFromFullName(String full) {
			return fullToAbbr.get(full);
		}

		private static String getFullNameFromAbbr(String abbr) {
			return abbrToFull.get(abbr);
		}
	}

	//
	// Generates an HTML select list that can be used to select a specific U.S.
	// state.
	//
	public static String createStateSelectList() {
		String list = "<select name=\"state\">\n"; // initialize
		for (State s : State.values()) {
			String fullName = s.getFullName();
			list += "<option value=\"" + fullName + "\">" + fullName
					+ "</option>\n";
		}
		list += "</select>\n";
		return list;
	}

	//
	// Parses the state from an HTML form submission, converting it to
	// the two-letter abbreviation. We need to store the two-letter abbreviation
	// in our database.
	//
	public static String parseSelectedState(String s) {
		String abbr = State.getAbbrFromFullName(s);
		if (abbr != null) {
			return abbr;
		}
		return "Couldn't find State with full name: " + s + ".";
	}

	//
	// Displays the full name of the state specified by the two-letter code.
	//
	public static String displayStateFullName(String abbr) {
		String fullName = State.getFullNameFromAbbr(abbr);
		if (fullName != null) {
			return fullName;
		}
		return "Couldn't find State with abbreviation: " + abbr + ".";
	}
}