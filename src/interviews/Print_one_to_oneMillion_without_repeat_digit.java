package interviews;

public class Print_one_to_oneMillion_without_repeat_digit {
	/**
	 * Print all numbers between 1 and 1 million without repeating digits.
	 * 
	 * @logic since one million might overflow integer, we may need to transfer it to long.
	 * 		   
	 */
	public void noRepeatDigits(int upperBound){
		for(int i = 1; i <= upperBound; i++){
			if(!hasDupDigit(i)){
				System.out.println(i);
			}
		}
	}
	public boolean hasDupDigit(int num){
		boolean[] digits = new boolean[10]; // one million
		while(num != 0){
			int rightmost = num % 10;
			if(digits[rightmost]) return true;
			digits[rightmost] = true;
			num /= 10;
		}
		return false;
	}
}
