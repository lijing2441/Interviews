package interviews;

public class Validate_UTF8_input {
	/**
	 * After given clearly definition of UTF-8 format. ex: 1-byte: 0b0xxxxxxx 2-bytes:.... 
	 * Asked to write a function to validate whether the input is
	 * valid UTF-8. Input will be string/byte array, output should be yes/no.
	 */
	public static boolean validate(byte[] bytes) {
	    int expectedLen;
	    if      (bytes.length == 0)                     return false;
	    else if ((bytes[0] & 0b10000000) == 0b00000000) expectedLen = 1;
	    else if ((bytes[0] & 0b11100000) == 0b11000000) expectedLen = 2;
	    else if ((bytes[0] & 0b11110000) == 0b11100000) expectedLen = 3;
	    else if ((bytes[0] & 0b11111000) == 0b11110000) expectedLen = 4;
	    else if ((bytes[0] & 0b11111100) == 0b11111000) expectedLen = 5;
	    else if ((bytes[0] & 0b11111110) == 0b11111100) expectedLen = 6;
	    else    return false;

	    if (expectedLen != bytes.length) return false;

	    for (int i = 1; i < bytes.length; i++) {
	        if ((bytes[i] & 0b11000000) != 0b10000000) {
	            return false;
	        }
	    }

	    return true;
	}
	
	/**
	 * Returns the number of UTF-8 characters, or -1 if the array
	 * does not contain a valid UTF-8 string.  Overlong encodings,
	 * null characters, invalid Unicode values, and surrogates are
	 * accepted.
	 */
	public static int charLength(byte[] bytes) {
	    int charCount = 0, expectedLen;

	    for (int i = 0; i < bytes.length; i++) {
	        charCount++;
	        // Lead byte analysis
	        if      ((bytes[i] & 0b10000000) == 0b00000000) continue;
	        else if ((bytes[i] & 0b11100000) == 0b11000000) expectedLen = 2;
	        else if ((bytes[i] & 0b11110000) == 0b11100000) expectedLen = 3;
	        else if ((bytes[i] & 0b11111000) == 0b11110000) expectedLen = 4;
	        else if ((bytes[i] & 0b11111100) == 0b11111000) expectedLen = 5;
	        else if ((bytes[i] & 0b11111110) == 0b11111100) expectedLen = 6;
	        else    return -1;

	        // Count trailing bytes
	        while (--expectedLen > 0) {
	            if (++i >= bytes.length) {
	                return -1;
	            }
	            if ((bytes[i] & 0b11000000) != 0b10000000) {
	                return -1;
	            }
	        }
	    }
	    return charCount;
	}
}
