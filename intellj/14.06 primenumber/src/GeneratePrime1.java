/**
 * purpose- print prime numbers up to user limit
 * Created by pi on 3/19/16.
 */
public class GeneratePrime1 {

    private  int limit;
    void setLimit(int limit)
    {
        this.limit = limit;
    }
     void genPrime()
    {
        //logic
        int primeCount =0;
        System.out.println("prime numbers within the limit "+ limit +" are:");
        for(int i=2;i<limit;i++)
        {

            int count=0;
            for(int j=2;j<=i;j++)
            {
                if (i%j == 0)
                {
                    count++;
                }
            }
            if(count == 1)
            {
                System.out.println(i);
                primeCount++;
            }
        }
        System.out.println("Total number of prime numbers with in limit "+limit+" is "+primeCount);
    }
}
