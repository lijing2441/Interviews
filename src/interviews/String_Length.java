package interviews;

public class String_Length {
	public static int res;
	public static int i;
	
	
	public static int length(String s){
		int c = 0;
		try{
			for(int i = 0; i >= 0; i++, c++){
				s.charAt(i);
			}
		}catch(Exception e){
			System.out.println("length is: ");
		}
		return c;
	}
	
	public static void main(String[] args){
        System.out.println("Original String is : ");
        System.out.println("Alive is awesome ");
        res=String_Length.length("Alive is awesome ");
        System.out.println(res);
    }
}
