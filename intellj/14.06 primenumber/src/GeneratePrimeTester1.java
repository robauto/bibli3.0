import java.util.Scanner;

/**
 * purpose- print prime numbers up to user limit
 * Created by pi on 3/19/16.
 */
public class GeneratePrimeTester1
{
    public static void main(String[]args)
    {
        Scanner in=new Scanner(System.in);
        System.out.println("Enter the max prime limit");
        int PrimeLimit=in.nextInt();
        GeneratePrime1 gp = new GeneratePrime1();
        gp.setLimit(PrimeLimit);
        gp.genPrime();
    }
}
