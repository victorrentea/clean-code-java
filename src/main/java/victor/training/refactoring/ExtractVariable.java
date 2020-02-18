package victor.training.refactoring;

import lombok.Data;

public class ExtractVariable {
    public double computeTotalPrice(Order order) {
        return order.getQuantity() * order.getItemPrice() -
                Math.max(0, order.getQuantity() - 500) * order.getItemPrice() * 0.05 +
                Math.min(order.getQuantity() * order.getItemPrice() * 0.1, 100);
    }
}

@Data
class Order {
    private int quantity;
    private int itemPrice;
}