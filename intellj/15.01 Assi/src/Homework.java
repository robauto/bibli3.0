/**
 * purpose- learn about abstract class and interface 
 * Created by pi on 3/30/16.
 */
public abstract class Homework {


    private int pagesToRead;
    private String typeHomework;

 //N0-arg Constructor
    public Homework() {
        pagesToRead = 0;
        typeHomework = "none";
 
    }

    public abstract void createAssignment(int p);

    public int getPages() {
        return pagesToRead;
    }

    public void setPagesToRead(int p) {
        pagesToRead = p;
    }

    public String getTypeHomework() {
        return typeHomework;
    }

    public void setTypeHomework(String hw) {
        typeHomework = hw;
    }
     
}
