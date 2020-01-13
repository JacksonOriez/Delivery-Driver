/**
 * @author Jackson Oriez, oriezj@purdue.edu
 * @version 10/31/2018
 */
public class Store {

    // You can add instance variables as needed

    private String name;

    private double revenue;

    private double materialCosts;

    private DeliveryDriver[] drivers;

    private DeliveryDriver driver;

    public Store(String storeName, DeliveryDriver[] drivers) {
        this.name = storeName;
        this.drivers = drivers;
    }

    public Store(String storeName, int numDrivers) {
        this.name = storeName;
        this.drivers = new DeliveryDriver[numDrivers];
        for (int i = 0; i < numDrivers; i++) {
            String driverName = "Driver" + i;
            this.drivers[i] = new DeliveryDriver(driverName);
        }
    }

    /**
     * DO NOT EDIT THIS CODE!
     */
    private static double percentDiff(double from, double to) {
        return Math.abs((to - from) / from * 100.0);
    }

    String getStoreName() {
        return this.name;
    }

    DeliveryDriver[] getDrivers() {
        return this.drivers;
    }

    /**
     * Updates the store's financial information. This function
     * handles assigning orders to drivers if it's a delivery and
     * manages sending drivers out on delivery.
     *
     * @param item - purchased item being ordered
     */
    public void placeOrder(PurchasedItem item) {
        this.materialCosts = this.materialCosts + item.getMaterialCost();
        this.revenue = this.revenue + item.getSalePrice();
        if (item.isDelivery()) {
            for (int i = 0; i < this.drivers.length; i++) {
                if (this.drivers[i].getNumOrders() == this.drivers[i].getMaxCapacity() &&
                        i == this.drivers.length - 1) {
                    this.drivers[i].deliverOrders();
                    this.drivers[i].pickupOrder(item);
                    this.driver = this.drivers[i];
                } else if (this.drivers[i].getNumOrders() == this.drivers[i].getMaxCapacity()) {
                    this.drivers[i].deliverOrders();
                } else {
                    this.drivers[i].pickupOrder(item);
                    this.driver = this.drivers[i];
                    i = drivers.length;
                }
            }
        }

    }

    /**
     * Cancels an order with the store. It works under the assumption
     * that this order has already been placed. Also, this function
     * won't reduce the store's total material cost, as the item is
     * already made and wasted.
     * <p>
     * <p>
     * This method will only fail to cancel an order if the item is
     * marked for delivery but the currently selected delivery driver
     * isn't holding the item / can't remove the item (it has likely
     * already been removed).
     *
     * @param item - the order to cancel
     * @return true if the order could be canceled, false otherwise
     */
    public boolean cancelOrder(PurchasedItem item) {
        this.revenue = this.revenue - item.getSalePrice();
        boolean output = true;
        if (!item.isDelivery()) {
            return true;
        } else {
            PurchasedItem[] items = driver.getOrders();
            for (int i = 0; i < items.length; i++) {
                if (items[i] == item) {
                    driver.removeOrder(item);
                    return true;
                } else {
                    output = false;
                }
            }
            return output;
        }
    }

    /**
     * Getter method for a store's revenue.
     *
     * @return gross revenue
     */
    public double getGrossRevenue() {
        return revenue;
    }

    /**
     * Getter method for a store's material costs.
     *
     * @return material costs
     */
    public double getMaterialCosts() {
        return materialCosts;
    }

    /**
     * Calculates the store's net profit using one of these equivalent
     * equations:
     * <p>
     *
     * <i>profit = $(revenue) - $(period costs)</i>
     * <p>
     * <i>profit = $(revenue) - $(material costs) - $(labor costs)</i>
     *
     * @return the net operating profit of the store at this point in
     * time
     */
    public double getNetProfit() {
        double totalLaborCost = 0;
        for (int i = 0; i < drivers.length; i++) {
            totalLaborCost = totalLaborCost + drivers[i].getMoneyEarned();
        }
        return revenue - materialCosts - totalLaborCost;
    }

    /**
     * Calculates the store's net income. The traditional formula
     * used to calculate net income is:
     * <p>
     *
     * <i>income = $(profit) - $(indirect costs)</i>
     *
     * @return net income
     */
    public double getNetIncome() {
        return getNetProfit() - 50.0 - (getNetProfit() * 0.15);
    }

    /**
     * DO NOT EDIT THIS CODE!
     */
    public String toString() {
        StringBuilder ret = new StringBuilder();

        ret.append(String.format("\nStore Info\n----------\nName: \"%s\"\n", this.name));
        ret.append(String.format("Revenue: $%.2f\nCosts: $%.2f\n", this.revenue, this.materialCosts));
        ret.append(String.format("Profit: $%.2f\nIncome: $%.2f\n", this.getNetProfit(), this.getNetIncome()));

        ret.append(String.format("\nDriver Info\n-----------\n"));
        int i = 1;
        for (DeliveryDriver driver2 : this.drivers)
            ret.append(String.format("%d.) %s\n", i++, driver2.toString()));

        return ret.toString();
    }

    /**
     * DO NOT EDIT THIS CODE!
     */
    private void printStatistics(double expRevenue, double expProfit, double expIncome) {
        double revenue2 = this.getGrossRevenue();
        System.out.printf("Revenue: $%.2f\t\tExpected: $%.2f\t\t%% Diff: %f%%\n",
                revenue2, expRevenue, percentDiff(expRevenue, revenue2));

        double profit = this.getNetProfit();
        System.out.printf("Profit: $%.2f\t\tExpected: $%.2f\t\t%% Diff: %f%%\n",
                profit, expProfit, percentDiff(expProfit, profit));

        double income = this.getNetIncome();
        System.out.printf("Income: $%.2f\t\tExpected: $%.2f\t%% Diff: %f%%\n",
                income, expIncome, percentDiff(expIncome, income));
    }

}
