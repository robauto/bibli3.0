package febonacy;

import java.util.Scanner;

import javax.swing.plaf.synth.SynthScrollBarUI;

public class FibonaccyNumber {

	public static void main(String[] args)
	{
		//prompt
		System.out.print("Enter a number to be input to the piecewise function to calculate fibanacci number: ");
		
		//create object
		FibonaccyNumber t = new FibonaccyNumber();

		//get user input
		Scanner in = new Scanner(System.in);
	    int number = in.nextInt();

		//print out the evaluation
		System.out.println(t.fib(number));
	}

	//piecewise function
	public int fib(int n)
	{
		
		//Error case
		 if(n<0)
		 {
			 return -1;
		 
		 }
		//base case
		 else	if(n ==0)
		{
			return 0;
		}
		 else if(n ==1)
		{
			return 1;
		}
		else
		{
			//if(n>1)recursive call
			return fib(n-1)+fib(n-2);
		}
	}
}
