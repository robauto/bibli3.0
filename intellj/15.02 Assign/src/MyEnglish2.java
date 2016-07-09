/**
 * purpose- learn about interface 
 * Created by pi on 3/30/16.
 */
public class MyEnglish2 extends Homework2 {

    public MyEnglish2() {
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
