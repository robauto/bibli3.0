/**
 * purpose- arrange list and get a list from a category
 * Created by pi on 5/21/16.
 */
public class CandyMachine
{
    public static void main(String args[])
    {

        Dispenser dispenser = new Dispenser(100,50);
        CashRegister cashRegister = new CashRegister();
        sellProduct(dispenser,cashRegister,75);
    }
    public static void sellProduct(Dispenser dispenser,CashRegister cashRegister,int amt)
    {
        if(amt > dispenser.getProductionCost())  //trying to sell one at a time
        {
            dispenser.makeSale();
            cashRegister.acceptAmount(amt - dispenser.getProductionCost());
            System.out.println("Collect your item at the bottom and enjoy");
        }
        else
        {
            System.out.println("Sorry not enough amount given");
        }
    }
}
