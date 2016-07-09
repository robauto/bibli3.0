/**
 * purpose- update the program with array list
 * Created by pi on 3/30/16.
 */
public class MyScience3 extends Homework3{

    public MyScience3()
    {
        super();
    }


    public void createAssignment(int p) {
        setTypeHomework("Science");
        setPagesToRead(p);

    }
    @Override
    public String toString()
    {
        return getTypeHomework()+ " - " +"You must Read" +" "+ getPages()+" "+"pages";
    }
    public void doReading()
    {
        int nop = getPages();
        nop -= 3;
        setPagesToRead(nop);

    }
}
