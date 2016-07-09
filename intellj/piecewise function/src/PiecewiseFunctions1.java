/**
 * purpose-program on piecewise functions
 * author-Pranav
 * version-3/21/16
 */

import java.util.Scanner;

public class PiecewiseFunctions1 {
    PiecewiseFunctions1()          //default constructor
    {
    }

    public int fOf(int x) {
        if (x <= 10)                        //the base case
        {
            System.out.println(x + " <= 10, therefore ... f(" + x + ") = -7");
            return -7;
        }
        else
        {
            System.out.println(x + " > 10, therefore ... f(" + x + ") = f(" + x + " - 4) + 2 = f(" + (x - 4) + ") + 2");
            return fOf(x - 4) + 2;
        }
    }

    public int fOf1(int x) {
        if (x <= 25)                        //the base case
        {
            System.out.println(x + " <= 25, therefore ... f(" + x + ") = 20");
            return 20;
        }
        else
        {
            System.out.println(x + " > 25, therefore ... f(" + x + ") = f(" + x / 12 + " +5) -3 = f(" + (x / 12 + 5) + ") -3");
            return fOf1(x / 12 + 5) - 3;
        }
    }

    public int fOf2(int x) {
        if (x <= 25)                        //the base case
        {
            System.out.println(x + " <= 25, therefore ... f(" + x + ") = 20");
            return 20;
        }
        else
        {
            System.out.println(x + " > 25, therefore ... f(" + x + ") = f(" + x / 12 + " +5) -3 = f(" + (x / 12 + 5) + ") -3");
            return fOf2(x / 12 + 5) - 3;
        }
    }

    public int fOf3(int x) {
        if (x <= 20)                        //the base case
        {
            System.out.println(x + " <= 20, therefore ... f(" + x + ") = f(" + x + " *2) -4 = f(" + (x * 2) + ") -4");
            return fOf3(x * 2) - 4;

        }
        else
        {
            System.out.println(x + " > 20, therefore ... f(" + x + ") = -100");
            return -100;
        }
    }
}