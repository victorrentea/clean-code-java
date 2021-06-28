package victor.training.cleancode;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class SplitVariable {

    public int discount(int price, int quantity) {
        if (price > 50) price = price - 2;
        if (quantity > 100) price = price - 1;
        return price;
    }



    @Test
    public void test() {
        assertEquals(1, discount(1,1));
        assertEquals(50, discount(50,1));
        assertEquals(49, discount(51,1));
        assertEquals(0, discount(1,101));
        assertEquals(49, discount(50,101));
        assertEquals(48, discount(51,101));
    }

}