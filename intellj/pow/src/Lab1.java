import java.util.Scanner;

public class Lab1 {
public static void main(String[] args) {

	System.out.println("Be the lucky person to win a smartphone");
	Scanner in=new Scanner(System.in);
  System.out.println("enter a number");
  int c=in.nextInt();
	System.out.println("hide the smartphone between the numbers 1,2and3 ");
	int a=in.nextInt();
	System.out.println("enter your second number to be the lucky one to get a smartphone ");
	int b=in.nextInt();
	if(a==b)
	{
		System.out.println("true");
	}
		else
		{
			System.out.println("false");
		
	}
	

}
}
