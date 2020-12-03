package victor.training.refactoring;

import lombok.Data;

public class ExtractVariable {
    public double computeTotalPrice(Order order) {
        int basePrice = order.getQuantity() * order.getItemPrice();
        double extraShipmentFee = Math.min(basePrice * 0.1, 100); // TODO idem

        return basePrice - computeQuantityDiscount(order) + extraShipmentFee;
    }

    private double computeQuantityDiscount(Order order) {
        if (order.getQuantity() <= 500) {
            return 0;
        }
        return (order.getQuantity() - 500) * order.getItemPrice() * 0.05;
    }
}

@Data
class Order {
    private int quantity;
    private int itemPrice;
}