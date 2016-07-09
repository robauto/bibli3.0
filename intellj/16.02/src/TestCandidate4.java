import java.util.ArrayList;

/**
 * purpose- update the last program and add replace method
 * Created by pi on 3/30/16.
 */
public class TestCandidate4
{

    public static void main(String args[])
    {
        TestCandidate4 tc= new TestCandidate4();
        ArrayList<Candidate1> election = new ArrayList<Candidate1>();
        election.add(new Candidate1("John Smith",5000));
        election.add(new Candidate1("Mary Miller",4000));
        election.add(new Candidate1("Michael Duffy",6000));
        election.add(new Candidate1("Tim Robinson",2500));
        election.add(new Candidate1("Joe Ashtony",1800));
        election.add(new Candidate1("Mickey Jones",3000));
        election.add(new Candidate1("Rebecca Morgan",2000));
        election.add(new Candidate1("Kathleen Turner",8000));
        election.add(new Candidate1("Tory Parker", 500));
        election.add(new Candidate1("Ashton Davis",10000));
        System.out.println("Original Results");
        tc.printResults(election);
        System.out.println("Changing Michael Duffy to John Elmos\n\n");
        tc.replaceName( election,"Michael Duffy", "John Elmos");
        tc.printResults(election);
        System.out.println("\n\nChanging Mickey Jones votes to 2500\n\n");
        tc.replaceVotes(election,"Mickey Jones",2500);
        tc.printResults(election);
        System.out.println("\n\nReplacing Kathleen Turner with John Kennedy\n\n");
        tc.replaceCandidate(election,"Kathleen Turner","John Kennedy", 8500);
        tc.printResults(election);
    }
    public void replaceName(ArrayList<Candidate1> election, String name1, String name2)
    {
        for(int i=0;i < election.size();i++)
        {
            if(election.get(i).getName() == name1)
            {
                election.get(i).setName(name2);
            }
        }
    }
    public void replaceVotes(ArrayList<Candidate1> election, String name, int votes)
    {
        for(int i=0; i < election.size();i++)
        {
            if(election.get(i).getName() == name)
            {
                election.get(i).setNumVotes(votes);
            }
        }
    }
    public void replaceCandidate(ArrayList<Candidate1> election, String name, String repName, int repVotes)
    {
        for(int i=0; i < election.size(); i++)
        {
            if(election.get(i).getName() == name)
            {
                election.get(i).setName(repName);
                election.get(i).setNumVotes(repVotes);
            }
        }
    }
    public void printVotes(ArrayList<Candidate1> election)
    {
        for(int i=0;i<election.size();i++)
        {
            Candidate1 c = election.get(i);
            System.out.println(c.getName()+"received "+c.getNumVotes()+" votes");
        }
    }
    public  int getTotal(ArrayList<Candidate1> election)
    {
        int totalCount =0;
        for(int i =0;i < election.size();i++)
        {
            totalCount += election.get(i).getNumVotes();
        }
        return totalCount;
    }
    public void printResults(ArrayList<Candidate1> election)
    {
        int totalVotes = getTotal(election);
        System.out.println("Candidates \t\t Votes received \t\t Percentage of total votes");
        for(int i=0;i< election.size();i++)
        {
            Candidate1 c = election.get(i);
            System.out.println(c.getName()+" \t\t"+ c.getNumVotes()+ "\t\t"+ Math.round(c.getNumVotes()/(double)totalVotes*100)+"%");

        }
        System.out.println("Total number of votes in election is" + totalVotes);

    }
}
