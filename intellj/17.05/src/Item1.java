/**
 * purpose- arrange list in assending and decending order
 * Created by pi on 5/18/16.
 */
public class Item1 {
    String itemID;
    String itemName;
    int inStore;
    double price;

    public Item1(String itemID, String itemName, int inStore, double price)
    {
        this.itemID = itemID;
        this.itemName = itemName;
        this.inStore = inStore;
        this.price = price;
    }

    public String toString()
    {
        return itemID + "\t\t\t"+itemName+"\t\t\t"+inStore+"\t\t\t"+price;
    }
    public void setItemID(String itemID)
    {
        this.itemID = itemID;
    }
    public String getItemID()
    {
        return itemID;
    }
    public String getItemName()
    {
        return itemName;
    }
    public void setItemName(String itemName)
    {
        this.itemName = itemName;
    }
    public void setInStore(int inStore)
    {
        this.inStore = inStore;
    }
    public int getInStore()
    {
        return inStore;
    }
    public void setPrice(double price)
    {
        this.price = price;
    }
    public double getPrice()
    {
        return price;
    }







}
