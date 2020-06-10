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
        if (quantity > 10)
            return basePrice() * 0.95;
        else
            return computeNormalPrice(basePrice());
    }

    private double basePrice() {
//        Thread.sleep(100); BAD - slow

        // sql(query bull) // side effect BAD not referential trasparent.
        // DB might change between the two calls of this method

        // write a file / send an SMS BAD side effect
        return quantity * itemPrice /* * Math.random()*/;
    }

    private double computeNormalPrice(double basePrice) {
        double factor = (basePrice > 1000) ? 0.95: 0.98;
        return factor * basePrice;
    }

    public int computeFidelityPoints() {
        return (int) (basePrice() / 2);
    }


}