import java.util.Comparator;

/**
 * purpose- value of cars in array list
 * Created by pi on 3/30/16.
 */
public class Tool  implements Product,Comparable<Product>
{

    String name;
    double cost;
    Tool(String name, double cost)
    {
        this.name = name;
        this.cost = cost;
    }
    public double getCost()
    {
        return cost;
    }
    public String getName()
    {
        return name;
    }

    public int compareTo(Product obj)
    {
        if(this.getCost() < obj.getCost())
        {
            return -1;
        }
        else
        if(this.getCost() > obj.getCost())
        {
            return 1;
        }
        else
        {
            return 0;
        }

    }
}
