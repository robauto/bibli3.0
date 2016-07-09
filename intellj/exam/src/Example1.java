/**
 * Created by admin on 6/7/16.
 */
import java.net.Inet4Address;
import java.util.ArrayList;
public class Example1
{
    public static void main(String args[])
    {
        ArrayList<Object> a = new ArrayList<Object>();

        a.add("blah");
        a.add("Yeah");
        String s=(String) a.get(0);
    }





public String doSomething(ArrayList a, int b) {
    int i;
    String s;

    for (i = 0; i < a.size(); i++) {
        s = (String) (a.get(i));
        if (s.length() == b) {
            return s;
        }
    }
    return null;
}
    public int mystery(int n) {
        if (n > 6) {
            return 1 + mystery(n - 1);
        }

        return n % 3;
    }
    public void removeValue(ArrayList<Integer> a, int val) {
        int i;

        for (i = 0; i < a.size(); i++) {
            if (a.get(i) == val) {
                a.remove(i);
            }
        }
        for(i=0;i< a.size();i++)
        {
            System.out.print(a.get(i)+"\t");
        }
    }


}
