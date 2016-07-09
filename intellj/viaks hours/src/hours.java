import java.util.Scanner;

public class hours {
public static void main(String[] args) {
	
	Scanner in=new Scanner(System.in);
	System.out.println("enter total hours ");
	double totalhours=in.nextDouble();
	
    double totalpay=totalhours*300;
    System.out.println("total pay="+totalpay);
}
}
