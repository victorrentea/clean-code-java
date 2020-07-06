package victor.training.refactoring;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ReplaceTempWithQuery {
    @Test
    public void testBasePrice() {
        PriceCalculator pc = new PriceCalculator(1, 5);
        assertEquals(4.9, pc.computePrice(),0.001);
        assertEquals(2, pc.computeFidelityPoints());
    }
    @Test
    public void testQuantityDiscount() {
        PriceCalculator pc = new PriceCalculator(10, 5);
        assertEquals(49, pc.computePrice(),0.001);
        assertEquals(25, pc.computeFidelityPoints());
    }
    @Test
    public void testAmountDiscount() {
        PriceCalculator pc = new PriceCalculator(1, 1001);
        assertEquals(950.95, pc.computePrice(),0.01);
        assertEquals(500, pc.computeFidelityPoints());
    }
}

class PriceCalculator {
    private final int quantity;
    private final double itemPrice;

    public PriceCalculator(int quantity, double itemPrice) {
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

    private double computeBasePrice() {
        return quantity * itemPrice ;
    }

    private double computeNormalPrice() {
        double factor = (computeBasePrice() > 1000) ? 0.95: 0.98;
        return factor * computeBasePrice();
    }

    public int computeFidelityPoints() {
        return (int) (computeBasePrice() / 2);
    }


}