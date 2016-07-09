/**
 * Created by admin on 5/21/16.
 */
public class Dispenser {
    int numberOfItems;
    int cost;
    public  Dispenser()
    {
        this.numberOfItems = 50;
        this.cost = 50;
    }
    public Dispenser(int numberOfItems,int cost)
    {
        if(numberOfItems <=0)
        {
            throw new IllegalArgumentException("number of items cannot be less" +
                    "than or equal to zero");
        }
        if(cost <= 0)
        {
            throw new IllegalArgumentException("cost should not be less than or equal to zero");
        }
        this.numberOfItems = numberOfItems;
        this.cost = cost;
    }
    public int getCount()
    {
        return numberOfItems;
    }
    public int getProductionCost()
    {
        return cost;
    }
    public void makeSale()
    {
        this.numberOfItems -= 1;
    }
}
