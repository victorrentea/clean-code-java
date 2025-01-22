package victor.training.cleancode.java.extra;

import org.junit.jupiter.api.Test;
import victor.training.cleancode.java.refactoring.ReplaceTempWithQuery;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ReplaceTempWithQueryTest {
    @Test
    public void testBasePrice() {
        ReplaceTempWithQuery pc = new ReplaceTempWithQuery(1, 5);
        assertEquals(4.9, pc.computePrice(),0.001);
        assertEquals(2, pc.computeFidelityPoints());
    }
    @Test
    public void testQuantityDiscount() {
        ReplaceTempWithQuery pc = new ReplaceTempWithQuery(10, 5);
        assertEquals(49, pc.computePrice(),0.001);
        assertEquals(25, pc.computeFidelityPoints());
    }
    @Test
    public void testAmountDiscount() {
        ReplaceTempWithQuery pc = new ReplaceTempWithQuery(1, 1001);
        assertEquals(950.95, pc.computePrice(),0.01);
        assertEquals(500, pc.computeFidelityPoints());
    }
}