package interviews;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.UUID;

public class Implement_Unique_ID_Generation_System {
	// To create a UUID
	// String uniqueID = UUID.randomUUID().toString();
	
	public static void main(String... arguments) {
		try {
			// Initialize SecureRandom
			// This is a lengthy operation, to be done only upon
			// initialization of the application
			SecureRandom prng = SecureRandom.getInstance("SHA1PRNG");

			// generate a random number
			String randomNum = new Integer(prng.nextInt()).toString();

			// get its digest
			MessageDigest sha = MessageDigest.getInstance("SHA-1");
			byte[] result = sha.digest(randomNum.getBytes());

			System.out.println("Random number: " + randomNum);
			System.out.println("Message digest: " + hexEncode(result));
		} catch (NoSuchAlgorithmException ex) {
			System.err.println(ex);
		}
	}

	/**
	 * The byte[] returned by MessageDigest does not have a nice textual
	 * representation, so some form of encoding is usually performed.
	 *
	 * This implementation follows the example of David Flanagan's book
	 * "Java In A Nutshell", and converts a byte array into a String of hex
	 * characters.
	 *
	 * Another popular alternative is to use a "Base64" encoding.
	 */
	static private String hexEncode(byte[] aInput) {
		StringBuilder result = new StringBuilder();
		char[] digits = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f' };
		for (int idx = 0; idx < aInput.length; ++idx) {
			byte b = aInput[idx];
			result.append(digits[(b & 0xf0) >> 4]);
			result.append(digits[b & 0x0f]);
		}
		return result.toString();
	}
	
	/**
	 * A class that provides an alternate implementation of {@link
	 * UUID#fromString(String)} and {@link UUID#toString()}.
	 *
	 * <p> The version in the JDK uses {@link String#split(String)}
	 * which does not compile the regular expression that is used for splitting
	 * the UUID string and results in the allocation of multiple strings in a
	 * string array. We decided to write {@link NessUUID} when we ran into
	 * performance issues with the garbage produced by the JDK class.
	 *
	 */
	private Implement_Unique_ID_Generation_System() {}

    private static final int NUM_ALPHA_DIFF = 'A' - '9' - 1;
    private static final int LOWER_UPPER_DIFF = 'a' - 'A';

    // FROM STRING
    public static UUID fromString(String str) {
        int dashCount = 4;
        int [] dashPos = new int [6];
        dashPos[0] = -1;
        dashPos[5] = str.length();

        for (int i = str.length()-1; i >= 0; i--) {
            if (str.charAt(i) == '-') {
                if (dashCount == 0) {
                    throw new IllegalArgumentException("Invalid UUID string: " + str);
                }
                dashPos[dashCount--] = i;
            }
        }

        if (dashCount > 0) {
            throw new IllegalArgumentException("Invalid UUID string: " + str);
        }

        long mostSigBits = decode(str, dashPos, 0) & 0xffffffffL;
        mostSigBits <<= 16;
        mostSigBits |= (decode(str, dashPos, 1) & 0xffffL);
        mostSigBits <<= 16;
        mostSigBits |= (decode(str,  dashPos, 2) & 0xffffL);

        long leastSigBits = (decode(str,  dashPos, 3) & 0xffffL);
        leastSigBits <<= 48;
        leastSigBits |= (decode(str,  dashPos, 4) & 0xffffffffffffL);

        return new UUID(mostSigBits, leastSigBits);
    }

    private static long decode(final String str, final int [] dashPos, final int field) {
        int start = dashPos[field]+1;
        int end = dashPos[field+1];
        if (start >= end) {
            throw new IllegalArgumentException("Invalid UUID string: " + str);
        }
        long curr = 0;
        for (int i = start; i < end; i++) {
            int x = getNibbleFromChar(str.charAt(i));
            curr <<= 4;
            if (curr < 0) {
                throw new NumberFormatException("long overflow");
            }
            curr |= x;
        }
        return curr;
    }

    static int getNibbleFromChar(final char c)
    {
        int x = c - '0';
        if (x > 9) {
            x -= NUM_ALPHA_DIFF; // difference between '9' and 'A'
            if (x > 15) {
                x -= LOWER_UPPER_DIFF; // difference between 'a' and 'A'
            }
            if (x < 10) {
                throw new IllegalArgumentException(c + " is not a valid character for an UUID string");
            }
        }

        if (x < 0 || x > 15) {
            throw new IllegalArgumentException(c + " is not a valid character for an UUID string");
        }

        return x;
    }

    // TO STRING

    public static String toString(UUID uuid)
    {
        return toString(uuid.getMostSignificantBits(), uuid.getLeastSignificantBits());
    }

    /** Roughly patterned (read: stolen) from java.util.UUID and java.lang.Long. */
    public static String toString(long msb, long lsb)
    {
        char[] uuidChars = new char[36];

        digits(uuidChars, 0, 8, msb >> 32);
        uuidChars[8] = '-';
        digits(uuidChars, 9, 4, msb >> 16);
        uuidChars[13] = '-';
        digits(uuidChars, 14, 4, msb);
        uuidChars[18] = '-';
        digits(uuidChars, 19, 4, lsb >> 48);
        uuidChars[23] = '-';
        digits(uuidChars, 24, 12, lsb);

        return new String(uuidChars);
    }

    private static void digits(char[] dest, int offset, int digits, long val) {
        long hi = 1L << (digits * 4);
        toUnsignedString(dest, offset, digits, hi | (val & (hi - 1)), 4);
    }

    private final static char[] DIGITS = {
        '0' , '1' , '2' , '3' , '4' , '5' ,
        '6' , '7' , '8' , '9' , 'a' , 'b' ,
        'c' , 'd' , 'e' , 'f' , 'g' , 'h' ,
        'i' , 'j' , 'k' , 'l' , 'm' , 'n' ,
        'o' , 'p' , 'q' , 'r' , 's' , 't' ,
        'u' , 'v' , 'w' , 'x' , 'y' , 'z'
    };

    private static void toUnsignedString(char[] dest, int offset, int len, long i, int shift) {
        int charPos = len;
        int radix = 1 << shift;
        long mask = radix - 1;
        do {
            dest[offset + --charPos] = DIGITS[(int)(i & mask)];
            i >>>= shift;
        } while (i != 0 && charPos > 0);
    }
}
