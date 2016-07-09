/**
 * Created by Pi on 5/1/16.
 */
public class FibonacciSeriesTester {

    public static void main(String[] args) {

        FibonacciSeries fs=new FibonacciSeries();
        //Enter the no for which fibonacci no will be calculated
        int y=3;

        //call the operation
        int z=fs.getFibonacci(y);
        System.out.println("Fibonacci number is:"+z);

    }

}
