
public class Candidate
{
    // instance variables 
    private String name;
    int numVotes;
 
   
    public Candidate(String num, int vote)
    {
        this.name = num;
        this.numVotes = vote;
    }
    public String getName()
    {
    return name;
    }
     
    public int getNumVotes() {
     return numVotes;
	}   
	public void setNumVotes(int numVotes) {
		this.numVotes = numVotes;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String toString()
    {
        return name + numVotes + " votes";
    }
 
}
