import java.util.Scanner;

public class Test2 {
	public static void main(String[] args)
	{
		//prompt the user 
		System.out.print("Enter a number to be input to the Recursivefunction: ");
		
		//create object
		Test2 t2 = new Test2();
		
		//get user input from  keyboard for RecursiveFunction
		Scanner in = new Scanner(System.in);
		int number = in.nextInt();
		
		//print out the evaluation
		
	int y=t2.recursivemethod(number);
	System.out.println(y);
	}
	
	//RecursiveFunction
	public int recursivemethod(int  x)
	{
		//base case
		if(x <= 20)
		{
			return recursivemethod(x*2) - 4;
		}
		else
		{
			return -100;
		}
	}
}
