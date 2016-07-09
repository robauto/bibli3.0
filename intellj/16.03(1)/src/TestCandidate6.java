import java.util.ArrayList;

public class TestCandidate5 {
	public static void main(String args[])
	{
		TestCandidate5 tc= new TestCandidate5();
		ArrayList<Candidate1> election = new ArrayList<>();
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
		election=tc.insertPosition(election,"Mickey Duck",14000,5);
		System.out.println("Added Mickey Duck,14000 votes");
		tc.printResults(election);
		election=tc.insertCandidate(election,"Kathleen Turner","Donald Mouse",100);
		System.out.println("Added Donald Mouse,100 votes");
		tc.printResults(election);

	}
	public ArrayList<Candidate1> insertPosition(ArrayList<Candidate1>election, String name, int votes, int position)
	{
		int length = election.size();
		ArrayList<Candidate1> startList =new ArrayList<Candidate1>();
		ArrayList<Candidate1> endList = new ArrayList<Candidate1>();
		for(int i=0;i<position;i++)
		{
			startList.add(election.get(i));
		}
		for(int i=position;i<length;i++)
		{
			endList.add(election.get(i));
		}
		Candidate1 candidate1 = new Candidate1(name,votes);
		startList.add(candidate1);
		startList.addAll(endList);
		return startList;
	}
	public ArrayList<Candidate1> insertCandidate(ArrayList<Candidate1> election,String name1,String name2,int votes)
	{
		int length = election.size();
		ArrayList<Candidate1> startList =new ArrayList<Candidate1>();
		ArrayList<Candidate1> endList = new ArrayList<Candidate1>();
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
		Candidate1 candidate1 = new Candidate1(name2,votes);
		startList.add(candidate1);
		startList.addAll(endList);
		return startList;
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
				election.get(i).setVotes(votes);
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
				election.get(i).setVotes(repVotes);
			}
		}
	}
	public  void printVotes(ArrayList<Candidate1> election)
	{
		for(int i=0;i<election.size();i++)
		{
			Candidate1 c = election.get(i);
			System.out.println(c.getName()+"received "+c.getVotes()+" votes");
		}
	}
	public  int getTotal(ArrayList<Candidate1> election)
	{
		int totalCount =0;
		for(int i =0;i < election.size();i++)
		{
			totalCount += election.get(i).getVotes();
		}
		return totalCount;
	}
	public  void printResults(ArrayList<Candidate1> election)
	{
		int totalVotes = getTotal(election);
		System.out.println("Candidates \t\t Votes received \t\t Percentage of total votes");
		for(int i=0;i< election.size();i++)
		{
			Candidate1 c = election.get(i);
			System.out.println(c.getName()+" \t\t"+ c.getVotes()+ "\t\t"+ Math.round(c.getVotes()/(double)totalVotes*100)+"%");

		}
		System.out.println("Total number of votes in election is" + totalVotes);

	}
}
