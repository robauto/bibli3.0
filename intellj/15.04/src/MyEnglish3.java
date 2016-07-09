/**
 * purpose- update the program with array list
 * Created by pi on 3/30/16.
 */
public class MyEnglish3 extends Homework3 {

    public MyEnglish3()
    {
        super();
    }



    public void createAssignment(int p) {
        setTypeHomework("MyEnglish");
        setPagesToRead(p);

    }
    @Override
    public String toString() {
        return getTypeHomework()+ " - " +"You must Read" +" "+ getPages()+" "+"pages";
    }
    public void doReading()
    {
        int nop = getPages();
        nop -= 1;
        setPagesToRead(nop);

    }
}
