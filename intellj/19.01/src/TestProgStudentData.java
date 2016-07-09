/**
 * purpose- assertions andexceptions
 * Created by pi on 5/21/16.
 */
public class TestProgStudentData
{

    public static void main(String args[])
    {
        double [] testscores = new double[0];
        //StudentData s = new StudentData("hello","blah",testscores);
        double [] jdTestScores = {90.00,78.00,95.0,63.0};
        StudentData jd = new StudentData("Jon","doe", jdTestScores);
        double [] lgTestScores = {88.0,90.0,100.0,88.0,90.0,100.0} ;
        StudentData lg = new StudentData("Lindsay","Green",lgTestScores);
        System.out.println(jd);
        System.out.println(lg);
    }
}
