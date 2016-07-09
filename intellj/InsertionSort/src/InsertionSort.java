/**
 * Created by admin on 6/4/16.
 */
public class InsertionSort
{


    public static void main(String args[])
    {
        int[] array = {11,17,30,8,20,25};
        int [] dest = new int[6];
        for (int i = 0; i < array.length; i++) {
            int next = array[i];
            int insertindex = 0;
            int k = i;
            while (k > 0 && insertindex == 0) {
                if (next > dest[k-1]) {
                    insertindex = k;
                }
                else {
                    dest[k] = dest[k - 1];
                }
                k--;
            }

            dest[insertindex] = next;
        }


        for(int i=0;i<dest.length;i++)
        {
            System.out.println(dest[i]);
        }
    }
}
