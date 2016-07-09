/**
 * purpose- learn about abstract class and interface 
 * Created by pi on 3/30/16.
 */
public class Myjava extends Homework {

    public Myjava() {
        super();
    }
    

	public void createAssignment(int p) {
        setTypeHomework("MyJava");
        setPagesToRead(p);

    }
	   @Override
       public String toString() {
           return getTypeHomework()+ " - " +"You must Read" +" "+ getPages()+" "+"pages";
   }

    
}
