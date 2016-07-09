/**
 * purpose- update the last program and add replace method
 * Created by pi on 3/30/16.
 */
public class Candidate1
{
    // instance variables 
    private String name;
    private int numVotes;
 
   
    public Candidate1(String num, int vote)
    {
        this.name = num;
        this.numVotes = vote;
    }
   
     
 
    public String getName()
    {
		return name;
	}



	public void setName(String name) {
		this.name = name;
	}



	public int getNumVotes()
    {
		return numVotes;
	}



	public void setNumVotes(int numVotes) {
		this.numVotes = numVotes;
	}



	public String toString()
    {
        return name + numVotes + " votes";
    }
 
}
