
public class MyMath extends Homework {


    
    public MyMath() {
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

    
}
