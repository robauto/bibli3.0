/**
 * purpose- adding student grades
 * Created by pi on 5/9/16.
 */
public class TestStudent {
	 public static void main(String [] args)
	    {
	        student[] myClass = new student[5];
	        myClass[0] = new student("Mark Kindy", 70, 80, 90, 100, 90);
	        myClass[1] = new student("Max Girald", 80, 85, 90, 85, 80);
	        myClass[2] = new student("Jean Smith", 50, 79, 89, 99,100);
	        myClass[3] = new student("Betty Farm", 85, 80, 85, 88, 89);
	        myClass[4] = new student("Dilbert Gamma", 70, 70, 90, 70, 80);
	 		TestStudent ts = new TestStudent();
			System.out.print("Starting Gradebook");
			ts.printBook(myClass);
			System.out.print("Changing Betty's name to Betty Boop");
			ts.replaceName(myClass,"Betty Farm","Betty Boop");
			ts.printBook(myClass);
			System.out.println("Changing Jean's quiz 1 score to 80");
			ts.replaceQuiz(myClass, "Jean Smith",1,80);
			ts.printBook(myClass);
			System.out.println("Replacing Dilbert with Mike Kappa, 80,80,80,90,90");
			ts.replaceStudent(myClass,"Dilbert Gamma","Mike Kappa",80,80,80,90,90);
			ts.printBook(myClass);
			System.out.println("Inserting Lilly mu before Betty");
			 myClass = ts.insertStudent(myClass,"Betty Boop","Lily Mu",85,95,70,0,100);
			ts.printBook(myClass);
			System.out.println("Deleting Max Gered");
			myClass = ts.deleteStudent(myClass,"Max Girald");
			ts.printBook(myClass);
	        
		}
	 public void printBook(student[] myClass)
	 {
		 System.out.println("Student   Q1   Q2   Q3   Q4    Q5");
		 System.out.println("------------------------------------");
		 for(int i=0;i < myClass.length;i++)
		 {
			 System.out.println(myClass[i].toString());
		 }
	 }
	 public void replaceName(student[] myclass, String name1,String name2)
	 {
		 for(int i=0; i < myclass.length; i++)
		 {
			 String Sname = myclass[i].getStudentName();
			 if(Sname == name1)
			 {
				 myclass[i].setStudentName(name2);
				 break;
			 }
		 }
	 }
	 public  void replaceQuiz(student[] myclass, String name, int qno,int qvalue)
	 {
		 for(int i=0; i < myclass.length; i++)
		 {
			 String Sname = myclass[i].getStudentName();
			 if(Sname == name)
			 {
				 myclass[i].setQuiz(qno,qvalue);
				 break;
			 }
		 }
	 }
	 public void replaceStudent(student[] myclass, String prevStudent,String curStudent, int qz1,int qz2,int qz3,int qz4,int qz5)
	 {
		 student s = new student(curStudent,qz1,qz2,qz3,qz4,qz5);
		 for(int i=0; i < myclass.length; i++)
		 {
			 String Sname = myclass[i].getStudentName();
			 if(Sname == prevStudent)
			 {
				 myclass[i] = s;
				 break;
			 }
		 }
	 }

	 public student[] insertStudent(student[] myclass, String prevStudent,String curStudent, int qz1,int qz2,int qz3,int qz4,int qz5)
	 {
		 int newLength = myclass.length + 1;
		 int position = -1;
		 student [] newMyClass = new  student[newLength];
		 student s = new student(curStudent,qz1,qz2,qz3,qz4,qz5);
		 for(int i=0; i < myclass.length; i++)
		 {
			 String Sname = myclass[i].getStudentName();
			 if(Sname == prevStudent)
			 {
				 position = i;
				 break;
			 }
		 }
		 System.arraycopy(myclass,0,newMyClass,0,myclass.length);
		 for(int i = myclass.length; i > position; i--)
		 {
			 newMyClass[i] = newMyClass[i-1];
		 }
		 newMyClass[position] = s;
		 return newMyClass;

	 }

	public student[] deleteStudent(student[] myclass,String prevStudent)
	{
		int newLength = myclass.length - 1;
		int position = -1;
		student[] newMyClass = new student[newLength];
		for(int i=0; i < myclass.length; i++)
		{
			String Sname = myclass[i].getStudentName();
			if(Sname == prevStudent)
			{
				position = i;
				break;
			}
		}
		for(int i=position;i<myclass.length-1;i++)
		{
			myclass[i] = myclass[i+1];
		}
		System.arraycopy(myclass,0,newMyClass,0,newLength);
		//myclass = newMyClass;
		return newMyClass;

	}


}