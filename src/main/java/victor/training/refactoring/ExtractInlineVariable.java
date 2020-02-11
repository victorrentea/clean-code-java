package victor.training.refactoring;

import lombok.Data;

public class ExtractInlineVariable {
    //  TODO: touch on split variable
    public double computePrice(Order order) {
        int basePrice = order.getQuantity() * order.getItemPrice();
        double volumeDiscount = Math.max(0, order.getQuantity() - 500) * order.getItemPrice() * 0.05;
        double shippingCost = Math.min(order.getQuantity() * order.getItemPrice() * 0.1, 100);
        return basePrice - volumeDiscount + shippingCost;
    }
}

@Data
class Order {
    private int quantity;
    private int itemPrice;
}