package victor.training.refactoring;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class SplitVariable {

    @Test
    public void test() {
        assertEquals(1, discount(1,1));
        assertEquals(50, discount(50,1));
        assertEquals(49, discount(51,1));
        assertEquals(0, discount(1,101));
        assertEquals(49, discount(50,101));
        assertEquals(48, discount(51,101));
    }

    public int discount(int inputValue, int quantity) {
        if (inputValue > 50) inputValue = inputValue - 2;
        if (quantity > 100) inputValue = inputValue - 1;
        return inputValue;
    }

}