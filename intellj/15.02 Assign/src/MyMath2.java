/**
 * purpose- learn about interface 
 * Created by pi on 3/30/16.
 */
public class MyMath2 extends Homework2 {

    public MyMath2() {
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
