import java.util.ArrayList;
/**
 * purpose- cresting insertion methods
 * Created by pi on 5/12/16.
 */
public class TestCandidate8 {
	public static void main(String args[])
	{
		TestCandidate8 tc= new TestCandidate8();
		ArrayList<Candidate3> election = new ArrayList<>();
		election.add(new Candidate3("John Smith",5000));
		election.add(new Candidate3("Mary Miller",4000));
		election.add(new Candidate3("Michael Duffy",6000));
		election.add(new Candidate3("Tim Robinson",2500));
		election.add(new Candidate3("Joe Ashtony",1800));
		election.add(new Candidate3("Mickey Jones",3000));
		election.add(new Candidate3("Rebecca Morgan",2000));
		election.add(new Candidate3("Kathleen Turner",8000));
		election.add(new Candidate3("Tory Parker", 500));
		election.add(new Candidate3("Ashton Davis",10000));
		System.out.println("Original Results");
		tc.printResults(election);
		System.out.println("Delete location 6");
		election = tc.deleteByLoc(election,6);
		tc.printResults(election);
		System.out.println("Deleted kathleen Turner");
		election = tc.deleteByName(election,"Kathleen Turner");
		tc.printResults(election);

	}
	public ArrayList<Candidate3> deleteByLoc(ArrayList<Candidate3> election, int position)
	{
		ArrayList<Candidate3> newElection = new ArrayList<Candidate3>();
		for (int i=0;i<position;i++)
		{
			newElection.add(i,election.get(i));

		}
		for(int i=position+1;i<election.size();i++)
		{
			newElection.add(i-1,election.get(i));
		}
		return newElection;

	}
	public ArrayList<Candidate3> deleteByName(ArrayList<Candidate3> election, String  name)
	{

		ArrayList<Candidate3> newElection = new ArrayList<Candidate3>();
		int position = -1;
		for(int i =0; i<election.size();i++)
		{
			if (election.get(i).getName() == name)
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

	public ArrayList<Candidate3> insertPosition(ArrayList<Candidate3>election, String name, int votes, int position)
	{
		int length = election.size();
		ArrayList<Candidate3> startList =new ArrayList<Candidate3>();
		ArrayList<Candidate3> endList = new ArrayList<Candidate3>();
		for(int i=0;i<position;i++)
		{
			startList.add(election.get(i));
		}
		for(int i=position;i<length;i++)
		{
			endList.add(election.get(i));
		}
		Candidate3 candidate1 = new Candidate3(name,votes);
		startList.add(candidate1);
		startList.addAll(endList);
		return startList;
	}
	public ArrayList<Candidate3> insertCandidate(ArrayList<Candidate3> election, String name1, String name2, int votes)
	{
		int length = election.size();
		ArrayList<Candidate3> startList =new ArrayList<Candidate3>();
		ArrayList<Candidate3> endList = new ArrayList<Candidate3>();
		int position = -1;
		for(int i =0; i<election.size();i++)
		{
			if (election.get(i).getName() == name1)
			{
				position = i;
				break;
			}
		}
		if(position == -1)
		{
			System.out.println("Cannot find the given candidate");
			System.exit(1);
		}
		for(int i=0;i<position;i++)
		{
			startList.add(election.get(i));
		}
		for(int i=position;i<length;i++)
		{
			endList.add(election.get(i));
		}
		Candidate3 candidate1 = new Candidate3(name2,votes);
		startList.add(candidate1);
		startList.addAll(endList);
		return startList;
	}
	public void replaceName(ArrayList<Candidate3> election, String name1, String name2)
	{
		for(int i=0;i < election.size();i++)
		{
			if(election.get(i).getName() == name1)
			{
				election.get(i).setName(name2);
			}
		}
	}
	public void replaceVotes(ArrayList<Candidate3> election, String name, int votes)
	{
		for(int i=0; i < election.size();i++)
		{
			if(election.get(i).getName() == name)
			{
				election.get(i).setNumVotes(votes);
			}
		}
	}
	public void replaceCandidate(ArrayList<Candidate3> election, String name, String repName, int repVotes)
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
	public  void printVotes(ArrayList<Candidate3> election)
	{
		for(int i=0;i<election.size();i++)
		{
			Candidate3 c = election.get(i);
			System.out.println(c.getName()+"received "+c.getNumVotes()+" votes");
		}
	}
	public  int getTotal(ArrayList<Candidate3> election)
	{
		int totalCount =0;
		for(int i =0;i < election.size();i++)
		{
			totalCount += election.get(i).getNumVotes();
		}
		return totalCount;
	}
	public  void printResults(ArrayList<Candidate3> election)
	{
		int totalVotes = getTotal(election);
		System.out.println("Candidates \t\t Votes received \t\t Percentage of total votes");
		for(int i=0;i< election.size();i++)
		{
			Candidate3 c = election.get(i);
			System.out.println(c.getName()+" \t\t"+ c.getNumVotes()+ "\t\t"+ Math.round(c.getNumVotes()/(double)totalVotes*100)+"%");

		}
		System.out.println("Total number of votes in election is" + totalVotes);

	}
}
