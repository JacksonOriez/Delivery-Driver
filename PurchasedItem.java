/**
 * @author Jackson Oriez, oriezj@purdue.edu
 * @version 10/31/2018
 */
public interface PurchasedItem {

    public boolean isDelivery();

    public String getCustomerName();

    public int getDeliveryTime();

    public void setDeliveryTime(int time); //minutes

    public double getMaterialCost();

    public double getSalePrice();

}
