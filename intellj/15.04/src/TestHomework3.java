/**
 * purpose- update the program with array list
 * Created by pi on 3/30/16.
 */
import java.util.ArrayList;
public class TestHomework3 {
    public static void main(String[] args) {
        // Calls the constructor of the MyMath class, which in turn
        // invokes the constructor of its superclass, the 'Homework' class
        ArrayList<Homework3>  arrayList = new ArrayList<Homework3>();
        MyMath3 one = new MyMath3();

        // Invokes the toString of the MyMath class. Since it does not have one,
        // The toString method of its superclass (Homework) is called.
        one.createAssignment(4);
        arrayList.add(one);


        MyScience3 ms = new MyScience3();

        // Invokes the toString of the MyMath class. Since it does not have one,
        // The toString method of its superclass (Homework) is called.
        ms.createAssignment(6);
        arrayList.add(ms);

        MyEnglish3 me = new MyEnglish3();

        // Invokes the toString of the MyMath class. Since it does not have one,
        // The toString method of its superclass (Homework) is called.
        me.createAssignment(4);
        arrayList.add(me);


        Myjava3 mj = new Myjava3();

        // Invokes the toString of the MyMath class. Since it does not have one,
        // The toString method of its superclass (Homework) is called.
        mj.createAssignment(5);
        arrayList.add(mj);
        for(int i=0; i<arrayList.size();i++)
        {

            System.out.println(arrayList.get(i));

        }
        int result = arrayList.get(0).compareTo(arrayList.get(2));
        if(result > 0)
        {
            System.out.println("the homework for math has more number of pages than homework of english");
        }
        else
        if(result < 0)
        {
            System.out.println("the homework for math has less number of pages than homework of english");
        }
        else
        {
            System.out.println("the homework for math and english are same number of pages");
        }

    }

}
