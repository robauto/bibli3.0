
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

    public int getVotes()
    {
        return numVotes;
    }
    public  void setName(String name)
    {
        this.name = name;
    }
    public void setVotes(int numVotes)
    {
        this.numVotes = numVotes;
    }
    public String toString()

    {
        return name + numVotes + " votes";
    }
}
