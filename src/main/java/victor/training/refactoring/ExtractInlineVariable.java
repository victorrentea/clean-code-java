package victor.training.refactoring;

import lombok.Data;

public class ExtractInlineVariable {
    //  TODO: touch on split variable
    public double computePrice(Order order) {
        double result = order.getQuantity() * order.getItemPrice() -
                Math.max(0, order.getQuantity() - 500) * order.getItemPrice() * 0.05 +
                Math.min(order.getQuantity() * order.getItemPrice() * 0.1, 100);
        return result;
    }
}

@Data
class Order {
    private int quantity;
    private int itemPrice;
}