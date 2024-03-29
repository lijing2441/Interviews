package interviews;

import java.util.ArrayList;

public class Airbnb_CSV_parser {
	/**
	John,Smith,john.smith@gmail.com,Los Angeles,1
	Jane,Roberts,janer@msn.com,"San Francisco, CA",0
	"Alexandra ""Alex""",Menendez,alex.menendez@gmail.com,Miami,1
	"""Alexandra Alex"""
	变成
	John|Smith|john.smith@gmail.com|Los Angeles|1
	Jane|Roberts|janer@msn.com|San Francisco, CA|0
	Alexandra "Alex"|Menendez|alex.menendez@gmail.com|Miami|1
	"Alexandra Alex"
	*/
	public static void main(String[] args) {
		// #1
		ArrayList<String> output = parseCSV("John,Smith,john.smith@gmail.com,Los Angeles,1");
		String strOutput = printStr(output);
		System.out.println(strOutput);
		// #2
		output = parseCSV("Jane,Roberts,janer@msn.com,\"San Francisco, CA\",0");
		strOutput = printStr(output);
		System.out.println(strOutput);
		// #3
		output = parseCSV2("\"Alexandra \"\"Alex\"\"\",Menendez,alex.menendez@gmail.com,Miami,1");
		strOutput = printStr(output);
		System.out.println(strOutput);
	}
	
	// function to parse into each string
	public static ArrayList<String> parseCSV(String str) {
        ArrayList<String> res = new ArrayList<String>();
        boolean inQuote = false;
        StringBuilder buffer = new StringBuilder();
        for(int i = 0; i < str.length(); i++) {
        	if(inQuote) {
        		if(str.charAt(i) == '"') {
        			if(i == str.length() - 1) {
        				res.add(buffer.toString());
        				return res;
        			} else if(str.charAt(i + 1) == '"') { // escape
        				buffer.append('"');
        				i++;
        			} else {
        				res.add(buffer.toString());
        				buffer.setLength(0);
        				inQuote = false;
        				i++;
        			}
        		} else buffer.append(str.charAt(i));
        	} else {
        		if(str.charAt(i) == '"') {
        			inQuote = true;
        		} else if( str.charAt(i) == ',') {
        			res.add(buffer.toString());
        			buffer.setLength(0);
        		} else {
        			buffer.append(str.charAt(i));
        		}
        	}
        }
        if(buffer.length() > 0) res.add(buffer.toString());
        return res;
	}
	
    public static String printStr(ArrayList<String> list) {
        StringBuilder res = new StringBuilder();
        for(int i = 0; i < list.size()-1; i++) {
        	res.append(list.get(i));
        	res.append('|');
        }
        res.append(list.get(list.size()-1));
        return res.toString();
    }
    
    
    public static ArrayList<String> parseCSV2(String input) {
    	ArrayList<String> res = new ArrayList<String>();
    	boolean isQuote = false;
    	StringBuilder sb = new StringBuilder();
    	for (int i = 0; i < input.length(); i++) {
    		if (isQuote) {
    			if (input.charAt(i) == '"') {
    				if (i == input.length() - 1) {
    					res.add(sb.toString());
    					return res;
    				} else if (input.charAt(i + 1) == '"') {
    					sb.append('"');
    					i++;
    				} else {
    					res.add(sb.toString());
    					sb.setLength(0);
    					isQuote = false;
    					i++;
    				}
    			} else {
    				sb.append(input.charAt(i));
    			}
    		} else {
    			if (input.charAt(i) == '"') {
    				isQuote = true;
    			} else if (input.charAt(i) == ',') {
    				res.add(sb.toString());
					sb.setLength(0);
    			} else {
    				sb.append(input.charAt(i));
    			}
    		}
    	}
    	if (sb.length() > 0) res.add(sb.toString());
    	return res;
    }
}