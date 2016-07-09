/**
 * Write a description of class TextMessageV1 here.
 * 
 * @author (Pranav) 
 * @version (2/8/16)
 */
public class TextMessageV2 {
	public static void main(String[] args){  
	     System.out.println("original message:");
	     
	    String hello="my name is pranav";
	    System.out.print("indexOf:");
	    int x=hello.indexOf('p');
	    System.out.println("indexOf p"+ " " + x);
	    System.out.println("substring:");
	    String y=hello.substring(3,13);
	    String f=hello.substring(2);
	    System.out.println("substring with two parameters:" + " " + y);
	    System.out.println("substring with one parameter:" +" " + f);
	    System.out.println("replaceall:");
	    String h=hello.replaceAll(hello, "my name is Pi");
	    System.out.println("replace all method:" + " "+ h);	    
}
}