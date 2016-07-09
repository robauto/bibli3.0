//purpose-Learn the use of polymorphism
//version-3/20/15
//author-pranav
import java.util.ArrayList;
public class TestPoly {
    public static void main(String[] args) {

        TestPoly tp = new TestPoly();
        ArrayList<Circle> al = new ArrayList();
        al.add(new Circle(2,4,3));
        al.add(new Cylinder(10,15,2,3));
        al.add(new Oval(25,10,4,5));
        al.add(new OvalCylinder(40,10,4,6,9));
        for(int i=0;i< al.size(); i++)
        {
            tp.showCenter(al.get(i));
        }
    }

    public void showCenter(Circle c)
    {
        System.out.println("for "+ c+" " + c.center());
    }
}
