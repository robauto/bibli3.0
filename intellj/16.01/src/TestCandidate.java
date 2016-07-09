import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * purpose- update the program with array list
 * Created by pi on 3/30/16.
 */
public class TestCandidate
{
    public static void main(String args[])
    {
        TestCandidate tc= new TestCandidate();
        Candidate[] election = new Candidate[5];
        election[0] =new Candidate("John Smith",5000);
         election[1]=new Candidate("Mary Miller",4000);
         election[2]=new Candidate("Michael Duffy",6000);
        election[3] =new Candidate("Tim Robinson",2500);
        election[4] =new Candidate("Joe Ashtony",1800);
        tc.printVotes(election);
        tc.printResults(election);
    }
    public  void printVotes(Candidate[] election)
    {
        for(int i=0;i<election.length;i++)
        {
            Candidate c = election[i];
            System.out.println(c.getName()+"received "+c.getNumVotes()+" votes");
        }
    }
    public  int getTotal(Candidate[] election)
    {
        int totalCount =0;
        for(int i =0;i < election.length;i++)
        {
            totalCount += election[i].getNumVotes();
        }
        return totalCount;
    }
    public  void printResults(Candidate[] election)
    {
        int totalVotes = getTotal(election);
        System.out.println("Candidates \t\t    Votes received \t\t    Percentage of total votes");
        for(int i=0;i< election.length;i++)
        {
            Candidate c = election[i];
            System.out.println(c.getName()+" \t\t"+ c.getNumVotes()+ "\t\t"+ Math.round(c.getNumVotes()/(double)totalVotes*100)+"%");

        }
        System.out.println("Total number of votes in election is" + totalVotes);

    }
}
