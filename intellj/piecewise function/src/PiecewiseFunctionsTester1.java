/**
 * purpose-program on piecewise functions
 * author-Pranav
 * version-3/21/16
 */

public class PiecewiseFunctionsTester1 {
	public static void main(String[] args)
	{
		int x;
		PiecewiseFunctions1 rMethods = new PiecewiseFunctions1();
		System.out.println("---------------------------------");
		System.out.println("       f(x - 4) + 2    if x >  10");
		System.out.println("f(x) = ");
		System.out.println("       -7              if x <= 10");
		System.out.println("---------------------------------");
		System.out.println();

		x = 25;
		System.out.println("Example 1:  x = " + x);
		System.out.println("f(" + x + ") = " + rMethods.fOf(x));
		System.out.println();

		x = 30;
		System.out.println("Example 2:  x = " + x);
		System.out.println("f(" + x + ") = " + rMethods.fOf1(x));
		System.out.println();

		x = 500;
		System.out.println("Example 3:  x = " + x);
		System.out.println("f(" + x + ") = " + rMethods.fOf2(x));
		System.out.println();

		x = 500;
		System.out.println("Example 4:  x = " + x);
		System.out.println("f(" + x + ") = " + rMethods.fOf3(x));
		System.out.println();
	}
}