
/**
 * purpose- adding student grades
 * Created by pi on 5/9/16.
 */

public class student {

    String StudentName;

    int qz1;

    int qz2;

    int qz3;

    int qz4;

    int qz5;

 

    public student (String name, int q1, int q2, int q3, int q4, int q5)

    {

        StudentName = name;

        qz1 = q1;

        qz2 = q2;

        qz3 = q3;

        qz4 = q4;

        qz5 = q5;

    }

   
    public String getStudentName() {
		return StudentName;
	}


	public void setStudentName(String studentName) {
		StudentName = studentName;
	}

	public int getQuiz(int qno)
	{
		switch (qno)
		{
			case 1:
				return qz1;
			case 2:
				return qz2;
			case 3:
				return qz3;
			case 4:
				return qz4;
			case 5:
				return qz5;
			default:
				return -1;
		}

	}
	public void setQuiz(int qno,int qz)
	{
		switch (qno)
		{
			case 1:
				this.qz1=qz;
				break;
			case 2:
				this.qz2 = qz;
				break;
			case 3:
				this.qz3 = qz;
				break;
			case 4:
				this.qz4 = qz;
				break;
			case 5:
				this.qz5 = qz;
				break;
			default:
				System.out.println("Unknown quiz number");
				break;
		}
	}

	public String toString()

    {

        return   this.StudentName +

                "   " + this.qz1 + "  " + this.qz2 +

                "  " + this.qz3 + "  " + this.qz4 +

                "  " + this.qz5;

         
    }

 

}  
