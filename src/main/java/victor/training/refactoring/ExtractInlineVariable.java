package victor.training.refactoring;

import lombok.Data;

public class ExtractInlineVariable {
    public double computeTotalPrice(Order order) {
        double discount = Math.max(0, order.getQuantity() - 500) * order.getItemPrice() * 0.05;
        double shipping = Math.min(100, order.getQuantity() * order.getItemPrice() * 0.1);

        return order.getQuantity() * order.getItemPrice() - discount + shipping;
    }
}

@Data
class Order {
    private int quantity;
    private int itemPrice;
}