/**
 * Created by admin on 6/4/16.
 */
public class BinarySearch
{


    public static void main(String args[])
    {
        int a[] = new int[300];
        for(int i=0;i<300;i++)
        {
            a[i] = i+1;
        }

        int x=1;
        int lower = 0;
        int upper = 299;
        int mid = (upper + lower)/2;
        int count =0 ;

        while (a[mid] != x)
        {
            if(a[mid] > x)
            {
                upper = mid;
            }
            else
            if(a[mid] < x)
            {
                lower = mid;
            }
            mid = (lower+upper)/2;
            count++;
        }
        System.out.println(count);
    }
}
