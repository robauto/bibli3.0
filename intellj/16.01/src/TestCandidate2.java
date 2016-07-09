import java.util.ArrayList;

/**
 * purpose- update the program with array list
 * Created by pi on 3/30/16.
 */
public class TestCandidate2
{

    public static void main(String args[])
    {

        TestCandidate2 tc = new TestCandidate2();
        ArrayList<Candidate> election = new ArrayList<Candidate>();
        election.add(new Candidate("John Smith",5000));
        election.add(new Candidate("Mary Miller",4000));
        election.add(new Candidate("Michael Duffy",6000));
        election.add(new Candidate("Tim Robinson",2500));
        election.add(new Candidate("Joe Ashtony",1800));
        tc.printVotes(election);
        tc.printResults(election);
    }
    public void printVotes(ArrayList<Candidate> election)
    {
        for(int i=0;i<election.size();i++)
        {
            Candidate c = election.get(i);
            System.out.println(c.getName()+"received "+c.getNumVotes()+" votes");
        }
    }
    public  int getTotal(ArrayList<Candidate> election)
    {
        int totalCount =0;
        for(int i =0;i < election.size();i++)
        {
            totalCount += election.get(i).getNumVotes();
        }
        return totalCount;
    }
    public void printResults(ArrayList<Candidate> election)
    {
        int totalVotes = getTotal(election);
        System.out.println("Candidates \t\t Votes received \t\t Percentage of total votes");
        for(int i=0;i< election.size();i++)
        {
            Candidate c = election.get(i);
            System.out.println(c.getName()+" \t\t"+ c.getNumVotes()+ "\t\t"+ Math.round(c.getNumVotes() /(double)totalVotes*100)+"%");

        }
        System.out.println("Total number of votes in election is" + totalVotes);

    }
}
