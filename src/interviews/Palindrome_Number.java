package interviews;

public class Palindrome_Number {
	// question for interviewers: negative number?
	
	public boolean isPalindrome(int x) {
        if(x < 0) return false;
        if(x < 10) return true;
        // get the length of the number
        // the number "help" is the one used to compare whether the current position is palindrome
        int tmp = x / 10;
        int help = 1;
        int len = 1;
        // get the help to the same digit of x, with a 1 at the most significant digit
        // for example, x = 505000, then help = 100000
        while(tmp > 0){
            help *= 10;
            tmp /= 10;
            len++;
        }
        // check half is enough
        len = len / 2;
        int _x = x;
        while(len > 0){
        	// check current position
            if(_x / help != x % 10) return false;
            // get the next digit
            _x = _x % help;
            help = help / 10;
            x = x / 10;
            len--;
        }
        return true;
    }
}
