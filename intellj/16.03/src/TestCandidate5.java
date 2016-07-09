/**
 * purpose- inserting and editing the standing
 * Created by pi on 3/30/16.
 */
public class TestCandidate5 {
	public static void main(String args[])
	{
		TestCandidate5 tc= new TestCandidate5();
		Candidate2[] election = new Candidate2[10];
		election[0]=new Candidate2("John Smith",5000);
		election[1]=new Candidate2("Mary Miller",4000);
		election[2]=new Candidate2("Michael Duffy",6000);
		election[3]=new Candidate2("Tim Robinson",2500);
		election[4]=new Candidate2("Joe Ashtony",1800);
		election[5]=new Candidate2("Mickey Jones",3000);
		election[6]=new Candidate2("Rebecca Morgan",2000);
		election[7]=new Candidate2("Kathleen Turner",8000);
		election[8]=new Candidate2("Tory Parker", 500);
		election[9]=new Candidate2("Ashton Davis",10000);
		System.out.println("Original Results");
		System.out.println();
		tc.printResults(election);
		tc.insertPosition(election,"Woody Pride ",14000,5);
		System.out.println("Added Woody Pride,14000 votes");
		System.out.println();
		tc.printResults(election);
		tc.insertCandidate(election,"Kathleen Turner","Joy Anderson",1100);
		System.out.println("Added Joy Anderson,1100 votes");
		System.out.println();
		tc.printResults(election);

	}
	public void insertPosition(Candidate2[] election, String name, int votes, int position)
	{
		int length = election.length;
		length++;
		Candidate2[] newElection = new Candidate2[length];
		System.arraycopy(election,0,newElection,0,election.length);


		for(int i=election.length -1;i > position - 1;i--)
		{
			//System.out.println(newElection[i].toString());
			newElection[i+1] = newElection[i];
		}
		//System.exit(1);
		newElection[position].setName(name);
		newElection[position].setNumVotes(votes);
		election = newElection;
	}
	public void insertCandidate(Candidate2[] election, String name1, String name2, int votes)
	{
		int length = election.length;
		length++;
		Candidate2[] newElection = new Candidate2[length];
		System.arraycopy(election,0,newElection,0,election.length);
		int position = -1;
		for(int i =0; i<election.length;i++)
		{
			if (election[i].getName() == name1)
			{
				position = i;
				break;
			}
		}
		if(position == -1)
		{
			return;
		}
		for(int i=election.length-1;i > position - 2;i--)
		{
			newElection[i+1] = newElection[i];
		}
		newElection[position-1].setName(name2);
		newElection[position-1].setNumVotes(votes);
		election = newElection;

	}
	public void replaceName(Candidate2[] election, String name1, String name2)
	{
		for(int i=0;i < election.length;i++)
		{
			if(election[i].getName() == name1)
			{
				election[i].setName(name2);
			}
		}
	}
	public void replaceVotes(Candidate2[] election, String name, int votes)
	{
		for(int i=0; i < election.length;i++)
		{
			if(election[i].getName() == name)
			{
				election[i].setNumVotes(votes);
			}
		}
	}
	public void replaceCandidate(Candidate2[] election, String name, String repName, int repVotes)
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
	public  void printVotes(Candidate2[] election)
	{
		for(int i=0;i<election.length;i++)
		{
			Candidate2 c = election[i];
			System.out.println(c.getName()+"received "+c.getNumVotes()+" votes");
		}
	}
	public  int getTotal(Candidate2[] election)
	{
		int totalCount =0;
		for(int i =0;i < election.length;i++)
		{
			totalCount += election[i].getNumVotes();
		}
		return totalCount;
	}
	public  void printResults(Candidate2[] election)
	{
		int totalVotes = getTotal(election);
		System.out.println("Candidates \t Votes received \t Percentage of total votes");
		for(int i=0;i< election.length;i++)
		{
			Candidate2 c = election[i];
			System.out.println(c.getName()+" \t\t"+ c.getNumVotes()+ "\t\t\t"+ Math.round(c.getNumVotes()/(double)totalVotes*100)+"%");

		}
		System.out.println("Total number of votes in election is" + totalVotes);

	}
}
