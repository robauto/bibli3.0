import java.util.Scanner;

public class lab1 {
public static void main(String[] args) {
	Scanner in = new Scanner(System.in);
	System.out.println("enter the amount of discs");
		int discs =in.nextInt();
		
		int moves=discs*2+1;
		System.out.println(moves);
}
}
