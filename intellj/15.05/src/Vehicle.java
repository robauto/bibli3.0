/**
 * purpose- value of cars in array list
 * Created by pi on 3/30/16.
 */
public abstract class Vehicle implements Product {
    private String name;
    private double cost;

    public Vehicle(String name, double cost) {
        this.name = name;
        this.cost = cost;
    }

    public String getName()
    {
        return name;
    }

    public double getCost()
    {
        return cost;
    }
}

     
