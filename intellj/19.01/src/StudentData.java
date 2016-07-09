/**
 * purpose- assertions andexceptions
 * Created by pi on 5/21/16.
 */
public class StudentData {
    String firstName, lastName;
    double[] testScores;
    char grade;


    public StudentData(String firstName,String lastName,double[] testScores)
    {
        if(firstName == "" || firstName == null)
        {
            throw  new IllegalArgumentException("first name is empty or null");
        }
        if(lastName == "" || lastName == null)
        {
            throw new IllegalArgumentException("lastName is empty or null");
        }
        this.firstName = firstName;
        this.lastName = lastName;
        this.testScores = testScores;

        this.grade = courseGrades(testScores);
    }

    public String getFirstName()
    {
        return firstName;
    }
    public String getLastName()
    {
        return lastName;
    }
    public char getGrade()
    {
        return grade;
    }
    public double[] getTestScores()
    {
        return testScores;
    }

    public String toString()
    {
        String retString = firstName+ " " + lastName;
        for (int i=0;i<testScores.length;i++)
        {
            retString+= " "+testScores[i];
        }
        retString+= " "+grade;

        return retString;
    }
    public char courseGrades(double[] list)
    {
        if(list.length == 0)
        {
            throw new IllegalArgumentException("Grade list cannot be  empty");
        }
        double avg=0;
        for(int i=0;i<list.length;i++)
        {
            avg += list[i];
        }
        avg /= (double) list.length;

        if(avg > 90 )
        {
            return 'A';
        }
        else
            if(avg >80 && avg <=90)
            {
                return 'B';
            }

        else
         if(avg > 70 && avg <= 80)
         {
             return 'C';
         }
        else
         if(avg>60 && avg <=70)
         {
             return 'D';
         }
        else
         {
             return 'F';
         }
    }

}
