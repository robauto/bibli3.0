/**
 * purpose- learn about abstract class and interface 
 * Created by pi on 3/30/16.
 */
public class MyEnglish extends Homework {

    public MyEnglish() {
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

    
}
