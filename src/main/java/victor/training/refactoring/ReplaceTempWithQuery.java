package victor.training.refactoring;

public class ReplaceTempWithQuery {

    private int quantity;
    private double itemPrice;

    public ReplaceTempWithQuery(int quantity, double itemPrice) {
        this.quantity = quantity;
        this.itemPrice = itemPrice;
    }


    public double computePrice() {
        if (quantity > 10) {
            return computeBasePrice() * 0.95;
        } else {
            return computeNormalPrice();
        }
    }

    private double computeNormalPrice() {
        if (computeBasePrice() > 1000) {
            return computeBasePrice() * 0.95;
        } else {
            return computeBasePrice() * 0.98;
        }
    }

    private double computeBasePrice() {
        return quantity * itemPrice;
    }

    public int computeFidelityPoints() {
        return (int) (computeBasePrice() / 2);
    }
}
