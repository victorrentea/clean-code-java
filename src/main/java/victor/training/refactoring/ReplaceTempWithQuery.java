package victor.training.refactoring;

public class ReplaceTempWithQuery {

    private int quantity;
    private double itemPrice;

    public ReplaceTempWithQuery(int quantity, double itemPrice) {
        this.quantity = quantity;
        this.itemPrice = itemPrice;
    }


    public double computePrice() {
        if (quantity > 10)
            return basePrice() * 0.95;
        else
            return computeNormalPrice();
    }

    private double basePrice() {
        return quantity * itemPrice ;
    }

    private double computeNormalPrice() {
        if (basePrice() > 1000) {
            return basePrice() * 0.95;
        } else {
            return basePrice() * 0.98;
        }
    }

    public int computeFidelityPoints() {
        return (int) (basePrice() / 2);
    }
}
