/**
 * purpose- arrange list and get a list from a category
 * Created by pi on 5/21/16.
 */
public class CashRegister {
    int cashOnHand;
    public CashRegister()
    {
        this.cashOnHand = 500;

    }
    public CashRegister(int cashOnHand)
    {
        if(cashOnHand < 0)
        {
            throw new IllegalArgumentException("Cash on hand cannot be zero");
        }
        this.cashOnHand = cashOnHand;

    }
    public void cashRegister(int cashOnHand)
    {

        if(cashOnHand < 0)
        {
            throw new IllegalArgumentException("Cash on hand cannot be zero");
        }
        this.cashOnHand = cashOnHand;
    }
    public void acceptAmount(int amt)
    {
        if(amt <= 0)
        {
            throw new IllegalArgumentException("amount cannot be less than" +
                    "or equal to zero");
        }
        this.cashOnHand += amt;
    }
}
