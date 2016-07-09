//purpose- to check if phrase is a palindrome
//Version- 3/16/16
//Author- Pranav
package palindrome;

import java.util.Scanner;

public class RecursivePalindromeTester {
	public static void main(String[] args) {
		Scanner in=new Scanner(System.in);
		System.out.println("enter a word or enter quit to quit the program");
		String x=in.nextLine();
		RecursivePalindrome inputstr=new RecursivePalindrome();	
		
		while (!x.equals("quit"))	
		{
			inputstr.ispalin(x);
			System.out.println("enter a word or enter quit to quit the program");
			x=in.nextLine();
		}
		System.out.println("im out of the loop");
	}

}
