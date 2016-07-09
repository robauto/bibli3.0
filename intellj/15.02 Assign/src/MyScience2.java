/**
 * purpose- learn about interface 
 * Created by pi on 3/30/16.
 */
public class MyScience2 extends Homework2{

    public MyScience2() {
        super();
    }


    public void createAssignment(int p) {
        setTypeHomework("Science");
        setPagesToRead(p);

    }
    @Override
    public String toString() {
        return getTypeHomework()+ " - " +"You must Read" +" "+ getPages()+" "+"pages";
    }
    public void doReading()
    {
        int nop = getPages();
        nop -= 3;
        setPagesToRead(nop);

    }
}
