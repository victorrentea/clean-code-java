package victor.training.cleancode.java.refactoring;

public class SplitVariable {

    // @see test
    public static int discount(int price, int quantity) {
        if (price > 50) price -= 2;
        if (quantity > 100) price -= 1;
        return price;
    }
}