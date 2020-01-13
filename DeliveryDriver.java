/**
 * @author Jackson Oriez, oriezj@purdue.edu
 * @version 10/31/2018
 */
public class DeliveryDriver {

    // You can add instance variables as needed

    private String name;

    private double wage;

    private int maxCarriableItems;

    private int numDeliveries;

    private int minutesDelivering;

    private int numItems;

    private PurchasedItem[] items;

    public DeliveryDriver(String name, double wage, int maxCarriableItems) {
        this.name = name;
        this.wage = wage;
        this.maxCarriableItems = maxCarriableItems;
        this.items = new PurchasedItem[this.maxCarriableItems];
    }

    public DeliveryDriver(String name, double wage) {
        this.name = name;
        this.wage = wage;
        this.maxCarriableItems = 5;
        this.items = new PurchasedItem[this.maxCarriableItems];
    }

    public DeliveryDriver(String name) {
        this.name = name;
        this.wage = 7.25;
        this.maxCarriableItems = 5;
        this.items = new PurchasedItem[this.maxCarriableItems];
    }

    /**
     * DO NOT EDIT THIS CODE!
     */
    public String getName() {
        return name;
    }

    /**
     * DO NOT EDIT THIS CODE!
     */
    public double getWage() {
        return wage;
    }

    /**
     * DO NOT EDIT THIS CODE!
     */
    public int getTimeSpent() {
        return minutesDelivering;
    }


    /**
     * DO NOT EDIT THIS CODE!
     * <p>
     * Consults the number of orders that the driver has delivered
     *
     * @return number of orders delivered
     */
    public int getNumDelivered() {
        return numDeliveries;
    }

    /**
     * DO NOT EDIT THIS CODE!
     */
    public int getMaxCapacity() {
        return maxCarriableItems;
    }


    /**
     * Add the order to the list/array of items to deliver.
     *
     * @param item - order to add
     * @return true if the item is for delivery and if the driver can
     * hold more orders, return false otherwise
     */
    public boolean pickupOrder(PurchasedItem item) {
        if ((this.numItems < this.maxCarriableItems && item.isDelivery())) {
            this.numItems = this.numItems + 1;
            for (int i = 0; i < items.length; i++) {
                if (this.items[i] == null) {
                    this.items[i] = item;
                    i = items.length;
                }
            }
            return true;
        } else {
            return false;
        }

    }

    /**
     * Returns the number of items in the delivery list
     *
     * @return num items
     */
    public int getNumOrders() {
        return numItems;
    }


    /**
     * Return an array of items to deliver.
     * the array has to be populated within the index 0 to numItems - 1
     * and of size numItems
     *
     * @return array of type PurchasedItem
     */
    public PurchasedItem[] getOrders() {
        PurchasedItem[] items2 = new PurchasedItem[this.numItems];
        for (int i = 0; i < items2.length; i++) {
            items2[i] = this.items[i];
        }
        return items2;
    }

    /**
     * Update how long the driver has been delivering and empty the
     * list of items to deliver.
     */
    public void deliverOrders() {
        int counter = getNumOrders();
        for (int i = 0; i < counter; i++) {
            if (this.items[i] != null) {
                this.minutesDelivering = minutesDelivering + this.items[i].getDeliveryTime();
                this.numDeliveries = this.numDeliveries + 1;
                this.numItems = this.numItems - 1;
                this.items[i] = null;
            }
        }
    }

    /**
     * Check if driver is scheduled to deliver an order and remove it
     * and update the driver's counters if the item is found.
     *
     * @param item - order to remove from deliveries
     * @return true if the driver is scheduled to deliver the item,
     * false otherwise
     */
    public boolean removeOrder(PurchasedItem item) {

        if (item != null && this.items != null) {
            boolean output = false;
            for (int i = 0; i < items.length; i++) {
                if (this.items[i] == item) {
                    output = true;
                    this.items[i] = null;
                    for (int j = 0; j < items.length; j++) {
                        if (i == items.length - 1) {
                            this.items[i] = null;
                        } else {
                            this.items[i] = this.items[i + 1];
                        }
                    }
                    this.numItems = this.numItems - 1;
                    i = items.length;
                }
            }

            return output;
        } else {
            return false;
        }

    }


    /**
     * Calculates the amount of money earned by the driver
     *
     * @return amount of money earned by the driver
     */
    public double getMoneyEarned() {
        int remainder;
        if (getTimeSpent() < 480) {
            return (wage * getTimeSpent() / 60.0);
        } else {
            remainder = getTimeSpent() - 480;
            return ((wage * 1.5) * remainder / 60.0) + (wage * (480) / 60.0);
        }

    }

    /**
     * Compares if the input object is equal to the instance
     * Two objects are equal if they are of the same type and
     * all instance variables are equal.
     *
     * @return true if they are, false if they are not
     */
    @Override
    public boolean equals(Object obj) {
        return (obj instanceof DeliveryDriver &&
                ((DeliveryDriver) obj).name.equalsIgnoreCase(this.name) &&
                ((DeliveryDriver) obj).wage == this.wage &&
                ((DeliveryDriver) obj).getTimeSpent() == this.getTimeSpent() &&
                ((DeliveryDriver) obj).getNumOrders() == this.getNumOrders());
    }

    /**
     * DO NOT EDIT THIS CODE!
     */
    @Override
    public String toString() {
        StringBuilder ret = new StringBuilder();

        ret.append("Name: ");
        ret.append(this.name);

        ret.append(" - Wage: $");
        ret.append(String.format("%.2f", this.wage));

        ret.append(" - Can Carry: ");
        ret.append(this.maxCarriableItems);

        ret.append(" items - Num Deliveries: ");
        ret.append(this.numDeliveries);

        ret.append(" - Minutes Worked: ");
        ret.append(this.minutesDelivering);
        ret.append(" min");

        ret.append(" - Currently Carrying: ");
        ret.append(this.numItems);
        ret.append(" items");

        return ret.toString();
    }

}
