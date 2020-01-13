/**
 * @author Jackson Oriez, oriezj@purdue.edu
 * @version 10/31/2018
 */
public class Sandwich implements PurchasedItem {

    public double costOfCondiment = 0.05;
    public double pricePerCondiment = 0.75;
    private String name;
    private double matCost;
    private double sellPrice;
    private int delTime;
    private Spicyness level;
    private int condiments;

    public Sandwich(String name, double matCost, double sellPrice) {
        this.name = name;
        this.matCost = matCost;
        this.sellPrice = sellPrice;
        this.condiments = 0;
        this.level = Spicyness.MILD;
        this.delTime = 0;
    }

    public Sandwich(String name, double matCost, double sellPrice, int delTime, Spicyness level, int condiments) {
        this.name = name;
        this.matCost = matCost;
        this.sellPrice = sellPrice;
        this.delTime = delTime;
        this.level = level;
        this.condiments = condiments;
    }

    public Sandwich(String name, double matCost) {
        this.name = name;
        this.matCost = matCost;
        this.condiments = 0;
        this.level = Spicyness.MILD;
        this.delTime = 0;
        this.sellPrice = matCost * 3.5;
    }

    public Spicyness getSpicyness() {
        return level;
    }

    public void setSpicyness(Spicyness level2) {
        this.level = level2;
    }

    public void addCondiments(int num) {
        this.condiments = condiments + num;
    }

    public void removeCondiments(int num) {
        if (num <= condiments) {
            this.condiments = condiments - num;
        } else {
            this.condiments = 0;
        }
    }

    public int getNumCondiments() {
        return condiments;
    }

    public boolean isDelivery() {
        return (getDeliveryTime() <= 60 && getDeliveryTime() > 0);
    }

    public String getCustomerName() {
        return name;
    }

    public int getDeliveryTime() {
        return delTime;
    }

    public void setDeliveryTime(int time) {
        if (time > 0 && time <= 60) {
            this.delTime = time;
        } else {
            this.delTime = 0;
        }
    }

    public double getMaterialCost() {
        return matCost + (condiments * costOfCondiment);
    }

    public double getSalePrice() {
        return sellPrice + (condiments * pricePerCondiment);
    }

    public boolean equals(Object obj) {
        return (obj instanceof Sandwich &&
                ((Sandwich) obj).name.equalsIgnoreCase(this.name) &&
                ((Sandwich) obj).matCost == this.matCost &&
                ((Sandwich) obj).sellPrice == this.sellPrice &&
                ((Sandwich) obj).getDeliveryTime() == this.getDeliveryTime() &&
                ((Sandwich) obj).level == this.level);
    }
}
