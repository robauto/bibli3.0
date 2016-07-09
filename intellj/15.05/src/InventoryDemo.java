import java.util.ArrayList;

/**
 * purpose- value of cars in array list
 * Created by pi on 3/30/16.
 */
public class InventoryDemo
{
    public static void main(String args[])
    {
        ArrayList<Product> arrayList = new ArrayList<Product>();
        ArrayList<String> vehicleArrayList = new ArrayList<String>();
        vehicleArrayList.add("jaguar");
        vehicleArrayList.add("Neon");
        vehicleArrayList.add("JigSaw");
        vehicleArrayList.add("RAM");
        vehicleArrayList.add("CircularSaw");

        arrayList.add(new Car("jaguar",1000000.00));
        arrayList.add(new Car("Neon",17000.00));
        arrayList.add(new Truck("JigSaw",149.18));
        arrayList.add(new Car("jaguar",110000.00));
        arrayList.add(new Car("Neon",17500.00));
        arrayList.add(new Car("Neon",17875.32));
        arrayList.add(new Truck("RAM",35700.00));
        arrayList.add(new Car("CircularSaw",200.00));
        arrayList.add(new Car("CircularSaw",150.00));

        for(int i=0; i< vehicleArrayList.size(); i++)
        {
            totalInventory(vehicleArrayList.get(i),arrayList);
        }

        Tool saw1 = new Tool("saw1",1000);
        Tool saw2 = new Tool("saw2", 2000);
        if(saw1.compareTo(saw2) < 0)
        {
            System.out.println("second saw is more expensive");
        }
        else
        if(saw1.compareTo(saw2) > 0)
        {
            System.out.println("first saw  is more expensive");
        }
        else
        {
            System.out.println("both prices are equal");
        }
    }
    public static void totalInventory(String name, ArrayList<Product> productArrayList)
    {

        int totalNoP = 0;
        double totalCost = 0.0;
        for(int i=0;i< productArrayList.size();i++)
        {
            if(productArrayList.get(i).getName() == name)
            {
                totalNoP++;
                totalCost+= productArrayList.get(i).getCost();
            }
        }
        System.out.println(name+": Quantity="+totalNoP+", Total Cost = "+ totalCost);


    }
}
