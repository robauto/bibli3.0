/**
 * purpose- update the program with array list
 * Created by pi on 3/30/16.
 */
public class MyMath3 extends Homework3 {

    public MyMath3() {
        super();
    }

    public void createAssignment(int p) {
        setTypeHomework("Math");
        setPagesToRead(p);

    }
    @Override
    public String toString() {
        return getTypeHomework()+ " - " +"You must Read" +" "+ getPages()+" "+"pages";
    }
    public void doReading()
    {
        int nop = getPages();
        nop -= 2;
        setPagesToRead(nop);

    }
}
