import java.util.Scanner;

public class Test1 {
	public static void main(String[] args)
	{
		//prompt the user 
		System.out.print("Enter a number to be input to the Recursivefunction: ");
		
		//create object
		Test1 t = new Test1();
		
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
		if(x <= 25)
		{
			return 20;
		}
		else
		{
			return recursivemethod((x/12)+5) - 3;
		}
	}
}
