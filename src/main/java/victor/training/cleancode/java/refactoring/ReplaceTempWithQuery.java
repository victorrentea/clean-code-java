package victor.training.cleancode.java.refactoring;

public class ReplaceTempWithQuery {
    private final int quantity;
    private final double itemPrice;

    public ReplaceTempWithQuery(int quantity, double itemPrice) {
        this.quantity = quantity;
        this.itemPrice = itemPrice;
    }

    public double computePrice() {
        double basePrice = quantity * itemPrice;
        if (quantity > 10)
            return basePrice * 0.95;
        else
            return computeNormalPrice(basePrice);
    }

    private double computeNormalPrice(double basePrice) {
        double factor = (basePrice > 1000) ? 0.95: 0.98;
        return factor * basePrice;
    }

    public int computeFidelityPoints() {
        return (int) (quantity * itemPrice / 2);
    }


}