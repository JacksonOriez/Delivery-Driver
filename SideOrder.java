/**
 * @author Jackson Oriez, oriezj@purdue.edu
 * @version 10/31/2018
 */
public class SideOrder implements PurchasedItem {

    private String name;
    private double matCost;
    private double sellPrice;
    private int delTime;
    private OrderSize size;

    public SideOrder(String name, double matCost, double sellPrice) {
        this.name = name;
        this.matCost = matCost;
        this.sellPrice = sellPrice;
        this.size = OrderSize.SMALL;
        this.delTime = 0;
    }

    public SideOrder(String name, double matCost, double sellPrice, int delTime) {
        this.name = name;
        this.matCost = matCost;
        this.sellPrice = sellPrice;
        this.delTime = delTime;
        this.size = OrderSize.SMALL;
    }

    public SideOrder(String name, double matCost, double sellPrice, int delTime, OrderSize size) {
        this.name = name;
        this.matCost = matCost;
        this.sellPrice = sellPrice;
        this.delTime = delTime;
        this.size = size;
    }

    public boolean isDelivery() {
        return (getDeliveryTime() > 0 && getDeliveryTime() <= 30);
    }

    public String getCustomerName() {
        return name;
    }

    public int getDeliveryTime() {
        return delTime;
    }

    public void setDeliveryTime(int time) {
        if (time > 0 && time <= 30) {
            this.delTime = time;
        } else {
            this.delTime = 0;
        }
    }

    public double getMaterialCost() {
        if (this.size == OrderSize.MEDIUM) {
            return matCost + 0.40;
        } else if (this.size == OrderSize.LARGE) {
            return matCost + 0.80;
        } else if (this.size == OrderSize.ABSURD) {
            return matCost + 1.50;
        } else {
            return matCost;
        }
    }

    public double getSalePrice() {
        if (this.size == OrderSize.MEDIUM) {
            return sellPrice + 2.00;
        } else if (this.size == OrderSize.LARGE) {
            return sellPrice + 3.00;
        } else if (this.size == OrderSize.ABSURD) {
            return sellPrice + 4.50;
        } else {
            return sellPrice;
        }
    }

    public OrderSize getOrderSize() {
        return size;
    }

    public void setOrderSize(OrderSize size2) {
        this.size = size2;
    }

    public boolean equals(Object obj) {
        return (obj instanceof SideOrder &&
                ((SideOrder) obj).name.equalsIgnoreCase(this.name) &&
                ((SideOrder) obj).matCost == this.matCost &&
                ((SideOrder) obj).sellPrice == this.sellPrice &&
                ((SideOrder) obj).getDeliveryTime() == this.getDeliveryTime() &&
                ((SideOrder) obj).size == this.size);
    }
}
