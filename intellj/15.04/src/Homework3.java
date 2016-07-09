/**
 * purpose- update the program with array list
 * Created by pi on 3/30/16.
 */
public abstract class Homework3 implements Processing1,Comparable<Homework3>
{

    private int pagesToRead;
    private String typeHomework;
    //N0-arg Constructor
    public Homework3()
    {
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
    public int getPagesRead()
    {
        return pagesToRead;
    }

    public String getTypeHomework() {
        return typeHomework;
    }

    public void setTypeHomework(String hw) {
        typeHomework = hw;
    }

	public int compareTo(Homework3 obj)
    {
        if(this.getPagesRead() < obj.getPagesRead())
            return -1;
        else if(this.getPagesRead() == obj.getPagesRead())
            return 0;
        else
            return 1;
    }
}