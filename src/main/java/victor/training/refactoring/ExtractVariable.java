package victor.training.refactoring;

public class ExtractVariable {
    public double computeTotalPrice(Order order) {
        return order.getQuantity() * order.getItemPrice() -
                Math.max(0, order.getQuantity() - 500) * order.getItemPrice() * 0.05 +
                Math.min(order.getQuantity() * order.getItemPrice() * 0.1, 100);
    }
}

class Order {
    private int quantity;
    private int itemPrice;

    public int getQuantity() {
        return quantity;
    }

    public int getItemPrice() {
        return itemPrice;
    }
}