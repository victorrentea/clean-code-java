package victor.training.cleancode.refactoring;

import lombok.Value;

public class ExtractVariable {
    public double computeTotalPrice(Order order) {
        return order.quantity() * order.itemPrice() -
               Math.max(0, order.quantity() - 500) * order.itemPrice() * 0.05 +
               Math.min(order.quantity() * order.itemPrice() * 0.1, 100);
    }
}

record Order(int quantity, int itemPrice) {
}