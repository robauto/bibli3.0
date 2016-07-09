/**
 * Created by admin on 5/11/16.
 */
public class TestCandidate3 {
    public static void main(String args[])
    {
        TestCandidate3 tc= new TestCandidate3();
        Candidate1[] election = new Candidate1[10];
        election[0]=new Candidate1("John Smith",5000);
        election[1]=new Candidate1("Mary Miller",4000);
        election[2]=new Candidate1("Michael Duffy",6000);
        election[3]=new Candidate1("Tim Robinson",2500);
        election[4]=new Candidate1("Joe Ashtony",1800);
        election[5]=new Candidate1("Mickey Jones",3000);
        election[6]=new Candidate1("Rebecca Morgan",2000);
        election[7]=new Candidate1("Kathleen Turner",8000);
        election[8]=new Candidate1("Tory Parker", 500);
        election[9]=new Candidate1("Ashton Davis",10000);
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
    public void replaceName(Candidate1[] election, String name1, String name2)
    {
        for(int i=0;i < election.length;i++)
        {
            if(election[i].getName() == name1)
            {
                election[i].setName(name2);
            }
        }
    }
    public void replaceVotes(Candidate1[] election, String name, int votes)
    {
        for(int i=0; i < election.length;i++)
        {
            if(election[i].getName() == name)
            {
                election[i].setNumVotes(votes);
            }
        }
    }
    public void replaceCandidate(Candidate1[] election, String name, String repName, int repVotes)
    {
        for(int i=0; i < election.length; i++)
        {
            if(election[i].getName() == name)
            {
                election[i].setName(repName);
                election[i].setNumVotes(repVotes);
            }
        }
    }
    public  void printVotes(Candidate1[] election)
    {
        for(int i=0;i<election.length;i++)
        {
            Candidate1 c = election[i];
            System.out.println(c.getName()+"received "+c.getNumVotes()+" votes");
        }
    }
    public  int getTotal(Candidate1[] election)
    {
        int totalCount =0;
        for(int i =0;i < election.length;i++)
        {
            totalCount += election[i].getNumVotes();
        }
        return totalCount;
    }
    public  void printResults(Candidate1[] election)
    {
        int totalVotes = getTotal(election);
        System.out.println("Candidates \t\t Votes received \t\t Percentage of total votes");
        for(int i=0;i< election.length;i++)
        {
            Candidate1 c = election[i];
            System.out.println(c.getName()+" \t\t"+ c.getNumVotes()+ "\t\t"+ Math.round(c.getNumVotes()/(double)totalVotes*100)+"%");

        }
        System.out.println("Total number of votes in election is" + totalVotes);

    }
}
