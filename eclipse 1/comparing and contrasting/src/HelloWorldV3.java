
public class HelloWorldV3 {
	//default constructor
	HelloWorldV3()
	{
	}
	//print two lines of text
	public void printTwoLines( )
	{
	System.out.println("Hello, Virtual World!");
	System.out.println("It is a great day for programming.");
	}
	//main method
	public static void main(String [] args)
	{
	HelloWorldV3 hello = new HelloWorldV3( );
	hello.printTwoLines();
	}
}
