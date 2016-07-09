/**
 * purpose- learn about abstract class and interface 
 * Created by pi on 3/30/16.
 */
public class MyScience extends Homework {
    
    public MyScience() {
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

    
}
