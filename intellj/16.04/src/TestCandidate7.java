/**
 * purpose- cresting insertion methods
 * Created by pi on 5/12/16.
 */
public class TestCandidate7 {
	public static void main(String args[])
	{
		TestCandidate7 tc= new TestCandidate7();
		Candidate3[] election = new Candidate3[10];
		election[0]=new Candidate3("John Smith",5000);
		election[1]=new Candidate3("Mary Miller",4000);
		election[2]=new Candidate3("Michael Duffy",6000);
		election[3]=new Candidate3("Tim Robinson",2500);
		election[4]=new Candidate3("Joe Ashtony",1800);
		election[5]=new Candidate3("Mickey Jones",3000);
		election[6]=new Candidate3("Rebecca Morgan",2000);
		election[7]=new Candidate3("Kathleen Turner",8000);
		election[8]=new Candidate3("Tory Parker", 500);
		election[9]=new Candidate3("Ashton Davis",10000);
		System.out.println("Original Results");
		System.out.println();
		tc.printResults(election);
		System.out.println();
		System.out.println("Delete location 6");
		System.out.println();
		election = tc.deleteByLoc(election,6);
		tc.printResults(election);
		System.out.println();
		System.out.println("Deleted kathleen Turner");
		System.out.println();
		election = tc.deleteByName(election,"Kathleen Turner");
		tc.printResults(election);

	}
	public Candidate3[] deleteByLoc(Candidate3[] election, int position)
	{
		int length = election.length;
		Candidate3[] newElection = new Candidate3[length-1];
		for (int i=0;i<position;i++)
		{
				newElection[i] = election[i];

		}
		for(int i=position+1;i<election.length;i++)
		{
			newElection[i-1] = election[i];
		}
		return newElection;

	}
	public Candidate3[] deleteByName(Candidate3[] election, String  name)
	{

		int length = election.length;
		length--;
		Candidate3[] newElection = new Candidate3[length];
		int position = -1;
		for(int i =0; i<election.length;i++)
		{
			if (election[i].getName() == name)
			{
				position = i;
				break;
			}
		}
		if(position == -1)
		{
			System.out.println("person not found");
			System.exit(1);
		}
		return deleteByLoc(election,position);

	}


	public void insertPosition(Candidate3[] election, String name, int votes, int position)
	{
		int length = election.length;
		length++;
		Candidate3[] newElection = new Candidate3[length];
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
	public void insertCandidate(Candidate3[] election, String name1, String name2, int votes)
	{
		int length = election.length;
		length++;
		Candidate3[] newElection = new Candidate3[length];
		System.arraycopy(election,0,newElection,0,election.length);
		int position = -1;
		for(int i =0; i<election.length;i++)
		{
			try {

				if (election[i].getName() == name1) {
					position = i;
					break;
				}
			}
			catch (NullPointerException e)
			{
				continue;
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
	public void replaceName(Candidate3[] election, String name1, String name2)
	{
		for(int i=0;i < election.length;i++)
		{
			if(election[i].getName() == name1)
			{
				election[i].setName(name2);
			}
		}
	}
	public void replaceVotes(Candidate3[] election, String name, int votes)
	{
		for(int i=0; i < election.length;i++)
		{
			if(election[i].getName() == name)
			{
				election[i].setNumVotes(votes);
			}
		}
	}
	public void replaceCandidate(Candidate3[] election, String name, String repName, int repVotes)
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
	public  void printVotes(Candidate3[] election)
	{
		for(int i=0;i<election.length;i++)
		{
			Candidate3 c = election[i];
			System.out.println(c.getName()+"received "+c.getNumVotes()+" votes");
		}
	}
	public  int getTotal(Candidate3[] election)
	{
		int totalCount =0;
		for(int i =0;i < election.length;i++)
		{
			totalCount += election[i].getNumVotes();
		}
		return totalCount;
	}
	public  void printResults(Candidate3[] election)
	{
		int totalVotes = getTotal(election);
		System.out.println("Candidates \t\t Votes received \t\t Percentage of total votes");
		for(int i=0;i< election.length;i++)
		{
			Candidate3 c = election[i];
			System.out.println(c.getName()+" \t\t"+ c.getNumVotes()+ "\t\t"+ Math.round(c.getNumVotes()/(double)totalVotes*100)+"%");

		}
		System.out.println("Total number of votes in election is" + totalVotes);

	}
}
