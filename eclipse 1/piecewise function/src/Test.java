import java.util.Scanner;

public class Test
{
	public static void main(String[] args)
	{
		//prompt the user 
		System.out.print("Enter a number to be input to the Recursivefunction: ");
		
		//create object
		Test t = new Test();
		
		//get user input from  keyboard for RecursiveFunction
		Scanner in = new Scanner(System.in);
		int number = in.nextInt();
		
		//print out the evaluation
		
	int y=t.recursivemethod(number);
	System.out.println(y);
	}
	
	//RecursiveFunction
	public int recursivemethod(int  x)
	{
		//base case
		if(x <= 10)
		{
			return -7;
		}
		else
		{
			return recursivemethod(x - 4) + 2;
		}
	}
}