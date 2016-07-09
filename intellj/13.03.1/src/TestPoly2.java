/**
 * Created by pi on 3/23/16.
 */
import java.util.ArrayList;
public class TestPoly2
{

    TestPoly2 tp = new TestPoly2();
    ArrayList<Circle2> al = new ArrayList();
    al.add(new Circle2(2,4,3));
    al.add(new Cylinder2(10,15,2,3));
    al.add(new Oval2(25,10,4,5));
    //al.add(new OvalCylinder2(40,10,4,6,9));
    for(int i=0;i< al.size(); i++)
    {
        tp.showCenter(al.get(i));
    }


    public void showCenter(Circle2 c)
    {
        System.out.println("for "+ c+" " + c.center());
    }
}
