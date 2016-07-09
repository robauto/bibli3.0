//purpose- Learn to extend classes 	
//version-3/18/16
//Author-Pranav
public class Test1 {
	public static void main (String []args)
	{
	    Rectangle1 one = new Rectangle1(2, 4);
	    Box1 two = new Box1(4,10,4);
	    
	    System.out.println("For Rectangle1:" );
	    System.out.println("The length is  " + one.getLength() );
	    System.out.println("The width is " + one.getWidth()); 
	    System.out.println("For Box1 : ");
	    System.out.println("The length is  " + two.getLength() );
	    System.out.println("The width is " + two.getWidth()); 
	    
	    
	}
}
