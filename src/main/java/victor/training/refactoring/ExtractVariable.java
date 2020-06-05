package victor.training.refactoring;

public class ExtractVariable {
    public double computeTotalPrice(Order order) {
        int basePrice = order.getQuantity() * order.getItemPrice();
        double volumeDiscount = Math.max(0, order.getQuantity() - 500) * order.getItemPrice() * 0.05;
        double shipping = Math.min(basePrice * 0.1, 100);

        return basePrice - volumeDiscount + shipping;
    }
}

class Order {
    private int quantity;
    private int itemPrice;

    public int getItemPrice() {
        return itemPrice;
    }

    public int getQuantity() {
        return quantity;
    }
}